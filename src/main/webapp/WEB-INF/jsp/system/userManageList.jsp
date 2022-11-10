<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script language="javascript">
	$(document).ready(function(){
		$("#gridList1").jqGrid({
		   	url:"${pageContext.request.contextPath}/system/userMangeList.ajax",
		   	mtype: "POST",
			datatype: "json",
			postData : {'useyn':'Y'},
			jsonReader : {
				root: "resultList",
				repeatitems: false
			},
			loadtext : '조회 중 입니다.',
			shrinkToFit:false,
			colModel:[
		   		{label:'ID', name:'userid', width:20},
		   		{label:'성명', name:'username', width:30},
		   		{label:'사용자권한', name:'usergradename', width:30},
		   		{label:'사용자권한', name:'usergrade', hidden:true},
		   		{label:'사용여부', name:'useyn', width:20},
		   		{label:'프로젝트', name:'projectname'},
		   		{label:'프로젝트ID', name:'projectid', hidden:true}
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
	});

	$(window).bind('resize', function() {
		$("#gridList1").setGridWidth($('#content').width()-25, true);
	}).trigger('resize');

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

			if (this.name == "userid") {
				$('[id=inputForm] #'+this.name).attr("readonly",true);
			}
        });

		$('[id=inputForm] #inputtype').val("update");
		$('#btn_update').attr("data-target","#modalpopup");
	}

	function initData() {
		$('#inputForm').each(function() {this.reset();});
		$('[id=inputForm] #userid').attr("readonly",false);
		$('[id=inputForm] #inputtype').val("insert");
		$('#btn_input').attr("data-target","#modalpopup");
	}

	function goSearch() {
		var formData = $('#searchForm').serializeArray();
		$('#gridList1').clearGridData();
		$('#gridList1').setGridParam({datatype : "json",
			                          postData : formData
			             }).trigger("reloadGrid");

	}

	function goSave() {

		if ($('[id=inputForm] #userid').val().trim() == '') {
			alert("사용자 ID를 입력 하세요");
			$('[id=inputForm] #userid').focus();
			return;
		}
		if ($('[id=inputForm] #username').val().trim() == '') {
			alert("사용자 성명을 입력 하세요");
			$('[id=inputForm] #username').focus();
			return;
		}
		if ($('[id=inputForm] #usergrade').val() == null) {
			alert("사용자 등급을 선택 하세요");
			$('[id=inputForm] #usergrade').focus();
			return;
		}
		if ($('[id=inputForm] #useyn').val() == null) {
			alert("사용여부 선택 하세요");
			$('[id=inputForm] #useyn').focus();
			return;
		}

		var rtnData;
		var formData = $('#inputForm').serializeArray();

		loadingOn();

		if ($('[id=inputForm] #inputtype').val() == "insert") {
			$.ajax({
	            type : "POST",
	            url : "${pageContext.request.contextPath}/system/selectUserDupChk.ajax",
	            data : formData,
	            async: false,
	            success : function(data){
	           	 if(data != null) {
	       	 		rtnData = data;
	           	 }
	            },
	            error : function(XMLHttpRequest, textStatus, errorThrown){
	                alert("작업 중 오류가 발생하였습니다.")
	                rtnData = -1;
	            }
	        });

			if (rtnData == -1) {
				return;
			} else if (rtnData == 1) {
	   	 		alert("이미 등록된 아이디가 존재합니다.");
	   	 		loadingOff();
	   	 		return;
			}
		}

		$.ajax({
             type : "POST",
             url : "${pageContext.request.contextPath}/system/userMangeUpdate.ajax",
             data : formData,
             async: false,
             success : function(data){
            	 if(data != null) {
            	 	alert("정상적으로 처리되었습니다.")
                 	goSearch();
            	 }
             },
             error : function(XMLHttpRequest, textStatus, errorThrown){
                 alert("작업 중 오류가 발생하였습니다.")
             }
         });

		 loadingOff();

		 $('#modalpopup').modal('hide');
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
             url : "${pageContext.request.contextPath}/system/userMangeDelete.ajax",
             data : rowData,
             async: false,
             success : function(data){
            	 if(data != null) {
            	 	alert("정상적으로 처리되었습니다.")
                 	goSearch();
            	 }
             },
             error : function(XMLHttpRequest, textStatus, errorThrown){
                 alert("작업 중 오류가 발생하였습니다.")
             }
         });

		 loadingOff();
	}

	function goProject() {
		var rowid = $("#gridList1").getGridParam( "selrow" );

		if (rowid == null || rowid < 1) {
			alert("프로젝트를 지정할 사용자를 선택하세요");
			return;
		}

		<c:forEach var="projectList" items="${projectList}" varStatus="status">
		    $('[id=inputProjectForm] #${projectList.projectid}').prop("checked",false);
		</c:forEach>

		var ret = $('#gridList1').jqGrid('getCell',rowid,'projectid');
		var projectids = ret.split(",");

		for (var i = 0; i < projectids.length; i++ ) {
			if (projectids[i] != "") {
				$('[id=inputProjectForm] #'+projectids[i]).prop("checked",true);
			}
		}

		$('#btn_project').attr("data-target","#modalpopupproject");
	}

	function goProjectSave() {

		var rowid = $("#gridList1").getGridParam( "selrow" );
		var userid = $('#gridList1').jqGrid('getCell',rowid,'userid');

        var dataList = new Array() ;
		var projectData = "";

		var data = new Object() ;
		data.name = "userid"
		data.value = userid;
		dataList.push(data);

		<c:forEach var="projectList" items="${projectList}" varStatus="status">
	    	if ($('[id=inputProjectForm] #${projectList.projectid}').is(":checked")) {
	    		projectData += "${projectList.projectid},";
	    	}
		</c:forEach>

		var data = new Object() ;
		data.name = "projectid";
		data.value = projectData;
		dataList.push(data);

        loadingOn();

        $.ajax({
            type : "POST",
            url : "${pageContext.request.contextPath}/system/userProjectUpdate.ajax",
            data : dataList,
            async: false,
            success : function(data){
           	 if(data != null) {
           	 	alert("정상적으로 처리되었습니다.")
                	goSearch();
           	 }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown){
                alert("작업 중 오류가 발생하였습니다.")
            }
        });

		 loadingOff();

        $('#modalpopupproject').modal('hide');
	}
</script>
<div class="container float-left">
	<div class="page-header">
		<h4><i class="bi bi-info-square-fill"></i> 사용자 관리</h4>
	</div>
	<p></p>
	<form  class="form-inline" id="searchForm" name="searchForm">
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">사용자 ID</div>
     		</div>
     		<input type="text" class="form-control" id="userid" name="userid" style="width:150px">
   		</div>
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">사용자 성명</div>
     		</div>
     		<input type="text" class="form-control" id="username" name="username" style="width:150px">
   		</div>
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">사용자권한</div>
     		</div>
  		    <select class="form-control" id="usergrade" name="usergrade" style="width:100px">
        		<option value="" selected></option>
        		<c:forEach var="systemGrade" items="${systemGrade}" varStatus="status">
        			<option value="${systemGrade.code}">${systemGrade.codename}</option>
      			</c:forEach>
      		</select>
   		</div>
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">사용여부</div>
     		</div>
  		    <select class="form-control" id="useyn" name="useyn" style="width:100px">
        		<option value="Y" selected>Y</option>
        		<option value="N">N</option>
      		</select>
   		</div>
		<button type="button" class="btn btn-sm btn-info mb-2" onclick="javascript:goSearch()"><i class="bi bi-search"></i> 조회</button>
	</form>
	<p></p>
	<table id="gridList1"></table>
	<p></p>
	<p>
		<button type="button" class="btn btn-sm btn-primary" id="btn_input" name="btn_input" data-toggle="modal" onclick="javascript:initData()"><i class="bi bi-pencil-fill"></i> 추가</button>
		<button type="button" class="btn btn-sm btn-primary" id="btn_update" name="btn_update" data-toggle="modal" onclick="javascript:dataSelect()"><i class="bi bi-pencil-square"></i> 수정</button>
		<button type="button" class="btn btn-sm btn-primary" onclick="javascript:goDelete();"><i class="bi bi-trash"></i> 삭제</button>
		<button type="button" class="btn btn-sm btn-success" id="btn_project" name="btn_project" data-toggle="modal" onclick="javascript:goProject();"><i class="bi bi-list-check"></i> 프로젝트지정</button>
	</p>
</div>

<div class="modal fade" id="modalpopup" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"><b><i class="bi bi-clipboard-plus"></i> 사용자 추가/수정</b></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form id="inputForm" name="inputForm">
		  <input type="hidden" id="inputtype" name="inputtype">
		  <div class="form-group required control-label">
		    <label for="userid"><i class="bi bi-check2-circle"></i> 사용자 ID</label>
		    <input type="text" class="form-control" id="userid" name="userid" maxlength="10">
		  </div>
		  <div class="form-group required control-label">
		    <label for="username"><i class="bi bi-check2-circle"></i> 사용자</label>
		    <input type="text" class="form-control" id="username" name="username" maxlength="50">
		  </div>
		  <div class="form-group required control-label">
		    <label for="usergrade"><i class="bi bi-check2-circle"></i> 사용자권한</label>
  		    <select class="form-control" id="usergrade" name="usergrade">
        		<c:forEach var="systemGrade" items="${systemGrade}" varStatus="status">
        			<option value="${systemGrade.code}">${systemGrade.codename}</option>
      			</c:forEach>
      		</select>
		  </div>
		  <div class="form-group required control-label">
		    <label for="useyn"><i class="bi bi-check2-circle"></i> 사용여부</label>
  		    <select class="form-control" id="useyn" name="useyn">
        		<option value="Y">Y</option>
        		<option value="N">N</option>
      		</select>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-sm btn-primary" onclick="javascript:goSave()"><i class="bi bi-save"></i> 저장</button>
        <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal"><i class="bi bi-x"></i> 닫기</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="modalpopupproject" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"><b><i class="bi bi-clipboard-plus"></i> 프로젝트 지정</b></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form id="inputProjectForm" name="inputProjectForm">
			<div style="height: 500px;" class="overflow-auto">
				<div class="list-group">
        		<c:forEach var="projectList" items="${projectList}" varStatus="status">
  					<a href="#" class="list-group-item list-group-item-action">
    					<div class="d-flex w-100 justify-content-between">
      						<h6 class="mb-1">
      							<div class="custom-control custom-switch">
			  						<input type="checkbox" value="${projectList.projectid}" class="custom-control-input" id="${projectList.projectid}" name="${projectList.projectid}">
			  						<label class="custom-control-label" for="${projectList.projectid}">${projectList.projectname}</label>
								</div>
							</h6>
      						<small>${projectList.projectstartdt} ~ ${projectList.projectenddt}</small>
    					</div>
    					<p class="mb-1"></p>
    					<small></small>
  					</a>
      			</c:forEach>
				</div>
			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-sm btn-primary" onclick="javascript:goProjectSave()"><i class="bi bi-save"></i> 저장</button>
        <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal"><i class="bi bi-x"></i> 닫기</button>
      </div>
    </div>
  </div>
</div>
