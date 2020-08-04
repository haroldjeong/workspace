package go.gg.cms.core.controller;

import go.gg.cms.core.service.CodeService;
import go.gg.cms.core.domain.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * CMS 공통코드 컨트롤러
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
