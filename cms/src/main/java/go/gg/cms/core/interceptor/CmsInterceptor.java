package go.gg.cms.core.interceptor;

import go.gg.cms.core.domain.Menu;
import go.gg.cms.core.service.CodeService;
import go.gg.cms.core.service.MenuService;
import go.gg.cms.core.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

	@Autowired
	private MenuService menuService;


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		long currentTime = System.currentTimeMillis();
		logger.info("======================== CmsInterceptor [preHandle][{}] ========================", currentTime);
		logger.info("Current Profile : {}", ENV_PROFILE);
		System.out.println(request.getRequestURI());
		request.setAttribute("newLineChar", "\n");
		request.setAttribute("codeSet", codeService.findCodeCache());

		List<Menu> menuSet =  menuService.findMenuCache(UserUtils.getUserInfo().getId());
		Menu currMenu = menuService.findCurentMenu(menuSet, request.getRequestURI());
		List<Menu> navigator = menuService.findMenuNavigator(menuSet, currMenu);

		request.setAttribute("menuSet", menuSet);
		request.setAttribute("currMenu", currMenu);
		request.setAttribute("menuNavigator", navigator);



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
