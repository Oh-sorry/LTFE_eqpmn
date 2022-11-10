package com.mtpms.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nhncorp.lucy.security.xss.XssPreventer;

public class stringUtil {

	/**
	 * 빈 문자열 <code>""</code>.
	 */
	public static final String EMPTY = "";

	/** LOG . */
	protected static Log log = LogFactory.getLog(stringUtil.class);

	/**
	 * <p>
	 * Padding을 할 수 있는 최대 수치
	 * </p>
	 */
	// private static final int PAD_LIMIT = 8192;
	/**
	 * <p>
	 * An array of <code>String</code>s used for padding.
	 * </p>
	 * <p>
	 * Used for efficient space padding. The length of each String expands as needed.
	 * </p>
	 */
	/*
	 * private static final String[] PADDING = new
	 * String[Character.MAX_VALUE]; static { // space
	 * padding is most common, start with 64 chars
	 * PADDING[32] =
	 * "                                                                "
	 * ; }
	 */

	/**
	 * 문자열이 지정한 길이를 초과했을때 지정한길이에다가 해당 문자열을 붙여주는 메서드.
	 *
	 * @param source
	 *        원본 문자열 배열
	 * @param output
	 *        더할문자열
	 * @param slength
	 *        지정길이
	 * @return 지정길이로 잘라서 더할분자열 합친 문자열
	 */
	public static String cutString(String source, final String output, final int slength) {
		String returnVal = null;

		if (source != null) {
			if (source.length() > slength) {
				source = removeHtml(source);
				returnVal = source.substring(0, slength) + output;
			} else {
				returnVal = source;
			}
		}
		return returnVal;
	}

	/**
	 * 문자열이 지정한 길이를 초과했을때 해당 문자열을 삭제하는 메서드
	 *
	 * @param source
	 *        원본 문자열 배열
	 * @param slength
	 *        지정길이
	 * @return 지정길이로 잘라서 더할분자열 합친 문자열
	 */
	public static String cutString(final String source, final int slength) {
		String result = null;
		if (source != null) {
			if (source.length() > slength) {
				result = source.substring(0, slength);
			} else {
				result = source;
			}
		}
		return result;
	}

	/**
	 * <p>
	 * String이 비었거나("") 혹은 null 인지 검증한다.
	 * </p>
	 *
	 * <pre>
	 *  StringUtil.isEmpty(null)      = true
	 *  StringUtil.isEmpty(&quot;&quot;)        = true
	 *  StringUtil.isEmpty(&quot; &quot;)       = false
	 *  StringUtil.isEmpty(&quot;bob&quot;)     = false
	 *  StringUtil.isEmpty(&quot;  bob  &quot;) = false
	 * </pre>
	 *
	 * @param str
	 *        - 체크 대상 스트링오브젝트이며 null을 허용함
	 * @return <code>true</code> - 입력받은 String 이 빈 문자열
	 *         또는 null인 경우
	 */
	public static boolean isEmpty(final String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * <p>
	 * 기준 문자열에 포함된 모든 대상 문자(char)를 제거한다.
	 * </p>
	 *
	 * <pre>
	 * StringUtil.remove(null, *)       = null
	 * StringUtil.remove(&quot;&quot;, *)         = &quot;&quot;
	 * StringUtil.remove(&quot;queued&quot;, 'u') = &quot;qeed&quot;
	 * StringUtil.remove(&quot;queued&quot;, 'z') = &quot;queued&quot;
	 * </pre>
	 *
	 * @param str
	 *        입력받는 기준 문자열
	 * @param remove
	 *        입력받는 문자열에서 제거할 대상 문자열
	 * @return 제거대상 문자열이 제거된 입력문자열. 입력문자열이 null인 경우
	 *         출력문자열은 null
	 */
	public static String remove(final String str, final char remove) {
		if (isEmpty(str) || str.indexOf(remove) == -1) {
			return str;
		}
		char[] chars = str.toCharArray();
		int pos = 0;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != remove) {
				chars[pos++] = chars[i];
			}
		}
		return new String(chars, 0, pos);
	}

	/**
	 * <p>
	 * 문자열 내부의 콤마 character(,)를 모두 제거한다.
	 * </p>
	 *
	 * <pre>
	 * StringUtil.removeCommaChar(null)       = null
	 * StringUtil.removeCommaChar(&quot;&quot;)         = &quot;&quot;
	 * StringUtil.removeCommaChar(&quot;asdfg,qweqe&quot;) = &quot;asdfgqweqe&quot;
	 * </pre>
	 *
	 * @param str
	 *        입력받는 기준 문자열
	 * @return " , "가 제거된 입력문자열 입력문자열이 null인 경우 출력문자열은
	 *         null
	 */
	public static String removeCommaChar(final String str) {
		return remove(str, ',');
	}

	/**
	 * <p>
	 * 문자열 내부의 마이너스 character(-)를 모두 제거한다.
	 * </p>
	 *
	 * <pre>
	 * StringUtil.removeMinusChar(null)       = null
	 * StringUtil.removeMinusChar(&quot;&quot;)         = &quot;&quot;
	 * StringUtil.removeMinusChar(&quot;a-sdfg-qweqe&quot;) = &quot;asdfgqweqe&quot;
	 * </pre>
	 *
	 * @param str
	 *        입력받는 기준 문자열
	 * @return " - "가 제거된 입력문자열 입력문자열이 null인 경우 출력문자열은
	 *         null
	 */
	public static String removeMinusChar(final String str) {
		return remove(str, '-');
	}

	/**
	 * 원본 문자열의 포함된 특정 문자열을 새로운 문자열로 변환하는 메서드
	 *
	 * @param source
	 *        원본 문자열
	 * @param subject
	 *        원본 문자열에 포함된 특정 문자열
	 * @param object
	 *        변환할 문자열
	 * @return sb.toString() 새로운 문자열로 변환된 문자열
	 */
	public static String replace(final String source, final String subject, final String object) {
		StringBuffer rtnStr = new StringBuffer();
		String preStr = "";
		String nextStr = source;
		String srcStr = source;

		while (srcStr.indexOf(subject) >= 0) {
			preStr = srcStr.substring(0, srcStr.indexOf(subject));
			nextStr = srcStr.substring(srcStr.indexOf(subject) + subject.length(), srcStr.length());
			srcStr = nextStr;
			rtnStr.append(preStr).append(object);
		}
		rtnStr.append(nextStr);
		return rtnStr.toString();
	}

	/**
	 * 원본 문자열의 포함된 특정 문자열 첫번째 한개만 새로운 문자열로 변환하는 메서드
	 *
	 * @param source
	 *        원본 문자열
	 * @param subject
	 *        원본 문자열에 포함된 특정 문자열
	 * @param object
	 *        변환할 문자열
	 * @return sb.toString() 새로운 문자열로 변환된 문자열 / source
	 *         특정문자열이 없는 경우 원본 문자열
	 */
	public static String replaceOnce(final String source, final String subject, final String object) {
		StringBuffer rtnStr = new StringBuffer();
		String preStr = "";
		String nextStr = source;
		if (source.indexOf(subject) >= 0) {
			preStr = source.substring(0, source.indexOf(subject));
			nextStr = source.substring(source.indexOf(subject) + subject.length(), source.length());
			rtnStr.append(preStr).append(object).append(nextStr);
			return rtnStr.toString();
		} else {
			return source;
		}
	}

	/**
	 * <code>subject</code>에 포함된 각각의 문자를 object로 변환한다.
	 *
	 * @param source
	 *        원본 문자열
	 * @param subject
	 *        원본 문자열에 포함된 특정 문자열
	 * @param object
	 *        변환할 문자열
	 * @return sb.toString() 새로운 문자열로 변환된 문자열
	 */
	public static String replaceChar(final String source, final String subject, final String object) {
		StringBuffer rtnStr = new StringBuffer();
		String preStr = "";
		String nextStr = source;
		String srcStr = source;

		char chA;

		for (int i = 0; i < subject.length(); i++) {
			chA = subject.charAt(i);

			if (srcStr.indexOf(chA) >= 0) {
				preStr = srcStr.substring(0, srcStr.indexOf(chA));
				nextStr = srcStr.substring(srcStr.indexOf(chA) + 1, srcStr.length());
				srcStr = rtnStr.append(preStr).append(object).append(nextStr).toString();
			}
		}
		return srcStr;
	}

	/**
	 * <p>
	 * <code>str</code> 중 <code>searchStr</code>의 시작(index) 위치를 반환.
	 * </p>
	 * <p>
	 * 입력값 중 <code>null</code>이 있을 경우 <code>-1</code>을 반환.
	 * </p>
	 *
	 * <pre>
	 * StringUtil.indexOf(null, *)          = -1
	 * StringUtil.indexOf(*, null)          = -1
	 * StringUtil.indexOf(&quot;&quot;, &quot;&quot;)           = 0
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;a&quot;)  = 0
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;)  = 2
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;ab&quot;) = 1
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;&quot;)   = 0
	 * </pre>
	 *
	 * @param str
	 *        검색 문자열
	 * @param searchStr
	 *        검색 대상문자열
	 * @return 검색 문자열 중 검색 대상문자열이 있는 시작 위치 검색대상 문자열이
	 *         없거나 null인 경우 -1
	 */
	public static int indexOf(final String str, final String searchStr) {
		if (str == null || searchStr == null) {
			return -1;
		}
		return str.indexOf(searchStr);
	}

	/**
	 * <p>
	 * 오라클의 decode 함수와 동일한 기능을 가진 메서드이다. <code>sourStr</code>과 <code>compareStr</code>의 값이 같으면 <code>returStr</code>을
	 * 반환하며, 다르면 <code>defaultStr</code>을 반환한다.
	 * </p>
	 *
	 * <pre>
	 * StringUtil.decode(null, null, &quot;foo&quot;, &quot;bar&quot;)= &quot;foo&quot;
	 * StringUtil.decode(&quot;&quot;, null, &quot;foo&quot;, &quot;bar&quot;) = &quot;bar&quot;
	 * StringUtil.decode(null, &quot;&quot;, &quot;foo&quot;, &quot;bar&quot;) = &quot;bar&quot;
	 * StringUtil.decode(&quot;하이&quot;, &quot;하이&quot;, null, &quot;bar&quot;) = null
	 * StringUtil.decode(&quot;하이&quot;, &quot;하이  &quot;, &quot;foo&quot;, null) = null
	 * StringUtil.decode(&quot;하이&quot;, &quot;하이&quot;, &quot;foo&quot;, &quot;bar&quot;) = &quot;foo&quot;
	 * StringUtil.decode(&quot;하이&quot;, &quot;하이  &quot;, &quot;foo&quot;, &quot;bar&quot;) = &quot;bar&quot;
	 * </pre>
	 *
	 * @param sourceStr
	 *        비교할 문자열
	 * @param compareStr
	 *        비교 대상 문자열
	 * @param returnStr
	 *        sourceStr와 compareStr의 값이 같을 때 반환할 문자열
	 * @param defaultStr
	 *        sourceStr와 compareStr의 값이 다를 때 반환할 문자열
	 * @return sourceStr과 compareStr의 값이 동일(equal)할 때
	 *         returnStr을 반환하며, <br/>
	 *         다르면 defaultStr을 반환한다.
	 */
	public static String decode(final String sourceStr, final String compareStr, final String returnStr, final String defaultStr) {
		if (sourceStr == null && compareStr == null) {
			return returnStr;
		}

		if (sourceStr == null && compareStr != null) {
			return defaultStr;
		}

		if (sourceStr.trim().equals(compareStr)) {
			return returnStr;
		}

		return defaultStr;
	}

	/**
	 * <p>
	 * 오라클의 decode 함수와 동일한 기능을 가진 메서드이다. <code>sourStr</code>과 <code>compareStr</code>의 값이 같으면 <code>returStr</code>을
	 * 반환하며, 다르면 <code>sourceStr</code>을 반환한다.
	 * </p>
	 *
	 * <pre>
	 * StringUtil.decode(null, null, &quot;foo&quot;) = &quot;foo&quot;
	 * StringUtil.decode(&quot;&quot;, null, &quot;foo&quot;) = &quot;&quot;
	 * StringUtil.decode(null, &quot;&quot;, &quot;foo&quot;) = null
	 * StringUtil.decode(&quot;하이&quot;, &quot;하이&quot;, &quot;foo&quot;) = &quot;foo&quot;
	 * StringUtil.decode(&quot;하이&quot;, &quot;하이 &quot;, &quot;foo&quot;) = &quot;하이&quot;
	 * StringUtil.decode(&quot;하이&quot;, &quot;바이&quot;, &quot;foo&quot;) = &quot;하이&quot;
	 * </pre>
	 *
	 * @param sourceStr
	 *        비교할 문자열
	 * @param compareStr
	 *        비교 대상 문자열
	 * @param returnStr
	 *        sourceStr와 compareStr의 값이 같을 때 반환할 문자열
	 * @return sourceStr과 compareStr의 값이 동일(equal)할 때
	 *         returnStr을 반환하며, <br/>
	 *         다르면 sourceStr을 반환한다.
	 */
	public static String decode(final String sourceStr, final String compareStr, final String returnStr) {
		return decode(sourceStr, compareStr, returnStr, sourceStr);
	}

	/**
	 * 객체가 null인지 확인하고 null인 경우 "" 로 바꾸는 메서드
	 *
	 * @param object
	 *        원본 객체
	 * @return resultVal 문자열
	 */
	public static String isNullToString(final Object object) {
		String string = "";
		if (object != null) {
			string = object.toString().trim();
		}
		return string;
	}

	/**
	 * <pre>
	 * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
	 * &#064;param src null값일 가능성이 있는 String 값.
	 * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
	 * </pre>
	 */
	public static String nullConvert(final Object src) {
		// if (src != null &&
		// src.getClass().getName().equals("java.math.BigDecimal"))
		// {
		if (src != null && src instanceof java.math.BigDecimal) {
			return ((BigDecimal) src).toString();
		}

		if (src == null || src.equals("null")) {
			return "";
		} else {
			return ((String) src).trim();
		}
	}

	/**
	 * <pre>
	 * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
	 * &#064;param src null값일 가능성이 있는 String 값.
	 * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
	 * </pre>
	 */
	public static String nullConvert(final String src) {

		if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
			return "";
		} else {
			return src.trim();
		}
	}

	/**
	 * <pre>
	 * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
	 * &#064;param src null값일 가능성이 있는 String 값.
	 * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
	 * </pre>
	 * @throws Exception
	 */
	public static String nullString(String src) {

		if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
			return "";
		} else {
			src = checkHtmlView(src);
			return src.trim();
		}
	}

	/**
	 * <pre>
	 * 인자로 받은 String이 null일 경우 &quot;0&quot;로 리턴한다.
	 * &#064;param src null값일 가능성이 있는 String 값.
	 * &#064;return 만약 String이 null 값일 경우 &quot;0&quot;로 바꾼 String 값.
	 * </pre>
	 */
	public static int zeroConvert(final Object src) {

		if (src == null || src.equals("null")) {
			return 0;
		} else {
			return Integer.parseInt(((String) src).trim());
		}
	}

	/**
	 * <pre>
	 * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
	 * &#064;param src null값일 가능성이 있는 String 값.
	 * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
	 * </pre>
	 */
	public static int zeroConvert(final String src) {

		if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
			return 0;
		} else {
			return Integer.parseInt(src.trim());
		}
	}

	/**
	 * <p>
	 * 문자열에서 {@link Character#isWhitespace(char)}에 정의된 모든 공백문자를 제거한다.
	 * </p>
	 *
	 * <pre>
	 * StringUtil.removeWhitespace(null)         = null
	 * StringUtil.removeWhitespace(&quot;&quot;)           = &quot;&quot;
	 * StringUtil.removeWhitespace(&quot;abc&quot;)        = &quot;abc&quot;
	 * StringUtil.removeWhitespace(&quot;   ab  c  &quot;) = &quot;abc&quot;
	 * </pre>
	 *
	 * @param str
	 *        the String to delete whitespace from, may
	 *        be null
	 * @return the String without whitespaces, <code>null</code> if null String input
	 */
	public static String removeWhitespace(final String str) {
		if (isEmpty(str)) {
			return str;
		}
		int sz = str.length();
		char[] chs = new char[sz];
		int count = 0;
		for (int i = 0; i < sz; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				chs[count++] = str.charAt(i);
			}
		}
		if (count == sz) {
			return str;
		}
		return new String(chs, 0, count);
	}

	public static String unscript(final String data) {
		if (data == null || data.trim().equals("")) {
			return "";
		}

		String ret = data;
		/* ret = ret.replaceAll("&" , "&amp;"); */
		ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
		ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");
		ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
		ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");
		ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
		ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");
		ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
		ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
		ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
		ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");
		/*
		 * ret = ret.replaceAll(">" , "&gt;");
		 * ret = ret.replaceAll("<" , "&lt;");
		 * ret = ret.replaceAll("\'" , "&#39;");
		 * ret = ret.replaceAll("\"" , "&#34;");
		 * ret = ret.replaceAll("/" , "&#47;");
		 * ret = ret.replaceAll("%" , "&#37;");
		 * ret = ret.replaceAll(" " , "&#10;");
		 * ret = ret.replaceAll("\r" , "&#10;");
		 * ret = ret.replaceAll("\n" , "&#10;");
		 * ret = ret.replaceAll("\\(" , "&#40;");
		 * ret = ret.replaceAll("\\)" , "&#41;");
		 * ret = ret.replaceAll("#" , "&#35;");
		 */

		return ret;
	}

	/**
	 * Html 코드가 들어간 문서를 표시할때 태그에 손상없이 보이기 위한 메서드
	 *
	 * @param strString
	 * @return HTML 태그를 치환한 문자열
	 */
	public static String checkHtmlView(final String strString) {
		String strNew = "";

		StringBuffer strTxt = new StringBuffer("");

		char chrBuff;
		int len = strString.length();

		for (int i = 0; i < len; i++) {
			chrBuff = strString.charAt(i);

			switch (chrBuff) {
				case '<':
					strTxt.append("&lt;");
					break;
				case '>':
					strTxt.append("&gt;");
					break;
				case '"':
					strTxt.append("&quot;");
					break;
				case '\'':
					strTxt.append("&apos;");
					break;
				case 10:
					strTxt.append("<br>");
					break;
				case ' ':
					strTxt.append("&nbsp;");
					break;
				 case '&' :
				    strTxt.append("&amp;");
				    break;
				default:
					strTxt.append(chrBuff);
					break;
			}
		}

		strNew = strTxt.toString();


		return strNew;
	}

	/**
	 * 문치환된 문자열에서 다시 원본 메시지를 복구
	 *
	 */
	public static String unEscape(final String strString) throws Exception{

		String strNew = XssPreventer.unescape(strString);
		strNew = strNew.replaceAll("&lt;", "<");
		strNew = strNew.replaceAll("&gt;", ">");
		strNew = strNew.replaceAll("&amp;", "&");
		strNew = strNew.replaceAll("&quot;", "\"");
		strNew = strNew.replaceAll("&middot;", "·");
		strNew = strNew.replaceAll("&lsquo;", "‘");
		strNew = strNew.replaceAll("&rsquo;", "’");

		return strNew;
	}

	/**
	 * 문자열을 지정한 분리자에 의해 배열로 리턴하는 메서드.
	 *
	 * @param source
	 *        원본 문자열
	 * @param separator
	 *        분리자
	 * @return result 분리자로 나뉘어진 문자열 배열
	 */
	public static String[] split(final String source, final String separator) throws NullPointerException {
		String[] returnVal = null;
		int cnt = 1;

		int index = source.indexOf(separator);
		int index0 = 0;
		while (index >= 0) {
			cnt++;
			index = source.indexOf(separator, index + 1);
		}
		returnVal = new String[cnt];
		cnt = 0;
		index = source.indexOf(separator);
		while (index >= 0) {
			returnVal[cnt] = source.substring(index0, index);
			index0 = index + 1;
			index = source.indexOf(separator, index + 1);
			cnt++;
		}
		returnVal[cnt] = source.substring(index0);
		return returnVal;
	}

	/**
	 * <p>
	 * {@link String#toLowerCase()}를 이용하여 소문자로 변환한다.
	 * </p>
	 * <p>
	 * A <code>null</code> input String returns <code>null</code>.
	 * </p>
	 *
	 * <pre>
	 * StringUtil.lowerCase(null)  = null
	 * StringUtil.lowerCase(&quot;&quot;)    = &quot;&quot;
	 * StringUtil.lowerCase(&quot;aBc&quot;) = &quot;abc&quot;
	 * </pre>
	 *
	 * @param str
	 *        the String to lower case, may be null
	 * @return the lower cased String, <code>null</code> if null String input
	 */
	public static String lowerCase(final String str) {
		if (str == null) {
			return null;
		}
		return str.toLowerCase();
	}

	/**
	 * <p>
	 * {@link String#toUpperCase()}를 이용하여 대문자로 변환한다.
	 * </p>
	 * <p>
	 * A <code>null</code> input String returns <code>null</code>.
	 * </p>
	 *
	 * <pre>
	 * StringUtil.upperCase(null)  = null
	 * StringUtil.upperCase(&quot;&quot;)    = &quot;&quot;
	 * StringUtil.upperCase(&quot;aBc&quot;) = &quot;ABC&quot;
	 * </pre>
	 *
	 * @param str
	 *        the String to upper case, may be null
	 * @return the upper cased String, <code>null</code> if null String input
	 */
	public static String upperCase(final String str) {
		if (str == null) {
			return null;
		}
		return str.toUpperCase();
	}

	/**
	 * <p>
	 * 입력된 String의 앞쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.
	 * </p>
	 * <p>
	 * A <code>null</code> input String returns <code>null</code>. An empty string ("") input returns the empty string.
	 * </p>
	 * <p>
	 * If the stripChars String is <code>null</code>, whitespace is stripped as defined by
	 * {@link Character#isWhitespace(char)}.
	 * </p>
	 *
	 * <pre>
	 * StringUtil.stripStart(null, *)          = null
	 * StringUtil.stripStart(&quot;&quot;, *)            = &quot;&quot;
	 * StringUtil.stripStart(&quot;abc&quot;, &quot;&quot;)        = &quot;abc&quot;
	 * StringUtil.stripStart(&quot;abc&quot;, null)      = &quot;abc&quot;
	 * StringUtil.stripStart(&quot;  abc&quot;, null)    = &quot;abc&quot;
	 * StringUtil.stripStart(&quot;abc  &quot;, null)    = &quot;abc  &quot;
	 * StringUtil.stripStart(&quot; abc &quot;, null)    = &quot;abc &quot;
	 * StringUtil.stripStart(&quot;yxabc  &quot;, &quot;xyz&quot;) = &quot;abc  &quot;
	 * </pre>
	 *
	 * @param str
	 *        the String to remove characters from, may
	 *        be null
	 * @param stripChars
	 *        the characters to remove, null treated as
	 *        whitespace
	 * @return the stripped String, <code>null</code> if null String input
	 */
	public static String stripStart(final String str, final String stripChars) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		int start = 0;
		if (stripChars == null) {
			while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
				start++;
			}
		} else if (stripChars.length() == 0) {
			return str;
		} else {
			while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
				start++;
			}
		}
		return str.substring(start);
	}

	/**
	 * <p>
	 * 입력된 String의 뒤쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.
	 * </p>
	 * <p>
	 * A <code>null</code> input String returns <code>null</code>. An empty string ("") input returns the empty string.
	 * </p>
	 * <p>
	 * If the stripChars String is <code>null</code>, whitespace is stripped as defined by
	 * {@link Character#isWhitespace(char)}.
	 * </p>
	 *
	 * <pre>
	 * StringUtil.stripEnd(null, *)          = null
	 * StringUtil.stripEnd(&quot;&quot;, *)            = &quot;&quot;
	 * StringUtil.stripEnd(&quot;abc&quot;, &quot;&quot;)        = &quot;abc&quot;
	 * StringUtil.stripEnd(&quot;abc&quot;, null)      = &quot;abc&quot;
	 * StringUtil.stripEnd(&quot;  abc&quot;, null)    = &quot;  abc&quot;
	 * StringUtil.stripEnd(&quot;abc  &quot;, null)    = &quot;abc&quot;
	 * StringUtil.stripEnd(&quot; abc &quot;, null)    = &quot; abc&quot;
	 * StringUtil.stripEnd(&quot;  abcyx&quot;, &quot;xyz&quot;) = &quot;  abc&quot;
	 * </pre>
	 *
	 * @param str
	 *        the String to remove characters from, may
	 *        be null
	 * @param stripChars
	 *        the characters to remove, null treated as
	 *        whitespace
	 * @return the stripped String, <code>null</code> if null String input
	 */
	public static String stripEnd(final String str, final String stripChars) {
		int end;
		if (str == null || (end = str.length()) == 0) {
			return str;
		}

		if (stripChars == null) {
			while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
				end--;
			}
		} else if (stripChars.length() == 0) {
			return str;
		} else {
			while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
				end--;
			}
		}
		return str.substring(0, end);
	}

	/**
	 * <p>
	 * 입력된 String의 앞, 뒤에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.
	 * </p>
	 * <p>
	 * This is similar to {@link String#trim()} but allows the characters to be stripped to be controlled.
	 * </p>
	 * <p>
	 * A <code>null</code> input String returns <code>null</code>. An empty string ("") input returns the empty string.
	 * </p>
	 * <p>
	 * If the stripChars String is <code>null</code>, whitespace is stripped as defined by
	 * </p>
	 *
	 * <pre>
	 * StringUtil.strip(null, *)          = null
	 * StringUtil.strip(&quot;&quot;, *)            = &quot;&quot;
	 * StringUtil.strip(&quot;abc&quot;, null)      = &quot;abc&quot;
	 * StringUtil.strip(&quot;  abc&quot;, null)    = &quot;abc&quot;
	 * StringUtil.strip(&quot;abc  &quot;, null)    = &quot;abc&quot;
	 * StringUtil.strip(&quot; abc &quot;, null)    = &quot;abc&quot;
	 * StringUtil.strip(&quot;  abcyx&quot;, &quot;xyz&quot;) = &quot;  abc&quot;
	 * </pre>
	 *
	 * @param str
	 *        the String to remove characters from, may
	 *        be null
	 * @param stripChars
	 *        the characters to remove, null treated as
	 *        whitespace
	 * @return the stripped String, <code>null</code> if null String input
	 */
	public static String strip(final String str, final String stripChars) {

		if (isEmpty(str)) {
			return str;
		}

		String srcStr = str;

		srcStr = stripStart(srcStr, stripChars);

		return stripEnd(srcStr, stripChars);

	}

	/**
	 * 문자열을 지정한 분리자에 의해 지정된 길이의 배열로 리턴하는 메서드.
	 *
	 * @param source
	 *        원본 문자열
	 * @param separator
	 *        분리자
	 * @param arraylength
	 *        배열 길이
	 * @return 분리자로 나뉘어진 문자열 배열
	 */
	public static String[] split(final String source, final String separator, final int arraylength) throws NullPointerException {
		String[] returnVal = new String[arraylength];
		int cnt = 0;
		int index0 = 0;
		int index = source.indexOf(separator);
		while (index >= 0 && cnt < (arraylength - 1)) {
			returnVal[cnt] = source.substring(index0, index);
			index0 = index + 1;
			index = source.indexOf(separator, index + 1);
			cnt++;
		}
		returnVal[cnt] = source.substring(index0);
		if (cnt < (arraylength - 1)) {
			for (int i = cnt + 1; i < arraylength; i++) {
				returnVal[i] = "";
			}
		}
		return returnVal;
	}

	/**
	 * 문자열 A에서 Z사이의 랜덤 문자열을 구하는 기능을 제공 시작문자열과 종료문자열 사이의
	 * 랜덤 문자열을 구하는 기능
	 *
	 * @param startChr
	 *        - 첫 문자
	 * @param endChr
	 *        - 마지막문자
	 * @return 랜덤문자
	 */
	public static String getRandomStr(final char startChr, final char endChr) throws Exception{

		int randomInt;
		String randomStr = null;

		// 시작문자 및 종료문자를 아스키숫자로 변환한다.
		int startInt = Integer.valueOf(startChr);
		int endInt = Integer.valueOf(endChr);

		// 시작문자열이 종료문자열보가 클경우
		if (startInt > endInt) {

			throw new IllegalArgumentException("Start String: " + startChr + " End String: " + endChr);
		}


		// 랜덤 객체 생성
		SecureRandom rnd = new SecureRandom();

		do {

			// 시작문자 및 종료문자 중에서 랜덤 숫자를 발생시킨다.
			randomInt = rnd.nextInt(endInt + 1);

		} while (randomInt < startInt); // 입력받은 문자
		// 'A'(65)보다
		// 작으면 다시
		// 랜덤 숫자
		// 발생.

		// 랜덤 숫자를 문자로 변환 후 스트링으로 다시 변환
		randomStr = (char) randomInt + "";


		// 랜덤문자열를 리턴
		return randomStr;

	}

	/**
	 * 문자열을 다양한 문자셋(EUC-KR[KSC5601],UTF-8..)을 사용하여
	 * 인코딩하는 기능 역으로 디코딩하여 원래의 문자열을 복원하는 기능을 제공함 String
	 * temp = new
	 * String(문자열.getBytes("바꾸기전 인코딩"),"바꿀 인코딩");
	 * String temp = new
	 * String(문자열.getBytes("8859_1"),"KSC5601"); =>
	 * UTF-8 에서 EUC-KR
	 *
	 * @param srcString
	 *        - 문자열
	 * @param srcCharsetNm
	 *        - 원래 CharsetNm
	 * @param cnvrCharsetNm
	 *        - CharsetNm
	 * @return 인(디)코딩 문자열
	 */
	public static String getEncdDcd(final String srcString, final String srcCharsetNm, final String cnvrCharsetNm) {

		String rtnStr = null;

		if (srcString == null) {
			return null;
		}

		try {
			rtnStr = new String(srcString.getBytes(srcCharsetNm), cnvrCharsetNm);
		} catch (UnsupportedEncodingException e) {
			rtnStr = null;
		}

		return rtnStr;

	}

	/**
	 * 특수문자를 웹 브라우저에서 정상적으로 보이기 위해 특수문자를 처리('<' -> & lT)하는 기능이다
	 * @param 	srcString 		- '<'
	 * @return 	변환문자열('<' -> "&lt"
	 */
	public static String getSpclStrCnvr(final String srcString) throws Exception{

		String rtnStr = null;

		StringBuffer strTxt = new StringBuffer("");

		char chrBuff;
		int len = srcString.length();

		for (int i = 0; i < len; i++) {
			chrBuff = srcString.charAt(i);

			switch (chrBuff) {
				case '<':
					strTxt.append("&lt;");
					break;
				case '>':
					strTxt.append("&gt;");
					break;
				case '&':
					strTxt.append("&amp;");
					break;
				default:
					strTxt.append(chrBuff);
					break;
			}
		}

		rtnStr = strTxt.toString();


		return rtnStr;

	}

	/**
	 * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을
	 * 구하는 기능
	 *
	 * @return Timestamp 값
	 */

	public static String getTimeStamp() throws Exception{

		String rtnStr = null;

		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";


		SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);

		Timestamp ts = new Timestamp(System.currentTimeMillis());

		rtnStr = sdfCurrent.format(ts.getTime());


		return rtnStr;
	}

	/**
	 * 글자수 체크후 ... 붙이는 메서드
	 *
	 * @param str
	 * @param maxLen
	 * @return
	 */
	public static String trimTitle(final String str, final int maxLen) {
		byte[] bytes = str.getBytes();
		int len = bytes.length;
		boolean in2Byte = false;
		int in3Byte = 0;
		String ret = str;
		int maxLen1 = maxLen;
		if (maxLen1 != 0) {
			if (len > maxLen1) {
				maxLen1 -= 3;
				for (int i = 0; i < maxLen1; i++) {
					byte b = bytes[i];
					if (b < 0) {
						in2Byte = !in2Byte;
						in3Byte++;
					}

					if (i == (maxLen1 - 1)) {
						/*
						 * if (in2Byte) { ret = new
						 * String(bytes, 0, i); } else
						 * { ret = new String(bytes, 0,
						 * i+1); }
						 */
						int mod = in3Byte % 3;
						if (mod == 0) {
							ret = new String(bytes, 0, i + 1);
						} else if (mod == 1) {
							ret = new String(bytes, 0, i + 3);
						} else {
							ret = new String(bytes, 0, i + 2);
						}
					}
				}
				ret += "...";
			}
		}
		return ret;
	}

	public static String cutText(String text, final int length, final String suffix) throws Exception {
		StringBuffer sb = new StringBuffer();

		if (!text.isEmpty()) {
			text = removeHtml(text);
			text = unEscape(text);
			if (text.length() > length) {
				sb.append(text.substring(0, length)).append(suffix);
			} else {
				sb.append(text);
			}
		} else {
			sb.append(text);
		}
		return sb.toString();
	}

	public static String replaceBrTag(String text) {
		if (text != null && !text.equals("")) {
			text = text.replaceAll("\\n", "<br/>");
		} else {
			text = "";
		}
		return text.replaceAll("\\n", "<br/>");
	}

	public static String lpad(final int value, final int length, final String prefix) {
		try {
			StringBuilder sb = new StringBuilder();
			String castValue = value + "";

			for (int i = castValue.length(); i < length; i++) {
				sb.append(prefix);
			}
			sb.append(castValue);
			return sb.toString();
		} catch (RuntimeException e) {
			return "";
		}
	}

	public static String rpad(final int value, final int length, final String prefix) {
		try {
			StringBuilder sb = new StringBuilder();
			String castValue = value + "";
			sb.append(castValue);
			for (int i = castValue.length(); i < length; i++) {
				sb.append(prefix);
			}
			return sb.toString();
		} catch (RuntimeException e) {
			return "";
		}
	}

	public static String getFileLinkType1(final String atchFileText) throws Exception{
		StringBuffer sb = new StringBuffer();

		String[] atchFileTextArr = atchFileText.split(",");
		for (int i = 0; i < atchFileTextArr.length; i++) {
			if (atchFileTextArr[i] != null && !"".equals(atchFileTextArr[i])) {
				String atchPath = atchFileTextArr[i].trim();
				atchPath = URLDecoder.decode(atchPath, "UTF-8");
				atchPath = atchPath.replaceAll("http://cms.khnp.co.kr", "");
				sb.append("<a href=\"" + atchPath
						+ "\" target=\"_blank\" title=\"파일 다운로드\"><img src=\"/publish/img/ico/ico_file.png\" alt=\"첨부파일 있음\"></a>");
			}
		}
		return sb.toString();
	}

	public static String getClientIp(final HttpServletRequest request) {
		String clientIp = stringUtil.nullString(request.getHeader("X-Forwarded-For"));

		if (clientIp == null || "".equals(clientIp)) {
			clientIp = stringUtil.nullString(request.getHeader("Proxy-Client-IP"));
		}
		if (clientIp == null || "".equals(clientIp)) {
			clientIp = stringUtil.nullString(request.getHeader("WL-Proxy-Client-IP"));
		}
		if (clientIp == null || "".equals(clientIp)) {
			clientIp = stringUtil.nullString(request.getHeader("HTTP_CLIENT_IP"));
		}
		if (clientIp == null || "".equals(clientIp)) {
			clientIp = stringUtil.nullString(request.getHeader("HTTP_X_FORWARDED_FOR"));
		}
		if (clientIp == null || "".equals(clientIp)) {
			clientIp = request.getRemoteAddr();
		}

		return clientIp.trim();
	}

	/**
	 * <pre>
	 * 인자로 받은 String의 개행문자를 제거한다.
	 * &#064;param src &#47;r&#47;n값을 포함할 가능성이 있는  String 값.
	 * &#064;return 만약 String이 &#47;r&#47;n 를 포함한 값일 경우 &quot;&quot;로 바꾼 String 값.
	 * </pre>
	 */
	public static String removeCrLf(final String src) {

		if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
			return "";
		} else {
			return src.replaceAll("\r\n", "");
		}
	}

	public static String osCheck(final String userAgent) {
		String osType = "";
		if (userAgent.indexOf("windows 95") != -1) {
			osType = "Win95";
		} else if (userAgent.indexOf("ipod") != -1 || userAgent.indexOf("iphone") != -1) {
			osType = "iOS";
		} else if (userAgent.indexOf("android") != -1) {
			osType = "Android";
		} else if (userAgent.indexOf("blackberry") != -1) {
			osType = "BlackBerry";
		} else if (userAgent.indexOf("windows phone") != -1) {
			osType = "WinPhone";
		} else if (userAgent.indexOf("win98") != -1 || userAgent.indexOf("windows 98") != -1) {
			osType = "Win98";
		} else if (userAgent.indexOf("windows nt 6.3") != -1) {
			osType = "Win8";
		} else if (userAgent.indexOf("windows nt 6.1") != -1) {
			osType = "Win7";
		} else if (userAgent.indexOf("windows nt 6") != -1) {
			osType = "Vista";
		} else if (userAgent.indexOf("windows nt 5.2") != -1) {
			osType = "Win2003";
		} else if (userAgent.indexOf("windows nt 5.01") != -1) {
			osType = "Win2000";
		} else if (userAgent.indexOf("windows nt 5.1") != -1) {
			osType = "WinXP";
		} else if (userAgent.indexOf("windows nt 5") != -1) {
			osType = "Win2000";
		} else if (userAgent.indexOf("macintosh") != -1 || userAgent.indexOf("mac_power") != -1 || userAgent.indexOf("mac") != -1) {
			osType = "Mac";
		} else if (userAgent.indexOf("linux") != -1 || userAgent.indexOf("wget") != -1) {
			osType = "Linux";
		} else if (userAgent.indexOf("unix") != -1) {
			osType = "Unix";
		} else {
			osType = "Etc";
		}
		return osType;
	}

	public static String browserCheck(final String userAgent) {
		String browserType = "";
		if (userAgent.indexOf("opera") != -1) {
			browserType = "Opera";
		} else if (userAgent.indexOf("windows phone") != -1 && userAgent.indexOf("msie") != -1) {
			browserType = "IEMobile";
		} else if (userAgent.indexOf("blackberry") != -1) {
			browserType = "Safari";
		} else if (userAgent.indexOf("chrome") != -1) {
			browserType = "Chrome";
		} else if (userAgent.indexOf("firefox") != -1) {
			browserType = "Firefox";
		} else if (userAgent.indexOf("msie 7.0") != -1) {
			browserType = "MSIE7";
		} else if (userAgent.indexOf("msie 8.0") != -1) {
			browserType = "MSIE8";
		} else if (userAgent.indexOf("msie 9.0") != -1) {
			browserType = "MSIE9";
		} else if (userAgent.indexOf("msie 10.0") != -1) {
			browserType = "MSIE10";
		} else if (userAgent.indexOf("rv:11.0") != -1) {
			browserType = "MSIE11";
		} else if (userAgent.indexOf("msie 11.0") != -1) {
			browserType = "MSIE11";
		} else if (userAgent.indexOf("windows nt 6.3; wow64;") != -1) {
			browserType = "MSIE11";
		} else {
			browserType = "Etc";
		}
		return browserType;
	}

	public static String removeHtml(String content) {
		if (content != null && !content.equals("")) {
			content = content.replaceAll("<(/)?([a-zA-Z0-9\\.]*)(\\s[a-zA-Z0-9\\.]*=[^>]*)?(\\s)*(/)?>", "");
			content = content.replaceAll("&nbsp;", " ");
		} else {
			content = "";
		}
		return content;
	}

	public static String getContentImage(final String content) {
		String imgTag = null;
		if (content != null && !content.equals("")) {
			// 이미지 태그를 추출하기 위한 정규식.
			Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
			Matcher match = pattern.matcher(content);
			if (match.find()) { // 이미지 태그를 찾았다면,,
				imgTag = match.group(1); // 글 내용 중에 첫번째 이미지 태그를 뽑아옴.
			}
		} else {
			imgTag = "";
		}
		return imgTag;
	}

	/**
	 * 문자열에서 개인정보 패턴을 추출하여 '*' 로 치환한다. (ex. 750101-1234567 -> ******-*******)
	 *
	 * @param str String : 개인정보가 포함된 문자열
	 * @return '*'로 치환된 문자열
	 */
	public static String chkRegExp(final String str) {
		String rtrnStr = "";
		if (str != null && !str.equals("")) {
			rtrnStr = str;
			String regExp = "";
			String regExpPttrn =
					"([01][0-9]{5}[[:space:],~-]+[1-4][0-9]{6}|[2-9][0-9]{5}[[:space:],~-]+[1-2][0-9]{6})|"
							+ //주민등록번호
							"([a-zA-Z]{2}[-~.[:space:]][0-9]{7})|"
							+ //여권번호
							"[0-9]{2}[-~.[:space:]][0-9]{6}[-~.[:space:]][0-9]{2}|"
							+ //운전면허 등록번호
							"01[016789][-~.[:space:]][0-9]{3,4}[-~.[:space:]][0-9]{4}|"
							+ //핸드폰 번호
							"([01][0-9]{5}[[:space:]~-]+[1-8][0-9]{6}|[2-9][0-9]{5}[[:space:]~-]+[1256][0-9]{6})|"
							+ //외국인등록번호
							"[34569][0-9]{3}[-~.[:space:]][0-9]{4}[-~.[:space:]][0-9]{4}[-~.[:space:]][0-9]{4}|"
							+ //신용카드번호
							"[1257][-~.[:space:]][0-9]{10}|"
							+ //건강보험 등록번호
							"([0-9]{2}[-~.[:space:]][0-9]{2}[-~.[:space:]][0-9]{6}|"
							+ //계좌번호
							"[0-9]{3}[-~.[:space:]]([0-9]{5,6}[-~.[:space:]][0-9]{3}|[0-9]{6}[-~.[:space:]][0-9]{5}|[0-9]{2,3}[-~.[:space:]][0-9]{6}|[0-9]{2}[-~.[:space:]][0-9]{7}|"
							+ "[0-9]{2}[-~.[:space:]][0-9]{4,6}[-~.[:space:]][0-9]|[0-9]{5}[-~.[:space:]][0-9]{3}[-~.[:space:]][0-9]{2}|[0-9]{2}[-~.[:space:]][0-9]{5}[-~.[:space:]][0-9]{3}|"
							+ "[0-9]{4}[-~.[:space:]][0-9]{4}[-~.[:space:]][0-9]{3}|[0-9]{6}[-~.[:space:]][0-9]{2}[-~.[:space:]][0-9]{3}|[0-9]{2}[-~.[:space:]][0-9]{2}[-~.[:space:]][0-9]{7})|"
							+ "[0-9]{4}[-~.[:space:]]([0-9]{3}[-~.[:space:]][0-9]{6}|[0-9]{2}[-~.[:space:]][0-9]{6}[-~.[:space:]][0-9])|[0-9]{5}[-~.[:space:]][0-9]{2}[-~.[:space:]][0-9]{6}|"
							+ "[0-9]{6}[-~.[:space:]][0-9]{2}[-~.[:space:]][0-9]{5,6})";

			//정규식 패턴으로 등록
			Pattern pattern = Pattern.compile(regExpPttrn);

			//입력받은 문자열 중에 패턴에 등록된 정규식이 포함되어 있는지 검사
			Matcher matches = pattern.matcher(str);

			//정규식이 포함되어 있으면
			if (matches.find()) {
				regExp = matches.group();

				//정규식의 숫자를 '*'로 치환
				regExp = regExp.replaceAll("[\\d]", "*");

				//원문 전체 글에서 정규식을 치환된 정규식으로 치환
				rtrnStr = rtrnStr.replaceAll(regExpPttrn, regExp);
			}
		}
		return rtrnStr;
	}

	/**
	 * 이름의 특정 부분을 '*' 로 치환한다. (ex. 메시 -> 메*, 호날두 -> 호*두, 발로텔리 -> 발로*리, 아데바요르 -> 아***르)
	 *
	 * @param str String : 개인정보가 포함된 문자열
	 * @return '*'로 치환된 문자열
	 */
	public static String chkNmMask(final String str) {
		String rtrnStr = "";
		if (str != null && !str.equals("")) {
			int len = str.length();
			rtrnStr = str;

			if (len == 1) {
				rtrnStr = str;
			} else if (len == 2) {
				rtrnStr = replace(str, str.substring(1, 2), "*");
			} else if (len == 3) {
				rtrnStr = replace(str, str.substring(1, 2), "*");
			} else if (len == 4) {
				rtrnStr = replace(str, str.substring(2, 3), "*");
			} else {
				rtrnStr = replace(str, str.substring(1, len - 1), rept("*", len - 2));
			}
		}
		return rtrnStr;
	}

	/**
	 * 원하는 문자열(str)을 원하는 숫자(n)만큼 뿌린다. (예 : rept("a", 3); ==> "aaa")
	 *
	 * @param str String 반복할 문자열
	 * @param n int 반복 횟수
	 * @return 횟수만큼 찍힌 문자열
	 */
	public static String rept(final String str, final int n) {
		String rtrnStr = "";
		if (str != null && !str.equals("")) {
			for (int i = 0; i < n; i++) {
				rtrnStr += str;
			}
		}

		return rtrnStr;
	}

	/**
	 * 문자열에서 전화, 휴대전화, 이메일 형식을 추출하여 특정 부분 "*"로 치환
	 *
	 * @param str String : 문자열
	 * @return String regExp : '*'로 치환된 문자열
	 */
	public static String chkHpEmailMask(final String str) {
		String regExp = "";
		if (str != null && !str.equals("")) {
			regExp = str;

			//전화번호 혹은 휴대전화 번호(02-123-4566, 011-123-4567, 010-1234-5678, 0505-1234-5677 등의 형식)
			String regExpPttrn1 = "(\\d{2,4})[-~.](\\d{3,4})[-~.](\\d{4})";

			//이메일(adcapsule.co.kr 또는 naver.com 또는 kepco.co.kr 등의 형식)
			String regExpPttrn2 = "([_A-Za-z0-9-]+)(\\.[_A-Za-z0-9-]+)*@([a-zA-Z0-9]+)*(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

			//정규식 패턴으로 등록
			Pattern pattern1 = Pattern.compile(regExpPttrn1);
			Pattern pattern2 = Pattern.compile(regExpPttrn2);

			//입력받은 문자열 중에 패턴에 등록된 정규식이 포함되어 있는지 검사
			Matcher matches1 = pattern1.matcher(str);
			Matcher matches2 = pattern2.matcher(str);

			//정규식이 포함되어 있으면(휴대전화)
			if (matches1.find()) {
				regExp = matches1.group();

				//가운데 자리만 "*"로 치환
				regExp = regExp.replaceAll(matches1.group(2), rept("*", matches1.group(2).length()));
			}

			//정규식이 포함되어 있으면(이메일)
			if (matches2.find()) {
				regExp = matches2.group();
				String replaceStr = matches2.group(1) + isNullToString(matches2.group(2));
				if (replaceStr.length() > 3) {
					//이메일 아이디 끝 2자리만 마스킹처리
					regExp = replaceStr.substring(0, replaceStr.length() - 2) + regExp.replaceAll(replaceStr, rept("*", 2));
				}
			}
		}
		return regExp;
	}

	/**
	 * 이메일 아이디 끝 2자리 마스킹처리
	 *
	 * @param str String : 개인정보가 포함된 문자열
	 * @return '*'로 치환된 문자열
	 */
	public static String chkEmailPrevMask(final String str) {
		String rtrnStr = "";
		if (str != null && !str.equals("")) {
			rtrnStr = str;
			rtrnStr = str.substring(0, str.length() - 2) + "**";
		}
		return rtrnStr;
	}

	/**
	 * 끝자리 변수만큼 마스킹 처리
	 *
	 * @param str String : 개인정보가 포함된 문자열
	 * @return '*'로 치환된 문자열
	 */
	public static String endMask(final String str, final int num) {
		String rtrnStr = "";
		if (str != null && !str.equals("")) {
			rtrnStr = str;
			rtrnStr = str.substring(0, str.length() - num) + rept("*", num);
		}
		return rtrnStr;
	}

	public static String chkHpEmailCenterMask(final String str) {
		String rtrnStr = "";
		if (str != null && !str.equals("")) {
			int length = str.length();
			for (int i = 0; i < length; i++) {
				rtrnStr = rtrnStr + "*";
			}
		}
		return rtrnStr;
	}

	/**
	 * 문자열에서 휴대전화, 이메일 형식을 추출하여 특정 부분 "*"로 치환
	 *
	 * @param str String : 문자열
	 * @return String regExp : '*'로 치환된 문자열
	 */
	public static String chkRegExpTest(final String str) {

		String regExp = "";
		if (str != null && !str.equals("")) {
			regExp = str;

			//휴대전화 번호(011-123-4567 또는 010-1234-5678 등의 형식)
			String regExpPttrn1 = "(01\\d{1})[-~.](\\d{3,4})[-~.](\\d{4})";

			//이메일(adcapsule.co.kr 또는 naver.com 또는 kepco.co.kr 등의 형식)
			String regExpPttrn2 = "([_A-Za-z0-9-]+)(\\.[_A-Za-z0-9-]+)*@([a-zA-Z0-9]+)*(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

			//정규식 패턴으로 등록
			Pattern pattern1 = Pattern.compile(regExpPttrn1);
			Pattern pattern2 = Pattern.compile(regExpPttrn2);

			//입력받은 문자열 중에 패턴에 등록된 정규식이 포함되어 있는지 검사
			Matcher matches1 = pattern1.matcher(str);
			Matcher matches2 = pattern2.matcher(str);

			//정규식이 포함되어 있으면(휴대전화)
			if (matches1.find()) {
				regExp = matches1.group();

				//가운데 자리만 "*"로 치환
				regExp = regExp.replaceAll(matches1.group(2), rept("*", matches1.group(2).length()));
			}

			//정규식이 포함되어 있으면(이메일)
			if (matches2.find()) {
				regExp = matches2.group();
				String replaceStr = matches2.group(1) + isNullToString(matches2.group(2));

				if (replaceStr.length() > 3) {
					//이메일 아이디 중, 세번째 이후 글자부터 "*"로 치환(아이디가 세자리 미만일 경우 치환하지 않음)
					regExp = replaceStr.substring(0, 3) + regExp.replaceAll(replaceStr, rept("*", replaceStr.length() - 3));
				}
			}
		}

		return regExp;
	}

	//전체주소에서 동, 리, 가 변환
	/**
	 * 주소에서 읍, 면, 동, 가, 로를 *로 마스킹
	 *
	 * @param fulladdr String : 문자열
	 * @return String temp_full_addr : '*'로 치환된 문자열
	 */
	public static String chkAddrMask(final String fulladdr) {

		String temp_full_addr = "";
		String full_addr = fulladdr; //넘어온 주소값
		String[] arr_addr1 = full_addr.split(" ");
		int chg_cnt = 0; //처음 동, 리, 가 부분 변경하고 나면 나머지는 그냥 패스(아파트 동관련)

		if (full_addr != null && arr_addr1.length > 1) {

			for (int j = 0; j < arr_addr1.length; j++) {

				if (arr_addr1[j] != null && !"".equals(arr_addr1[j]) && arr_addr1[j].length() > 1) { //각각의 배열이 널이 아니고 문자길이가 2개 이상일때

					int pre_leng = arr_addr1[j].length() - 1; //마지막 문자길이 하나전 숫자
					int leng = arr_addr1[j].length(); //마지막 문자길이

					//각각의 배열중에 끝이 '동'이나 '읍'으로 끝나는거 추출
					if ("동".equals(arr_addr1[j].substring(pre_leng, leng)) || "읍".equals(arr_addr1[j].substring(pre_leng, leng))
							|| "가".equals(arr_addr1[j].substring(pre_leng, leng)) || "면".equals(arr_addr1[j].substring(pre_leng, leng))
							|| "로".equals(arr_addr1[j].substring(pre_leng, leng)) || "길".equals(arr_addr1[j].substring(pre_leng, leng))) {
						if (chg_cnt == 0) {
							//							if( "로".equals(arr_addr1[j].substring(pre_leng, leng))){
							//								temp_full_addr = temp_full_addr +" ****";
							//								chg_cnt = 1;
							//							}else
							if ("길".equals(arr_addr1[j].substring(pre_leng, leng))) {
								int ro_num1 = arr_addr1[j].indexOf("로");
								int ro_num2 = arr_addr1[j].lastIndexOf("로");
								int gil_num1 = arr_addr1[j].indexOf("길"); // 길자가 3자
								int gil_num2 = arr_addr1[j].lastIndexOf("길");

								//패턴처리를 해서 갯수가 2개 이상이면
								Pattern p = Pattern.compile("길");
								Matcher m = p.matcher(arr_addr1[j]);
								int m_cnt = 0;
								for (int m_i = 0; m.find(m_i); m_i = m.end()) {
									m_cnt++;
								}

								if (m_cnt > 2) {// 길자가 2자 이상
									for (int gil_cnt = 2; gil_cnt < m_cnt; gil_cnt++) {
										gil_num1 = gil_num1 + arr_addr1[j].substring(gil_num1, arr_addr1[j].length()).indexOf("길") + 1;
									}
								}

								if (ro_num1 > -1 && ro_num1 == ro_num2) {
									temp_full_addr = temp_full_addr + " ****" + arr_addr1[j].substring(ro_num1 + 1, arr_addr1[j].length());
									chg_cnt = 1;
								} else if (ro_num1 > -1 && ro_num1 < ro_num2) {
									temp_full_addr = temp_full_addr + " ****" + arr_addr1[j].substring(ro_num2 + 1, arr_addr1[j].length());
									chg_cnt = 1;
								} else if (ro_num1 == -1 && gil_num1 > 0 && gil_num1 < gil_num2) {
									temp_full_addr = temp_full_addr + " ****" + arr_addr1[j].substring(gil_num1 + 1, arr_addr1[j].length());
									chg_cnt = 1;
								}

							} else {
								temp_full_addr = temp_full_addr + " ****";
								chg_cnt = 1;
							}
						} else { // 처음 변환이 끝났으면 더이상 변환 안한다(아파트 동 관련)
							temp_full_addr = temp_full_addr + " " + arr_addr1[j];
						}
					} else {

						if (!"".equals(temp_full_addr)) {
							temp_full_addr = temp_full_addr + " " + arr_addr1[j];
						} else {
							temp_full_addr = temp_full_addr + arr_addr1[j];
						}

					} //if
				} else {

					if (!"".equals(temp_full_addr)) {
						temp_full_addr = temp_full_addr + " " + arr_addr1[j];
					} else {
						temp_full_addr = temp_full_addr + arr_addr1[j];
					}

				} //if
			} //for
		} else {
			temp_full_addr = fulladdr;
		}//if
		return temp_full_addr;
	} //end chkAddrMask()

	/**
	 * 클라이언트(Client)의 웹브라우저 종류를 조회하는 기능
	 *
	 * @param request HttpServletRequest Request객체
	 * @return String webKind 웹브라우저 종류
	 * @exception Exception
	 */
	public static String getClntWebKind(final HttpServletRequest request) throws Exception {

		String user_agent = request.getHeader("user-agent");

		// 웹브라우저 종류 조회
		String webKind = "";
		if (user_agent.toUpperCase().indexOf("GECKO") != -1) {
			if (user_agent.toUpperCase().indexOf("NESCAPE") != -1) {
				webKind = "Netscape (Gecko/Netscape)";
			} else if (user_agent.toUpperCase().indexOf("FIREFOX") != -1) {
				webKind = "Mozilla Firefox (Gecko/Firefox)";
			} else {
				webKind = "Mozilla (Gecko/Mozilla)";
			}
		} else if (user_agent.toUpperCase().indexOf("MSIE") != -1) {
			if (user_agent.toUpperCase().indexOf("OPERA") != -1) {
				webKind = "Opera (MSIE/Opera/Compatible)";
			} else {
				webKind = "Internet Explorer (MSIE/Compatible)";
			}
		} else if (user_agent.toUpperCase().indexOf("SAFARI") != -1) {
			if (user_agent.toUpperCase().indexOf("CHROME") != -1) {
				webKind = "Google Chrome";
			} else {
				webKind = "Safari";
			}
		} else if (user_agent.toUpperCase().indexOf("THUNDERBIRD") != -1) {
			webKind = "Thunderbird";
		} else {
			webKind = "Other Web Browsers";
		}
		return webKind;
	}

	/**
	 * 클라이언트(Client)의 웹브라우저 버전을 조회하는 기능
	 *
	 * @param request HttpServletRequest Request객체
	 * @return String webVer 웹브라우저 버전
	 * @exception Exception
	 */
	public static String getClntWebVer(final HttpServletRequest request) throws Exception {

		String user_agent = request.getHeader("user-agent");

		// 웹브라우저 버전 조회
		String webVer = "";
		String[] arr = { "MSIE", "OPERA", "NETSCAPE", "FIREFOX", "SAFARI" };
		for (String element : arr) {
			int s_loc = user_agent.toUpperCase().indexOf(element);
			if (s_loc != -1) {
				int f_loc = s_loc + element.length();
				webVer = user_agent.toUpperCase().substring(f_loc, f_loc + 5);
				webVer = webVer.replaceAll("/", "").replaceAll(";", "").replaceAll("^", "").replaceAll(",", "").replaceAll("//.", "");
			}
		}
		return webVer;
	}




	/**
	 *
	 * url 메뉴코드 가져오기
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getMenuCd(final String url) throws Exception {
		Map<String, String> infoMap = new HashMap<String, String>();
		String regExpPttrn1 = "/([a-z]{2})/([a-zA-Z0-9]+)/([a-zA-Z0-9]+)/([a-zA-Z0-9\\_]+\\.?)";
		Pattern ptn = Pattern.compile(regExpPttrn1);
		Matcher matcher = ptn.matcher(url);

		String regExpPttrn2 = "/([a-z]+)/([a-zA-Z0-9]+)/([a-zA-Z0-9\\_]+\\.?)";
		Pattern ptn2 = Pattern.compile(regExpPttrn2);
		Matcher matcher2 = ptn2.matcher(url);

		String regExpPttrn3 = "/([a-z]{2})/([a-zA-Z0-9]+)/([a-zA-Z0-9]+)/([a-zA-Z0-9]+)/([a-zA-Z0-9\\_]+\\.?)";
		Pattern ptn3 = Pattern.compile(regExpPttrn3);
		Matcher matcher3 = ptn3.matcher(url);

		 if(matcher3.find()) {
			infoMap.put("depth0", matcher3.group(1));
			infoMap.put("depth1", matcher3.group(2));
			infoMap.put("depth2", matcher3.group(3));
			infoMap.put("depth3", matcher3.group(4));
			if(!matcher3.group(5).contains(".")){
				infoMap.put("depth4", matcher3.group(5));
			}
		}else if(matcher.find()) {
			infoMap.put("depth0", matcher.group(1));
			infoMap.put("depth1", matcher.group(2));
			infoMap.put("depth2", matcher.group(3));
			if(!matcher.group(4).contains(".")){
				infoMap.put("depth3", matcher.group(4));
			}
		} else if(matcher2.find()){
			infoMap.put("depth0", matcher2.group(1));
			infoMap.put("depth1", matcher2.group(2));
			if(!matcher2.group(3).contains(".")){
				infoMap.put("depth2", matcher2.group(3));
			}
		}
		return infoMap;
	}

	/**
	 *
	 * url 메뉴코드 가져오기
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getMicroMenuCd(final String url) throws Exception {
		Map<String, String> infoMap = new HashMap<String, String>();
		String regExpPttrn1 = "/([a-z]+)/([a-zA-Z0-9]+)/([a-zA-Z0-9]+)/([a-zA-Z0-9]+\\.?)";
		Pattern ptn = Pattern.compile(regExpPttrn1);
		Matcher matcher = ptn.matcher(url);

		String regExpPttrn2 = "/([a-z]+)/([a-zA-Z0-9]+)/([a-zA-Z0-9]+\\.?)";
		Pattern ptn2 = Pattern.compile(regExpPttrn2);
		Matcher matcher2 = ptn2.matcher(url);


		if(matcher.find()) {
			infoMap.put("depth0", matcher.group(1));
			infoMap.put("depth1", matcher.group(2));
			infoMap.put("depth2", matcher.group(3));
			infoMap.put("depth3", matcher.group(4));
		}else if(matcher2.find()){
			infoMap.put("depth0", matcher2.group(1));
			infoMap.put("depth1", matcher2.group(2));
			infoMap.put("depth2", matcher2.group(3));
		}
		return infoMap;
	}

	public static String replaceFilePath(String path) {
		String str ="";
		str = path;
		if(!str.equals("")){
			str = str.replaceAll("/", "");
			str = str.replaceAll("\\\\", "");
			str = str.replaceAll(".", "");
			str = str.replaceAll("&", "");
		}

		return str;
	}

	public static String getRandomNum(int num) {
		String randomNum = "";

		String numStr = "1";
		String plusNumStr = "1";

		for(int i = 0; i < num; i++){
			numStr += "0";

			if(i != num-1){
				plusNumStr += "0";
			}
		}

		Random random = new Random();
		int result = random.nextInt(Integer.parseInt(numStr))+Integer.parseInt(plusNumStr);

		if(result > Integer.parseInt(numStr)){
			result = result - Integer.parseInt(plusNumStr);
		}

		randomNum = String.valueOf(result);
		return randomNum;

	}

	public static String getRandomNumString(int num) {
		String randomNum = "";

		Random rnd =new Random();
		StringBuffer buf =new StringBuffer();

		for(int i=0;i<num;i++){
			if(rnd.nextBoolean()){
				buf.append((char)((int)(rnd.nextInt(26))+97));
			}else{
				buf.append((rnd.nextInt(10)));
			}
		}
		randomNum = buf.toString();

		return randomNum;

	}

	public static String replaceAll(final String source, final String subject,final String prefix, final String suffix){
		StringBuffer rtnStr = new StringBuffer();
		String preStr = "";
		String nextStr = source;
		String srcStr = source;
		String object = "";

		while (srcStr.toUpperCase().indexOf(subject.toUpperCase()) >= 0) {
			preStr = srcStr.substring(0, srcStr.toUpperCase().indexOf(subject.toUpperCase()));
			object = srcStr.substring(srcStr.toUpperCase().indexOf(subject.toUpperCase()),srcStr.toUpperCase().indexOf(subject.toUpperCase()) + subject.length());
			nextStr = srcStr.substring(srcStr.toUpperCase().indexOf(subject.toUpperCase()) + subject.length(), srcStr.length());
			srcStr = nextStr;
			rtnStr.append(preStr).append(prefix).append(object).append(suffix);
		}
		rtnStr.append(nextStr);

		return rtnStr.toString();
	}

	public static String strToHp(final String hp){

		if(hp == "" || hp == null){
			return null;
		}

		StringBuffer rtnStr = new StringBuffer();
		String first = "";
		String middle = "";
		String last = "";
		if(hp.length()>8 && hp.length() <= 11){
			if(hp.indexOf("02") == 0){
				first = hp.substring(0,2);
				if(hp.length() == 9){
					middle = hp.substring(2,5);
					last = hp.substring(5,hp.length());
				}else if(hp.length() == 10){
					middle = hp.substring(2,6);
					last = hp.substring(6,hp.length());
				}else{
					return hp;
				}
			}else{
				first = hp.substring(0,3);
				if(hp.length() == 10){
					middle = hp.substring(3,6);
					last = hp.substring(6,hp.length());
				}else if(hp.length() == 11){
					middle = hp.substring(3,7);
					last = hp.substring(7,hp.length());
				}else{
					return hp;
				}
			}
		}else if (hp.length() == 8){
			first = hp.substring(0,4);
			last = hp.substring(4,hp.length());
			rtnStr.append(first).append("-").append(last);
			return rtnStr.toString();
		}else{
			return hp;
		}


		rtnStr.append(first).append("-").append(middle).append("-").append(last);

		return rtnStr.toString();
	}

	public static String fineImg(final String cont){
		Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");

		Matcher match = pattern.matcher(cont);

		String imgTag = "";

		if(match.find()){
			imgTag = match.group(1);
		}
		return imgTag;
	}

	public static String convertStreamToString(InputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			//e.printStackTrace();
			log.debug("error");
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				log.debug("error");
				//e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String convertFileSize(final String bytes) {
		String retFormat = "0";
		long size = Long.parseLong(bytes);

		String[] s = {"bytes", "KB", "MB", "GB", "TB"};

		if (!"0".equals(bytes)) {
			int idx = (int) Math.floor(Math.log(size) / Math.log(1024));
			DecimalFormat df = new DecimalFormat("#,###.##");
			double ret = ((size / Math.pow(1024, Math.floor(idx))));
			retFormat = df.format(ret) + " " + s[idx];
		} else {
			retFormat += " " + s[0];
		}

		return retFormat;
	}
}
