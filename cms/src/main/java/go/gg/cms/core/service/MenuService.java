package go.gg.cms.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import go.gg.cms.core.domain.Menu;
import go.gg.common.util.DeepfineUtils;
import go.gg.common.util.JsonUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * CMS 메뉴 서비스
 * @author jh.park (DEEP.FINE)
 * @since 2020.08.11
 * @version 1.0.0
 */
@Service("core.menu.service")
public class MenuService extends EgovAbstractMapper {

	private final String QUERY_ID_PREFIX = "go.gg.cms.core.commonMenu.";
	private String MENU_KEY = "";


	/**
	 * 전체 공통메뉴 목록
	 * @return 메뉴 리스트
	 */
	@Cacheable(cacheNames="menuCache", key="#loginId")
	public List<Menu> findMenuCache(String loginId) {

		Menu condition = new Menu();
		condition.setSearchUseYn("Y");
		condition.setRegId();

		MENU_KEY = condition.getRegId();

		List<Menu> menuList = null;
		try {
			menuList = super.selectList(QUERY_ID_PREFIX + "findMenuCache", condition);
		} catch (Exception e) {
			logger.error(e);
		}
		//result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(menuList);
		return menuList;
	}


	/**
	 * 현재 메뉴 정보
	 * @return 메뉴 상세
	 */
	public Menu findCurentMenu(List<Menu> menuList, String url) {
		Menu returnMenu = null;
		for (Menu menu : menuList) {
			if(menu.getLinkUrl().startsWith("/")){
				if(url.substring(0,url.lastIndexOf("/")).startsWith(menu.getLinkUrl().substring(0,menu.getLinkUrl().lastIndexOf("/")))){
					returnMenu = menu;
					break;
				}
			}
		}
		return returnMenu;
	}

	/**
	 * 현재 메뉴 네비게이터 정보
	 * @return 메뉴 리스트
	 */
	public List<Menu> findMenuNavigator(List<Menu> menuList, Menu menu) {
		Collections.reverse(menuList);
		List<Menu> navigator = Lists.newArrayList();
		if(menu != null){
			navigator.add(menu);
			for (Menu me : menuList) {
				if(menu.getParentId().equals(me.getId())){
					navigator.add(me);
					menu = me;
				}
			}
		}
		Collections.reverse(navigator);
		Collections.reverse(menuList);
		return navigator;
	}

	/**
	 * 메뉴 케시 삭제
	 * @return void
	 */
	@CacheEvict(cacheNames = "menuCache", allEntries = true, key="#loginId")
	public void evictCache(String loginId) {
		logger.info("delete cache all");
	}


	/**
	 * 전체 공통메뉴 목록
	 * @return 메뉴 목록
	 */
	public List<Menu> findAll() {
		return super.selectList(QUERY_ID_PREFIX + "findMenuAll");
	}

	/**
	 * 메뉴 상세정보
	 * @return 메뉴 상세정보
	 */
	public Menu findById(Menu condition) {
		return super.selectOne(QUERY_ID_PREFIX + "findById", condition);
	}

	/**
	 * 하위메뉴 추가
	 * @return 메뉴 상세정보
	 */
	@Transactional
	public Menu insert(Menu condition) {
		condition.setParentId(condition.getId());
		condition.initId();
		condition.setSeq(condition.getSeq() + 1);
		condition.setRegId();
		condition.setRegIp();
		condition.setRegAgent();
		super.insert(QUERY_ID_PREFIX + "insertMaster", condition);
		super.insert(QUERY_ID_PREFIX + "insertMl", condition);

		return condition;
	}

	/**
	 * 최상위 메뉴 추가
	 * @return 메뉴 상세정보
	 */
	@Transactional
	public Menu insertTop(Menu condition) {

		// 최상위 메뉴의 부모코드는 ROOT 코드
		Menu root = super.selectOne(QUERY_ID_PREFIX + "findRooTMenu");

		condition.setParentId(root.getId());
		condition.initId();
		condition.setSeq(condition.getSeq() + 1);
		condition.setRegId();
		condition.setRegIp();
		condition.setRegAgent();
		super.insert(QUERY_ID_PREFIX + "insertMaster", condition);
		super.insert(QUERY_ID_PREFIX + "insertMl", condition);

		return condition;
	}

	/**
	 * 메뉴정보 수정
	 */
	@Transactional
	public void update(Menu condition) {
		condition.setModId();
		condition.setModIp();
		condition.setModAgent();
		super.update(QUERY_ID_PREFIX + "updateMaster", condition);
		super.delete(QUERY_ID_PREFIX + "deleteMl", condition);
		super.update(QUERY_ID_PREFIX + "insertMl", condition);
	}

	/**
	 * 메뉴정보 삭제
	 */
	@Transactional
	public void delete(Menu condition) {
		condition.setModId();
		condition.setModIp();
		condition.setModAgent();
		super.delete(QUERY_ID_PREFIX + "delete", condition);
	}

	/**
	 * 메뉴 이동 및 순서변경
	 */
	@Transactional
	public void saveSeq(List<Menu> condition) {
		if (condition != null && !condition.isEmpty()) {
			Menu menu = new Menu();
			menu.setMenus(condition);
			menu.setModId();
			menu.setModIp();
			menu.setModAgent();
			super.update(QUERY_ID_PREFIX + "updateSeq", menu);
		}
	}
}
