package go.gg.cms.core.security;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import go.gg.cms.core.domain.SecurityUserDetail;
import go.gg.cms.core.service.SecurityUserService;
import go.gg.cms.core.util.ClientUtils;
import go.gg.common.util.DeepfineUtils;
import go.gg.cms.core.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Spring Security Handler (Login Success)
 * @apiNote 로그인 성공 시 핸들링
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.29
 * @version 1.0.0
 */
public class CmsLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private final static Logger logger = LoggerFactory.getLogger(CmsLoginSuccessHandler.class);

	private SecurityUserService securityUserService;

	public void setSecurityUserService(SecurityUserService securityUserService) {
		logger.debug("CmsLoginSuccessHandler ::: setUserService");
		this.securityUserService = securityUserService;
	}

	@Override
	public void onAuthenticationSuccess (
		HttpServletRequest request
		, HttpServletResponse response
		, Authentication authentication) throws ServletException, IOException {

		SecurityUserDetail userDetails = (SecurityUserDetail) authentication.getDetails();
		String loginId = userDetails.getDetail().getLoginId();
		logger.debug("[Spring Security] CmsLoginSuccessHandler ::: onAuthenticationSuccess ::: {}", loginId);

		// IP 설정
		String ip = ClientUtils.getRemoteIP(request);

		// IP 제어
		String[] allowIps = DeepfineUtils.defaultString(userDetails.getUserInfo().getAllowIp()).split(",");

		logger.debug("로그인 아이피 확인 : " + ip);
		boolean isPwInit = userDetails.getUserInfo().isPwInit();

		// checkIp의 결과가 true 인게 없을 때 허용되지 않은 IP 처리
		if (Lists.newArrayList(allowIps).stream().filter(allowIp -> checkIp(allowIp, ip)).count() == 0) {

			// 비밀번호 불일치 : 로그인 실패 건수 1 증가
			securityUserService.updateFailCnt(userDetails.getUserInfo().getLoginId());
			getRedirectStrategy().sendRedirect(request, response, "/login.do?message=" + URLEncoder.encode("모든 사용자는 지정된 PC에서만 로그인이 가능하며, 로그인 5회 실패 시 계정의 사용이 정지되니 주의해주시기 바랍니다..", "UTF-8"));

		} else if(isPwInit) {
			// todo: 비밀번호 부여받았을 경우 새로운 비밀번호 수정 처리 로직 구현 필요 (jm.lee)
			response.sendRedirect(request.getContextPath() + "/reset-password.do");
		}else {
			logger.debug("Success login with (ID: '{}')", loginId);
			response.setStatus(HttpServletResponse.SC_OK);

			// 로그인 실패 횟수 초기화
			securityUserService.loginSuccess(authentication.getName());

			// todo: 로그인 성공 이력(로그) 구현 필요 (jm.lee)
			securityUserService.saveHistory("LOGIN_SUCCESS", authentication.getName(), "LOGIN_SUCCESS");

			HttpSession session = request.getSession();

			if (session != null) {
				String redirectUrl = (String) session.getAttribute("prevPage");

				if (redirectUrl != null) {
					session.removeAttribute("prevPage");
					getRedirectStrategy().sendRedirect(request, response, redirectUrl);
				} else {
					RequestCache requestCache = new HttpSessionRequestCache();
					SavedRequest savedRequest = requestCache.getRequest(request, response);

					if (savedRequest == null) {
						getRedirectStrategy().sendRedirect(request, response, "/");

					} else {
						getRedirectStrategy().sendRedirect(request, response, savedRequest.getRedirectUrl());
					}
				}
			} else {
				getRedirectStrategy().sendRedirect(request, response, "/");
			}
		}
	}


	// IPv4 만 허용
	private boolean checkIp(String allowIp, String ip) {

		List<String> allowIpClassList = Splitter.on('.').trimResults().splitToList(allowIp);
		List<String> ipClassList = Splitter.on('.').splitToList(ip);

		// 로컬 설정인 경우
		if (DeepfineUtils.equalsIgnoreCase(ip, "127.0.0.1")
				|| DeepfineUtils.equalsIgnoreCase(ip, "127.000.000.001")
				|| DeepfineUtils.equalsIgnoreCase(ip, "0:0:0:0:0:0:0:1")) {
			return true;
		}
		// 범위 연산
		return allowIpClassList.size() == 4 && ipClassList.size() == 4 && IntStream.range(0, 4).filter(i -> {
			try {
				String pattern = allowIpClassList.get(i);
				int target = Integer.parseInt(ipClassList.get(i));
				if (DeepfineUtils.equalsIgnoreCase(pattern, "*")) {
					return true;
				}
				if (DeepfineUtils.containsOnly("-", pattern)) { // 범위 연산
					List<Integer> loopAreaList = Splitter.on('-').splitToList(pattern).stream()
						.map(Integer::parseInt)
						.collect(Collectors.toList());

					return IntStream.range(loopAreaList.get(0), loopAreaList.get(1) + 1)
						.filter(j -> j == target)
						.count() > 0;
				}
				if (pattern.contains(",")) {
					return Splitter.on(',').splitToList(pattern).stream()
						.map(Integer::parseInt)
						.collect(Collectors.toList()).contains(target);
				}
				return Integer.parseInt(pattern) == target;
			} catch (NumberFormatException nfe) {
				return false;
			}
		}).count() == 4;
	}
}
