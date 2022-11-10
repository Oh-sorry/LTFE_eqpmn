<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script language="javascript">
	$(document).ready(function(){
		$("#gridList1").jqGrid({
		   	url:"${pageContext.request.contextPath}/system/menuMangeList.ajax",
		   	mtype: "POST",
			datatype: "json",
			postData : {'menuid':'0'},
			jsonReader : {
				root: "resultList",
				repeatitems: false
			},
			loadtext : '조회 중 입니다.',
			shrinkToFit:false,
			colModel:[
				{label:'경로', name:'path', width:250},
				{label:'LEVEL', name:'level', hidden:true},
		   		{label:'메뉴ID', name:'menuid', width:80},
		   		{label:'메뉴명', name:'menuname'},
		   		{label:'프로그램 URL', name:'programurl', width:350},
		   		{label:'상위메뉴ID', name:'parentmenuid'},
		   		{label:'사용자권한', name:'systemgradename'},
		   		{label:'사용자권한', name:'systemgrade', hidden:true},
		   		{label:'정렬순서', name:'sortno'},
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

		goMenuSelect();
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
        });

		$('#btn_update').attr("data-target","#modalpopup");
	}

	function initData() {
		$('#inputForm').each(function() { this.reset();});
		$('[id=inputForm] #menuid').val('0');
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

		if ($('[id=inputForm] #menuname').val().trim() == '') {
			alert("메뉴명을 입력 하세요");
			$('[id=inputForm] #menuname').focus();
			return;
		}
		if ($('[id=inputForm] #parentmenuid').val() == null) {
			alert("상위메뉴를 선택 하세요");
			$('[id=inputForm] #parentmenuid').focus();
			return;
		}
		//if ($('[id=inputForm] #programurl').val().trim() == '') {
		//	alert("프로그램 URL을 입력 하세요");
		//	$('[id=inputForm] #programurl').focus();
		//	return;
		//}
		if ($('[id=inputForm] #sortno').val().trim() == '') {
			alert("정렬순서를 입력 하세요");
			$('[id=inputForm] #sortno').focus();
			return;
		}
		if ($('[id=inputForm] #systemgrade').val() == null) {
			alert("사용자권한을 선택 하세요");
			$('[id=inputForm] #systemgrade').focus();
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
             url : "${pageContext.request.contextPath}/system/menuMangeUpdate.ajax",
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
             url : "${pageContext.request.contextPath}/system/menuMangeDelete.ajax",
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
             url : "${pageContext.request.contextPath}/system/selectParentMenuList.ajax",
             async: false,
             success : function(data){
            	 if(data != null) {
            		 selectoption = "<option value='0'>상위메뉴</option>";
                     $.each(data , function (i, item) {
                    	 selectoption += "<option value=" + item.menuid + ">" + item.menuname + "</option>";
                     });
                     $('[id=searchForm] #menuid option').remove();
                     $('[id=searchForm] #menuid').append(selectoption);

                     $('[id=inputForm] #parentmenuid option').remove();
                     $('[id=inputForm] #parentmenuid').append(selectoption);
            	 }
             },
             error : function(XMLHttpRequest, textStatus, errorThrown){
                 alert("작업 중 오류가 발생하였습니다.")
             }
         });
	}
</script>
<div class="container float-left">
	<div class="page-header">
		<h4><i class="bi bi-info-square-fill"></i> 메뉴 관리</h4>
	</div>
	<p></p>
	<form  class="form-inline" id="searchForm" name="searchForm">
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">메뉴 ID</div>
     		</div>
  		    <select class="form-control" id="menuid" name="menuid" style="width:150px"></select>
   		</div>
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">메뉴명</div>
     		</div>
     		<input type="text" class="form-control" id="menuname" name="menuname" style="width:150px">
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
        <h5 class="modal-title" id="exampleModalLabel"><b><i class="bi bi-clipboard-plus"></i> 메뉴 추가/수정</b></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form id="inputForm" name="inputForm">
		  <div class="form-group required control-label">
		    <label for="menuid"><i class="bi bi-check2-circle"></i> 메뉴 ID</label>
		    <input type="text" class="form-control" id="menuid" name="menuid" maxlength="5" readonly>
		  </div>
		  <div class="form-group required control-label">
		    <label for="menuname"><i class="bi bi-check2-circle"></i> 메뉴명</label>
		    <input type="text" class="form-control" id="menuname" name="menuname" maxlength="100">
		  </div>
		  <div class="form-group control-label">
		    <label for="programurl"><i class="bi bi-check2-circle"></i> 프로그램 URL</label>
		    <input type="text" class="form-control" id="programurl" name="programurl" maxlength="200">
		  </div>
		  <div class="form-group required control-label">
		    <label for="parentmenuid"><i class="bi bi-check2-circle"></i> 상위메뉴</label>
		    <select class="form-control" id=parentmenuid name="parentmenuid"></select>
		  </div>
		  <div class="form-group required control-label">
		    <label for="sortno"><i class="bi bi-check2-circle"></i> 정렬순서</label>
		    <input type="text" class="form-control" id="sortno" name="sortno" maxlength="5">
		  </div>
		  <div class="form-group required control-label">
		    <label for="systemgrade"><i class="bi bi-check2-circle"></i> 사용자권한</label>
  		    <select class="form-control" id="systemgrade" name="systemgrade">
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
