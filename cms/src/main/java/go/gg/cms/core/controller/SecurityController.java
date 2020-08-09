package go.gg.cms.core.controller;

import go.gg.cms.apps.user.service.UserService;
import go.gg.common.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CMS 관리자 로그인 컨트롤러
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.29
 * @version 1.0.0
 */
@Controller("core.security.controller")
public class SecurityController extends BaseController {

	@Autowired
	private UserService userService;

	/**
	 * 로그인 화면
	 * @param message 메시지
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("login.do")
	public String login(@RequestParam(value = "message", required = false) String message
			, Model model
			, HttpServletRequest request
			, HttpServletResponse response) {

		logger.debug("Welcome to Login Page.");
		model.addAttribute("message", message);

		//logger.debug("enc test: {}", SecurityUtils.sha512BySalt("1234", "hUixYjGNhSzOhUOpjCly43GAixY4+Vv1rNOjIAgYhskl4HlAl+N2CdjqqzVqObN05PfbmvY3"));

		return "core/security/login";
	}
}
