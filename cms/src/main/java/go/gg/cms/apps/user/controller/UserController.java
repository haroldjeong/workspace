package go.gg.cms.apps.user.controller;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import go.gg.cms.apps.user.service.UserService;
import go.gg.cms.core.controller.BaseController;
import go.gg.common.util.DeepfineUtils;
import go.gg.cms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CMS 관리자 기본 컨트롤러
 * @apiNote 관리자 그룹 및 메뉴권한 등은 별도 컨트롤러로 구현
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
@Controller("apps.user.controller")
@RequestMapping({"user"})
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	/**
	 * 시스템관리 > 관리자 관리 > 목록
	 * @param condition 도메인
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("list.do")
	public String list(@ModelAttribute("condition") User condition, Model model) {
		System.out.println("[SOUT] UserController ::: list.do ::::");
		logger.debug("[LOGGER.DEBUG] UserController ::: list.do ::::");

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(condition.getPageIndex());
		paginationInfo.setRecordCountPerPage(condition.getPageUnit());
		paginationInfo.setPageSize(condition.getPageSize());

		List<User> resultList = userService.find(condition);
		paginationInfo.setTotalRecordCount(userService.count(condition));

		model.addAttribute("resultList", resultList);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("groupList", userService.findGroup(condition));

		return VIEW_PATH + "/list.tiles.default";
	}

	/**
	 * 시스템관리 > 관리자 관리 > 상세
	 * @param condition 도메인
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("detail.do")
	public String detail(@ModelAttribute("condition") User condition, Model model) {
		model.addAttribute("result", userService.detail(condition));
		return VIEW_PATH + "/detail.tiles.default";
	}

	/**
	 * 시스템관리 > 관리자 관리 > 등록/수정
	 * @param condition 도메인
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("form.do")
	public String form(@ModelAttribute("condition") User condition, Model model) {
		model.addAttribute("groupList", userService.findGroup(condition));
		model.addAttribute("result", userService.detail(condition));
		return VIEW_PATH + "/form.tiles.default";
	}

	/**
	 * 시스템관리 > 관리자 관리 > 저장
	 * @param condition 도메인
	 * @return View Page (JSP)
	 */
	@ResponseBody
	@RequestMapping("save.do")
	public User save(@RequestBody User condition) {
		if (DeepfineUtils.isEmpty(condition.getId())) {
			// todo: Insert 로직 구현 필요. 비밀번호 관련하여 추후 구현 예정 (jm.lee)
		} else {
			userService.updateUser(condition);
		}
		return condition;
	}

	/**
	 * 시스템관리 > 관리자 관리 > 삭제
	 * @param condition 도메인
	 * @return View Page (JSP)
	 */
	@ResponseBody
	@RequestMapping("remove.do")
	public User remove(@RequestBody User condition) {
		if (!DeepfineUtils.isEmpty(condition.getId())) {
			userService.delete(condition);
		}
		return condition;
	}

}
