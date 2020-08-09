package go.gg.cms.apps.user.service;

import go.gg.cms.core.service.BaseService;
import go.gg.cms.core.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CMS 관리자 관리 기본 서비스
 * @apiNote 관리자 그룹 및 메뉴권한 등은 별도 서비스로 구현
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
@Service("apps.user.service")
public class UserService extends BaseService<User, String> {

	private final String QUERY_ID_PREFIX = "user";

	public List<User> find(User condition) {
		return super.find(QUERY_ID_PREFIX, "List", condition);
	}

	public int count(User condition) {
		return super.count(QUERY_ID_PREFIX, "List", condition);
	}

	public User detail(User condition) {
		return super.findBy(QUERY_ID_PREFIX, "Detail", condition);
	}

	public List<User> findGroup(User condition) {
		return super.find(QUERY_ID_PREFIX, "GroupList", condition);
	}

	@Transactional
	public int insert(User condition) {
		return super.insert(QUERY_ID_PREFIX, "Insert", condition);
	}

	@Transactional
	public int updateUser(User condition) {
		return super.update(QUERY_ID_PREFIX, "User", condition);
	}

	@Transactional
	public int updatePwd(User condition) {
		return super.update(QUERY_ID_PREFIX, "Pwd", condition);
	}

	@Transactional
	public int delete(User condition) {
		return super.delete(QUERY_ID_PREFIX, "User", condition);
	}

}
