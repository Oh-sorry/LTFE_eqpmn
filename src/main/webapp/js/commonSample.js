var hwpSj = "";
$(function(){
	space();
    gnbMenu();
    familySite();
    allmenu();
    lnb();
    quick_event();
    searchDetail();
	$('.numOnly').keydown(function(event) {
		this.value=this.value.replace(/[^0-9]/g,'');
	});
	$('.numOnly').keyup(function(event) {
		this.value=this.value.replace(/[^0-9]/g,'');
	});
	$('.floatNumOnly').keydown(function(event) {
      this.value=this.value.replace(/^(-?)([0-9]*)(\.?)([^0-9]*)([0-9]*)([^0-9]*)/,'$1$2$3$5');
   	});
   	$('.floatNumOnly').keyup(function(event) {
      this.value=this.value.replace(/^(-?)([0-9]*)(\.?)([^0-9]*)([0-9]*)([^0-9]*)/,'$1$2$3$5');
   	});

//    var inputText = $(':text:not([id=name]):not([class=test1])');
//    var inputText = $(':text');
//    
//    inputText.keypress(function(e){
//	
//	    var classNameStr = this.className;
//		if (classNameStr.indexOf("noSearch") == -1 && e.keyCode == 13 ) {
//			e.preventDefault;
//	        fncAjaxList('1');
//		}
//    })

	
});

$(document).ajaxStop(function() {
	space();
})

//location select
jQuery(function($){
    // Common
    var select_root = $('div.location_select');
    var select_value = $('.location_select .my_value');
    var select_a = $('div.location_select>ul>li>a');
    var select_input = $('div.location_select>ul>li>input[type=radio]');
    var select_label = $('div.location_select>ul>li>label');
    // Radio Default Value
    $('.location_select .my_value').each(function(){
        var default_value = $(this).next('.i_list').find('input[checked]').next('label').text();
        $(this).append(default_value);
    });
    // Line
    select_value.bind('focusin',function(){$(this).addClass('outLine');});
    select_value.bind('focusout',function(){$(this).removeClass('outLine');});
    select_input.bind('focusin',function(){$(this).parents('div.location_select').children('div.my_value').addClass('outLine');});
    select_input.bind('focusout',function(){$(this).parents('div.location_select').children('div.my_value').removeClass('outLine');});
    // Show
    function show_option(){
        $(this).parents('div.location_select:first').toggleClass('open');
    }
    // Hover
    function i_hover(){
        $(this).parents('ul:first').children('li').removeClass('hover');
        $(this).parents('li:first').toggleClass('hover');
    }
    // Hide
    function hide_option(){
        var t = $(this);
        setTimeout(function(){
            t.parents('div.location_select:first').removeClass('open');
        }, 1);
    }
    // Set Input
    function set_label(){
        var v = $(this).next('label').text();
        $(this).parents('ul:first').prev('.my_value').text('').append(v);
        $(this).parents('ul:first').prev('.my_value').addClass('selected');
    }
    // Set Anchor
    function set_anchor(){
        var v = $(this).text();
        $(this).parents('ul:first').prev('.my_value').text('').append(v);
        $(this).parents('ul:first').prev('.my_value').addClass('selected');
    }
    // Anchor Focus Out
    $('*:not("div.location_select a")').focus(function(){
        $('.location_select .a_list').parent('.location_select').removeClass('open');
    });
    select_value.mouseover(show_option);
    select_root.removeClass('open');
    select_root.mouseleave(function(){$(this).removeClass('open');});
    select_a.click(set_anchor).click(hide_option).focus(i_hover).hover(i_hover);
    select_input.change(set_label).focus(set_label);
    select_label.hover(i_hover).mouseleave(hide_option);
});

jQuery(function($){
    // Common
    var select_root = $('div.fake_select');
    var select_value = $('.fake_select .my_value');
    var select_a = $('div.fake_select>ul>li>a');
    var select_input = $('div.fake_select>ul>li>input[type=radio]');
    var select_label = $('div.fake_select>ul>li>label');
    // Radio Default Value
    $('.fake_select .my_value').each(function(){
        var default_value = $(this).next('.i_list').find('input[checked]').next('label').text();
        $(this).append(default_value);
    });
    // Line
    select_value.bind('focusin',function(){$(this).addClass('outLine');});
    select_value.bind('focusout',function(){$(this).removeClass('outLine');});
    select_input.bind('focusin',function(){$(this).parents('div.fake_select').children('div.my_value').addClass('outLine');});
    select_input.bind('focusout',function(){$(this).parents('div.fake_select').children('div.my_value').removeClass('outLine');});
    // Show
    function show_option(){
        $(this).parents('div.fake_select:first').toggleClass('open');
    }
    // Hover
    function i_hover(){
        $(this).parents('ul:first').children('li').removeClass('hover');
        $(this).parents('li:first').toggleClass('hover');
    }
    // Hide
    function hide_option(){
        var t = $(this);
        setTimeout(function(){
            t.parents('div.fake_select:first').removeClass('open');
        }, 1);
    }
    // Set Input
    function set_label(){
        var v = $(this).next('label').text();
        $(this).parents('ul:first').prev('.my_value').text('').append(v);
        $(this).parents('ul:first').prev('.my_value').addClass('selected');
    }
    // Set Anchor
    function set_anchor(){
        var v = $(this).text();
        $(this).parents('ul:first').prev('.my_value').text('').append(v);
        $(this).parents('ul:first').prev('.my_value').addClass('selected');
    }
    // Anchor Focus Out
    $('*:not("div.fake_select a")').focus(function(){
        $('.a_list').parent('.fake_select').removeClass('open');
    });
    select_value.click(show_option);
    select_root.removeClass('open');
    select_root.mouseleave(function(){$(this).removeClass('open');});
    select_a.click(set_anchor).click(hide_option).focus(i_hover).hover(i_hover);
    select_input.change(set_label).focus(set_label);
    select_label.hover(i_hover).click(hide_option);
});

function gnbMenu(e){
	$('.gnb, .gnb_bg').hover(function () {
        $('.gnb_bg').css('top', $('#header').innerHeight());
        var heightArr = [];
        $('.depth_menu').each(function () {
            heightArr.push($(this).innerHeight());
        });
        $('.gnb_bg').css('height', Math.max.apply(null, heightArr));
        $('.depth_menu').css('height', Math.max.apply(null, heightArr));
        $('.gnb_bg, .depth_menu').stop().slideDown();
    }, function() {
    	$('.gnb_bg, .depth_menu').stop().slideUp();
    });
}

function allmenu(){
    var allMenuArea = $("#all_menu_area");
    $(".btn_all_menu").click(function(){
        event.preventDefault();       
        var left = (( $(window).width() -  allMenuArea.width()) / 2 );
        var top = (( $(window).height() -  allMenuArea.height()) / 1.5 );
        allMenuArea.css({'left':left,'top':top, 'position':'absolute', 'display':'block'});
        $("body").addClass('over_h');
        document.getElementById("js-menu-bg").style.display = "block";
    });
    $(".menu_close").click(function(){
        event.preventDefault();
        allMenuArea.fadeOut(200);
        /*allMenuArea.css({"display":"none"});*/
        $("body").removeClass('over_h');
        document.getElementById("js-menu-bg").style.display = "none";
    });
}

function lnb(){
	$(".deps1 li.has_lower > a").on("click", function(){
		var element = $(this).parent("li");
		if(element.hasClass("open")){
			$(this).removeAttr("href");	
		}
	})
    $(".deps1 li.has_sub > a").on("click", function(){
        var element = $(this).parent("li");
        if (element.hasClass("open")) {
            element.removeClass("open");
            element.find("li").removeClass("open");
            element.find(".deps2").slideUp(200);
        }
        else {
            element.addClass("open");
            element.children(".deps2").slideDown(200);
            element.siblings("li").children(".deps2").slideUp(200);
            element.siblings("li").removeClass("open");
        }
    });
}

function quick_event(){
    $(".quick_handle").click(function() {
        if($("#container").hasClass("quick_off")){
            $("#container").removeClass("quick_off");
        }else{
            $("#container").addClass("quick_off");
        }
        return false;
    });
    $( ".btn_top" ).click( function() {
        $( "html, body" ).animate( { scrollTop : 0 }, 200 );
        return false;
    } );
}

function familySite(){
    $(".footBtn button").click(function(){
        $(".family_list").slideToggle("fast", function(){
            $(".footBtn").toggleClass("open");
        });
    });
    $(".family_list a").click(function(){
        if($(".footBtn").hasClass("open")){
            $(".family_list").slideUp("4000");
            $(".footBtn").removeClass("open");
        }
    });
    $("html").click(function(e) {
        if(!$(e.target).parents().hasClass("footBtn")) {
            $(".family_list").slideUp();
            $(".footBtn").removeClass("open");
        }
    });
}

function searchDetail(){
    $(".js-btnDetail").click(function(){
        event.preventDefault();
        if($(this).hasClass("on")){
            $(this).removeClass("on");
            $(this).children("span").text("상세검색");
        }else{
            $(this).addClass("on");
            $(this).children("span").text("검색닫기");
        }
        $(".search_fixed").closest("tr").nextUntil(".detail_last").toggleClass("open");
        $(".detail_last").toggleClass("open");
    });
}

//tab
function tab(){
    $(".js-tab-content").hide();
    $(".js-tab-content:first").show();
    $(".js-tab a").click(function(event) {
        event.preventDefault(); //주소에 #숨김
        $(this).parent().addClass("current");
        $(this).parent().siblings().removeClass("current");
        var tab = $(this).attr("href");
        $(".js-tab-content").not(tab).css("display", "none");
        $(tab).fadeIn();
    });
}


//dimd 로딩창
//divn이 null일때는 현재창 dimd
//divn popDirect일때는 pop창 dimd
//나머지 부모창 dimd 
var layerCnt = 0;
function createDimd(divn){
	if(layerCnt == 0){
		var dimmedHTML = "<div class=\"dimmed\" id=\"dimmedLayer\"><span class=\"dimmed_i\"><img src=\"/ma/images/common/loading.gif\"></span></div>";
		if(divn == null || divn == "" || divn == "undefined"){
			$("#wrapper").append(dimmedHTML);
		}else if(divn == "popDirect"){
			$("#win_container").append(dimmedHTML);	
		}else{
			$(opener.document).find("#wrapper").append(dimmedHTML);	
		}
		layerCnt = 1;
	}
}

//dimd 삭제
function removeDimd(divn){
	if(divn == null || divn == "" || divn == "undefined"){
		$("#dimmedLayer").fadeOut().remove();
	}else{
		$(opener.document).find("#dimmedLayer").fadeOut().remove();
	}
	layerCnt = 0;
}

// date 추가 
var dateAdd = function (sDate, v, t) {
    var yy = parseInt(sDate.substr(0, 4), 10);
    var mm = parseInt(sDate.substr(5, 2), 10);
    var dd = parseInt(sDate.substr(8), 10);

    if(t == "d"){
        d = new Date(yy, mm - 1, dd + v);
    }else if(t == "m"){
        d = new Date(yy, mm - 1 + v, dd);
    }else if(t == "y"){
        d = new Date(yy + v, mm - 1, dd);
    }else{
        d = new Date(yy, mm - 1, dd + v);
    }

    yy = d.getFullYear();
    mm = d.getMonth() + 1; mm = (mm < 10) ? '0' + mm : mm;
    dd = d.getDate(); dd = (dd < 10) ? '0' + dd : dd;

    return '' + yy + '.' +  mm  + '.' + dd;

}
    
// 달력
var fncDate = function(){
	var setDate = arguments;
	var getId;
	var fmt = "yy.mm.dd";
	switch (setDate.length) {
        
		case 1: getId ="#"+setDate[0];break;
		case 2: if(setDate[1] != ''){getId ="#"+setDate[0]+", #"+setDate[1];break;}else{getId ="#"+setDate[0];break;}
		case 3: if(setDate[1] != ''){getId ="#"+setDate[0]+", #"+setDate[1];fmt=setDate[2];break;}else{getId ="#"+setDate[0];fmt=setDate[2];break;}
        default : getId ="#"+setDate[0];break;
	}
	
	 var dates = $( getId ).datepicker({
      changeMonth: true,
      changeYear: true,
      showOn: "button",
      buttonImage: "/ma/images/sub/i_cal.png",
      buttonImageOnly: true,
      dateFormat : fmt,
      onSelect: function( selectedDate ) {
          var option = this.id == setDate[0] ? "minDate" : "maxDate",
          instance = $( this ).data( "datepicker" ),
          date = (fmt == 'yy.mm' ? new Date(instance.selectedYear, instance.selectedMonth, 1) : $.datepicker.parseDate( $.datepicker._defaults.dateFormat, selectedDate, instance.settings ))
    	  dates.not( this ).datepicker( "option", option, date );
      }
  });
}

// 달력 두 날짜 사이 계산
var fncDateCal = function(){
    var setDate = arguments;
    var getId;
    var fmt = "yy.mm.dd";
    switch (setDate.length) {

        case 1: getId ="#"+setDate[0];break;
        case 2: if(setDate[1] != ''){getId ="#"+setDate[0]+", #"+setDate[1];break;}else{getId ="#"+setDate[0];break;}
        case 3: if(setDate[1] != ''){getId ="#"+setDate[0]+", #"+setDate[1];break;}else{getId ="#"+setDate[0];break;}
        default : getId ="#"+setDate[0];break;
    }

    var dates = $( getId ).datepicker({
        changeMonth: true,
        changeYear: true,
        showOn: "button",
        buttonImage: "/ma/images/sub/i_cal.png",
        buttonImageOnly: true,
        dateFormat : fmt,
        onSelect: function( selectedDate ) {
            var option = this.id == setDate[0] ? "minDate" : "maxDate",
                instance = $( this ).data( "datepicker" ),
                date = (fmt == 'yy.mm' ? new Date(instance.selectedYear, instance.selectedMonth, 1) : $.datepicker.parseDate( $.datepicker._defaults.dateFormat, selectedDate, instance.settings ))
            dates.not( this ).datepicker( "option", option, date );
            //날짜 사이 계산
            let stDate = new Date($("#"+setDate[0]).val());
            let endDate = new Date($("#"+setDate[1]).val());
            let calDate = Math.floor(Math.abs(endDate.getTime() - stDate.getTime())/(1000*60*60*24));
            $("#"+setDate[2]).text(calDate+1);

        }
    });
}

function replaceAll(str, oldChar, newChar){
	var tmp = str.split(oldChar).join(newChar);
	return tmp;
}

//layer popup
function view_show(num) {
    var left = (( $(window).width() - $("#display_view_"+num).width()) / 2 );
    var top = (( $(window).height() - $("#display_view_"+num).height()) / 2 );
    document.getElementById("js-popup-bg").style.display = "block";
    $("#display_view_"+num).css({'left':left,'top':top, 'position':'fixed', 'display':'block'});
    return false;
 }
function view_hide(num) {
    document.getElementById("js-popup-bg").style.display = "none";
    $("#display_view_"+num).css('display','none');
    return false;
}

function fileLayer(atchFileId){
	$("#footerAtchFileId").val(atchFileId);
	
	footerFileFrm.action="/atch/fileUpload.do";
	footerFileFrm.target="footerAtchFileIdFrame";
	footerFileFrm.submit();
	
}

function fileLayer_show() {
    //event.preventDefault();
	var left = (( $(window).width() - $("#file_layer").width()) / 2 );
    var top = (( $(window).height() - $("#file_layer").height()) / 2 );// - 50;
    $("#file_layer").css({'left':left,'top':top, 'position':'absolute'});
    document.getElementById("file_layer").style.display = "block";
    //document.getElementById("js-filepopup-bg").style.display = "block";
    return false;
 }
function fileLayer_hide() {
    event.preventDefault();
    document.getElementById("file_layer").style.display = "none";
    //document.getElementById("js-filepopup-bg").style.display = "none";
    return false;
}

function lpad(item, len, item2) { 
	var left = '';
	item = item.toString();
	if(item2 == "" || item2.length > 2){
		item2 = "0";
	}
	if (item.length < len) {
	  for (i = 0; i < len - item.length; i++) {
	    left += item2;
	  }
	}
	return left + item;
}

//ngins7512추가 // 2016-02-14 
//textarea Byte Check
function fnTextareaCheckByte(ObjId, showByte ,limitByte){
    // count 정보 및 count 정보와 관련된 textarea/input 요소를 찾아내서 변수에 저장한다.
    var $maxcount = $("#"+limitByte);
    var $count = $("#"+showByte);
    var $input = $("#"+ObjId);
    // .text()가 문자열을 반환하기에 이 문자를 숫자로 만들기 위해 1을 곱한다.
    var maximumByte = $maxcount.text() * 1;
    // update 함수는 keyup, paste, input 이벤트에서 호출한다.
    var update = function () {
      var before = $count.text() * 1;
      var str_len = $input.val().length;
      var cbyte = 0;
      var li_len = 0;
      for (i = 0; i < str_len; i++) {
          var ls_one_char = $input.val().charAt(i);
          if (escape(ls_one_char).length > 4) {
              cbyte += 2; //한글이면 2를 더한다
          } else {
              cbyte++; //한글아니면 1을 다한다
          }
          if (cbyte <= maximumByte) {
              li_len = i + 1;
          }
      }
      // 사용자가 입력한 값이 제한 값을 초과하는지를 검사한다.
      if (parseInt(cbyte) > parseInt(maximumByte)) {
          alert(maximumByte +' Byte가 초과되었습니다.\r\n\n초과된 부분은 자동으로 삭제됩니다.');
          var str = $input.val();
          var str2 = $input.val().substr(0, li_len);
          $input.val(str2);
          var cbyte = 0;
          for (i = 0; i < $input.val().length; i++) {
              var ls_one_char = $input.val().charAt(i);
              if (escape(ls_one_char).length > 4) {
                  cbyte += 2; //한글이면 2를 더한다
              } else {
                  cbyte++; //한글아니면 1을 다한다
              }
          }
      }
      $count.text(cbyte);
    };
    // input, keyup, paste 이벤트와 update 함수를 바인드한다
    $input.bind('input keyup keydown paste change', function () {
        update
    });
    update();
}

//달력(월)
let fncMonth = function(){
    let setDate = arguments;
    let getId;
    let fmt = "yy.mm";
    switch (setDate.length) {
        case 1: getId ="#"+setDate[0];break;
        case 2: if(setDate[1] != ''){getId ="#"+setDate[0]+", #"+setDate[1];break;}else{getId ="#"+setDate[0];break;}
    }

    let dates = $( getId ).monthpicker({
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월','7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        showOn: "button",
        buttonImage: "/ma/images/sub/i_cal.png",
        buttonImageOnly: true,
        changeYear: false,
        yearRange: 'c-2:c+2',
        dateFormat : fmt,
        onSelect: function( selectedDate ) {
            var option = this.id == setDate[0] ? "minDate" : "maxDate",
                instance = $( this ).data( "monthpicker" ),
                date = (fmt == 'yy.mm' ? new Date(instance.selectedYear, instance.selectedMonth, 1) : $.monthpicker.parseDate( $.monthpicker._defaults.dateFormat, selectedDate, instance.settings ))
            dates.not( this ).monthpicker( "option", option, date );
        }
    });
}

//글자수 체크
function fncChkWord(getText, getCnt, textCount) {
    // getText = 텍스트
    // getCnt = 글자수 카운팅
    // textCount = 글자수
    let content = $("#"+getText).val();
    $("#"+getCnt).text(content.length + "/ "+textCount);

    $("#"+getText).on('keydown', function (){
        let content = $(this).val();
        $("#"+getCnt).text(content.length + "/ "+textCount);
        if (content.length > textCount) {
            $(this).val(content.substring(0, textCount));
            $("#"+getCnt).text(content.substring(0, textCount).length + "/ "+textCount);
        }
    });
    $("#"+getText).on('keyup', function (){
        let content = $(this).val();
        $("#"+getCnt).text(content.length + "/ "+textCount);
        if (content.length > textCount) {
            $(this).val(content.substring(0, textCount));
            $("#"+getCnt).text(content.substring(0, textCount).length + "/ "+textCount);
        }
    });
}

//달력(시간노출)
var fncDateTime = function(){
	var setDate = arguments;
	var getId;
	var fmt = "yy.mm.dd";
	switch (setDate.length) {
		case 1: getId ="#"+setDate[0];break;
		case 2: if(setDate[1] != ''){getId ="#"+setDate[0]+", #"+setDate[1];break;}else{getId ="#"+setDate[0];break;}
		case 3: if(setDate[1] != ''){getId ="#"+setDate[0]+", #"+setDate[1];fmt=setDate[2];break;}else{getId ="#"+setDate[0];fmt=setDate[2];break;}
        default : getId ="#"+setDate[0];break;
	}
	
	var dates = $( getId ).datetimepicker({
		monthNamesShort:[ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
    	dayNamesMin:[ '일', '월', '화', '수', '목', '금', '토' ],
		showMonthAfterYear:true,
    	changeMonth: true,
    	changeYear: true,
    	showOn: "button",
    	buttonImage: "/ma/images/sub/icon_calendar.gif",
    	buttonImageOnly: true,
    	dateFormat : fmt,
		//timepicker
		timeFormat:'HH:mm',
		controlType:'select',
		oneLine:true,
    	onSelect: function( selectedDate ) {
    		var option = this.id == setDate[0] ? "minDate" : "maxDate",
    		instance = $( this ).data( "datepicker" ),
    		date = (fmt == 'yy.mm' ? new Date(instance.selectedYear, instance.selectedMonth, 1) : $.datepicker.parseDate( $.datepicker._defaults.dateFormat, selectedDate, instance.settings ))
    		dates.not( this ).datepicker( "option", option, date );
    	}
	});
}

//달력(시간노출)-년월 고정
let fncTime = function(setId,minDt,maxDt){
    let getId = "#"+setId;
    let fmt = "yy.mm.dd";

    var dates = $( getId ).datetimepicker({
        monthNamesShort:[ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
        dayNamesMin:[ '일', '월', '화', '수', '목', '금', '토' ],
        showMonthAfterYear:true,
        changeMonth: true,
        changeYear: true,
        showOn: "button",
        buttonImage: "/ma/images/sub/icon_calendar.gif",
        buttonImageOnly: true,
        dateFormat : fmt,
        currentText : '',
        minDate : minDt,
        maxDate : maxDt,
        //timepicker
        timeFormat : 'HH:mm',
        minTime : '06:00',
        maxTime : '20:00',
        controlType : 'select',
        step : '10',
        oneLine : true
    });
}

//우편번호
function execDaumPostcode(menu) {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 도로명 조합형 주소 변수
            var jibunAddr = ''; //지번주소에서 번지 분리
            var bunji = ''; //번지

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if(fullRoadAddr !== ''){
                fullRoadAddr += extraRoadAddr;
            }
            if(data.jibunAddress !== ''){
            	var addr = data.jibunAddress.split(' ');
            	for(i=0; i<addr.length-1; i++){
            		jibunAddr += addr[i];
            		if(i != addr.length-2){
            			jibunAddr += " ";
            		}
            		
            	}
            	bunji = addr[addr.length -1];
            }
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            if(menu == 'eers'){
            	 document.getElementById('addrNameText').value = jibunAddr;
            	 document.getElementById('bunji').value = bunji;
                 document.getElementById('addrNameText1').value = data.roadAddress;
                 document.getElementById('roadNm').value = data.roadname;
                 
                 var road = data.roadAddress.split(" ");
                 var roadNo= road[road.length -1].split("-");
                 
                 document.getElementById('bldMainNo').value = roadNo[0];
                 if(roadNo[1] != null && roadNo[1] != '' && roadNo[1] != 'undefined'){
                	 document.getElementById('bldSubNo').value = roadNo[1];
                 }
                 
                 
            }else{
            	document.getElementById('postNo').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('bascAddr').value = fullRoadAddr;
            }
            
             // 주소로 상세 정보를 검색
            /* geocoder.addressSearch(fullRoadAddr, function(results, status) {
                // 정상적으로 검색이 완료됐으면
                if (status === daum.maps.services.Status.OK) {

                    var result = results[0]; //첫번째 결과의 값을 활용

                    // 해당 주소에 대한 좌표를 받아서
                    //var coords = new daum.maps.LatLng(result.y, result.x);
                    document.getElementById('goX').value = result.x;
                    document.getElementById('goY').value = result.y;
                }
            }); */

            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                //예상되는 도로명 주소에 조합형 주소를 추가한다.
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

            } else {
                //document.getElementById('guide').innerHTML = '';
            }
        }
    }).open();
}

// null undefined "" 를 boolen 리턴
function nullString(item){
	item = $.trim(item);
	if(item == null || item == undefined || item ==""){
		return false;
	}
	return true;
}

// upperCode - 부모코드
// codeType - select, check, radio
// defVal - 기본값 SELECT박스 전체, 선택 등등
// selVal - 선택할값(검색값유지) checkbox는 콤마로 구분해서 넣으면 됩니다.
// name - name속성 값 checkbox랑 radio만 넣어주면됩니다.
// tagId - 태그 ID select는 select 태그의 id, 나머지는 부모태그의 id값 넣어주면 됩니다.
// idKey - checkbox, radio 동적으로 추가할일 있을때 사용 기본 check_코드, radio_코드지만 값을 주면 check_코드_값, radio_코드_값
// function fncCodeList(upperCode, codeType, defVal, selVal, name, tagId, idKey){
//     $.ajax({
//         url      : "/cmmn/getFtCode.do",
//         type     : "post",
//         data     : {
//             uppoCdVal : upperCode,
//             codeType  : codeType,
//             defVal    : defVal,
//             selVal    : selVal,
//             name      : name,
//             idKey     : idKey
//         },
//         dataType : "html",
//         async    : false,
//         success  : function(data){
//             $("#" + tagId).html(data);
//         }
//     })
// }

// upperCode - 부모코드
// codeType - select, check, radio
// defVal - 기본값 SELECT박스 전체, 선택 등등
// selVal - 선택할값(검색값유지) checkbox는 콤마로 구분해서 넣으면 됩니다.
// name - name속성 값 checkbox랑 radio만 넣어주면됩니다.
// tagId - 태그 ID select는 select 태그의 id, 나머지는 부모태그의 id값 넣어주면 됩니다.
// idKey - checkbox, radio 동적으로 추가할일 있을때 사용 기본 check_코드, radio_코드지만 값을 주면 check_코드_값, radio_코드_값
function fncCodeList(upperCode, codeType, defVal, selVal, name, tagId, idKey){
    if($("#"+tagId).find("option").length < 2) {
        $.ajax({
            url: "/cmmn/getMaCode.do",
            type: "post",
            data: {
                uppoCdVal: upperCode,
                codeType: codeType,
                defVal: defVal,
                selVal: selVal,
                name: name,
                idKey: idKey
            },
            dataType: "html",
            async: false,
            success: function (data) {
                $("#" + tagId).html(data);
            }
        })
    }
}

// ROW병합
var rowspan = function(colIdx, t) {
	return this.each(function(){
		console.log(colIdx);
	  	var that;
	  	if(!t) t = "td";
	  	$('tr', this).each(function(row) {
	  		$(t + ':eq('+colIdx+')', this).each(function(col) {
	      		if ($(this).html() == $(that).html()) {
	        	rowspan = $(that).attr("rowSpan");
	        	if (rowspan == undefined) {
	          		$(that).attr("rowSpan",1);
	          		rowspan = $(that).attr("rowSpan");
	        	}
	        	rowspan = Number(rowspan)+1;
	        	$(that).attr("rowSpan",rowspan);
	        	$(this).hide();
	      		} else {
	        		that = this;
	      		}
	      		that = (that == null) ? this : that;
	 		});
		});
	});
}	 

//ROW병합 속성
var rowspanAttr = function(colIdx) {
	return this.each(function(){
		console.log(colIdx);
		var that;
		$('tr', this).each(function(row) {
  			$('td:eq('+colIdx+')', this).each(function(col) {
      			if ($(this).attr("data-rowspan") == $(that).attr("data-rowspan")) {
					rowspan = $(that).attr("rowSpan");
        			if (rowspan == undefined) {
          				$(that).attr("rowSpan",1);
          				rowspan = $(that).attr("rowSpan");
        			}
        			rowspan = Number(rowspan)+1;
        			$(that).attr("rowSpan",rowspan); 
        			$(this).hide(); 
      			} else {
        			that = this;
      			}
      			that = (that == null) ? this : that; 
  			});
 		});
 	});
};
	 
//COL병합
var colspan = function(rowIdx) {
	 return this.each(function(){
	  var that;
	  $('tr', this).filter(":eq("+rowIdx+")").each(function(row) {
	  $(this).find('td').each(function(col) {
	      if ($(this).html() == $(that).html()) {
	        colspan = $(that).attr("colSpan");
	        if (colspan == undefined) {
	          $(that).attr("colSpan",1);
	          colspan = $(that).attr("colSpan");
	        }
	        colspan = Number(colspan)+1;
	        $(that).attr("colSpan",colspan);
	        $(this).hide(); 
	      } else {
	        that = this;
	      }
	      that = (that == null) ? this : that;
	  });
	 });

	 });
	};

//날짜 세팅
var setDate = function(){
	var getId = arguments;
	var curdate = new Date();
	var year = curdate.getFullYear();
	var month = curdate.getMonth()+1;
	var day = curdate.getDate();
	console.log(getId.length);
	for (var i = 0; i < getId.length; i++) {
		$("#"+getId[i]).val(year+'.'+(month < 10 ? '0'+ month : month)+'.'+(day < 10 ? '0'+ day : day));
	}
};
//월 세팅
var setDateMon = function(){
	var getId = arguments;
	var curdate = new Date();
	var year = curdate.getFullYear();
	var month = curdate.getMonth()+1;
	console.log(getId.length);
	for (var i = 0; i < getId.length; i++) {
		$("#"+getId[i]).val(year+'.'+(month < 10 ? '0'+ month : month));
	}
};

//selectBox
function selectBoxReset(id, text){
    var html = "<option value='' >"+text+"</option>";
    $("#" + id).html(html);
}

function fncSftwList(scgId, id, selVal, text) {
    if($("#"+id).find("option").length < 2) {
        /*
        * scgId 제품기능 코드
        * id     셀렉트 아이디
        * selVal 선택값
        * text   기본 문구
        * */
        $.ajax({
            url: '/cmmn/getSftwList.do'
            , type: 'post'
            , data: {'scgId': scgId, 'schEtc01': 'select'}
            , async: false
            , success: function (sftwList) {
                let html = "";
                html += "<option value='' >" + text + "</option>";
                if (sftwList.length > 0) {

                    $.each(sftwList, function (a, b) {
                        html += "<option value='" + b.prdcSeqNo + "'>" + b.sftwPckgNm + "</option>";
                    });

                    $("#" + id).html(html);

                    if (selVal) {
                        $("#" + id).val(selVal);
                    }
                } else {
                    $("#" + id).html(html);
                }
            }, error: function (error) {
                console.log(error);
            }
        });
    }
}

//점검양식 호출
function fncFrmList(clVal2, id, selVal, text) {
    if($("#"+id).find("option").length < 2) {
        /*
        * clVal2 구분 코드
        * id     셀렉트 아이디
        * selVal 선택값
        * text   기본 문구
        * */
        $.ajax({
            url: '/cmmn/getFrmList.do'
            , type: 'post'
            , data: {'clVal2': clVal2}
            , async: false
            , success: function (frmList) {
                let html = "";
                html += "<option value='' >" + text + "</option>";
                if (frmList.length > 0) {

                    $.each(frmList, function (a, b) {
                        html += "<option value='" + b.frmSeqNo + "'>" + b.rptdFrmNm + "</option>";
                    });

                    $("#" + id).html(html);

                    if (selVal) {
                        $("#" + id).val(selVal);
                    }
                } else {
                    $("#" + id).html(html);
                }
            }, error: function (error) {
                console.log(error);
            }
        });
    }
}

//1차 사업소 호출
function fncTs1BizpList(id, selVal, text) {
    /*
    * id     셀렉트 아이디
    * selVal 선택값
    * text   기본 문구
    * */
    $.ajax({
          url: '/cmmn/getBizpList.do'
        , type: 'post'
        , async: false
        , success: function (bizpList) {
            let html = "";
            html += "<option value='0000' >"+text+"</option>";
            if (bizpList.length > 0) {

                $.each(bizpList, function (a, b) {
                    html += "<option value='" + b.ts1BizpCd + "'>" + b.ts1BizpNm + "</option>";
                });

                $("#" + id).html(html);

                if (selVal) {
                    $("#" + id).val(selVal);
                }
            }else{
                $("#" + id).html(html);
            }
        }, error: function (error) {
            console.log(error);
        }
    });
}

//2차사업소 호출
function fncTs2BizpList(ts1BizpCd, id, selVal, text) {
    /*
    * ts1BizpCd 1차사업소 코드
    * id     셀렉트 아이디
    * selVal 선택값
    * text   기본 문구
    * */
    $.ajax({
          url: '/cmmn/getBizpList.do'
        , type: 'post'
        , data: {'ts1BizpCd': ts1BizpCd}
        , async: false
        , success: function (bizpList) {
            let html = "";
            html += "<option value='0000' >"+text+"</option>";
            if (bizpList.length > 0) {

                $.each(bizpList, function (a, b) {
                    html += "<option value='" + b.ts2BizpCd + "'>" + b.ts2BizpNm + "</option>";
                });

                $("#" + id).html(html);

                if (selVal) {
                    $("#" + id).val(selVal);
                }
            }else{
                $("#" + id).html(html);
            }
        }, error: function (error) {
            console.log(error);
        }
    });
}

//소분류에 따른 소프트명 리스트 호출
function fncSfnmList(ts1BizpCd, ts2BizpCd, sftwId, id, selVal, text) {
    if($("#"+id).find("option").length < 2) {
        /*
        * ts1BizpCd 1차사업소코드
        * ts2BizpCd 2차사업소코드
        * sftwId 소프트웨어 ID
        * id     셀렉트 아이디
        * selVal 선택값
        * text   기본 문구
        * */
        $.ajax({
            url: '/cmmn/getSfnmList.do'
            , type: 'post'
            , data: {'ts1BizpCd': ts1BizpCd, 'ts2BizpCd': ts2BizpCd, 'sftwId': sftwId}
            , async: false
            , success: function (getSfnmList) {
                let html = "";
                html += "<option value='' >" + text + "</option>";
                if (getSfnmList.length > 0) {

                    $.each(getSfnmList, function (a, b) {
                        html += "<option value='" + b.seqNo + "'>" + b.sftwNm + "</option>";
                    });

                    $("#" + id).html(html);

                    if (selVal) {
                        $("#" + id).val(selVal);
                    }
                } else {
                    $("#" + id).html(html);
                }
            }, error: function (error) {
                console.log(error);
            }
        });
    }
}

//업체 호출
function getCoNmList(id, selVal, text, coCgCd) {
    if($("#"+id).find("option").length < 2) {
        /*
        * id     셀렉트 아이디
        * selVal 선택값
        * text   기본 문구
        * */
        $.ajax({
            url: '/cmmn/getCoNmList.do'
            , type: 'post'
            , data: {coCgCd : coCgCd}
            , async: false
            , success: function (getCoNmList) {
                if (getCoNmList.length > 0) {
                    var html = "";
                    html += "<option value='' >" + text + "</option>";

                    $.each(getCoNmList, function (a, b) {
                        html += "<option value='" + b.coSeqNo + "'>" + b.coNm + "</option>";
                    });

                    $("#" + id).html(html);

                    if (selVal) {
                        $("#" + id).val(selVal);
                    }
                }
            }, error: function (error) {
                console.log(error);
            }
        });
    }
}

//엔지니어/담당자 호출
function getTchgrNmList(seqNo, id, selVal, text,tchgrClNm) {
    if($("#"+id).find("option").length < 2) {
        /*
        * seqNo  업체명 일련번호
        * id     셀렉트 아이디
        * selVal 선택값
        * text   기본 문구
        * tchgrClNm   담당자 구분
        * */
        $.ajax({
            url: '/cmmn/getTchgrNmList.do'
            , type: 'post'
            , data: {'coSeqNo': seqNo,'tchgrClNm':tchgrClNm}
            , async: false
            , success: function (maTchgrList) {
                var html = "";
                html += "<option value=''>" + text + "</option>";
                if (maTchgrList.length > 0) {
                    $.each(maTchgrList, function (a, b) {
                        html += "<option value='" + b.tchgrSeqNo + "'>" + b.tchgrNm + "</option>";
                    });
                    $("#" + id).html(html);

                    if (selVal) {
                        $("#" + id).val(selVal);
                    }
                } else {
                    $("#" + id).html(html);
                }
            }, error: function (error) {
                console.log(error);
            }
        });
    }
}

//권한에 맞는 유저 호출
function getAuthUserList(authCdVal, id, selVal, text) {
    if($("#"+id).find("option").length < 2) {
        /*
        * authCdVal 권한정보
        * id       셀렉트 아이디
        * selVal   선택값
        * text     기본 문구
        * */
        $.ajax({
            url: '/cmmn/authUserList.do'
            , type: 'post'
            , data: {'authCdVal': authCdVal}
            , async: false
            , success: function (authUserList) {
                var html = "";
                html += "<option value=''>" + text + "</option>";
                if (authUserList.length > 0) {
                    $.each(authUserList, function (a, b) {
                        html += "<option value='" + b.userId + "'>" + b.userId + ":" + b.userNm + "</option>";
                    });
                    $("#" + id).html(html);

                    if (selVal) {
                        $("#" + id).val(selVal);
                    }
                } else {
                    $("#" + id).html(html);
                }
            }, error: function (error) {
                console.log(error);
            }
        });
    }
}

//년도 호출
function fncYearList(id, selVal) {
    if($("#"+id).find("option").length < 2) {
        /*
        * id     셀렉트 아이디
        * selVal 선택값
        * */
        $.ajax({
            url: '/cmmn/getYearList.do'
            , type: 'post'
            , async: false
            , success: function (getYearList) {
                let html = "";
                if (getYearList.length > 0) {

                    $.each(getYearList, function (a, b) {
                        html += "<option value='" + b.year + "'>" + b.year + "년</option>";
                    });

                    $("#" + id).html(html);

                    if (selVal) {
                        $("#" + id).val(selVal);
                    }
                } else {
                    $("#" + id).html(html);
                }
            }, error: function (error) {
                console.log(error);
            }
        });
    }
}

// window Popup
let comPpopNum = 0;
function window_show(id, url, wth, het){
    let popupX = (window.screen.width / 2) - (200 / 2);
    let popupY= (window.screen.height / 2) - (300 / 2);
    window.open('', url.replace(".do","") + comPpopNum, 'left='+popupX+',top='+popupY+',titlebar=no,status=no,toolbar=no,resizable=yes,scrollbars=yes,width='+wth+'px, height='+het+'px');
    $("#"+id).attr({"action" : url, "method" : "post", "target" : url.replace(".do","") + comPpopNum, "onsubmit" : ""}).submit();
}

function space(e) {
	// startsWith func
  String.prototype.startsWith = function (str) {
    if (this.length < str.length) {
      return false;
    }
    return this.indexOf(str) == 0;
  }

  // endsWith func
  String.prototype.endsWith = function (str) {
    if (this.length < str.length) {
      return false;
    }
    return this.lastIndexOf(str) + str.length == this.length;
  }

  $('body').find($("[class*='mar_']")).each(function () {
    var _mar = $(this).attr('class').split(' ');

    for (var i = 0; i < _mar.length; i++) {
      var num = '';
      var position = '';
      var arr = _mar[i].split('');
      if (_mar[i].startsWith('mar_') === true) {
        for (var j = 0; j < arr.slice(5).length; j++) {
          num += arr.slice(5)[j];
        }

        if (arr[4] === 't') position += 'margin-top';
        if (arr[4] === 'r') position += 'margin-right';
        if (arr[4] === 'b') position += 'margin-bottom';
        if (arr[4] === 'l') position += 'margin-left';

        $(this).css(position, parseInt(num));
      }
    }
  });

  $('body').find($("[class*='pad_']")).each(function () {
    var _pad = $(this).attr('class').split(' ');

    for (var i = 0; i < _pad.length; i++) {
      var num = '';
      var position = '';
      var arr = _pad[i].split('');
      if (_pad[i].startsWith('pad_') === true) {
        for (var j = 0; j < arr.slice(5).length; j++) {
          num += arr.slice(5)[j];
        }

        if (arr[4] === 't') position += 'padding-top';
        if (arr[4] === 'r') position += 'padding-right';
        if (arr[4] === 'b') position += 'padding-bottom';
        if (arr[4] === 'l') position += 'padding-left';

        $(this).css(position, parseInt(num));
      }
    }
  });

  $('body').find($("[class*='w']")).each(function () {
    var _w = $(this).attr('class').split(' ');

    for (var i = 0; i < _w.length; i++) {
      var num = _w[i].replace(/[^0-9]/g, '');
      if (_w[i].startsWith('w') && (_w[i].endsWith('p') || _w[i].endsWith('px'))) {
        if (_w[i].startsWith('w') === true && _w[i].endsWith('p')) $(this).css("width", num + "%");
        else if (_w[i].startsWith('w') === true && _w[i].endsWith('px')) $(this).css("width", num + "px");
      }
    }
  });

  $('body').find($("[class*='fs']")).each(function () {
    var _fs = $(this).attr('class').split(' ');

    for (var i = 0; i < _fs.length; i++) {
      var num = _fs[i].replace(/[^0-9,_]/g, '');
      var underbar = _fs[i].replace(/[^_]/g, '');
      if (_fs[i].startsWith('fs') && (_fs[i].endsWith('p') || _fs[i].endsWith('px') || _fs[i].endsWith('rem'))) {
        if (underbar) {
          num = num.replace('_', '.');
        }
        if (_fs[i].startsWith('fs') === true && _fs[i].endsWith('p')) $(this).css("fontSize", num + "%");
        else if (_fs[i].startsWith('fs') === true && _fs[i].endsWith('px')) $(this).css("fontSize", num + "px");
        else if (_fs[i].startsWith('fs') === true && _fs[i].endsWith('rem')) $(this).css("fontSize", num + "rem");
      }
    }
  });
};

function fillForm(){
    for(let i = 0; i < document.defaultFrm.elements.length; i++) {
        let el =  document.defaultFrm.elements[i];
        let classes =  el.classList;
        if (el.getAttribute("required") != null) {
            let type = el.type;
            if(type == "select-one"){
                $("#"+el.getAttribute("id")+" option:eq(1)").prop("selected", true);
            }else if(classes.contains("hasDatepicker")){
                $("#"+el.getAttribute("id")).val("2022.02.22");
            }else if(type == "text"){
                $("#"+el.getAttribute("id")).val("1");
            }
        }
    }
}