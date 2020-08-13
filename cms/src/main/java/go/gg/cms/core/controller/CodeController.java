package go.gg.cms.core.controller;

import go.gg.cms.core.domain.Code;
import go.gg.cms.core.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

	/**
	 * 공통코드 > 첫 화면
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("list.do")
	public String list(Model model) {
		model.addAttribute("codeList", codeService.findAll());
		return "core/code/list.tiles.default";
	}

	/**
	 * 공통코드 > 전체 코드목록
	 * @return Code List
	 */
	@ResponseBody
	@RequestMapping("findAll.do")
	public List<Code> findAll() {
		return codeService.findAll();
	}

	/**
	 * 공통코드 > 코드 상세정보
	 * @return Code
	 */
	@ResponseBody
	@RequestMapping("detail.do")
	public Code detail(@RequestBody Code condition) {
		return codeService.findById(condition);
	}

	/**
	 * 공통코드 > 하위코드 추가
	 * @return Code
	 */
	@ResponseBody
	@RequestMapping("add.do")
	public Code add(@RequestBody Code condition) {
		return codeService.insert(condition);
	}

	/**
	 * 공통코드 > 최상위 코드 추가
	 * @return Code
	 */
	@ResponseBody
	@RequestMapping("add-top.do")
	public Code addTop(@RequestBody Code condition) {
		return codeService.insertTop(condition);
	}

	/**
	 * 공통코드 > 코드정보 수정
	 * @return Code
	 */
	@ResponseBody
	@RequestMapping("modify.do")
	public Code modify(@RequestBody Code condition) {
		codeService.update(condition);
		return condition;
	}

	/**
	 * 공통코드 > 코드정보 삭제
	 * @return Code
	 */
	@ResponseBody
	@RequestMapping("remove.do")
	public Code remove(@RequestBody Code condition) {
		codeService.delete(condition);
		return condition;
	}

	/**
	 * 공통코드 > 코드 이동 및 순서변경
	 * @return Code
	 */
	@ResponseBody
	@RequestMapping("saveSeq.do")
	public ResponseEntity<List<Code>> saveSeq(RequestEntity<List<Code>> entity) {
		codeService.saveSeq(entity.getBody());
		return ResponseEntity.ok(entity.getBody());
	}
}
