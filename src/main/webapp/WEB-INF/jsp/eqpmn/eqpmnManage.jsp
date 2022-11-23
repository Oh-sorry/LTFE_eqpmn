<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script language="javascript">
	$(document).ready(function(){
		$("#gridList1").jqGrid({
		   	url:"${pageContext.request.contextPath}/eqpmn/eqpmnManage.ajax",
		   	mtype: "POST",
			datatype: "json",
			/* postData : {'codenm':''}, */
			jsonReader : {
				root: "resultList",
				repeatitems: false
			},
			loadtext : '조회 중 입니다.',
			shrinkToFit:false,
			colModel:[
				{label:'장비코드', name:'eqpmncode', hidden:true},
		   		{label:'장비명', name:'codenm', width:30},
		   		{label:'관리번호', name:'manageno', width:30},
		   		{label:'구매일', name:'purchsdate', width:30},
		   		{label:'구매금액', name:'purchsamount', width:30},
		   		{label:'사용자 ', name:'pernno', width:20},
		   		{label:'사용여부', name:'useyn', hidden:true},
		   		{label:'폐기여부', name:'disuseyn', width:20},
		   		{label:'사용장소', name:'useplace', width:30},
		   		{label:'비고', name:'remark', hidden:true}
		   	],
		   	loadonce: true,
		   	sortable : true,
		   	showpage : false,
            rownumbers : true,
		   	rowNum: 9007199254740992,
		   	width: 800,
            height: 450,
		   	beforeRequest : function () {loadingOn();},
		   	loadComplete: function (data) {if($('#gridList1').getGridParam("records")== -1) alert('조회된 내용이 없습니다.');loadingOff();}
		});
		$("#gridList1").setGridWidth($('#content').width()-25, true);
		
		goMenuSelect();
		goMenuSelect2();
		goMenuSelect3();
		goMenuSelect4();
	
	});

	
	
	function initData() {
		$('#inputForm').each(function() { this.reset();});
		$('[id=inputForm] #eqpmncode').val('');
		$('#btn_input').attr("data-target","#modalpopup");
		
	}
	
	function dataSelect() {
		var rowid = $("#gridList1").getGridParam( "selrow" );

		if (rowid == null || rowid < 1) {
			alert("수정할 데이터를 선택하세요");
			return;
		}

		var rowData = $("#gridList1").getRowData(rowid);
		var formData = $('#inputForm').serializeArray();

		jQuery.each(formData, function() {
			for(key in rowData) {
			    if (key == this.name) {
			    	$('[id=inputForm] #'+this.name).val(rowData[key]);
			    }
			}
        });

		$('#btn_update').attr("data-target","#modalpopup");
	}
	
	function goSearch() {
		var formData = $('#searchForm').serializeArray();
		
		$('#gridList1').clearGridData();
		$('#gridList1').setGridParam({datatype : "json",
			                          postData : formData
			             }).trigger("reloadGrid");

	}
	
	function goDelete() {
		var rowid = $("#gridList1").getGridParam( "selrow" );

		if (rowid == null || rowid < 1) {
			alert("삭제할 데이터를 선택하세요");
			return;
		}

		var rowData = $("#gridList1").getRowData(rowid);

		if (!confirm("정말로 삭제하시겠습니까?")) {
			return;
		}

		loadingOn();

		$.ajax({
             type : "POST",
             url : "${pageContext.request.contextPath}/eqpmn/eqpmnManageDelete.ajax",
             data : rowData,
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
	}
	
	function goMenuSelect() {
		$.ajax({
             type : "POST",
             url : "${pageContext.request.contextPath}/eqpmn/selectEqpmnCode.ajax",
             async: false,
             success : function(data){
            	 if(data != null) {
            		 selectoption = "<option value=''>전체</option>";
                     $.each(data , function (i, item) {
                    	 selectoption += "<option value= " + item.eqpmncode + ">" + item.codenm + "</option>";
                     });
                     $('[id=searchForm] #eqpmncode option').remove();
                     $('[id=searchForm] #eqpmncode').append(selectoption);
                     
            	 }
             },
             error : function(XMLHttpRequest, textStatus, errorThrown){
                 alert("작업 중 오류가 발생하였습니다.")
             }
         });
	}
	function goMenuSelect2() {
		$.ajax({
             type : "POST",
             url : "${pageContext.request.contextPath}/eqpmn/selectEqpmnCode2.ajax",
             async: false,
             success : function(data){
            	 if(data != null) {
            		 selectoption = "<option value=''>전체</option>";
            		    $.each(data , function (i, item) {
            		   	 selectoption += "<option value=" + item.useplace + ">" + item.useplace + "</option>";
            		    });
            		    $('[id=searchForm] #useplace option').remove();
            		    $('[id=searchForm] #useplace').append(selectoption);
                     
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
            		   	 selectoption += "<option value=" + item.useyn + ">" + item.useyn + "</option>";
            		    });
            		    $('[id=searchForm] #useyn option').remove();
            		    $('[id=searchForm] #useyn').append(selectoption);
                     
            	 }
             },
             error : function(XMLHttpRequest, textStatus, errorThrown){
                 alert("작업 중 오류가 발생하였습니다.")
             }
         });
	}
	
	function goSave() {

		if ($('[id=inputForm] #eqpmncode').val() == null) {
			alert("장비명을 선택 하세요");
			$('[id=inputForm] #eqpmncode').focus();
			return;
		}
		/* if ($('[id=inputForm] #eqpmncode').val() == null) {
			alert("mac주소를 선택 하세요");
			$('[id=inputForm] #eqpmncode').focus();
			return;
		} */
		//if ($('[id=inputForm] #programurl').val().trim() == '') {
		//	alert("프로그램 URL을 입력 하세요");
		//	$('[id=inputForm] #programurl').focus();
		//	return;
		//}
		if ($('[id=inputForm] #purchsdate').val().trim() == '') {
			alert("구매일자를 입력 하세요");
			$('[id=inputForm] #purchsdate').focus();
			return;
		}
		if ($('[id=inputForm] #purchsamount').val() == null) {
			alert("구매금액을 선택 하세요");
			$('[id=inputForm] #purchsamount').focus();
			return;
		}
		if ($('[id=inputForm] #useplace').val() == null) {
			alert("사용 장소를 선택 하세요");
			$('[id=inputForm] #useplace').focus();
			return;
		}
		if ($('[id=inputForm] #disuseyn').val() == null) {
			alert("폐기여부를 선택 하세요");
			$('[id=inputForm] #disuseyn').focus();
			return;
		}

		var formData = $('#inputForm').serializeArray();

		loadingOn();

		$.ajax({
             type : "POST",
             url : "${pageContext.request.contextPath}/eqpmn/eqpmnManageUpdate.ajax",
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


		 $('#modalpopup').modal('hide');
	}
	
	
</script>
<div class="container float-left">
	<div class="page-header">
		<h4><i class="bi bi-info-square-fill"></i> 장비관리 현황</h4>
	</div>
	<p></p>
	<form  class="form-inline" id="searchForm" name="searchForm">
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">구분</div>
     		</div>
  		    <select class="form-control" id="eqpmncode" name="eqpmncode" style="width:100px">
  		    	<c:forEach var="cnmList" items="${cnmList}" varStatus="status">
        			<option value="${cnmList.eqpmncode}">${cnmList.codenm}</option>
      			</c:forEach>
  		    </select>
   		</div>
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">구매일자</div>
     		</div>
     		<input type="date" class="form-control" id="purchsdate" name="purchsdate" style="width:200px">
   		</div>
   		<%-- <div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">사용여부</div>
     		</div>
  		    <select class="form-control" id="useyn" name="useyn" style="width:100px">
        		<!-- <option value="Y" selected>사용</option>
        		<option value="N">미사용</option> -->
        		<c:forEach var="cnmList" items="${cnmList}" varStatus="status">
        			<option value="${cnmList.useyn}">${cnmList.useyn}</option>
      			</c:forEach>
      		</select>
   		</div> --%>
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">폐기여부</div>
     		</div>
  		    <select class="form-control" id="disuseyn" name="disuseyn" style="width:100px">
        		<option value="Y">폐기</option>
        		<option value="N" selected>미폐기</option>
        		<%-- <c:forEach var="cnmList" items="${cnmList}" varStatus="status">
        			<option value="${cnmList.disuseyn}">${cnmList.disuseyn}</option>
      			</c:forEach> --%>
      		</select>
   		</div>
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">사용장소</div>
     		</div>
  		    <select class="form-control" id="useplace" name="useplace" style="width:150px"></select>
   		</div>
		<button type="button" class="btn btn-sm btn-info mb-2" id="btnSearch" onclick="javascript:goSearch()"><i class="bi bi-search"></i> 조회</button>
	</form>
	<p></p>
	<table id="gridList1"></table>
	<p></p>
	<p>
		<button type="button" class="btn btn-sm btn-primary" id="btn_input" name="btn_input" data-toggle="modal" onclick="javascript:initData()"><i class="bi bi-pencil-fill"></i>장비등록</button>
		<button type="button" class="btn btn-sm btn-primary" id="btn_update" name="btn_update" data-toggle="modal" onclick="javascript:dataSelect()"><i class="bi bi-pencil-square"></i> 수정</button>
		<button type="button" class="btn btn-sm btn-primary" onclick="javascript:goDelete();"><i class="bi bi-trash"></i> 삭제</button>
	</p>
</div>

<div class="modal fade" id="modalpopup" name="modalpopup" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"><b><i class="bi bi-clipboard-plus"></i> 장비 등록/변경</b></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form id="inputForm" name="inputForm">
		  <div class="form-group required control-label">
		    <label for="codenm"><i class="bi bi-check2-circle"></i> 장비명</label>
			<select class="form-control" id="eqpmncode" name="eqpmncode">
				<c:forEach var="cnmList" items="${cnmList}" varStatus="status">
					<option value="${cnmList.code}">${cnmList.codenm}</option>
      			</c:forEach>
			</select>
		  </div>
		  <div class="form-group control-label">
		    <label for="programurl"><i class="bi bi-check2-circle"></i> manageno</label>
		    <input type="text" class="form-control" id="manageno" name="manageno" maxlength="200">
		  </div>
		  <div class="form-group required control-label">
		    <label for="purchsdate"><i class="bi bi-check2-circle"></i> 구매일자</label>
		    <input type="date" class="form-control" id="purchsdate" name="purchsdate" maxlength="100">
		  </div>
		  <div class="form-group required control-label">
		    <label for="purchsamount"><i class="bi bi-check2-circle"></i>  구매금액</label>
		    <input type="text" class="form-control" id="purchsamount" name="purchsamount" maxlength="5">
		  </div>
		  <div class="form-group required control-label">
		    <label for="useplace"><i class="bi bi-check2-circle"></i> 장비사용 장소</label>
  		    <select class="form-control" id="useplace" name="useplace">
  		    	<c:forEach var="list" items="${list}" varStatus="status">
        			<option value="${list.useplace}">${list.useplace}</option>
      			</c:forEach>
      		</select>
		  </div>
		  <div class="form-group required control-label">
		    <label for="disuseyn"><i class="bi bi-check2-circle"></i> 폐기여부</label>
  		    <select class="form-control" id="disuseyn" name="disuseyn">
        		<option value="N">N</option>
        		<option value="Y">Y</option>
      		</select>
		  </div>
		  <div class="form-group required control-label">
		    <label for="remark"><i class="bi bi-check2-circle"></i> 비고</label>
		    <input type="text" class="form-control" id="remark" name="remark" maxlength="200">
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-sm btn-primary" onclick="javascript:goSave()"><i class="bi bi-save"></i> 저장</button>
        <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal"><i class="bi bi-x"></i> 취소</button>
      </div>
    </div>
  </div>
</div>