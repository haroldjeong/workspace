package go.gg.cms.core.service;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import go.gg.cms.core.domain.Menu;
import go.gg.cms.core.domain.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public int insert(Role condition) {
		condition.initId();
		condition.setRegId();
		condition.setRegIp();
		condition.setRegAgent();
		super.delete(QUERY_ID_PREFIX + "deleteMenuRole", condition);
		super.insert(QUERY_ID_PREFIX + "insertMenuRole", condition);
		return super.insert(QUERY_ID_PREFIX + "insert", condition);
	}



	@Transactional
	public int update(Role condition) {
		condition.setRegId();
		condition.setRegIp();
		condition.setRegAgent();
		condition.setModId();
		condition.setModIp();
		condition.setModAgent();
		super.delete(QUERY_ID_PREFIX + "deleteMenuRole", condition);
		super.insert(QUERY_ID_PREFIX + "insertMenuRole", condition);
		return super.update(QUERY_ID_PREFIX + "update", condition);
	}

	@Transactional
	public int delete(Role condition) {
		condition.setModId();
		condition.setModIp();
		condition.setModAgent();
		super.delete(QUERY_ID_PREFIX + "deleteMenuRole", condition);
		return super.delete(QUERY_ID_PREFIX + "delete", condition);
	}


	/**
	 * Role 메뉴 목록
	 * @return 메뉴 목록
	 */
	public List<Menu> findMenuAll(Menu parameterObject) {
		return selectList(QUERY_ID_PREFIX + "findMenuAll", parameterObject);
	}

}

