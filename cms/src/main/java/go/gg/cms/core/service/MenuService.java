package go.gg.cms.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import go.gg.cms.core.domain.Menu;
import go.gg.common.util.DeepfineUtils;
import go.gg.common.util.JsonUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	/**
	 * 전체 메뉴 목록
	 * @return 계층형 Tree 구조 JSON String
	 */
	public String findMenuCacheHierarchy () {

		List<Menu> menuList = super.selectList(QUERY_ID_PREFIX + "findMenuCache");

		// ROOT Menu ID
		String rootId = menuList.stream().filter(
				menu -> "ROOT".equals(menu.getCd())&& "".equals(DeepfineUtils.defaultString(menu.getParentId())))
				.findFirst().get().getId();

		List<Map<String, Object>> resultList = JsonUtils.convertorTreeMap(menuList, rootId, "id", "parentId", "cd", "seq");
		String result = "";

		try {
			result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(resultList);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}

//		logger.debug(result);
		return result;
	}

	/**
	 * 전체 공통메뉴 목록
	 * @return 2차원 배열 JSON String
	 */
	@Cacheable(cacheNames="menuCache", key="#root.methodName")
	public String findMenuCache() {

		Menu condition = new Menu();
		condition.setSearchUseYn("Y");

		List<Menu> menuList = super.selectList(QUERY_ID_PREFIX + "findMenuCache", condition);
		String result = "";

		try {
			result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(menuList);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}

		logger.debug(result);
		return result;
	}



	/**
	 * 전체 공통메뉴 목록
	 * @return 메뉴 목록
	 */
	public List<Menu> findAll() {
		return super.selectList(QUERY_ID_PREFIX + "findMenuCache");
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
