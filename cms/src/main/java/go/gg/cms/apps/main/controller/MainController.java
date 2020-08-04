package go.gg.cms.apps.main.controller;

import go.gg.cms.apps.main.service.MainService;
import go.gg.cms.core.controller.BaseController;
import go.gg.cms.domain.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * CMS 메인 컨트롤러
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
@Controller("apps.main.controller")
@RequestMapping({"/"})
public class MainController extends BaseController {

	@Autowired
	private MainService mainService;

	@Value("${site.domain.front}")
	private String frontDomain;

	/**
	 * 메인화면
	 * @param main 도메인
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("main.do")
	public String list(@ModelAttribute("condition") Main main, Model model) {
		return "main.tiles.main";
	}

}
