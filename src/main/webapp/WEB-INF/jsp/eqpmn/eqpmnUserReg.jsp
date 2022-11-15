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
	
	function initData() {
		$('#inputForm').each(function() { this.reset();});
		$('[id=inputForm] #menuid').val('0');
		$('#btn_input').attr("data-target","#modalpopup");
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
						<input type="text" class="form-cotrol" id="pernno" name="pernno" maxlength="100" readonly>
						<button type="button" class="btn btn-sm btn-info" id="btn-user" data-toggle="modal" onclick="">사용자조회</button>
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
						<button type="button" class="btn btn-sm btn-info" id="btn-eqpmn" data-toggle="modal" onclick="">장비검색</button>
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
						<input type="text" class="form-cotrol" id="pcmanageno1" name="pcmanageno1" maxlength="100" readonly>
						<button type="button" class="btn btn-sm btn-info" id="btn-eqpmn" data-toggle="modal" onclick="">장비검색</button>
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
						<button type="button" class="btn btn-sm btn-info" id="btn-user" data-toggle="modal" onclick="">장비검색</button>
						<button type="button" class="btn btn-sm btn-secondary" onclick="javascript:resetInput()">X</button>
					</div>
				</td>
				<td colspan="10%" align="center" bgcolor="#E9ECEF">
					<label for="momanageno2">모니터2</label>
				</td>
				<td colspan="35%">
					<div class="input-group mb-2 mr-sm-2">
						<input type="text" class="form-cotrol" id="momanageno2" name="momanageno2" maxlength="100" readOnly>
						<button type="button" class="btn btn-sm btn-info" id="btn-user" data-toggle="modal" onclick="">장비검색</button>
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
						<button type="button" class="btn btn-sm btn-info" id="btn-user" data-toggle="modal" onclick="">장비검색</button>
						<button type="button" class="btn btn-sm btn-secondary" onclick="javascript:resetInput()">X</button>
					</div>
				</td>
			</tr>
		</table>
		
	</form>
	<div class="float-right">
		<button type="button" class="btn btn-sm btn-primary" onclick="javascript:initData()"><i class="bi bi-save"></i> 저장</button>
		<button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal"><i class="bi bi-x"></i> 뒤로</button>
	</div>
	
<!-- 사용자 조회 popup 시작-->
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
<!-- 사용자 조회 popup 끝-->
