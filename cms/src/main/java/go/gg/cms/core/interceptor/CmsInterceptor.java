package go.gg.cms.core.interceptor;

import go.gg.cms.core.service.CodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CMS 공통 Interceptor
 * @apiNote CMS 내 Request 적용
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
public class CmsInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger("interceptor");

	@Value("${globals.profile}")
	private String ENV_PROFILE;

	@Autowired
	private CodeService codeService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		long currentTime = System.currentTimeMillis();
		logger.info("======================== CmsInterceptor [preHandle][{}] ========================", currentTime);
		logger.info("Current Profile : {}", ENV_PROFILE);

		request.setAttribute("newLineChar", "\n");
		request.setAttribute("codeSet", codeService.findCodeCache());

		// todo: controller method body 로직 수행 전 처리로직 구현 (jm.lee)

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler
			, @Nullable ModelAndView modelAndView) {

		// todo: controller method body 로직 수행 후 처리로직 구현 (jm.lee)

		long currentTime = System.currentTimeMillis();
		logger.info("======================== CmsInterceptor [postHandle][{}] ========================", currentTime);
	}
}
