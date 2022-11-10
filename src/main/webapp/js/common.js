$(function() {
    //모든 datepicker에 대한 공통 옵션 설정
    $.datepicker.setDefaults({
		showMonthAfterYear : true,
		showOtherMonths: true,
		changeMonth:true,
		changeYear:true,
		dateFormat : "yymmdd",
		dayNamesMin : ['일','월','화','수','목','금','토'],
		monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		yearRange: 'c-20:c+20',
		yearSuffix: "년"
    });
 });

function gridDateFormatter(cellValue, options, rowdata, action) {
	var formatter = cellValue.substring(0,4) + "." + cellValue.substring(4,6) + "." + cellValue.substring(6,8);
	return formatter;
}

function dellDateFormatter(value) {
	value = value.replace(/\./g,'');
	return value;
}

function fileChk(object) {
	if( !$(object).val() ) return;
	var f = object.files[0];
    var size = f.size || f.fileSize;

    var limit = 200000000;
    //alert( size );

    if( size > limit ) {
		alert( '파일용량은 200mb 를 넘을수 없습니다.' );
        $(object).val('');
        return;
	}

	var fileVal = $(object).val();
    var ext = fileVal.split('.').pop().toLowerCase(); //확장자분리
     //아래 확장자가 있는지 체크
     if($.inArray(ext, ['jpg','jpeg','gif','png','hwp','hwpx','doc','docx','ppt','pptx','pdf','zip','xls','xlsx','tar','gzip','txt','sql','jar','java','css','js','class','xml','map']) == -1) {
          alert('첨부할수 없는 파일타입 입니다.');
          $(object).val('');
          return;
    }

    $(object).parent().find('input[type=text]').val( $(this).val() );
}

 var setToDay = function(){
	var getId = arguments;
	var curdate = new Date();
	var year = curdate.getFullYear();
	var month = curdate.getMonth()+1;
	var day = curdate.getDate();
	console.log(getId.length);
	for (var i = 0; i < getId.length; i++) {
		$("#"+getId[i]).val(year+'-'+(month < 10 ? '0'+ month : month)+'-'+(day < 10 ? '0'+ day : day));
	}
};

//월 세팅
var setToDateMon = function(){
	var getId = arguments;
	var curdate = new Date();
	var year = curdate.getFullYear();
	var month = curdate.getMonth()+1;
	console.log(getId.length);
	for (var i = 0; i < getId.length; i++) {
		$("#"+getId[i]).val(year+'.'+(month < 10 ? '0'+ month : month));
	}
};

var summernoteSetting = {
    height : 200,
    minHeight : 50,
    lang: 'ko-KR',
    fontNames: ['굴림','굴림체','돋움','돋움체','바탕','바탕체','궁서','궁서체','맑은 고딕','Arial','Arial Black','Comic Sans Ms','Courier New','Helvetica','Impact','Tahoma','Times New Roman','Verdana'],
	fontNamesIgnoreCheck: ['굴림','굴림체','돋움','돋움체','바탕','바탕체','궁서','궁서체','맑은 고딕','Arial','Arial Black','Comic Sans Ms','Courier New','Helvetica','Impact','Tahoma','Times New Roman','Verdana']
}

var summernoteMobileSetting = {
    height : 200,
    minHeight : 50,
    lang: 'ko-KR',
    fontNames: ['굴림','굴림체','돋움','돋움체','바탕','바탕체','궁서','궁서체','맑은 고딕','Arial','Arial Black','Comic Sans Ms','Courier New','Helvetica','Impact','Tahoma','Times New Roman','Verdana'],
	fontNamesIgnoreCheck: ['굴림','굴림체','돋움','돋움체','바탕','바탕체','궁서','궁서체','맑은 고딕','Arial','Arial Black','Comic Sans Ms','Courier New','Helvetica','Impact','Tahoma','Times New Roman','Verdana'],
    toolbar: [
              //[groupName, [list of button]],
              ['style', ['style']],
              ['font', ['bold', 'italic', 'underline']],
              //['fontsize', ['strikethrough']],
              //['color', ['color']],
              ['para', ['ul', 'ol', 'paragraph']],
              //['height', ['height']],
              ['insert', ['link', 'picture', 'video']]
  ]
}

var summernoteSetting_h50 = {
    height : 50,
    minHeight : 50,
    lang: 'ko-KR',
    fontNames: ['굴림','굴림체','돋움','돋움체','바탕','바탕체','궁서','궁서체','맑은 고딕','Arial','Arial Black','Comic Sans Ms','Courier New','Helvetica','Impact','Tahoma','Times New Roman','Verdana'],
	fontNamesIgnoreCheck: ['굴림','굴림체','돋움','돋움체','바탕','바탕체','궁서','궁서체','맑은 고딕','Arial','Arial Black','Comic Sans Ms','Courier New','Helvetica','Impact','Tahoma','Times New Roman','Verdana']
}

var summernoteSetting_h100 = {
    height : 100,
    minHeight : 50,
    lang: 'ko-KR',
    fontNames: ['굴림','굴림체','돋움','돋움체','바탕','바탕체','궁서','궁서체','맑은 고딕','Arial','Arial Black','Comic Sans Ms','Courier New','Helvetica','Impact','Tahoma','Times New Roman','Verdana'],
	fontNamesIgnoreCheck: ['굴림','굴림체','돋움','돋움체','바탕','바탕체','궁서','궁서체','맑은 고딕','Arial','Arial Black','Comic Sans Ms','Courier New','Helvetica','Impact','Tahoma','Times New Roman','Verdana']
}

var summernoteMobileSetting_h100 = {
    height : 100,
    minHeight : 50,
    lang: 'ko-KR',
    fontNames: ['굴림','굴림체','돋움','돋움체','바탕','바탕체','궁서','궁서체','맑은 고딕','Arial','Arial Black','Comic Sans Ms','Courier New','Helvetica','Impact','Tahoma','Times New Roman','Verdana'],
	fontNamesIgnoreCheck: ['굴림','굴림체','돋움','돋움체','바탕','바탕체','궁서','궁서체','맑은 고딕','Arial','Arial Black','Comic Sans Ms','Courier New','Helvetica','Impact','Tahoma','Times New Roman','Verdana'],
    toolbar: [
              //[groupName, [list of button]],
              ['style', ['style']],
              ['font', ['bold', 'italic', 'underline']],
              //['fontsize', ['strikethrough']],
              //['color', ['color']],
              ['para', ['ul', 'ol', 'paragraph']],
              //['height', ['height']],
              ['insert', ['link', 'picture', 'video']]
  ]
}

var summernoteSetting_h300 = {
    height : 300,
    minHeight : 50,
    lang: 'ko-KR',
    fontNames: ['굴림','굴림체','돋움','돋움체','바탕','바탕체','궁서','궁서체','맑은 고딕','Arial','Arial Black','Comic Sans Ms','Courier New','Helvetica','Impact','Tahoma','Times New Roman','Verdana'],
	fontNamesIgnoreCheck: ['굴림','굴림체','돋움','돋움체','바탕','바탕체','궁서','궁서체','맑은 고딕','Arial','Arial Black','Comic Sans Ms','Courier New','Helvetica','Impact','Tahoma','Times New Roman','Verdana']
}

var entityMap = {
  '&': '&amp;',
  '<': '&lt;',
  '>': '&gt;',
  '"': '&quot;',
  "'": '&#39;',
  '/': '&#x2F;',
  '`': '&#x60;',
  '=': '&#x3D;',
  ' ': '&nbsp;'
};

function escapeHtml (string) {
  return String(string).replace(/[&<>"'`=\/]/g, function (s) {
    return entityMap[s];
  });
}

/*
주쓰는 정규식
특정문자 제거(#제거)   : str.replace(/\#/g,'');
앞의 공백 제거           : str.replace(/^\s+/,'');
뒤 공백 제거              : str.replace(/\s+$/,'');
앞 공백 제거              : str.replace(/^\s+|\s+$/g,'');
문자열 내의 공백 제거 : str.replace(/\s/g,'');
줄바꿈 제거               : str.replace(/\n/g,'');
엔터 제거                  : str.replace(/\r/g,'');
0 제거                      : str.replace(/[^(1-9)]/gi,"");
*/

// 특수문자 키보드 입력 못하게 한다.
function onlyCodeForId(e) {
	if(window.event){
		if ( (event.keyCode>=48 && event.keyCode<=57)
		  || (event.keyCode>=65 && event.keyCode<=90)
		  || (event.keyCode>=97 && event.keyCode<=122) ) {
		    event.returnValue = true;
		} else {
		    event.returnValue = false;
		}
	} else {
		if ( (e.which>=48 && e.which<=57)
		  || (e.which>=65 && e.which<=90)
		  || (e.which>=97 && e.which<=122) ) {
		} else {
			e.preventDefault();
		}
	}
}

// 이메일 유효체크
function validEmailCheck(emailad) {

	var exclude=/[^@\-\.\w]|^[_@\.\-]|[\._\-]{2}|[@\.]{2}|(@)[^@]*\1/;
	var check=/@[\w\-]+\./;
	var checkend=/\.[a-zA-Z]{2,3}$/;

    if(emailad.length == 0) {
		return true;
    } else {
    	if(((emailad.search(exclude) != -1)||(emailad.search(check)) == -1)||(emailad.search(checkend) == -1)) {
       		return true;
    	} else {
    		return false;
    	}
    }
}

function validHPCheck(phonNumber) {
	var regExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;

    if(phonNumber.length == 0) {
		return true;
    } else {
		if (!regExp.test(phonNumber)) {
			return true;
		} else {
			return false;
		}
    }
}

function validPostCheck(postNumber) {
	var regExp = /^\d{3}-\d{3}/;

    if(postNumber.length == 0) {
		return true;
    } else {
		if (!regExp.test(postNumber)) {
			return true;
		} else {
			return false;
		}
    }
}

function validPhoneCheck(phoneNumber) {
	var regExp = /^0(2|1[016789]|[0-9][0-9])-[0-9]{3,}-[0-9]{4}$/;

    if(phoneNumber.length == 0) {
		return true;
    } else {
		if (!regExp.test(phoneNumber)) {
			return true;
		} else {
			return false;
		}
    }
}

// 숫자만 입력
function isOnlyNum(e) {
	if(window.event){
		if ( event.keyCode>=48 && event.keyCode<=57 ) {
		    event.returnValue = true;
		} else {
		    event.returnValue = false;
		}
	}else{
		if ( e.which<48 || e.which>57 ) {
			e.preventDefault();
		}
	}
}

// 문자만 입력
function isOnlyChar(e) {
	if(window.event){
		if ( event.keyCode>=65 && event.keyCode<=90 ) {
		    event.returnValue = true;
		} else {
		    event.returnValue = false;
		}
	}else{
		if ( e.which<65 || e.which>90 ) {
			e.preventDefault();
		}
	}
}

//앞뒤 공백제거
function trim(objValue) {
	var oval=objValue;

	var myre1=/^(\s)+(\S*)/g;
	var myre2=/(\S*)(\s+)$/g;

	oval=myre1.test(oval) ? oval.replace(myre1,"$2") : oval;
	oval=myre2.test(oval) ? oval.replace(myre2,"$1") : oval;

	return oval;
}

function Replace(strString, strChar){
	var strTmp = "";
	for(i = 0 ; i < strString.length ; i++){
		if(strString.charAt(i) != strChar){
			strTmp = strTmp + strString.charAt(i);
		}
	}

	return strTmp;
}

function isNull(str) {
    return ((str == null) || (str.length == 0))
}

/*
 * 숫자체크
 */
function isNum(str){
	var len = str.length;
	var comp = "0123456789,-";
	for(i=0;i<len;i++){
		if(comp.indexOf(str.substring(i,i+1))<0){
			return false;
		}
	}
	return true;
}
/*
 * 숫자체크
 */
function isNumONLY(str){
	var len = str.length;
	var comp = "0123456789";
	for(i=0;i<len;i++){
		if(comp.indexOf(str.substring(i,i+1))<0){
			return false;
		}
	}
	return true;
}

function goDateChk(dayStr) {
	var valid = false;
	if(dayStr.search(/\d{4}(0[1-9]|1[0-2])([0-3][0-9])/)==0) {
	    var year = parseInt(dayStr.substring(0,4));
	    var month = parseInt(dayStr.substring(4,6).replace(/^0(\d)/g,"$1"));
	    var day = parseInt(dayStr.substring(6,8).replace(/^0(\d)/g,"$1"));
	    var d = new Date(year,month-1,day);
	    if(d.getMonth() == month-1 && d.getDate() == day ) valid = true ;
	}
	return valid;
}

function goYearChk(year) {
	var valid = false;
	if(year.search(/\d{4}/)==0 || year == "") {
		valid = true ;
	}
	return valid;
}
function goMonthChk(month) {
	var valid = false;
	if(month.search(/(0[1-9]|1[0-2])/)==0 || month == "") {
		valid = true ;
	}
	return valid;
}

/*
 * 날짜에 '.' 포함
 */
function addDot(obj){
	var str = returnNum(obj.value);

	if (str == "" && obj.value.length > 0) {
		alert("날짜가 올바르지 않습니다.");
		obj.focus();
		return;
	}

	var returnValue = goDateChk(str);
	if(str.length>0){
		if(returnValue == false) {
			alert("날짜가 올바르지 않습니다.");
			obj.focus();
			return;
		}
		if(str.length != 8) {
			alert("날짜가 올바르지 않습니다.");
			obj.focus();
			return;
		}
		var year = str.substring(0,4);
		var month = str.substring(4,6);
		var day = str.substring(6,8);
		str = year+"."+month+"."+day;

		obj.value = str;
	}
}

/*
 * 날짜에 '.'제거
 */
function delDot(obj){
	var str = obj.value;
	while (str.indexOf(".") > -1)
		str = str.replace(".", "");
	obj.value = str;
}


/*
 * 3자리마다 콤마 (,)붙이기
 */
function commaSplit(srcNumber) {
	var txtNumber = '' + srcNumber;

	var rxSplit = new RegExp('([0-9])([0-9][0-9][0-9][,.])');
	var arrNumber = txtNumber.split('.');
	arrNumber[0] += '.';
	do {
		arrNumber[0] = arrNumber[0].replace(rxSplit, '$1,$2');
	}while (rxSplit.test(arrNumber[0]));

	if(arrNumber.length > 1) {
		return arrNumber.join('');
	}else{
		return arrNumber[0].split('.')[0];
	}
}

function commaSplitObj(obj) {
	var txtNumber = '' + obj.value;
	var rxSplit = new RegExp('([0-9])([0-9][0-9][0-9][,.])');
	var arrNumber = txtNumber.split('.');
	arrNumber[0] += '.';
	do {
		arrNumber[0] = arrNumber[0].replace(rxSplit, '$1,$2');
	}while (rxSplit.test(arrNumber[0]));

	if(arrNumber.length > 1) {
		//return arrNumber.join('');
		obj.value = arrNumber.join('');
	}else{
		//return arrNumber[0].split('.')[0];
		obj.value = arrNumber[0].split('.')[0];
	}

}

/*
 * 문자, 기호등을 제거한 숫자만 반환
 */
function returnNum(str){
	var len = str.length;
	var comp = "0123456789";
	var temp="";
	for(i=0;i<len;i++){
		if(comp.indexOf(str.substring(i,i+1))>=0){
			temp = temp+str.substring(i,i+1);
		}
	}
	return temp;
}
/*
 *  숫자와 . 반환
 */
function returnNumComma(str){
	var len = str.length;
	var comp = "0123456789.";
	var temp="";
	for(i=0;i<len;i++){
		if(comp.indexOf(str.substring(i,i+1))>=0){
			temp = temp+str.substring(i,i+1);
		}
	}
	return temp;
}

/*
 * 콤마 지우기
 */
function delCommaObj(obj){

	var str = obj.value;
	while (str.indexOf(",") > -1)
		str = str.replace(",","");
	obj.value = str;
}

function delComma(value){

	var str = value;
	while (str.indexOf(",") > -1)
		str = str.replace(",","");
	return str;
}

/*
 * 스페이스 지우기
 */
function delSpace(obj){

	var str = obj;
	while (str.indexOf(" ") > -1)
		str = str.replace(" ","");
	str;

	return str;
}


//절삭
function getCutNumber(num, place) {
	 var returnNum;
	 var str = "1";

	 return Math.floor( num * Math.pow(10,parseInt(place,10)) ) / Math.pow(10,parseInt(place,10));
}

function textareaMax(str, limit) {
	var val=document.getElementById(str);
    if (  getByte(val.value) > limit  ) {

      alert('입력하신값이 너무 큽니다. 다시 한번 입력해 주십시요.');
      textCut(val, limit)
      //val.value = val.value.substring(0, limit);
      val.focus();
    }
 }

/**********************************************************************
테스트에어리어 에서 길이수 제안하기
onkeydown 이벤트시 호출
텍스트에어리어 컴포넌트객체와 라인수를 인자로 받아서 체크
**********************************************************************/
function chklen(objFrm,len){
	  var tt = objFrm.value
	  if (tt.length>len-2){
		alert('더이상 입력이 불가능합니다.')
		objFrm.focus();
	  }
}

/**********************************************************************
테스트에어리어 에서 라인수 제안하기
onkeydown 이벤트시 호출
텍스트에어리어 컴포넌트객체와 라인수를 인자로 받아서 체크
**********************************************************************/
function chkline(objFrm,line){ // 스크롤글상자(textarea) 줄수 제한 입력하기
	var form = objFrm;
	var newheight = form.scrollHeight; // 텍스트라에 Height변수
	var row2 = (newheight - 2) / 15; // 줄수 알아내는 변수
	var cnt=0;

	if (event.keyCode==13){
		  tt = objFrm.value
		  cnt = 0
		  for (i=0; i<tt.length; i++){
			if  (tt.charCodeAt(i) == 13)  cnt++;
		  }
		  if (cnt>Number(line)-2){
			alert((cnt+1)+'줄이상 입력되지 않습니다.');
			event.returnValue=false;
		  }else{
			event.returnValue=true;
		  }
    }
}
/**********************************************************************
테스트에어리어 에서 onblur 시에 라인수와 글자수 제한하여 나머지 글자는 삭제
텍스트에어리어 컴포넌트객체와 라인수를 인자로 받아서 체크
line:라인수
len:글자수
**********************************************************************/
function chklinelen(objFrm,line,len){
	var newheight = objFrm.scrollHeight; // 텍스트라에 Height변수
	var row2 = parseInt((newheight - 2) / 15); // 줄수 알아내는 변수
	var cnt=0;
	var val=objFrm.value;
	var NewVal="";
	if(row2>line+1){
		for(var i=0;i<val.length;i++){
			if(val.charCodeAt(i)==13) cnt++;
			if(cnt<line-1) {
				NewVal+=val.substring(i,i+1);
			}
		}
		alert(line+ '라인까지 입력가능 합니다.초과된 글자는 삭제 됩니다.');
		objFrm.value=NewVal;
	}else{
		objFrm.value=val;
	}
	NewVal="";
	var tt = objFrm.value;
	//alert(tt.length+' '+len);
	if (tt.length>len-2){
		alert(len+'글자까지 입력가능 합니다. 초과된 글자는 삭제 됩니다.');
		for(var i=0;i<len-2;i++){
			NewVal+=tt.substring(i,i+1);
		}
		objFrm.value=NewVal;
	}
}


//숫자 및 길이 체크 소숫점 체크
//type 'N' 숫자만
//num1 정수 길이
//num2 소숫점길이
function strLimitCheck(ob, type, num1, num2) {
    var flag = true;
    var idxFlag = true;
    var realStr = ob.value;
    var tempStr = ob.value;
    var num = num1+num2;

    if ( type == "N" ) {
      tempStr = commaDel(tempStr);
      if (tempStr.charAt(0) == '-' ) {
        num = num+1;
        tempStr = tempStr.substring(1);
      }
      if ( tempStr.indexOf(".") < 0 ) {
         idxFlag = false;
      }
      if ( isNaN(tempStr) && flag || event.keyCode == "32") {//스페이스 방지
        flag = false;
        alert("숫자만 입력하실수 있습니다.");
	    //ob.value = Number(returnNum(textCut1(ob.value, ob.value.length-1)));
        ob.value = Number(returnNum(ob.value));
	    return;
	  }
      if ( num2 == 0 && flag && idxFlag ) {
        flag = false;
        alert("소수점은  입력 할 수 없습니다.");
        ob.value = Number(textCut1(ob.value, ob.value.length-1));
        return;
      }
      if ( idxFlag && getByte(tempStr.substring(tempStr.indexOf(".")+1)) > num2 && flag ) {
        flag = false;
        alert("소수점은 "+num2+"자리까지만 입력 가능합니다.");
        ob.value = Number(textCut1(ob.value, ob.value.length-1));
        return;
      }

      if ( idxFlag && getByte(tempStr.substring(0, tempStr.indexOf(".")-1)) > num1 && flag ) {
        flag = false;
        alert(num1+"자리까지만 입력 가능합니다.");
        ob.value = Number( textCut1( ob.value, num1));
        return;
      } else if ( !idxFlag && getByte(tempStr) > num1 && flag ) {
        flag = false;
        alert(num1+"자리까지만 입력 가능합니다.");
        ob.value = Number(textCut1(ob.value, num1));
        return;
      }

      if ( idxFlag ) {
         num = num+1;
      }

      if (getByte(commaDel(ob.value)) > num  ) {
	    alert(num +"자 이상 입력하실 수 없습니다.");
	    ob.value = Number(textCut1(commaDel(ob.value), num));
	    return;
	  }
      if(Number( ob.value ) ==0){
    	  ob.value ="0";
    	  return;
      }

    } else {

      if ( flag && idxFlag && ob.type== "text" && event.keyCode == "222") { // ' , " 입력방지
        alert(ob.value.charAt(ob.value.length-1)+" 는 입력할 수 없습니다.");
        removeStr(ob,"'");
        removeStr(ob,"\"");
    	return;
      }

	  if (getByte(ob.value) > num  ) {
		if(ob.type == "textarea"){
			alert(num +"byte 이상 입력하실 수 없습니다.");
		}else{
			alert(num +"자 이상 입력하실 수 없습니다.");
		}

	    ob.value = textCut1(ob.value, num);
	    return;
	  }
	}
}
/**
 * 텍스트절삭
 * @param str
 */
function textCut1(str, limit) {
	var l = 0;
	var retStr;
	for (var i=0; i<str.length; i++) {
		l += (str.charCodeAt(i) > 128) ? 2 : 1;

		if(l> limit) {
			retStr = str.substring(0, i);
			break;
		}
	}
	return retStr;
}

/**
 * byte 구하기
 * @param str
 * @returns {Number}
 */
function getByte(str) {
	var l = 0;
	for (var i=0; i<str.length; i++) l += (str.charCodeAt(i) > 128) ? 2 : 1;
	return l;
}

function specialCharCheck(obj, CharCheck) {
	 //var num = "{}[]()<>?|`~'!@#$%^&*-+=,.;:\"'\\/";
	var num = "";

	if (CharCheck == "false") return;
	if (CharCheck == "basic") {
		//num = "\"'";
		num = "\"'{}<>|`^;\\!";
	} else {
		num = "\"'{}<>|`^;\\!";
	}

	 var inputVal = obj.val();
	 var bFlag = true;
	 var f_chr_ind = -1;

	 for (var i = 0;i < inputVal.length;i++) {
		 if(num.indexOf(inputVal.charAt(i)) != -1) {
			 bFlag = false;
			 if (f_chr_ind == -1) f_chr_ind = i;
		 }
	 }

	 if (!bFlag) {
		 alert("입력하실 수 없는 문자 입니다.");
		 obj.val(String(inputVal).substring(0, f_chr_ind));
	 }
}

function removeHtml(text) {
	text = text.replace(/<br>/ig, "\n"); // <br>을 엔터로 변경
	text = text.replace(/&nbsp;/ig, " "); // 공백
	// HTML 태그제거
	//text = text.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "");
	//text = text.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "");

	// shkim.add.
	text = text.replace(/<(no)?script[^>]*>.*?<\/(no)?script>/ig, "");
	text = text.replace(/<style[^>]*>.*<\/style>/ig, "");
	text = text.replace(/<a[^>]*>/ig, "");
	text = text.replace(/<\/a>/ig, "");
	text = text.replace(/<img[^>]*>/ig, "");
	//text = text.replace(/<(\"[^\"]*\"|\"[^\"]*\"|[^\"\">])*>/ig, "");
	//text = text.replace(/<\\w+\\s+[^<]*\\s*>/ig, "");
	//text = text.replace(/&[^;]+;/ig, "");
	//text = text.replace(/\\s\\s+/ig, "");

	return text;
}
