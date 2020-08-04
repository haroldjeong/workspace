package go.gg.web.core.util;

import go.gg.common.util.DeepfineUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;

public class ClientUtils {

	public static String getHostIP(){
		String hostIp = "";
		try {
			hostIp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return hostIp;
	}

	public static String getRemoteIP(){
		HttpServletRequest request = getRequest();
		return getRemoteIP(request);
	}

	public static String getRemoteIP(HttpServletRequest request){
		String ip = request.getHeader("X-FORWARDED-FOR");

		//proxy 환경일 경우
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		//웹로직 서버일 경우
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0) {
			ip = request.getRemoteAddr() ;
		}

		return ip;
	}

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}

	@SuppressWarnings("deprecation")
	public static String URLEncode(String s){
		return URLEncoder.encode(s);
	}

	@SuppressWarnings("deprecation")
	public static String URLDecode(String s){
		return URLDecoder.decode(s);
	}

	public static String getCookie(String key, HttpServletRequest request){
		Cookie ck = getCookieObject(key,request);

		if(ck == null){
			return "";
		}else{
			return URLDecode(ck.getValue());
		}
	}

	public static Cookie getCookieObject(String key, HttpServletRequest request){
		if(request != null){
			try{
				Cookie[] cookies = request.getCookies();

				if(cookies != null){
					for(int i = 0; i < cookies.length; i++){
						if( cookies[i].getName().equalsIgnoreCase(key) ){
							return cookies[i];
						}
					}
				}
			}catch(Exception e){
				return null;
			}
		}

		return null;
	}

	public static void setCookie(String key, String val, int hour, HttpServletResponse response){
		if(response != null){
			Cookie cook1 = new Cookie(key, URLEncode(val));
			cook1.setHttpOnly(true);
			cook1.setSecure(true);
			cook1.setPath("/");
			cook1.setMaxAge(60*60*hour);
			response.addCookie(cook1);
		}
	}

	public static void setCookie(String key, String val, HttpServletResponse response){
		setCookie(key, val, 24, response);
	}

	public static void setCookie(Cookie ck, HttpServletResponse response){
		if(ck != null){
			response.addCookie(ck);
		}
	}


	public static String getReferer(){
		HttpServletRequest request = getRequest();
		return getReferer(request);
	}

	public static String getReferer(HttpServletRequest request){
		String referer = request.getHeader("Referer");

		if (DeepfineUtils.isEmpty(referer)) {
			referer = DeepfineUtils.defaultString((String)request.getAttribute("javax.servlet.forward.request_uri"));
		}

		return referer;
	}

	public static String getUserAgent() {
		HttpServletRequest request = getRequest();
		return getUserAgent(request);
	}

	public static String getUserAgent(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		return userAgent;
	}


}
