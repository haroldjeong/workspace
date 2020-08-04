package go.gg.cms.core.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring Security Handler (Access Denied)
 * @apiNote 접근 권한 불충분 시 핸들링
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.29
 * @version 1.0.0
 */
public class CmsAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(
		HttpServletRequest request
		, HttpServletResponse response
		, AccessDeniedException e) throws IOException, ServletException {

		if (e.getMessage().contains("Invalid CSRF Token")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/csrf");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
			dispatcher.forward(request, response);
		}
	}
}
