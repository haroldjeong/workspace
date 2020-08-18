package go.gg.cms.core.service;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import go.gg.cms.core.domain.Menu;
import go.gg.cms.core.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CMS 룰 게시판 서비스
 * @apiNote 룰 게시판
 * @author jh.park (DEEP.FINE)
 * @since 2020.08.05
 * @version 1.0.0
 */
@Service("core.role.service")
public class RoleService extends EgovAbstractMapper {

	private final String QUERY_ID_PREFIX = "go.gg.cms.core.role.";

	public List<Role> selectList(Role parameterObject) {
		return selectList(QUERY_ID_PREFIX + "findList", parameterObject);
	}

	public Integer count(Role parameterObject) {
		return selectOne(QUERY_ID_PREFIX + "countList", parameterObject);
	}

	public Role detail(Role parameterObject) throws Exception {
		return selectOne(QUERY_ID_PREFIX + "findDetail", parameterObject);
	}

	public int insert(Role parameterObject) {
		return insert(QUERY_ID_PREFIX + "insert", parameterObject);
	}

	public int insertMenu(Role parameterObject) {
		delete(QUERY_ID_PREFIX + "deleteMenuRole", parameterObject);
		return insert(QUERY_ID_PREFIX + "insertMenuRole", parameterObject);
	}




	public int update(Role parameterObject) {
		return update(QUERY_ID_PREFIX + "update", parameterObject);
	}

	public int delete(Role parameterObject) {
		return delete(QUERY_ID_PREFIX + "delete", parameterObject);
	}


	/**
	 * Role 메뉴 목록
	 * @return 메뉴 목록
	 */
	public List<Menu> findMenuAll(Menu parameterObject) {
		return selectList(QUERY_ID_PREFIX + "findMenuAll", parameterObject);
	}

}

