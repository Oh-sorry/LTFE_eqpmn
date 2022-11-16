<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script language="javascript">
	function resetDate() {
		$('[id=regForm] #expdate').val('');
		$('[id=regForm] #inputdate').val('');
	}
	function resetInput() {
		$("#reset").click(function () {
			$(":text").val('');
		})
		
	}
	function initUser() {
		$('#userForm').each(function() { this.reset();});
		$('[id=userForm] #pernno').val('0');
		$('#btn-user').attr("data-target","#modalpopup");
	}

	$(document).ready(function(){
		$("#gridList1").jqGrid({
		   	url:"${pageContext.request.contextPath}/eqpmn/userSelect.ajax",
		   	mtype: "POST",
			datatype: "json",
			postData : {'pernno':'0'},
			jsonReader : {
				root: "resultList",
				repeatitems: false
			},
			loadtext : '조회 중 입니다.',
			shrinkToFit:false,
			colModel:[
				{label:'성명', name:'name', width:80},
				{label:'사번', name:'pernno', width:100},
				{label:'직위', name:'postname', width:80},
				{label:'전화번호', name:'phoneno', width:200},
				{label:'LEVEL', name:'level', hidden:true}
		   	],
		   	loadonce: true,
		   	sortable : true,
		   	showpage : false,
            rownumbers : true,
		   	rowNum: 9007199254740992,
		   	width: 460,
            height: 450,
		   	beforeRequest : function () {loadingOn();},
		   	loadComplete: function (data) {if($('#gridList1').getGridParam("records")== 0) alert('조회된 내용이 없습니다.');loadingOff();}
		});

		/* $("#gridList1").setGridWidth($('#content').width()-25, true); */

	});
	
	function goSearch() {
		var formData = $('#userForm').serializeArray();
		
		$('#gridList1').clearGridData();
		$('#gridList1').setGridParam({datatype : "json",
			                          postData : formData
			             }).trigger("reloadGrid");

	}
	
	function userSelect() {
		var rowid = $("#gridList1").getGridParam( "selrow" );

		if (rowid == null || rowid < 1) {
			alert("데이터를 선택하세요");
			return;
		}
		var rowData = $("#gridList1").getRowData(rowid);
		var formData = $('#userForm').serializeArray();

		jQuery.each(formData, function() {
			for(key in rowData) {
			    if (key == this.name) {
			    	$('[id=userForm] #'+this.name).val(rowData[key]);
			    }
			}
        });
		$('input[id=pernno]').attr('value', rowData.name+"("+rowData.pernno+")");
		$('#modalpopup').modal('hide');
	}
	//수탁망pc 선택
	function initEqpmn() {
		$('#eqpmnForm').each(function() { this.reset();});
		$('[id=eqpmnForm] #codenm').val('수탁망PC');
		$('#btn-eqpmn').attr("data-target","#modalpopup1");
		goSearch1();
	}
	//인터넷망pc 선택
	function initEqpmn2() {
		$('#eqpmnForm').each(function() { this.reset();});
		$('[id=eqpmnForm] #codenm').val('인터넷망PC');
		$('#btn-eqpmn2').attr("data-target","#modalpopup1");
		goSearch1();
	}

	function goSearch1() {
		var formData = $('#eqpmnForm').serializeArray();
		
		$('#gridList2').clearGridData();
		$('#gridList2').setGridParam({datatype : "json",
			                          postData : formData
			             }).trigger("reloadGrid");

	}
	
	$(document).ready(function(){
		$("#gridList2").jqGrid({
		   	url:"${pageContext.request.contextPath}/eqpmn/eqpmnSelect.ajax",
		   	mtype: "POST",
			datatype: "json",
			postData : {'codenm':'0'},
			jsonReader : {
				root: "resultList",
				repeatitems: false
			},
			loadtext : '조회 중 입니다.',
			shrinkToFit:false,
			colModel:[
				{label:'장비코드', name:'code', hidden:true},
				{label:'장비명', name:'codenm', width:100},
				{label:'관리번호', name:'manageno', width:150},
				{label:'사용여부', name:'useyn', width:80},
				{label:'사용자', name:'pernno', width:120}
		   	],
		   	loadonce: true,
		   	sortable : true,
		   	showpage : false,
            rownumbers : true,
		   	rowNum: 9007199254740992,
		   	width: 460,
            height: 450,
		   	beforeRequest : function () {loadingOn();},
		   	loadComplete: function (data) {if($('#gridList2').getGridParam("records")== 0);loadingOff();}
		});

		/* $("#gridList1").setGridWidth($('#content').width()-25, true); */
		goMenuSelect();
	});
	function goMenuSelect() {
		$.ajax({
             type : "POST",
             url : "${pageContext.request.contextPath}/eqpmn/selectEqpmnCode.ajax",
             async: false,
             success : function(data){
            	 if(data != null) {
            		 selectoption = "<option value=''>전체</option>";
                     $.each(data , function (i, item) {
                    	 selectoption += "<option value= " + item.codenm + ">" + item.codenm + "</option>";
                     });
                     $('[id=eqpmnForm] #codenm option').remove();
                     $('[id=eqpmnForm] #codenm').append(selectoption);
                     
            	 }
             },
             error : function(XMLHttpRequest, textStatus, errorThrown){
                 alert("작업 중 오류가 발생하였습니다.")
             }
         });
	}
	
	function eqpmnSelect() {
		var rowid = $("#gridList2").getGridParam( "selrow" );
		var moData = $('input#momanageno1').val();
		
		if (rowid == null || rowid < 1) {
			alert("데이터를 선택하세요");
			return;
		}
		var rowData = $("#gridList2").getRowData(rowid);
		var formData = $('#eqpmnForm').serializeArray();
		
		jQuery.each(formData, function() {
			for(key in rowData) {
			    if (key == this.name) {
			    	$('[id=eqpmnForm] #'+this.name).val(rowData[key]);
			    }
			}
        });
		if(rowData.codenm == "수탁망PC") {
			$('input[id=pcmanageno1]').attr('value', rowData.manageno);
		} else if (rowData.codenm == "인터넷망PC") {
			$('input[id=pcmanageno2]').attr('value', rowData.manageno);
		} else if (rowData.codenm == "모니터") {
			if(moData){
				/* if(moData == rowData.manageno) {
					alert("이미 선택된 장비입니다.")
					return;
				} */
				$('input[id=momanageno2]').attr('value', rowData.manageno);
			} else {
				$('input[id=momanageno1]').attr('value', rowData.manageno);
			}
		} else {
			$('input[id=phmanageno]').attr('value', rowData.manageno);
		}
		$('#modalpopup1').modal('hide');
	}
</script>

<div class="container float-left">
	<div class="page-header">
		<h4><i class="bi bi-info-square-fill"></i> 장비 사용자 등록</h4>
	</div>
	<p></p>
</div>
	<form id="regForm" name="regForm">
		<table class="table table-bordered" border="1" width ="100%" height="740" align = "center" style="font-size: 16px;" >
			<tr>
				<td colspan="20%" align="center" bgcolor="#E9ECEF">
					<label for="pernno">사용자(사번)</label>
				</td>
				<td colspan="80%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="text" class="form-cotrol" id="pernno" name="pernno" maxlength="100" value="" readonly>
						<button type="button" class="btn btn-sm btn-info" id="btn-user" name="btn-user" data-toggle="modal" onclick="javascript:initUser()">사용자조회</button>
						<button type="button" class="btn btn-sm btn-secondary" id="reset" onclick="javascript:resetInput()">X</button>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="20%" align="center" bgcolor="#E9ECEF">
					<label for="inputdate">투입일자(철수일자)</label>
				</td>
				<td colspan="80%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="text" class="form-cotrol" id="inputdate" name="inputdate" maxlength="100" placeholder="투입일자">
						~
						<input type="text" class="form-cotrol" id="expdate" name="expdate" maxlength="100" placeholder="철수일자">
						<button type="button" class="btn btn-sm btn-secondary" onclick="javascript:resetDate()">X</button>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="10%" align="center" rowspan="2" bgcolor="#E9ECEF">
					<label>수탁망PC</label>
				</td>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="pcmanageno1">PC선택</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="text" class="form-cotrol" id="pcmanageno1" name="pcmanageno1" maxlength="100" readonly>
						<button type="button" class="btn btn-sm btn-info" id="btn-eqpmn" data-toggle="modal" onclick="javascript:initEqpmn()">장비검색</button>
						<button type="button" class="btn btn-sm btn-secondary"  onclick="javascript:resetInput()">X</button>
					</div>
				</td>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="cmospw">CMOS PW</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="text" class="form-cotrol" id="cmospw" name="cmospw" maxlength="100">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="ssopw">SSO 계정</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="text" class="form-cotrol" id="ssopw" name="ssopw" maxlength="100">
					</div>
				</td>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="ssopw">SSO PW</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="text" class="form-cotrol" id="ssopw" name="ssopw" maxlength="100">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="10%" align="center" rowspan="2" bgcolor="#E9ECEF">
					<label>인터넷망PC</label>
				</td>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="pcmanageno1">PC선택</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="text" class="form-cotrol" id="pcmanageno2" name="pcmanageno2" maxlength="100" readonly>
						<button type="button" class="btn btn-sm btn-info" id="btn-eqpmn2" data-toggle="modal" onclick="javascript:initEqpmn2()">장비검색</button>
						<button type="button" class="btn btn-sm btn-secondary" onclick="javascript:resetInput()">X</button>
					</div>
				</td>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="cmospw">CMOS PW</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="text" class="form-cotrol" id="cmospw" name="cmospw" maxlength="100">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="ssopw">SSO 계정</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="text" class="form-cotrol" id="ssopw" name="ssopw" maxlength="100">
					</div>
				</td>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="ssopw">SSO PW</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="text" class="form-cotrol" id="ssopw" name="ssopw" maxlength="100">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="20%" align="center" bgcolor="#E9ECEF">
					<label for="momanageno1">모니터1</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="text" class="form-cotrol" id="momanageno1" name="momanageno1" maxlength="100" readOnly >
						<button type="button" class="btn btn-sm btn-info" id="btn-user" data-toggle="modal" onclick="javascript:initEqpmn()">장비검색</button>
						<button type="button" class="btn btn-sm btn-secondary" onclick="javascript:resetInput()">X</button>
					</div>
				</td>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="momanageno2">모니터2</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="text" class="form-cotrol" id="momanageno2" name="momanageno2" maxlength="100" readOnly>
						<button type="button" class="btn btn-sm btn-info" id="btn-user" data-toggle="modal" onclick="javascript:initEqpmn()">장비검색</button>
						<button type="button" class="btn btn-sm btn-secondary" onclick="javascript:resetInput()">X</button>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="20%" align="center" bgcolor="#E9ECEF">
					<label for="phmanageno">전화기</label>
				</td>
				<td colspan="80%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="text" class="form-cotrol" id="phmanageno" name="phmanageno" maxlength="100" readOnly>
						<button type="button" class="btn btn-sm btn-info" id="btn-user" data-toggle="modal" onclick="javascript:initEqpmn()">장비검색</button>
						<button type="button" class="btn btn-sm btn-secondary" onclick="javascript:resetInput()">X</button>
					</div>
				</td>
			</tr>
		</table>
		
	</form>
	<div class="float-right">
		<button type="button" class="btn btn-sm btn-primary" onclick="javascript:goSave()"><i class="bi bi-save"></i> 저장</button>
		<button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal"><i class="bi bi-x"></i> 뒤로</button>
	</div>
	
<!-- 사용자 조회 popup 시작-->
<div class="modal fade" id="modalpopup" name="modalpopup" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" style="text-align:center">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"><b><i class="bi bi-clipboard-plus"></i> 사용자 조회</b></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form id="userForm" name="userForm">
		<div class="input-group">
     		<div class="input-group-prepend">
       			<div class="input-group-text">검색 </div>
     		</div>
  		    <select class="form-control" id="type" name="type" style="width:50px">
  		    	<option value="name" selected> 성명</option>
  		    	<option value="pernno"> 사번</option>
  		    	<option value="postname"> 직위</option>
  		    	<option value="phoneno"> 전화번호</option>
  		    </select>
   		</div>
   		<div class="input-group mb-2 mr-sm-2">
     		<input type="text" class="form-control" id="keyword" name="keyword" style="width:10px">
   		</div>
		<button type="button" class="btn btn-sm btn-info mb-2" onclick="javascript:goSearch()"><i class="bi bi-search"></i> 조회</button>
			<p></p>
				<table id="gridList1"></table>
			<p></p>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-sm btn-primary" onclick="javascript:userSelect()"><i class="bi bi-save"></i> 저장</button>
        <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal"><i class="bi bi-x"></i> 닫기</button>
      </div>
    </div>
  </div>
</div>
<!-- 사용자 조회 popup 끝-->

<!-- 장비 조회 popup 시작-->
<div class="modal fade" id="modalpopup1" name="modalpopup1" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" style="text-align:center">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"><b><i class="bi bi-clipboard-plus"></i> 장비 조회</b></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form id="eqpmnForm" name="eqpmnForm">
   		  <div class="input-group">
     		<div class="input-group-prepend">
       			<div class="input-group-text">장비명 </div>
     		</div>
			<select class="form-control" id="codenm" name="codenm">
				<c:forEach var="cnmList" items="${cnmList}" varStatus="status">
        			<option value="${cnmList.codenm}">${cnmList.codenm}</option>
      			</c:forEach>
			</select>
			<div class="input-group-prepend">
       			<div class="input-group-text">사용여부</div>
     		</div>
			<select class="form-control" id="useyn" name="useyn">
				<c:forEach var="cnmList" items="${cnmList}" varStatus="status">
        			<option value="${cnmList.useyn}">${cnmList.useyn}</option>
      			</c:forEach>
			</select>
		  </div>
		<button type="button" class="btn btn-sm btn-info mb-2" onclick="javascript:goSearch1()"><i class="bi bi-search"></i> 조회</button>
			<p></p>
				<table id="gridList2"></table>
			<p></p>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-sm btn-primary" onclick="javascript:eqpmnSelect()"><i class="bi bi-save"></i> 저장</button>
        <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal"><i class="bi bi-x"></i> 닫기</button>
      </div>
    </div>
  </div>
</div>
<!-- 장비 조회 popup 끝-->
