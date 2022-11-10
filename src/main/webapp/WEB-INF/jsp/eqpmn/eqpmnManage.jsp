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
			postData : {'useyn':'Y'},
			jsonReader : {
				root: "resultList",
				repeatitems: false
			},
			loadtext : '조회 중 입니다.',
			shrinkToFit:false,
			colModel:[
		   		{label:'NO', name:'rownum', hidden:true},
		   		{label:'장비명', name:'group_code', width:30},
		   		{label:'관리번호', name:'manage_no', width:30},
		   		{label:'구매일', name:'purchs_date', width:30},
		   		{label:'구매금액', name:'purchs_amount', width:30},
		   		{label:'사용자', name:'name', width:20},
		   		{label:'사용여부', name:'use_yn', hidden:true},
		   		{label:'폐기여부', name:'disuse_yn', width:20},
		   		{label:'사용장소', name:'use_place', width:30}
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
		
		function goSearch() {
			var formData = $('#searchForm').serializeArray();
			$('#gridList1').clearGridData();
			$('#gridList1').setGridParam({datatype : "json",
				                          postData : formData
				             }).trigger("reloadGrid");

		}
	});
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
  		    <select class="form-control" id="code" name="code" style="width:100px"></select>
   		</div>
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">구매일자</div>
     		</div>
     		<input type="text" class="form-control" id="codename" name="codename" style="width:150px">
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
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">폐기여부</div>
     		</div>
  		    <select class="form-control" id="useyn" name="useyn" style="width:100px">
        		<option value="Y" selected>Y</option>
        		<option value="N">N</option>
      		</select>
   		</div>
   		<div class="input-group mb-2 mr-sm-2">
     		<div class="input-group-prepend">
       			<div class="input-group-text">사용장소</div>
     		</div>
  		    <select class="form-control" id="code" name="code" style="width:150px"></select>
   		</div>
		<button type="button" class="btn btn-sm btn-info mb-2" onclick="javascript:goSearch()"><i class="bi bi-search"></i> 조회</button>
	</form>
	<p></p>
	<table id="gridList1"></table>
	<p></p>
</div>