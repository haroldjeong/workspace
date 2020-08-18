package go.gg.cms.core.controller;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import go.gg.cms.core.domain.Menu;
import go.gg.cms.core.domain.Role;
import go.gg.cms.core.service.RoleService;
import go.gg.common.util.DeepfineUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * CMS Role 게시판 컨트롤러
 * @author jh.park (DEEP.FINE)
 * @since 2020.08.05
 * @version 1.0.0
 */
@Controller("core.role.controller")
@RequestMapping({"role"})
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	/**
	 * Role 게시판 > 목록
	 * @param condition 도메인
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("list.do")
	public String list(@ModelAttribute("condition") Role condition, Model model) {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(condition.getPageIndex());
		paginationInfo.setRecordCountPerPage(condition.getPageUnit());
		paginationInfo.setPageSize(condition.getPageSize());

		List<Role> resultList = roleService.selectList(condition);
		paginationInfo.setTotalRecordCount(roleService.count(condition));

		model.addAttribute("resultList", resultList);
		model.addAttribute("paginationInfo", paginationInfo);

		return "core/role/list.tiles.default";
	}

	/**
	 * Role 게시판 > 상세
	 * @param condition 도메인
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("detail.do")
	public String detail(@ModelAttribute("condition") Role condition, Model model) throws Exception {
		model.addAttribute("result", roleService.detail(condition));
		return "core/role/detail.tiles.default";
	}

	/**
	 * Role 게시판 > 등록/수정
	 * @param condition 도메인
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("form.do")
	public String form(@ModelAttribute("condition") Role condition, Model model) throws Exception {

		Role result = new Role();

		if (!DeepfineUtils.isEmpty(condition.getId())) {
			result = roleService.detail(condition);
		}

		model.addAttribute("result", result);

		return "core/role/form.tiles.default";
	}

	/**
	 * Role 게시판 > 데이터 저장
	 * @param condition 도메인
	 * @return View Page (JSP)
	 */
	@ResponseBody
	@RequestMapping("save.do")
	public Role save(@RequestBody Role condition) {

		if (DeepfineUtils.isEmpty(condition.getId())) {
			condition.initId();
			roleService.insert(condition);
		} else {
			roleService.update(condition);
		}
		roleService.insertMenu(condition);

		return condition;
	}

	/**
	 * Role 게시판 > 데이터 삭제
	 * @param condition 도메인
	 * @return View Page (JSP)
	 */
	@ResponseBody
	@RequestMapping("remove.do")
	public Role remove(@RequestBody Role condition) {
		if (!DeepfineUtils.isEmpty(condition.getId())) {
			roleService.delete(condition);
		}
		return condition;
	}

	/**
	 * Role 게시판 > 전체 메뉴목록
	 * @param condition 도메인
	 * @return Menu List
	 */
	@ResponseBody
	@RequestMapping("findMenuAll.do")
	public List<Menu> findMenuAll(@RequestBody Menu condition) {
		return roleService.findMenuAll(condition);
	}

}
