package go.gg.cms.core.security;

import go.gg.cms.apps.user.service.UserService;
import go.gg.cms.core.service.SecurityUserDetailsService;
import go.gg.cms.core.service.SecurityUserService;
import go.gg.common.util.DeepfineUtils;
import go.gg.cms.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

		// 90일 만료 Case
		if ( exception.getClass() == CredentialsExpiredException.class) {
			// todo: 아래 파라미터명 확인 필요 (로그인 유저의 UUID로 하는게 좋을 듯) (jm.lee)
			String id = request.getParameter("userId");
			String tempKey = DeepfineUtils.remove(DeepfineUtils.generateUUID(), "-").toLowerCase();		// 임시키
			LocalDateTime tempExpires = LocalDateTime.now().plusMinutes(20);	// 5분 후 만료

			User user = new User();
			user.setLoginId(id);
			user.setTempKey(tempKey);
			user.setTempKeyExpireDt(tempExpires);

			request.getSession().setAttribute("LOGIN_FAIL_PWD_EXP_KEY", user);

			try {
				securityUserService.updateChangePwdKey(user);
			} catch (Exception e) {
				if(logger.isErrorEnabled()) {
					logger.debug(e.getMessage());
				}
			}
			response.sendRedirect(request.getContextPath() + "/login/change-pwd");

		} else if (exception.getClass() == UsernameNotFoundException.class) {
			request.getRequestDispatcher("/login?message=" + URLEncoder.encode("아이디, 비밀번호를 확인해주세요."
				+ "비밀번호와 IP 오류가 5회 누적되면 사용이 정지됩니다.", "UTF-8")).forward(request, response);
		} else{
			request.getRequestDispatcher("/login?message=" + URLEncoder.encode(exception.getMessage(), "UTF-8")).forward(request, response);
		}
	}
}
