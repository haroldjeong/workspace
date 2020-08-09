package go.gg.cms.core.security;

import go.gg.cms.core.service.SecurityUserService;
import go.gg.common.util.DeepfineUtils;
import go.gg.cms.core.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;

/**
 * Spring Security Handler (Login Failure)
 * @apiNote 로그인 실패 시 핸들링
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.29
 * @version 1.0.0
 */
@Component
public class CmsLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	final static Logger logger = LoggerFactory.getLogger(CmsLoginFailureHandler.class);

	private SecurityUserService securityUserService;

	public void setSecurityUserService(SecurityUserService securityUserService) {
		logger.debug("CmsLoginFailureHandler ::: setUserService");
		this.securityUserService = securityUserService;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
										HttpServletResponse response,
										AuthenticationException exception) throws IOException, ServletException {

		logger.debug("[Spring Security] CmsLoginFailureHandler ::: onAuthenticationFailure ::: {} ::: {}", exception.getClass(), exception.getMessage());

		// 90일 만료 Case
		if (exception.getClass() == CredentialsExpiredException.class) {

			// todo: 비밀번호 변경 페이지 이동 및 처리 로직 구현 필요 (jm.lee)
			response.sendRedirect(request.getContextPath() + "/reset-password.do");

		} else if (exception.getClass() == UsernameNotFoundException.class) {
			request.getRequestDispatcher("/login.do?message=" + URLEncoder.encode("로그인 정보가 유효하지 않습니다."
				+ "\n로그인 5회 실패 시 계정의 사용이 정지되니 주의해주시기 바랍니다.", "UTF-8")).forward(request, response);
		} else{
			request.getRequestDispatcher("/login.do?message=" + URLEncoder.encode(exception.getMessage(), "UTF-8")).forward(request, response);
		}
	}
}
