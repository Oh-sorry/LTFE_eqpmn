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
			postData : {'codenm':'0'},
			jsonReader : {
				root: "resultList",
				repeatitems: false
			},
			loadtext : '조회 중 입니다.',
			shrinkToFit:false,
			colModel:[
				{label:'사용자', name:'name', width:30},
		   		{label:'사번', name:'pernno', width:30},
		   		{label:'수탁망PC', name:'pcmanageno1', width:30},
		   		{label:'인터넷망PC', name:'pcmanageno2', width:30},
		   		{label:'모니터1', name:'momanageno1', width:30},
		   		{label:'모니터2', name:'momanageno2', width:30},
		   		{label:'전화기', name:'phmanageno', width:30},
		   		{label:'검색어', name:'searchText', hidden:true}
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
	
	function initData() {
		location.href = "${pageContext.request.contextPath}/eqpmn/eqpmnUserReg.do";
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
             url : "${pageContext.request.contextPath}/eqpmn/userManageDelete.ajax",
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
		<h4><i class="bi bi-info-square-fill"></i> 사용자 장비관리 현황</h4>
	</div>
	<p></p>
	<form  class="form-inline" id="searchForm" name="searchForm">
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">검색 </div>
     		</div>
  		    <select class="form-control" id="type" name="type" style="width:100px">
  		    	<option value="name" selected> 사용자</option>
  		    	<option value="pernno"> 사번</option>
  		    	<option value="pcmanageno1"> 수탁망PC</option>
  		    	<option value="pcmanageno2"> 인터넷망PC</option>
  		    	<option value="momanageno1"> 모니터1</option>
  		    	<option value="momanageno2"> 모니터2</option>
  		    	<option value="phmanageno"> 전화기</option>
  		    </select>
   		</div>
   		<div class="input-group mb-2 mr-sm-2">
     		<input type="text" class="form-control" id="keyword" name="keyword" style="width:300px">
   		</div>
		<button type="button" class="btn btn-sm btn-info mb-2" onclick="javascript:goSearch()"><i class="bi bi-search"></i> 조회</button>
	</form>
	<p></p>
	<table id="gridList1"></table>
	<p></p>
	<p>
		<button type="button" class="btn btn-sm btn-primary" id="btn_input" name="btn_input" data-toggle="modal" onclick="javascript:initData()"><i class="bi bi-pencil-fill"></i>사용자등록</button>
		<!-- <button type="button" class="btn btn-sm btn-primary" id="btn_update" name="btn_update" data-toggle="modal" onclick="javascript:dataSelect()"><i class="bi bi-pencil-square"></i> 사용자수정</button> -->
		<button type="button" class="btn btn-sm btn-primary" onclick="javascript:goDelete();"><i class="bi bi-trash"></i> 사용자삭제</button>
	</p>
</div>

