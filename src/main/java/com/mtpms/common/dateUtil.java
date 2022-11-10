package com.mtpms.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public final class dateUtil {
	/**
	 * <pre>
	 * Description : This prevents the default parameterless constructor
	 *               from being used elsewhere in code.
	 * </pre>
	 *
	 * Constructor of CodeManagerUtil.java class
	 *
	 * @auther : User
	 * @since : 2016. 2. 24.
	 */
	private dateUtil() {
		// Throw an exception if this ever *is* called
		// throw new AssertionError("Instantiating utility class");
	}

	/**
	 * Log4j2 Logger.
	 */
	private static final Logger LOGGER = LogManager.getLogger(dateUtil.class.getName());

	/**
	 * 기본 날짜 포멧 : 일 길이(2).
	 */
	private static final int LENGTH_DAY = 2;

	/**
	 * 기본 날짜 포멧 : 월 길이(2).
	 */
	private static final int LENGTH_MONTH = 2;

	/**
	 * 기본 날짜 포멧 : 연 길이(4).
	 */
	private static final int LENGTH_YEAR = 4;

	/**
	 * 날짜 포멧 구분자 길이(1).
	 */
	private static final int LENGTH_DATE_SEPERATOR = 1;

	/**
	 * 기본 체크 전 DATE 문자열 길이(10).
	 */
	private static final int LENGTH_FORMATTED_DATE = 10;

	/**
	 * 기본 체크 후 DATE 문자열 길이(8).
	 */
	private static final int LENGTH_RAW_DATE = 8;

	/**
	 * 기본 날짜 포멧 : 시간 길이(2).
	 */
	private static final int LENGTH_HOUR = 2;

	/**
	 * 기본 날짜 포멧 : 분 길이(2).
	 */
	private static final int LENGTH_MINUTE = 2;

	/**
	 * 시간 포멧 구분자 길이(1).
	 */
	private static final int LENGTH_TIME_SEPERATOR = 1;

	/**
	 * 기본 체크 전 TIME 문자열 길이(8).
	 */
	private static final int LENGTH_FORMATTED_TIME = 8;

	/**
	 * 기본 체크 후 TIME 문자열 길이(6).
	 */
	private static final int LENGTH_RAW_TIME = 6;

	/**
	 * <p>
	 * yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 년, 월, 일을 증감한다. 년, 월, 일은 가감할 수를 의미하며, 음수를 입력할 경우 감한다.
	 * </p>
	 *
	 * <pre>
	 * DateUtil.addYearMonthDay("19810828", 0, 0, 19)  = "19810916"
	 * DateUtil.addYearMonthDay("20060228", 0, 0, -10) = "20060218"
	 * DateUtil.addYearMonthDay("20060228", 0, 0, 10)  = "20060310"
	 * DateUtil.addYearMonthDay("20060228", 0, 0, 32)  = "20060401"
	 * DateUtil.addYearMonthDay("20050331", 0, -1, 0)  = "20050228"
	 * DateUtil.addYearMonthDay("20050301", 0, 2, 30)  = "20050531"
	 * DateUtil.addYearMonthDay("20050301", 1, 2, 30)  = "20060531"
	 * DateUtil.addYearMonthDay("20040301", 2, 0, 0)   = "20060301"
	 * DateUtil.addYearMonthDay("20040229", 2, 0, 0)   = "20060228"
	 * DateUtil.addYearMonthDay("20040229", 2, 0, 1)   = "20060301"
	 * </pre>
	 *
	 * @param sDate
	 *        날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param year
	 *        가감할 년. 0이 입력될 경우 가감이 없다
	 * @param month
	 *        가감할 월. 0이 입력될 경우 가감이 없다
	 * @param day
	 *        가감할 일. 0이 입력될 경우 가감이 없다
	 * @return yyyyMMdd 형식의 날짜 문자열
	 * @throws IllegalArgumentException
	 *         날짜 포맷이 정해진 바와 다를 경우. 입력 값이 <code>null</code>인 경우.
	 */
	public static String addYearMonthDay(final String sDate, final int year, final int month, final int day) {
		String dateStr = validChkDate(sDate);
		LocalDate locDate = null;
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		try {
			locDate = LocalDate.parse(dateStr, dateFormat);
		} catch (DateTimeParseException e) {
			// TODO: handle exception
			LOGGER.error(e);
		}
		if (year != 0) {
			locDate = locDate.plusYears(year);
		}
		if (month != 0) {
			locDate = locDate.plusMonths(month);
		}
		if (day != 0) {
			locDate = locDate.plusDays(day);
		}
		return dateFormat.format(locDate);
	}

	/**
	 * <p>
	 * yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 년을 증감한다. <code>year</code>는 가감할 수를 의미하며, 음수를 입력할 경우 감한다.
	 * </p>
	 *
	 * <pre>
	 * DateUtil.addYear("20000201", 62)  = "20620201"
	 * DateUtil.addYear("20620201", -62) = "20000201"
	 * DateUtil.addYear("20040229", 2)   = "20060228"
	 * DateUtil.addYear("20060228", -2)  = "20040228"
	 * DateUtil.addYear("19000101", 200) = "21000101"
	 * </pre>
	 *
	 * @param dateStr
	 *        날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param year
	 *        가감할 년. 0이 입력될 경우 가감이 없다
	 * @return yyyyMMdd 형식의 날짜 문자열
	 * @throws IllegalArgumentException
	 *         날짜 포맷이 정해진 바와 다를 경우. 입력 값이 <code>null</code>인 경우.
	 */
	public static String addYear(final String dateStr, final int year) {
		return addYearMonthDay(dateStr, year, 0, 0);
	}

	/**
	 * <p>
	 * yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 월을 증감한다. <code>month</code>는 가감할 수를 의미하며, 음수를 입력할 경우 감한다.
	 * </p>
	 *
	 * <pre>
	 * DateUtil.addMonth("20010201", 12)  = "20020201"
	 * DateUtil.addMonth("19800229", 12)  = "19810228"
	 * DateUtil.addMonth("20040229", 12)  = "20050228"
	 * DateUtil.addMonth("20050228", -12) = "20040228"
	 * DateUtil.addMonth("20060131", 1)   = "20060228"
	 * DateUtil.addMonth("20060228", -1)  = "20060128"
	 * </pre>
	 *
	 * @param dateStr
	 *        날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param month
	 *        가감할 월. 0이 입력될 경우 가감이 없다
	 * @return yyyyMMdd 형식의 날짜 문자열
	 * @throws IllegalArgumentException
	 *         날짜 포맷이 정해진 바와 다를 경우. 입력 값이 <code>null</code>인 경우.
	 */
	public static String addMonth(final String dateStr, final int month) {
		return addYearMonthDay(dateStr, 0, month, 0);
	}

	/**
	 * <p>
	 * yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 일(day)를 증감한다. <code>day</code>는 가감할 수를 의미하며, 음수를 입력할 경우 감한다. <br/>
	 * <br/>
	 * 위에 정의된 addDays 메서드는 사용자가 ParseException을 반드시 처리해야 하는 불편함이 있기 때문에 추가된 메서드이다.
	 * </p>
	 *
	 * <pre>
	 * DateUtil.addDay("19991201", 62) = "20000201"
	 * DateUtil.addDay("20000201", -62) = "19991201"
	 * DateUtil.addDay("20050831", 3) = "20050903"
	 * DateUtil.addDay("20050831", 3) = "20050903"
	 * // 2006년 6월 31일은 실제로 존재하지 않는 날짜이다 -> 20060701로 간주된다
	 * DateUtil.addDay("20060631", 1) = "20060702"
	 * </pre>
	 *
	 * @param dateStr
	 *        날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param day
	 *        가감할 일. 0이 입력될 경우 가감이 없다
	 * @return yyyyMMdd 형식의 날짜 문자열
	 * @throws IllegalArgumentException
	 *         날짜 포맷이 정해진 바와 다를 경우. 입력 값이 <code>null</code>인 경우.
	 */
	public static String addDay(final String dateStr, final int day) {
		return addYearMonthDay(dateStr, 0, 0, day);
	}

	/**
	 * <p>
	 * yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열 <code>dateStr1</code>과 <code>
	 * dateStr2</code> 사이의 일 수를 구한다.<br>
	 * <code>dateStr2</code>가 <code>dateStr1</code> 보다 과거 날짜일 경우에는 음수를 반환한다. 동일한 경우에는 0을 반환한다.
	 * </p>
	 *
	 * <pre>
	 * DateUtil.getDaysDiff("20060228","20060310") = 10
	 * DateUtil.getDaysDiff("20060101","20070101") = 365
	 * DateUtil.getDaysDiff("19990228","19990131") = -28
	 * DateUtil.getDaysDiff("20060801","20060802") = 1
	 * DateUtil.getDaysDiff("20060801","20060801") = 0
	 * </pre>
	 *
	 * @param sDate1
	 *        날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param sDate2
	 *        날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @return 일 수 차이.
	 * @throws Exception
	 * @throws IllegalArgumentException
	 *         날짜 포맷이 정해진 바와 다를 경우. 입력 값이 <code>null</code>인 경우.
	 */
	public static long getDaysDiff(final String sDate1, final String sDate2) throws Exception {
		String dateStr1 = validChkDate(sDate1);
		String dateStr2 = validChkDate(sDate2);

		if (!checkDate(sDate1) || !checkDate(sDate2)) {
			throw new IllegalArgumentException("Invalid date format: args[0]=" + sDate1 + " args[1]=" + sDate2);
		}
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate startDate = LocalDate.parse(dateStr1, dateFormat);
		LocalDate endDate = LocalDate.parse(dateStr2, dateFormat);

		return ChronoUnit.DAYS.between(startDate, endDate);
	}

	/**
	 * <p>
	 * yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 유효한 날짜인지 검사.
	 * </p>
	 *
	 * <pre>
	 * DateUtil.checkDate("1999-02-35") = false
	 * DateUtil.checkDate("2000-13-31") = false
	 * DateUtil.checkDate("2006-11-31") = false
	 * DateUtil.checkDate("2006-2-28")  = false
	 * DateUtil.checkDate("2006-2-8")   = false
	 * DateUtil.checkDate("20060228")   = true
	 * DateUtil.checkDate("2006-02-28") = true
	 * </pre>
	 *
	 * @param sDate
	 *        날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @return 유효한 날짜인지 여부
	 */
	public static boolean checkDate(final String sDate) throws Exception{
		String dateStr = "";
		// trim()한 문자열이 10자리일 때 숫자만 남김
		if (sDate.trim().length() == LENGTH_FORMATTED_DATE) {
			dateStr = sDate.trim().replaceAll("[^0-9]+", "");
		} else {
			dateStr = sDate;
		}

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate locDate = LocalDate.parse(dateStr, dateFormat);
		return dateFormat.format(locDate).equals(dateStr);
	}

	/**
	 * <p>
	 * 입력한 년, 월, 일이 유효한지 검사.
	 * </p>
	 *
	 * @param year
	 *        연도
	 * @param month
	 *        월
	 * @param day
	 *        일
	 * @return 유효한 날짜인지 여부
	 * @throws Exception
	 */
	public static boolean checkDate(final String year, final String month, final String day) throws Exception {
		return checkDate(org.apache.commons.lang3.StringUtils.leftPad(year, LENGTH_YEAR, '0') + org.apache.commons.lang3.StringUtils.leftPad(month, LENGTH_MONTH, '0')
				+ org.apache.commons.lang3.StringUtils.leftPad(day, LENGTH_DAY, '0'));
	}

	/**
	 * <pre>
	 * Description :  날짜형태의 String의 날짜 포맷 및 TimeZone을 변경해 주는 메서드.
	 * </pre>
	 *
	 * @param strSource
	 *        바꿀 날짜 String
	 * @param fromDateFormat
	 *        기존의 날짜 형태
	 * @param toDateFormat
	 *        원하는 날짜 형태
	 * @param strTimeZone
	 *        변경할 TimeZone(""이면 변경 안함) <br>
	 *        •EST - -05:00 •HST - -10:00 •MST - -07:00 <br>
	 *        •ACT - Australia/Darwin •AET - Australia/Sydney <br>
	 *        •AGT - America/Argentina/Buenos_Aires <br>
	 *        •ART - Africa/Cairo •AST - America/Anchorage <br>
	 *        •BET - America/Sao_Paulo •BST - Asia/Dhaka <br>
	 *        •CAT - Africa/Harare •CNT - America/St_Johns <br>
	 *        •CST - America/Chicago •CTT - Asia/Shanghai <br>
	 *        •EAT - Africa/Addis_Ababa •ECT - Europe/Paris <br>
	 *        •IET - America/Indiana/Indianapolis •IST - Asia/Kolkata<br>
	 *        •JST - Asia/Tokyo •MIT - Pacific/Apia •NET - Asia/Yerevan<br>
	 *        •NST - Pacific/Auckland •PLT - Asia/Karachi <br>
	 *        •PNT - America/Phoenix •PRT - America/Puerto_Rico <br>
	 *        •PST - America/Los_Angeles •SST - Pacific/Guadalcanal <br>
	 *        •VST - Asia/Ho_Chi_Minh
	 * @return 소스 String의 날짜 포맷을 변경한 String
	 */
	public static String convertDate(final String strSource, final String fromDateFormat, final String toDateFormat, final String strTimeZone) {

		DateTimeFormatter dateFormat = null;
		LocalDateTime srcDate = null;
		String strFromDateFormat = "";
		String strToDateFormat = "";

		// strSource가 null 또는 공백이면
		if (org.apache.commons.lang3.StringUtils.defaultString(strSource).trim().equals("")) {
			return "";
		}

		// Input Format이 없을 경우 기본 Format 설정
		if (org.apache.commons.lang3.StringUtils.defaultString(fromDateFormat).trim().equals("")) {
			strFromDateFormat = "yyyyMMddHHmmss"; // default값
		} else {
			strFromDateFormat = fromDateFormat;
		}

		// Output Format이 없을 경우 기본 Format 설정
		if (org.apache.commons.lang3.StringUtils.defaultString(toDateFormat).trim().equals("")) {
			strToDateFormat = "yyyy-MM-dd HH:mm:ss"; // default값
		} else {
			strToDateFormat = toDateFormat;
		}

		try {
			// Input 처리
			dateFormat = DateTimeFormatter.ofPattern(strFromDateFormat, Locale.getDefault());
			srcDate = LocalDateTime.parse(strSource, dateFormat);

			// Output 처리
			dateFormat = DateTimeFormatter.ofPattern(strToDateFormat, Locale.getDefault());
			if (!org.apache.commons.lang3.StringUtils.defaultString(strTimeZone).trim().equals("")) {
				ZonedDateTime zoneDt = srcDate.atZone(ZoneId.of(ZoneId.SHORT_IDS.get(strTimeZone)));
				return dateFormat.format(zoneDt.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime());
			}
			return dateFormat.format(srcDate);
		} catch (RuntimeException exception) {
			throw new RuntimeException(exception);
		}
	}

	/**
	 * <pre>
	 * Description :  yyyyMMdd 형식의 날짜문자열을 원하는 캐릭터(ch)로 쪼개 돌려준다.
	 * </pre>
	 *
	 * <pre>
	 * ex) 20030405, ch(.) -> 2003.04.05
	 * ex) 200304, ch(.) -> 2003.04
	 * ex) 20040101,ch(/) --> 2004/01/01 로 리턴
	 * </pre>
	 *
	 * @param sDate
	 *        yyyyMMdd 형식의 날짜문자열
	 * @param ch
	 *        구분자
	 * @return 변환된 문자열
	 */
	public static String formatDate(final String sDate, final char ch) {
		String dateStr = sDate;

		// trim()한 문자열이 10자리일 때 숫자만 남김
		if (sDate.trim().length() == LENGTH_FORMATTED_DATE || sDate.trim().length() == LENGTH_YEAR + LENGTH_DATE_SEPERATOR + LENGTH_MONTH) {
			dateStr = sDate.trim().replaceAll("[^0-9]+", "");
		}

		// 일이 00일 경우 제거
		dateStr = dateStr.replaceAll("^([0-9]{6})00", "$1");
		// 월이 00일 경우 제거
		dateStr = dateStr.replaceAll("^([0-9]{4})00[0-9]*", "$1");
		// 년이 0000일 경우 제거
		dateStr = dateStr.replaceAll("^0000[0-9]*", "");

		if (dateStr.length() == LENGTH_RAW_DATE) {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
			LocalDate locDate = LocalDate.parse(dateStr, dateFormat);
			dateFormat = DateTimeFormatter.ofPattern("yyyy" + ch + "MM" + ch + "dd");
			return dateFormat.format(locDate);
		} else if (dateStr.length() == LENGTH_YEAR + LENGTH_MONTH) {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMM");
			YearMonth yearMonth = YearMonth.parse(dateStr, dateFormat);
			dateFormat = DateTimeFormatter.ofPattern("yyyy" + ch + "MM");
			return dateFormat.format(yearMonth);
		} else if (dateStr.length() == LENGTH_YEAR) {
			return dateStr;
		} else {
			return "";
		}
	}

	/**
	 * HH24MISS 형식의 시간문자열을 원하는 캐릭터(ch)로 쪼개 돌려준다. <br>
	 *
	 * <pre>
	 *     ex) 151241, ch(/) -> 15/12/31
	 * </pre>
	 *
	 * @param sTime
	 *        HH24MISS 형식의 시간문자열
	 * @param ch
	 *        구분자
	 * @return 변환된 문자열
	 */
	public static String formatTime(final String sTime, final char ch) {
		String timeStr = sTime;

		// trim()한 문자열이 8자리 또는 5자리일 때 숫자만 남김
		if (sTime.trim().length() == LENGTH_FORMATTED_TIME || sTime.trim().length() == LENGTH_HOUR + LENGTH_TIME_SEPERATOR + LENGTH_MINUTE) {
			timeStr = sTime.trim().replaceAll("[^0-9]+", "");
		}

		if (timeStr.length() == LENGTH_RAW_TIME) {
			// ex) 235959
			DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmmss");
			LocalTime locTime = LocalTime.parse(timeStr, timeFormat);
			timeFormat = DateTimeFormatter.ofPattern("HH" + ch + "mm" + ch + "ss");
			return timeFormat.format(locTime);
		} else if (timeStr.length() == LENGTH_HOUR + LENGTH_MINUTE) {
			// ex) 2359
			DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
			LocalTime locTime = LocalTime.parse(timeStr, timeFormat);
			timeFormat = DateTimeFormatter.ofPattern("HH" + ch + "mm");
			return timeFormat.format(locTime);
		} else {
			return "";
		}
	}

	/**
	 * <pre>
	 * Description :  연도를 입력 받아 해당 연도 2월의 말일(일수)를 문자열로 반환한다.
	 * </pre>
	 *
	 * @param year
	 *        해당 연도
	 * @return 해당 연도 2월의 말일(일수)
	 */
	public static String leapYear(final int year) {
		LocalDate init = LocalDate.of(year, 2, 1);
		return Integer.toString(init.lengthOfMonth());
	}

	/**
	 * <p>
	 * 입력받은 연도가 윤년인지 아닌지 검사한다.
	 * </p>
	 *
	 * <pre>
	 * DateUtil.isLeapYear(2004) = false
	 * DateUtil.isLeapYear(2005) = true
	 * DateUtil.isLeapYear(2006) = true
	 * </pre>
	 *
	 * @param year
	 *        연도
	 * @return 윤년 여부
	 */
	public static boolean isLeapYear(final int year) {
		return Year.isLeap(year);
	}

	/**
	 * 현재(한국기준) 날짜정보를 얻는다. <BR>
	 * 표기법은 yyyy-mm-dd <BR>
	 *
	 * @return String yyyymmdd형태의 현재 한국시간. <BR>
	 */
	public static String getToday() {
		return getCurrentDate("");
	}

	/**
	 * <pre>
	 * Description : 현재(한국기준) 날짜정보를 얻는다. <br>
	 * 표기법은 yyyy-mm-dd
	 * </pre>
	 *
	 * @auther : User
	 * @since : 2016. 2. 29.
	 * @param dateType
	 *        yyyy-mm-dd
	 * @return String yyyymmdd형태의 현재 한국시간.
	 */
	public static String getCurrentDate(final String dateType) {
		LocalDateTime locDt = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
		return convertDate(dateFormat.format(locDt), timeFormat.format(locDt), dateType);
	}

	/**
	 * <pre>
	 * Description : 오늘 날짜를 ISO-8601 타입으로 가져온다.
	 * (FullCalendar plug-in 사용)
	 * </pre>
	 *
	 * @return "yyyy-MM-dd'T'HH:mm:ss"
	 */
	public static String getNowUTC() {
		LocalDateTime locDt = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
		return dateFormat.format(locDt);
	}

	/**
	 * <pre>
	 * Description :  날짜형태의 String의 날짜 포맷만을 변경해 주는 메서드.
	 * </pre>
	 *
	 * @param sDate
	 *        날짜
	 * @param sTime
	 *        시간
	 * @param sFormatStr
	 *        포멧 스트링 문자열
	 * @return 지정한 날짜/시간을 지정한 포맷으로 출력
	 * @See Letter Date or Time Component Presentation Examples G Era designator
	 *      Text AD<br>
	 *      y Year Year 1996; 96<br>
	 *      M Month in year Month July; Jul; 07<br>
	 *      w Week in year Number 27<br>
	 *      W Week in month Number 2<br>
	 *      D Day in year Number 189<br>
	 *      d Day in month Number 10<br>
	 *      F Day of week in month Number 2<br>
	 *      E Day in week Text Tuesday; Tue<br>
	 *      a Am/pm marker Text PM<br>
	 *      H Hour in day (0-23) Number 0<br>
	 *      k Hour in day (1-24) Number 24<br>
	 *      K Hour in am/pm (0-11) Number 0<br>
	 *      h Hour in am/pm (1-12) Number 12<br>
	 *      m Minute in hour Number 30<br>
	 *      s Second in minute Number 55<br>
	 *      S Millisecond Number 978<br>
	 *      z Time zone General time zone Pacific Standard Time; PST<br>
	 *      Z Time zone RFC 822 time zone -0800<br>
	 *
	 *      Date and Time Pattern Result<br>
	 *      "yyyy.MM.dd G 'at' HH:mm:ss z" 2001.07.04 AD at 12:08:56 PDT<br>
	 *      "EEE, MMM d, ''yy" Wed, Jul 4, '01<br>
	 *      "h:mm a" 12:08 PM<br>
	 *      "hh 'o''clock' a, zzzz" 12 o'clock PM, Pacific Daylight Time<br>
	 *      "K:mm a, z" 0:08 PM, PDT<br>
	 *      "yyyyy.MMMMM.dd GGG hh:mm aaa" 02001.July.04 AD 12:08 PM<br>
	 *      "EEE, d MMM yyyy HH:mm:ss Z" Wed, 4 Jul 2001 12:08:56 -0700<br>
	 *      "yyMMddHHmmssZ" 010704120856-0700<br>
	 */
	public static String convertDate(final String sDate, final String sTime, final String sFormatStr) {
		String dateStr = validChkDate(sDate);
		String timeStr = validChkTime(sTime);

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		LocalDateTime locDt = LocalDateTime.parse(dateStr + timeStr, dateFormat);

		if (org.apache.commons.lang3.StringUtils.isNotEmpty(sFormatStr)) {
			dateFormat = DateTimeFormatter.ofPattern(sFormatStr);
		}
		dateFormat.withLocale(Locale.ENGLISH);
		return dateFormat.format(locDt);
	}

	/**
	 * <pre>
	 * Description :  입력받은 일자 사이의 임의의 일자를 반환.
	 * </pre>
	 *
	 * @param sDate1
	 *        시작일자
	 * @param sDate2
	 *        종료일자
	 * @return 임의일자
	 */
	public static String getRandomDate(final String sDate1, final String sDate2) {
		String dateStr1 = validChkDate(sDate1);
		String dateStr2 = validChkDate(sDate2);

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		long beginTime = Timestamp.valueOf(convertDate(dateStr1, "0000", "yyyy-MM-dd HH:mm:ss")).getTime();
		long endTime = Timestamp.valueOf(convertDate(dateStr2, "2359", "yyyy-MM-dd HH:mm:ss")).getTime();
		long diff = beginTime + (long) (new SecureRandom().nextDouble() * (endTime - beginTime + 1));
		Instant instant = Instant.ofEpochMilli(diff);
		LocalDateTime randomDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

		// 랜덤문자열를 리턴 return randomDate;
		return dateFormat.format(randomDate);
	}

	/**
	 * <pre>
	 * Description :  입력받은 요일의 영문명을 국문명의 요일로 반환.
	 * </pre>
	 *
	 * @param sWeek
	 *        영문 요일명
	 * @return 국문 요일명
	 */
	public static String convertWeek(final String sWeek) {
		String retStr = null;

		if (sWeek.equals("SUN")) {
			retStr = "일요일";
		} else if (sWeek.equals("MON")) {
			retStr = "월요일";
		} else if (sWeek.equals("TUE")) {
			retStr = "화요일";
		} else if (sWeek.equals("WED")) {
			retStr = "수요일";
		} else if (sWeek.equals("THR")) {
			retStr = "목요일";
		} else if (sWeek.equals("FRI")) {
			retStr = "금요일";
		} else if (sWeek.equals("SAT")) {
			retStr = "토요일";
		}

		return retStr;
	}

	/**
	 * <pre>
	 * Description :  입력일자의 유효 여부를 확인.
	 * </pre>
	 *
	 * @param sDate
	 *        일자
	 * @return 유효 여부
	 */
	public static boolean validDate(final String sDate) throws Exception {
		String dateStr = sDate;

		// trim()한 문자열이 10자리일 때 숫자만 남김
		if (sDate.trim().length() == LENGTH_FORMATTED_DATE) {
			dateStr = sDate.trim().replaceAll("[^0-9]+", "");
		}

		LocalDate locDate = null;
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		locDate = LocalDate.parse(dateStr, dateFormat);
		return dateFormat.format(locDate).equals(dateStr);
	}

	/**
	 * <pre>
	 * Description :  입력일자, 요일의 유효 여부를 확인.
	 * </pre>
	 *
	 * @param sDate
	 *        일자 yyyyMMdd
	 * @param sWeek
	 *        요일 (DAY_OF_WEEK) From 1(Monday) to 7(Sunday)
	 * @return 유효 여부
	 * @throws Exception
	 */
	public static boolean validDate(final String sDate, final int sWeek) throws Exception {
		String dateStr = validChkDate(sDate);

		boolean ret = false;

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate locDate = LocalDate.parse(dateStr, dateFormat);
		int weekDay = locDate.getDayOfWeek().getValue();

		if (validDate(sDate)) {
			if (sWeek == weekDay) {
				ret = true;
			}
		}

		return ret;
	}

	/**
	 * <pre>
	 * Description :  입력시간의 유효 여부를 확인.
	 * </pre>
	 *
	 * @param sTime
	 *        입력시간 HHmm
	 * @return 유효 여부
	 */
	public static boolean validTime(final String sTime) throws Exception{
		String timeStr = validChkTime(sTime);
		@SuppressWarnings("unused")
		LocalDateTime locTime = null;
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HHmm");
		locTime = LocalDateTime.parse(timeStr, dateFormat);
		return true;
	}

	/**
	 * <pre>
	 * Description :  입력된 일자에 연, 월, 일을 가감한 날짜의 요일을 반환.
	 * </pre>
	 *
	 * @param sDate
	 *        날짜
	 * @param year
	 *        연
	 * @param month
	 *        월
	 * @param day
	 *        일
	 * @return 계산된 일자의 요일(DAY_OF_WEEK)
	 */
	public static String addYMDtoWeek(final String sDate, final int year, final int month, final int day) {
		String dateStr = validChkDate(sDate);

		LocalDate locDate = null;

		dateStr = addYearMonthDay(dateStr, year, month, day);

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

		locDate = LocalDate.parse(dateStr, dateFormat);

		dateFormat = DateTimeFormatter.ofPattern("E");
		dateFormat = dateFormat.withLocale(Locale.ENGLISH);

		return dateFormat.format(locDate);
	}

	/**
	 * <pre>
	 * Description : 입력된 일자에 연, 월, 일, 시간, 분을 가감한 날짜, 시간을 포멧스트링 형식으로 반환..
	 * </pre>
	 *
	 * @auther : User
	 * @since : 2016. 3. 4.
	 * @param sDate
	 *        날짜
	 * @param sTime
	 *        시간
	 * @param addMap
	 *        가감할 연, 월, 일, 시간, 분 <br>
	 *        Key : year, month, day, hour, minute(String) / Value : Integer
	 *
	 * @param formatStr
	 *        포멧스트링
	 * @return String 원하는 포멧으로 계산된 값 Return
	 */
	public static String addYMDtoDayTime(final String sDate, final String sTime, final Map<String, Integer> addMap, final String formatStr) {
		String dateStr = validChkDate(sDate);
		String timeStr = validChkTime(sTime);

		dateStr = addYearMonthDay(dateStr, addMap.get("year"), addMap.get("month"), addMap.get("day"));
		dateStr = convertDate(dateStr, timeStr, "yyyyMMddHHmm");

		LocalDateTime locDt = null;
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		dateFormat.withLocale(Locale.ENGLISH);
		try {
			locDt = LocalDateTime.parse(dateStr, dateFormat);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid date format: " + dateStr);
		}
		if (addMap.get("hour") != 0) {
			locDt.plusHours(addMap.get("hour"));
		}
		if (addMap.get("minute") != 0) {
			locDt.plusMinutes(addMap.get("minute"));
		}
		return dateFormat.format(locDt);
	}

	/**
	 * <pre>
	 * Description :  입력된 일자를 int 형으로 반환.
	 * </pre>
	 *
	 * @param sDate
	 *        일자
	 * @return int(일자)
	 */
	public static int datetoInt(final String sDate) {
		return Integer.parseInt(convertDate(sDate, "0000", "yyyyMMdd"));
	}

	/**
	 * <pre>
	 * Description :  입력된 시간을 int 형으로 반환.
	 * </pre>
	 *
	 * @param sTime
	 *        시간
	 * @return int(시간)
	 */
	public static int timetoInt(final String sTime) {
		return Integer.parseInt(convertDate("00010101", sTime, "HHmm"));
	}

	/**
	 * <pre>
	 * Description :  입력된 일자 문자열을 확인하고 8자리로 리턴.
	 * </pre>
	 *
	 * @param sDate
	 *        일자 문자열
	 * @return yyyyMMdd
	 */
	public static String validChkDate(final String sDate) {
		String convStrDate = sDate;

		// trim()한 문자열이 10자리일 때 숫자만 남김
		if (sDate.trim().length() == LENGTH_FORMATTED_DATE) {
			convStrDate = sDate.trim().replaceAll("[^0-9]+", "");
		}

		// Parse() 가능한 문자열인지 체크 후 맞으면 yyyyMMdd 문자열 리턴
		try {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
			LocalDate locDate = LocalDate.parse(convStrDate, dateFormat);
			if (dateFormat.format(locDate).equals(convStrDate)) {
				return dateFormat.format(locDate);
			} else {
				throw new IllegalArgumentException("Invalid date format: " + sDate);
			}
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid date format: " + sDate);
		}
	}

	/**
	 * <pre>
	 * Description :  입력된 시간 문자열을 확인하고 4자리로 리턴.
	 * </pre>
	 *
	 * @param timeStr
	 *        시간 문자열
	 * @return String HHmm
	 */
	public static String validChkTime(final String timeStr) {
		String convTimeStr = timeStr;

		// 5자리일 경우 숫자만 남김
		if (convTimeStr.length() == LENGTH_HOUR + LENGTH_TIME_SEPERATOR + LENGTH_MINUTE) {
			/* convTimeStr = StringUtil.remove(convTimeStr,':'); */
			convTimeStr = convTimeStr.replaceAll("[^0-9]+", "");
		}

		// Parse() 가능한 문자열인지 체크 후 맞으면 yyyyMMdd 문자열 리턴
		try {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HHmm");
			LocalTime locTime = LocalTime.parse(convTimeStr, dateFormat);
			if (dateFormat.format(locTime).equals(convTimeStr)) {
				return dateFormat.format(locTime);
			} else {
				throw new IllegalArgumentException("Invalid date format: " + timeStr);
			}
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid date format: " + timeStr);
		}
	}

	/**
	 * <pre>
	 * Description : 오늘 날짜를 INPUT 형태로 출력.
	 * </pre>
	 *
	 * @auther : User
	 * @since : 2016. 3. 2.
	 * @param dateType
	 *        날짜 표현 형식 yyyy-MM-dd
	 * @return String 오늘 날짜를 INPUT 형태로 출력
	 */
	public static String getNowDate(final String dateType) {

		LocalDate locDate = LocalDate.now();
		DateTimeFormatter dateFormat = null;
		try {
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(dateType)) {
				dateFormat = DateTimeFormatter.ofPattern(dateType, Locale.getDefault());
			} else {
				dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault());
			}

			return dateFormat.format(locDate);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid date type: " + dateType);
		}

	}

	/**
	 * 2013.02.20 날짜 시간 포멧 컨버팅 추가 (유효성 검사 없이 자리수를 가지고만 체크).
	 *
	 * @param ymd
	 *        yyyyMMdd, yyyyMM
	 * @param time
	 *        HHmmss, HHmm
	 * @param ch
	 *        날짜 구분자 /, -, .
	 * @return String
	 */
	public static String getDateFormat(final String ymd, final String time, final char ch) {

		String dateStr = "";
		String timeStr = "";

		// Date 문자열 변환
		if (ymd != null && !ymd.equals("")) {
			if (Character.isWhitespace(ch)) {
				dateStr = formatDate(ymd, '-');
			} else {
				dateStr = formatDate(ymd, ch);
			}
		}

		// Time 문자열 변환
		if (time != null && !time.equals("")) {
			timeStr = formatTime(time, ':');
		}

		// 문자열 결합
		if (dateStr.length() == LENGTH_FORMATTED_DATE) {
			if (org.apache.commons.lang3.StringUtils.isEmpty(timeStr)) {
				return dateStr;
			} else {
				return dateStr + " " + timeStr;
			}
		} else if (dateStr.length() == LENGTH_YEAR + LENGTH_DATE_SEPERATOR + LENGTH_MONTH) {
			return dateStr;
		} else {
			return "";
		}
	}

	/**
	 * 2013.02.20 날짜 시간 포멧 컨버팅 추가(유효성 검사 없이 자리수를 가지고만 체크).
	 *
	 * @param ymd
	 *        YYYYMMDD
	 * @return String Korean Date
	 */
	public static String getDateFormatKor(final String ymd) {
		String repDt = "";

		//년월일 설정
		if (ymd != null && !ymd.equals("")) {
			if (ymd.length() == LENGTH_RAW_DATE) {
				// ex) 20130225
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
				LocalDate locDate = LocalDate.parse(ymd, dateFormat);
				dateFormat = DateTimeFormatter.ofPattern("yyyy 년 MM 월 dd 일");
				repDt = dateFormat.format(locDate);

			} else if (ymd.length() == LENGTH_YEAR + LENGTH_MONTH) {
				// ex) 201302
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMM");
				LocalDate locDate = LocalDate.parse(ymd, dateFormat);
				dateFormat = DateTimeFormatter.ofPattern("yyyy 년 MM 월");
				repDt = dateFormat.format(locDate);
			} else {
				// null 리턴 방지
				if (org.apache.commons.lang3.StringUtils.isNotEmpty(ymd)) {
					repDt = ymd;
				}
			}
		}
		return repDt;
	}

	/**
	 * <pre>
	 * Description :  입력된 일자에 RSS 날짜 형식 반환.
	 * </pre>
	 *
	 * @param sDate
	 *        날짜 ( ex - 20120730 )
	 * @return RSS 날짜 형식 ( ex - Mon, 30 Jul 2012 00:00:00 +0900)
	 */
	public static String rssDate(final String sDate) {
		String dateStr = validChkDate(sDate);
		DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime locDate = LocalDateTime.parse(dateStr + "000000", inputFormat);
		ZonedDateTime zoneDate = ZonedDateTime.of(locDate, ZoneId.systemDefault());
		return DateTimeFormatter.RFC_1123_DATE_TIME.format(zoneDate);
	}

	/**
	 * <pre>
	 * Description :  응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능.
	 * </pre>
	 *
	 * @param
	 * @return Timestamp 값 또는 null
	 * @see
	 */
	public static String getTimeStamp() {
		String rtnStr = null;

		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		try {
			LocalDateTime locDt = LocalDateTime.now();
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
			rtnStr = dtFormat.format(locDt);
		} catch (RuntimeException e) {
			LOGGER.debug(e);
			//e.printStackTrace();
		}

		return rtnStr;
	}

	public static String getTimeStamp2() {
		String rtnStr = null;

		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		try {
			LocalDateTime locDt = LocalDateTime.now();
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			rtnStr = dtFormat.format(locDt);
		} catch (RuntimeException e) {
			LOGGER.debug(e);
			//e.printStackTrace();
		}

		return rtnStr;
	}

	/**
	 * <pre>
	 * Description : 특정 달 첫번째 날의 요일을 반환.
	 * </pre>
	 *
	 * @param sDate 특정일자
	 * @param pDatePattern yyyyMMdd
	 * @return 0-6(Sunday-Saturday)
	 */
	public static int getFirstWeekdayOfMonth(final String sDate, final String pDatePattern) {

		String dateStr = validChkDate(sDate);
		LocalDate locDate = null;
		DateTimeFormatter dateFormat = null;
		if (org.apache.commons.lang3.StringUtils.isEmpty(pDatePattern)) {
			dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		} else {
			dateFormat = DateTimeFormatter.ofPattern(pDatePattern);
		}
		locDate = LocalDate.parse(dateStr, dateFormat);
		return locDate.with(TemporalAdjusters.firstDayOfMonth()).getDayOfWeek().getValue();

	}

	/**
	 * <pre>
	 * Description : 특정 달 마지막 날을 반환.
	 * </pre>
	 *
	 * @param sDate 특정일자
	 * @param pDatePattern yyyyMMdd
	 * @return 0-31
	 */
	public static int getLastDayOfMonth(final String sDate, final String pDatePattern) {

		String dateStr = validChkDate(sDate);
		LocalDate locDate = null;
		DateTimeFormatter dateFormat = null;
		if (org.apache.commons.lang3.StringUtils.isEmpty(pDatePattern)) {
			dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		} else {
			dateFormat = DateTimeFormatter.ofPattern(pDatePattern);
		}
		locDate = LocalDate.parse(dateStr, dateFormat);
		return locDate.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

	}

	/**
	 * <pre>
	 * Description : 특정일을 더하거나 뺀 값을 원하는 포맷으로 리턴.
	 * </pre>
	 *
	 * @param sDate 특정일
	 * @param inDatePattern 입력패턴
	 * @param outDatePattern 출력패턴
	 * @param year 추가 년
	 * @param month 추가 월
	 * @param day 추가 일
	 * @return 출력패턴에 맞는 문자열
	 */
	public static String adjustYearMonthDay(final String sDate, final String inDatePattern, final String outDatePattern, final int year,
											final int month, final int day) {

		String dateStr = validChkDate(sDate);
		LocalDate locDate = null;
		DateTimeFormatter dateFormat = null;

		if (org.apache.commons.lang3.StringUtils.isEmpty(inDatePattern)) {
			dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		} else {
			dateFormat = DateTimeFormatter.ofPattern(inDatePattern);
		}

		locDate = LocalDate.parse(dateStr, dateFormat);

		if (year != 0) {
			locDate = locDate.plusYears(year);
		}
		if (month != 0) {
			locDate = locDate.plusMonths(month);
		}
		if (day != 0) {
			locDate = locDate.plusDays(day);
		}

		if (org.apache.commons.lang3.StringUtils.isEmpty(outDatePattern)) {
			dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		} else {
			dateFormat = DateTimeFormatter.ofPattern(outDatePattern);
		}

		return dateFormat.format(locDate);
	}

	/**
	 * <pre>
	 * Description : 사원번호를 받아 입사년도를 YYYY에 맞게 출력.
	 * </pre>
	 *
	 * @param pEmpNo 사원번호
	 * @return YYYY 입사연도
	 */
	public static String convertEmpNoToEntYear(final String pEmpNo) {
		// yy에서 yyyy으로 변환 시 사용될 날짜를 현재날짜에서 조정
		final int adjustBaseYear = 50;

		if (org.apache.commons.lang3.StringUtils.isNotEmpty(pEmpNo) && pEmpNo.matches("[0-9]{8}")) {
			DateTimeFormatter dateFormat =
					new DateTimeFormatterBuilder().appendValueReduced(ChronoField.YEAR, 2, 2, Year.now().getValue() - adjustBaseYear).toFormatter();
			Year entYear = Year.parse(pEmpNo.substring(0, 2), dateFormat);

			return entYear.toString();
		} else {
			return pEmpNo;
		}
	}

	/**
	 * <pre>
	 * Description : 올해연도 출력.
	 * </pre>
	 *
	 * @return yyyy
	 */
	public static int getThisYear() {
		// yy에서 yyyy으로 변환 시 사용될 날짜를 현재날짜에서 조정
		return Year.now().getValue();
	}

	/**
	 * 현재달
	 * @return
	 */
	public static String getThisMonth() {

		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		String monthString = "";
		if(month < 10){
			monthString = "0"+month;
		} else {
			monthString = String.valueOf(month);
		}

		return monthString;
	}

	/**
	 * <pre>
	 * Description : 주의 첫번째 날 구하기.
	 * </pre>
	 *
	 * @param pYear 년도
	 * @param pWeek 주차
	 * @return yyyyMMdd
	 */
	public static String getFirstDayOfWeek(final String pYear, final String pWeek) {
		return LocalDate
				.parse(pYear + "-" + pWeek,
						new DateTimeFormatterBuilder().appendPattern("YYYY-w").parseDefaulting(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1)
								.toFormatter()).format(DateTimeFormatter.ofPattern("yyyyMMdd")).toString();

	}

	/**
	 * <pre>
	 * Description : 년도, 주단위 덧셈뺄센.
	 * </pre>
	 *
	 * @param currentYear 현재년
	 * @param currentWeek 현재주
	 * @param outPattern 출력 패턴
	 * @param adjustYear 연도 가감
	 * @param adjustWeek 주 가감
	 * @return YYYYww
	 */
	public static String
	adjustWeek(final int currentYear, final int currentWeek, final String outPattern, final int adjustYear, final int adjustWeek) {

		LocalDate weekDate =
				LocalDate.parse(String.valueOf(currentYear) + "-" + String.valueOf(currentWeek),
						new DateTimeFormatterBuilder().appendPattern("YYYY-w").parseDefaulting(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1)
								.toFormatter());

		if (adjustYear != 0) {
			weekDate = weekDate.plusYears(adjustYear);
		}
		if (adjustWeek != 0) {
			weekDate = weekDate.plusWeeks(adjustWeek);
		}
		if (StringUtils.isNotEmpty(outPattern)) {
			return weekDate.format(DateTimeFormatter.ofPattern(outPattern)).toString();
		} else {
			return weekDate.format(DateTimeFormatter.ofPattern("YYYYww")).toString();
		}
	}

	public static String lastMon( final String  year, final String mon) {
		LocalDate date = LocalDate.of(Integer.parseInt(year),Integer.parseInt(mon), 01);
		LocalDate endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
		String lastMon = endOfMonth+"";

		return lastMon.replaceAll("-", ".");
	}
}
