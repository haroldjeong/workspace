package go.gg.web.core.controller;

import go.gg.web.core.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * WEB 공통코드 컨트롤러
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
@Controller("apps.code.controller")
@RequestMapping({"code"})
public class CodeController extends BaseController {

	@Autowired
	private CodeService codeService;

}
