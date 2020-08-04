package go.gg.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * DEEP.FINE 공통 유틸리티 (Apache StringUtils 상속)
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
public class DeepfineUtils extends StringUtils {
	/**
	 * @apiNote 신규 UUID 생성 (for Primary Key)
	 * @return new UUID
	 */
	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * @apiNote Convert Object to JSON
	 * @param object 원본 Object
	 * @return 변환된 JSON
	 */
	public static String makeJsonString(Object object) {
		String jsonString;
		ObjectMapper mapper = new ObjectMapper();

		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			return "";
		}
		return jsonString;
	}

	/**
	 * @apiNote Convert JSON String to HashMap
	 * @param JSONString 원본 JSON String
	 * @return 변환된 HashMap
	 */
	public static Map<String, String> makeMapToJSONString(String JSONString) {
		Map<String, String> resultMap = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			resultMap = mapper.readValue(JSONString, HashMap.class);
		} catch (Exception e) {
			return Maps.newHashMap();
		}
		return resultMap;
	}

	/**
	 * Blank 체크 (Object)
	 * @apiNote default: ""
	 * @param obj 입력 문자열
	 */
	public static Object defaultString(Object obj) {
		return defaultString(obj, "");
	}

	/**
	 * Blank 체크 (Object)
	 * @apiNote default: ""
	 * @param obj 입력 Object
	 * @param defaultStr default 문자열
	 */
	public static Object defaultString(Object obj, String defaultStr) {
		return obj == null ? defaultStr: obj;
	}

	/**
	 * Blank 체크
	 * @apiNote default: ""
	 * @param str 입력 문자열
	 */
	public static String defaultString(String str) {
		return defaultString(str, "");
	}

	/**
	 * Blank 체크
	 * @apiNote default: 사용자 지정 String
	 * @param str 입력 문자열
	 * @param defaultStr default 문자열
	 */
	public static String defaultString(String str, String defaultStr) {
		return StringUtils.isBlank(str) ? defaultStr : str;
	}

	/**
	 * 비밀번호 유효성 확인
	 * @apiNote
	 *  NULL, White Space 사용 불가
	 *  8자리 이하 사용 불가
	 *  영문 대소문자 + 숫자 + 특수문자 혼용
	 *  영문+ 숫자 + 특수문자 혼용
	 * @param strPwd 비밀번호
	 * @return 검증결과 true : false
	 */
	public static boolean availablePassword(String strPwd) {
		if (defaultString(strPwd).length() < 8) {
			return false;
		}

		List<Pattern> matcherList = Lists.newArrayList(
				Pattern.compile("([0-9])")
				, Pattern.compile("([!@#$%^&*()<>{}\\-+,.?])")
				, Pattern.compile("([a-z])")
				, Pattern.compile("([A-Z])")
		);

		return !matcherList.stream()
				.map(p -> p.matcher(strPwd).find())
				.collect(Collectors.toList()).contains(false);
	}

	/**
	 * @apiNote Convert String to Calendar Format
	 * @param strDate 날짜(String)
	 * @return 날짜(Calendar)
	 */
	public static Calendar converterDate(String strDate) {
		Calendar cal = Calendar.getInstance();

		if (!StringUtils.isBlank(strDate) && NumberUtils.isNumber(strDate)) {
			strDate = (strDate.trim() + "0101").substring(0, 8);
			cal.set(Calendar.YEAR, Integer.parseInt(strDate.substring(0, 4)));
			cal.set(Calendar.MONTH, Integer.parseInt(strDate.substring(4, 6)) - 1);
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strDate.substring(6)));
		}

		return cal;
	}

	/**
	 * @apiNote 숫자 세자리 콤마
	 * @param num 대상 숫자
	 * @return 콤마가 추가된 문자열 (###,###,###)
	 */
	public static String toNumberWithComma(int num) {
		try{
			DecimalFormat df = new DecimalFormat("#,###");
			return df.format(num);
		}catch(Exception e){
			return "0";
		}
	}
}
