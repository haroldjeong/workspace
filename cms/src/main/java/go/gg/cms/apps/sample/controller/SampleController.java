package go.gg.cms.apps.sample.controller;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import go.gg.cms.apps.sample.service.SampleService;
import go.gg.cms.core.controller.BaseController;
import go.gg.common.util.DeepfineUtils;
import go.gg.cms.domain.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * CMS 샘플 게시판 컨트롤러
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
@Controller("apps.sample.controller")
@RequestMapping({"sample"})
public class SampleController extends BaseController {

	@Autowired
	private SampleService sampleService;

	/**
	 * 샘플 게시판 > 목록
	 * @param condition 도메인
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("list.do")
	public String list(@ModelAttribute("condition") Sample condition, Model model) {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(condition.getPageIndex());
		paginationInfo.setRecordCountPerPage(condition.getPageUnit());
		paginationInfo.setPageSize(condition.getPageSize());

		List<Sample> resultList = sampleService.find(condition);
		paginationInfo.setTotalRecordCount(sampleService.count(condition));

		model.addAttribute("resultList", resultList);
		model.addAttribute("paginationInfo", paginationInfo);

		return VIEW_PATH + "/list.tiles.default";
	}

	/**
	 * 샘플 게시판 > 상세
	 * @param condition 도메인
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("detail.do")
	public String detail(@ModelAttribute("condition") Sample condition, Model model) throws Exception {
		model.addAttribute("result", sampleService.detail(condition));
		return VIEW_PATH + "/detail.tiles.default";
	}

	/**
	 * 샘플 게시판 > 등록/수정
	 * @param condition 도메인
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("form.do")
	public String form(@ModelAttribute("condition") Sample condition, Model model) throws Exception {

		Sample result = new Sample();

		if (!DeepfineUtils.isEmpty(condition.getId())) {
			result = sampleService.detail(condition);
		}

		model.addAttribute("result", result);

		return VIEW_PATH + "/form.tiles.default";
	}

	/**
	 * 샘플 게시판 > 데이터 저장
	 * @param condition 도메인
	 * @return View Page (JSP)
	 */
	@ResponseBody
	@RequestMapping("save.do")
	public Sample save(@RequestBody Sample condition) {
		if (DeepfineUtils.isEmpty(condition.getId())) {
			condition.initId();
			sampleService.insert(condition);
		} else {
			sampleService.update(condition);
		}
		return condition;
	}

	/**
	 * 샘플 게시판 > 데이터 삭제
	 * @param condition 도메인
	 * @return View Page (JSP)
	 */
	@ResponseBody
	@RequestMapping("remove.do")
	public Sample remove(@RequestBody Sample condition) {
		if (!DeepfineUtils.isEmpty(condition.getId())) {
			sampleService.delete(condition);
		}
		return condition;
	}






	@RequestMapping("template_table.do")
	public String template_table(@ModelAttribute("condition") Sample condition, Model model) {
		logger.debug(VIEW_PATH + "/template_table.tiles.default");
		return VIEW_PATH + "/template_table.tiles.default";
	}

	@RequestMapping("template_form.do")
	public String template_form(@ModelAttribute("condition") Sample condition, Model model) {
		logger.debug(VIEW_PATH + "/template_form.tiles.default");
		return VIEW_PATH + "/template_form.tiles.default";
	}

	@RequestMapping("template_modal.do")
	public String template_modal(@ModelAttribute("condition") Sample condition, Model model) {
		logger.debug(VIEW_PATH + "/template_modal.tiles.default");
		return VIEW_PATH + "/template_modal.tiles.default";
	}

}
