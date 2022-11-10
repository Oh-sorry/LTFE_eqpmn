<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script language="javascript">
	$(document).ready(function(){

		$(function() {
			$('[id=inputForm] #projectstartdt').datepicker();
		});

		$(function() {
			$('[id=inputForm] #projectenddt').datepicker();
		});

		$('[id=inputForm] #projectstartdt').datepicker({
			onClose: function(selectedDate) {
				$('[id=inputForm] #projectenddt').datepicker("option", "minDate", selectedDate);
		    }
		});

		$("#gridList1").jqGrid({
		   	url:"${pageContext.request.contextPath}/system/projectMangeList.ajax",
		   	mtype: "POST",
			datatype: "json",
			jsonReader : {
				root: "resultList",
				repeatitems: false
			},
			loadtext : '조회 중 입니다.',
			colModel:[
				{label:'프로젝트ID', name:'projectid'},
				{label:'프로젝트명', name:'projectname'},
		   		{label:'시작일', name:'projectstartdt', formatter:gridDateFormatter},
		   		{label:'종료일', name:'projectenddt', formatter:gridDateFormatter},
		   		{label:'사용여부', name:'useyn'}
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
			    	if (this.name == "projectstartdt" || this.name == "projectenddt") {
			    		$('[id=inputForm] #'+this.name).val(dellDateFormatter(rowData[key]));
			    	} else {
			    		$('[id=inputForm] #'+this.name).val(rowData[key]);
			    	}
			    }
			}
        });

		$('#btn_update').attr("data-target","#modalpopup");
	}

	function initData() {
		$('#inputForm').each(function() { this.reset();});
		$('[id=inputForm] #projectid').val('0');
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

		if ($('[id=inputForm] #projectname').val().trim() == '') {
			alert("프로젝트명을 입력 하세요");
			$('[id=inputForm] #menuname').focus();
			return;
		}
		if ($('[id=inputForm] #projectstartdt').val().trim() == '') {
			alert("시작일을 입력 하세요");
			$('[id=inputForm] #projectstartdt').focus();
			return;
		}
		if ($('[id=inputForm] #projectenddt').val().trim() == '') {
			alert("종료일을 입력 하세요");
			$('[id=inputForm] #projectenddt').focus();
			return;
		}
		if ($('[id=inputForm] #useyn').val() == null) {
			alert("사용여부 선택 하세요");
			$('[id=inputForm] #useyn').focus();
			return;
		}

		var formData = $('#inputForm').serializeArray();

		loadingOn();

		$.ajax({
             type : "POST",
             url : "${pageContext.request.contextPath}/system/projectMangeUpdate.ajax",
             data : formData,
             async: false,
             success : function(data){
            	 if(data != null) {
            	 	alert("정상적으로 처리되었습니다.")
                 	goSearch();
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
             url : "${pageContext.request.contextPath}/system/projectMangeDelete.ajax",
             data : rowData,
             async: false,
             success : function(data){
            	 if(data != null) {
            	 	alert("정상적으로 처리되었습니다.")
                 	goSearch();
	           		loadingOff();
            	 }
             },
             error : function(XMLHttpRequest, textStatus, errorThrown){
                 alert("작업 중 오류가 발생하였습니다.")
                 loadingOff();
             }
         });
	}

</script>
<div class="container float-left">
	<div class="page-header">
		<h4><i class="bi bi-info-square-fill"></i> 프로젝트 관리</h4>
	</div>
	<p></p>
	<form  class="form-inline" id="searchForm" name="searchForm">
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">프로젝트명</div>
     		</div>
  		    <input type="text" class="form-control" id="projectname" name="projectname" style="width:200px">
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
	</p>
</div>

<div class="modal fade" id="modalpopup" name="modalpopup" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"><b><i class="bi bi-clipboard-plus"></i> 프로젝트 추가/수정</b></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form id="inputForm" name="inputForm">
		  <div class="form-group required control-label">
		    <label for="projectid"><i class="bi bi-check2-circle"></i> 프로젝트 ID</label>
		    <input type="text" class="form-control" id="projectid" name="projectid" maxlength="5" readonly>
		  </div>
		  <div class="form-group required control-label">
		    <label for="projectname"><i class="bi bi-check2-circle"></i> 프로젝트명</label>
		    <input type="text" class="form-control" id="projectname" name="projectname" maxlength="200">
		  </div>
		  <div class="form-group required control-label">
		    <label for="projectstartdt"><i class="bi bi-check2-circle"></i> 시작일</label>
		    <input type="text" class="form-control" id="projectstartdt" name="projectstartdt" maxlength="8" readonly>
		  </div>
		  <div class="form-group required control-label">
		    <label for="projectenddt"><i class="bi bi-check2-circle"></i> 종료일</label>
		    <input type="text" class="form-control" id="projectenddt" name="projectenddt" maxlength="8" readonly>
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
