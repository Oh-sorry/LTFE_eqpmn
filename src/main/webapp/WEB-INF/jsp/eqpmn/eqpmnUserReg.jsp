<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script language="javascript">
	/* 삭제버튼 X 시작 */
	function resetDate() {
		$('[id=regForm] #expdate').val('');
		$('[id=regForm] #inputdate').val('');
	}
	function resetInput(arg0) {
		var resetData = $(arg0).attr('id');
		
		$('[id=regForm] #'+resetData).val('');
		
	}
	/* 삭제버튼 X 끝 */
	
	/* 사용자 조회 시작 */
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
				{label:'부서명', name:'deptname', width:200},
				{label:'전화번호', name:'phoneno', hidden:true},
				{label:'LEVEL', name:'level', hidden:true},
				{label:'수탁망PC', name:'pcmanageno1', hidden:true},
				{label:'인터넷망PC', name:'pcmanageno2', hidden:true},
				{label:'모니터1', name:'momanageno1', hidden:true},
				{label:'모니터2', name:'momanageno2', hidden:true},
				{label:'전화기', name:'phmanageno', hidden:true},
				{label:'투입일자', name:'inputdate', hidden:true},
				{label:'철수일자', name:'expdate', hidden:true}
		   	],
		   	loadonce: true,
		   	sortable : true,
		   	showpage : false,
            rownumbers : true,
		   	rowNum: 9007199254740992,
		   	width: 460,
            height: 400,
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
		
		$('input[id=pernno]').attr('value', rowData.pernno);
		$('input[id=name]').attr('value', rowData.name);
		
		var timestamp = parseInt(rowData.inputdate)
		var dateFormat = new Date(timestamp);
		var inputdate = dateFormat.getFullYear() + "-"+((dateFormat.getMonth()>9) ? (dateFormat.getMonth()+1) : ("0"+(dateFormat.getMonth()+1)))+ "-"+((dateFormat.getDate()>9) ? (dateFormat.getDate()) :"0" +(dateFormat.getDate()));
		
		
		var timestamp2 = parseInt(rowData.expdate)
		var dateFormat2 = new Date(timestamp2);
		var expdate = dateFormat2.getFullYear() + "-"+((dateFormat2.getMonth()>9) ? (dateFormat2.getMonth()+1) : ("0"+(dateFormat2.getMonth()+1)))+ "-"+((dateFormat2.getDate()>9) ? (dateFormat2.getDate()) :"0" +(dateFormat2.getDate()));
		
		$('input[id=inputdate]').attr('value', inputdate);
		$('input[id=expdate]').attr('value', expdate);
		$('input[id=pcmanageno1]').attr('value', rowData.pcmanageno1);
		$('input[id=pcmanageno2]').attr('value', rowData.pcmanageno2);
		$('input[id=momanageno1]').attr('value', rowData.momanageno1);
		$('input[id=momanageno2]').attr('value', rowData.momanageno2);
		$('input[id=phmanageno]').attr('value', rowData.phmanageno);
		$('#modalpopup').modal('hide');
		
	}
	/* 사용자 조회 끝 */
	
	/* 장비 선택 시작 */
	function initEqpmn(arg0) {
		
		var btnId = $(arg0).attr('id');
		var btnVal = $(arg0).val();
		
		$('#eqpmnForm').each(function() { this.reset();});
		$('[id=eqpmnForm] #eqpmncode').val(btnVal);
		$('#'+btnId).attr("data-target","#modalpopup1");
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
			/* postData : {'codenm':'0'}, */
			jsonReader : {
				root: "resultList",
				repeatitems: false
			},
			loadtext : '조회 중 입니다.',
			shrinkToFit:false,
			colModel:[
				{label:'장비코드', name:'eqpmncode', hidden:true},
				{label:'장비명', name:'codenm', width:100},
				{label:'관리번호', name:'manageno', width:150},
				{label:'사용여부', name:'useyn', width:80},
				{label:'사용자', name:'pernno', width:120},
				{label:'이름', name:'name', hidden:true},
				{label:'수탁망PC', name:'pcmanageno1', hidden:true},
				{label:'인터넷망PC', name:'pcmanageno2', hidden:true},
				{label:'모니터1', name:'momanageno1', hidden:true},
				{label:'모니터2', name:'momanageno2', hidden:true},
				{label:'전화기', name:'phmanageno', hidden:true},
				{label:'투입일자', name:'inputdate', hidden:true},
				{label:'철수일자', name:'expdate', hidden:true},
				{label:'사용여부', name:'useyn', hidden:true}
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
		goMenuSelect3();
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
                    	 selectoption += "<option value= " + item.code + ">" + item.codenm + "</option>";
                     });
                     $('[id=eqpmnForm] #eqpmncode option').remove();
                     $('[id=eqpmnForm] #eqpmncode').append(selectoption);
                     
            	 }
             },
             error : function(XMLHttpRequest, textStatus, errorThrown){
                 alert("작업 중 오류가 발생하였습니다.")
             }
         });
	}
	function goMenuSelect3() {
		$.ajax({
             type : "POST",
             url : "${pageContext.request.contextPath}/eqpmn/selectEqpmnCode3.ajax",
             async: false,
             success : function(data){
            	 if(data != null) {
            		 selectoption = "<option value=''>전체</option>";
                     $.each(data , function (i, item) {
                    	 selectoption += "<option value= " + item.useyn + ">" + item.useyn + "</option>";
                     });
                     $('[id=eqpmnForm] #useyn option').remove();
                     $('[id=eqpmnForm] #useyn').append(selectoption);
                     
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
		/* if(rowData.useyn == "Y") {
			alert("사용가능한 장비를 선택해주세요");
			return;
			
		} */
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
	/* 장비 선택 끝 */
	
	$(document).ready(function(){
		$('#btn_close').click(function () {
			$('#regForm').attr('action', "${pageContext.request.contextPath}/eqpmn/eqpmnManageList.do");
			$('#regForm').submit();
	    });
    });
	
	function goSave() {

		if ($('[id=regForm] #pernno').val().trim() == '') {
			alert("사원명을 선택 하세요");
			$('[id=regForm] #pernno').focus();
			return;
		}

		if($('[id=regForm] #pcmanageno1').val().trim() == '' && 
			$('[id=regForm] #pcmanageno2').val().trim() == '' && 
			$('[id=regForm] #momanageno1').val().trim() == '' && 
			$('[id=regForm] #momanageno2').val().trim() == '' && 
			$('[id=regForm] #phmanageno').val().trim() == '') {
			alert("장비를 선택 하세요");
			return;
		}
		var formData = $('#regForm').serializeArray();
		
		loadingOn();

		$.ajax({
             type : "POST",
             url : "${pageContext.request.contextPath}/eqpmn/userManageUpdate.ajax",
             data : formData,
             async: false,
             success : function(data){
            	 if(data != null) {
            	 	alert("정상적으로 처리되었습니다.")
                 	goSearch();
            	 	goMenuSelect();
	           		loadingOff();
            	 }
             },
             error : function(XMLHttpRequest, textStatus, errorThrown){
                 alert("작업 중 오류가 발생하였습니다.")
                 loadingOff();
             }
         });
		location.href = "${pageContext.request.contextPath}/eqpmn/eqpmnManageList.do";
	}
</script>

<div class="container float-left">
	<div class="page-header">
		<h4><i class="bi bi-info-square-fill"></i> 장비 사용자 등록</h4>
	</div>
	<p></p>
</div>
	<form id="regForm" name="regForm">
		<input type="hidden" id="useyn" name="useyn" value="Y">
		<table class="table table-bordered" border="1" width ="100%" height="740" align = "center" style="font-size: 16px;" >
			<tr>
				<td colspan="20%" align="center" bgcolor="#E9ECEF">
					<label for="pernno">사용자(사번)</label>
				</td>
				<td colspan="80%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="text" class="form-cotrol" id="name" name="name" readonly>
						<input type="hidden" class="form-cotrol" id="pernno" name="pernno" maxlength="100" readonly>
						<button type="button" class="btn btn-sm btn-info" id="btn-user" name="btn-user" data-toggle="modal" onclick="javascript:initUser()">사용자조회</button>
						<button type="button" class="btn btn-sm btn-secondary" id="pernno" onclick="javascript:resetInput(this)">X</button>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="20%" align="center" bgcolor="#E9ECEF">
					<label for="inputdate">투입일자(철수일자)</label>
				</td>
				<td colspan="80%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="date" class="form-cotrol" id="inputdate" name="inputdate" data-date-format="YYYY-MM-DD" maxlength="100">
						~
						<input type="date" class="form-cotrol" id="expdate" name="expdate" maxlength="100">
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
						<button type="button" class="btn btn-sm btn-info" id="btn-eqpmn" value="PC01" data-toggle="modal" onclick="javascript:initEqpmn(this)">장비검색</button>
						<button type="button" class="btn btn-sm btn-secondary" id="pcmanageno1" onclick="javascript:resetInput(this)">X</button>
					</div>
				</td>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="cmospw">CMOS PW</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
<!-- 						<input type="text" class="form-cotrol" id="cmospw" name="cmospw" maxlength="100"> -->
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="ssopw">SSO 계정</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
<!-- 						<input type="text" class="form-cotrol" id="ssopw" name="ssopw" maxlength="100"> -->
					</div>
				</td>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="ssopw">SSO PW</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
<!-- 						<input type="text" class="form-cotrol" id="ssopw" name="ssopw" maxlength="100"> -->
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
						<button type="button" class="btn btn-sm btn-info" id="btn-eqpmn2" value="PC02" data-toggle="modal" onclick="javascript:initEqpmn(this)">장비검색</button>
						<button type="button" class="btn btn-sm btn-secondary" id="pcmanageno2" onclick="javascript:resetInput(this)">X</button>
					</div>
				</td>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="cmospw">CMOS PW</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
<!-- 						<input type="text" class="form-cotrol" id="cmospw" name="cmospw" maxlength="100"> -->
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="ssopw">SSO 계정</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
<!-- 						<input type="text" class="form-cotrol" id="ssopw" name="ssopw" maxlength="100"> -->
					</div>
				</td>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="ssopw">SSO PW</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
<!-- 						<input type="text" class="form-cotrol" id="ssopw" name="ssopw" maxlength="100"> -->
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
						<button type="button" class="btn btn-sm btn-info" id="btn-eqpmn3" value="MO01" data-toggle="modal" onclick="javascript:initEqpmn(this)">장비검색</button>
						<button type="button" class="btn btn-sm btn-secondary" id="momanageno1" onclick="javascript:resetInput(this)">X</button>
					</div>
				</td>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="momanageno2">모니터2</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="text" class="form-cotrol" id="momanageno2" name="momanageno2" maxlength="100" readOnly>
						<button type="button" class="btn btn-sm btn-info" id="btn-eqpmn4" value="MO01" data-toggle="modal" onclick="javascript:initEqpmn(this)">장비검색</button>
						<button type="button" class="btn btn-sm btn-secondary" id="momanageno2" onclick="javascript:resetInput(this)">X</button>
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
						<button type="button" class="btn btn-sm btn-info" id="btn-eqpmn5"  value="PH01" data-toggle="modal" onclick="javascript:initEqpmn(this)">장비검색</button>
						<button type="button" class="btn btn-sm btn-secondary" id="phmanageno" onclick="javascript:resetInput(this)">X</button>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<div class="float-right">
		<button type="button" class="btn btn-sm btn-primary" onclick="javascript:goSave()"><i class="bi bi-save"></i> 등록</button>
		<button type="button" class="btn btn-sm btn-secondary" id="btn_close" name="btn_close"><i class="bi bi-x"></i> 취소</button>
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
		<form class="form-inline" id="userForm" name="userForm" onsubmit="return false;">
				<div class="form-row">
		  	  &emsp;<select class="form-control" id="type" name="type">
		  		    	<option value="name" selected> 성명</option>
		  		    	<option value="pernno"> 사번</option>
		  		    	<option value="postname"> 직위</option>
		  		    	<option value="deptname"> 부서명</option>
		  		    </select>
		  		    <div class="form-row">
			     		&nbsp;<input type="text" class="form-control" id="keyword" name="keyword">
			   			&nbsp;<button type="button" class="btn btn-sm btn-info mb-2" onclick="javascript:goSearch()"><i class="bi bi-search"></i> 조회</button>
			   		</div>
		   		</div>
		</form>
		<p></p>
			<table id="gridList1"></table>
		<p></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-sm btn-primary" onclick="javascript:userSelect()"><i class="bi bi-save"></i> 선택</button>
        <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal"><i class="bi bi-x"></i> 취소</button>
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
		<form class="form-inline" id="eqpmnForm" name="eqpmnForm" onsubmit="return false;">
   		  <div class="input-group">
   		  	<div class="form-row">
   		  		<div class="form-row">
		     		<div class="input-group-prepend">
		       			&emsp;<div class="input-group-text">장비명 </div>
		     		</div>
					<select class="form-control" id="eqpmncode" name="eqpmncode">
						<c:forEach var="cnmList" items="${cnmList}" varStatus="status">
		        			<option value="${cnmList.code}">${cnmList.codenm}</option>
		      			</c:forEach>
					</select>
				</div>
				<%-- <div class="form-row">
					<div class="input-group-prepend">
		       			&emsp;<div class="input-group-text">사용여부</div>
		     		</div>
					<select class="form-control" id="useyn" name="useyn">
		  		    	<c:forEach var="cnmList" items="${cnmList}" varStatus="status">
		        			<option value="${cnmList.useyn}">${cnmList.useyn}</option>
		      			</c:forEach>
					</select>
				</div> --%>
				&emsp;<button type="button" class="btn btn-sm btn-info mb-2" onclick="javascript:goSearch1()"><i class="bi bi-search"></i> 조회</button>
			  </div>
			  
		</div>
			
		</form>
		<p></p>
			<table id="gridList2"></table>
		<p></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-sm btn-primary" onclick="javascript:eqpmnSelect()"><i class="bi bi-save"></i> 선택</button>
        <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal"><i class="bi bi-x"></i> 취소</button>
      </div>
    </div>
  </div>
</div>
<!-- 장비 조회 popup 끝-->
