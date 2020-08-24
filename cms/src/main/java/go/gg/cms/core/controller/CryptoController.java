package go.gg.cms.core.controller;

import go.gg.common.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * CMS 암복호화 컨트롤러
 * @author jm.lee (DEEP.FINE)
 * @since 2020.08.18
 * @version 1.0.0
 */
@Controller("apps.crypto.controller")
@RequestMapping({"crypto"})
public class CryptoController extends BaseController {

	@Value("${crypto.key}")
	private String SECRET_KEY;

	/**
	 * 암복호화 테스트 페이지
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("form.do")
	public String list(Model model) {
		return "core/crypto/form.tiles.default";
	}


	/**
	 * 암호화 > AES256
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("aes-enc.do")
	public String aesEnc(@RequestBody String str) {
		SecurityUtils.setSecretKey(SECRET_KEY);
		logger.debug("input value: {}", str);
		String result = SecurityUtils.encrypt(str);
		logger.debug("output value: {}", result);
		return result;
	}


	/**
	 * 복호화 > AES256
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("aes-dec.do")
	public String aesDec(@RequestBody String str) {
		SecurityUtils.setSecretKey(SECRET_KEY);
		logger.debug("input value: {}", str);
		String result = SecurityUtils.decrypt(str);
		logger.debug("output value: {}", result);
		return result;
	}


	/**
	 * 암호화 > SHA512
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("sha-enc.do")
	public String shaEnc(@RequestBody String str) {
		String val = "";
		String salt = "";

		if (str.contains("::")) {
			val = str.split("::")[0];
			salt = str.split("::")[1];
		}
		logger.debug("input value: {} (salt: {})", val, salt);
		String result = SecurityUtils.sha512BySalt(val, salt);
		logger.debug("output value: {}", result);
		return result;
	}
}
