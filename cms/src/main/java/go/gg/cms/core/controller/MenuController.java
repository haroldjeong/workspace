package go.gg.cms.core.controller;

import go.gg.cms.core.domain.Menu;
import go.gg.cms.core.service.MenuService;
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
 * CMS 메뉴 컨트롤러
 * @author jh.park (DEEP.FINE)
 * @since 2020.08.11
 * @version 1.0.0
 */
@Controller("core.menu.controller")
@RequestMapping({"menu"})
public class MenuController extends BaseController {

	@Autowired
	private MenuService menuService;
	
	/**
	 * 공통메뉴 > 첫 화면
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("list.do")
	public String list(Model model) {
		model.addAttribute("menuList", menuService.findAll());
		return "core/menu/list.tiles.default";
	}

	/**
	 * 공통메뉴 > 전체 메뉴목록
	 * @return Menu List
	 */
	@ResponseBody
	@RequestMapping("findAll.do")
	public List<Menu> findAll() {
		return menuService.findAll();
	}

	/**
	 * 공통메뉴 > 메뉴 상세정보
	 * @return Menu
	 */
	@ResponseBody
	@RequestMapping("detail.do")
	public Menu detail(@RequestBody Menu condition) {
		return menuService.findById(condition);
	}

	/**
	 * 공통메뉴 > 하위메뉴 추가
	 * @return Menu
	 */
	@ResponseBody
	@RequestMapping("add.do")
	public Menu add(@RequestBody Menu condition) {
		return menuService.insert(condition);
	}

	/**
	 * 공통메뉴 > 최상위 메뉴 추가
	 * @return Menu
	 */
	@ResponseBody
	@RequestMapping("add-top.do")
	public Menu addTop(@RequestBody Menu condition) {
		return menuService.insertTop(condition);
	}

	/**
	 * 공통메뉴 > 메뉴정보 수정
	 * @return Menu
	 */
	@ResponseBody
	@RequestMapping("modify.do")
	public Menu modify(@RequestBody Menu condition) {
		menuService.update(condition);
		return condition;
	}

	/**
	 * 공통메뉴 > 메뉴정보 삭제
	 * @return Menu
	 */
	@ResponseBody
	@RequestMapping("remove.do")
	public Menu remove(@RequestBody Menu condition) {
		menuService.delete(condition);
		return condition;
	}

	/**
	 * 공통메뉴 > 메뉴 이동 및 순서변경
	 * @return Menu
	 */
	@ResponseBody
	@RequestMapping("saveSeq.do")
	public ResponseEntity<List<Menu>> saveSeq(RequestEntity<List<Menu>> entity) {
		menuService.saveSeq(entity.getBody());
		return ResponseEntity.ok(entity.getBody());
	}



}
