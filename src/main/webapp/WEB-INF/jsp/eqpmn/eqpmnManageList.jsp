<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script language="javascript">
	$(document).ready(function(){
		$("#gridList1").jqGrid({
		   	url:"${pageContext.request.contextPath}/eqpmn/eqpmnManageList.ajax",
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
		   		{label:'사용자', name:'pernno', width:30},
		   		{label:'수탁망PC', name:'manageno', width:30},
		   		{label:'인터넷망PC', name:'purchsdate', width:30},
		   		{label:'모니터1', name:'purchsamount', width:30},
		   		{label:'모니터2', name:'name', width:30},
		   		{label:'전화기', name:'useyn', width:30}
		   	],
		   	loadonce: true,
		   	sortable : true,
		   	showpage : false,
            rownumbers : true,
		   	rowNum: 9007199254740992,
		   	width: 800,
            height: 450,
		   	beforeRequest : function () {loadingOn();},
		   	loadComplete: function (data) {if($('#gridList1').getGridParam("records")== 0) alert('조회된 내용이 없습니다.');loadingOff();}
		});

		$("#gridList1").setGridWidth($('#content').width()-25, true);
		
		goMenuSelect();
	});
	
	function initData() {
		$('#inputForm').each(function() { this.reset();});
		$('[id=inputForm] #pernno').val('0');
		$('#btn_input').attr("data-target","#modalpopup");
	}
	function goSearch() {
		var formData = $('#searchForm').serializeArray();
		
		$('#gridList1').clearGridData();
		$('#gridList1').setGridParam({datatype : "json",
			                          postData : formData
			             }).trigger("reloadGrid");

	}
	function goMenuSelect() {
		$.ajax({
             type : "POST",
             url : "${pageContext.request.contextPath}/eqpmn/selectEqpmnListCode.ajax",
             async: false,
             success : function(data){
            	 if(data != null) {
            		 selectoption = "<option value=''>전체</option>";
                     $.each(data , function (i, item) {
                    	 selectoption += "<option value= " + item.codenm + ">" + item.codenm + "</option>";
                     });
                     $('[id=searchForm] #codenm option').remove();
                     $('[id=searchForm] #codenm').append(selectoption);
                     
            	 }
             },
             error : function(XMLHttpRequest, textStatus, errorThrown){
                 alert("작업 중 오류가 발생하였습니다.")
             }
         });
	}
	
	function goSave() {

		/* if ($('[id=inputForm] #codenm').val().trim() == '') {
			alert("장비명을 선택 하세요");
			$('[id=inputForm] #codenm').focus();
			return;
		} */
		if ($('[id=inputForm] #eqpmncode').val() == null) {
			alert("mac주소를 선택 하세요");
			$('[id=inputForm] #eqpmncode').focus();
			return;
		}
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
             url : "${pageContext.request.contextPath}/eqpmn/eqpmnManageListUpdate.ajax",
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
		<h4><i class="bi bi-info-square-fill"></i> 사용자 장비관리 현황</h4>
	</div>
	<p></p>
	<form  class="form-inline" id="searchForm" name="searchForm">
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">검색 </div>
     		</div>
  		    <select class="form-control" id="codenm" name="codenm" style="width:100px"></select>
   		</div>
   		<div class="input-group mb-2 mr-sm-2">
     		<input type="text" class="form-control" id="purchsdate" name="purchsdate" style="width:100px">
   		</div>
		<button type="button" class="btn btn-sm btn-info mb-2" onclick="javascript:goSearch()"><i class="bi bi-search"></i> 조회</button>
	</form>
	<p></p>
	<table id="gridList1"></table>
	<p></p>
	<p>
		<button type="button" class="btn btn-sm btn-primary" id="btn_input" name="btn_input" data-toggle="modal" onclick="javascript:initData()"><i class="bi bi-pencil-fill"></i>사용자등록</button>
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
			<select class="form-control" id="codenm" name="codenm">
				<c:forEach var="cnmList" items="${cnmList}" varStatus="status">
        			<option value="${cnmList.codenm}">${cnmList.codenm}</option>
      			</c:forEach>
			</select>
		  </div>
		  <div class="form-group control-label">
		    <label for="programurl"><i class="bi bi-check2-circle"></i> mac주소</label>
		    <input type="text" class="form-control" id="eqpmncode" name="eqpmncode" maxlength="200">
		  </div>
		  <div class="form-group required control-label">
		    <label for="purchsdate"><i class="bi bi-check2-circle"></i> 구매일자</label>
		    <input type="text" class="form-control" id="purchsdate" name="purchsdate" maxlength="100">
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