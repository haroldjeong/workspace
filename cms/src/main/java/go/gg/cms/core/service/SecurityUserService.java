package go.gg.cms.core.service;

import com.google.common.base.Splitter;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import go.gg.cms.core.domain.User;
import go.gg.cms.core.domain.UserRole;
import go.gg.cms.core.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Spring Security 사용자 인증 성공/실패에 대한 이후 서비스 로직
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.29
 * @version 1.0.0
 */
@Service
public class SecurityUserService extends EgovAbstractMapper {

	protected Logger logger = LoggerFactory.getLogger(SecurityUserService.class);
	private final String QUERY_ID_PREFIX = "go.gg.cms.core.security.";

	@Autowired
	private MenuService menuService;

	/**
	 * 관리자 정보 조회
	 *
	 * @param loginId
	 * @return
	 */
	public User getUserInfo(String loginId) {
		logger.debug("Fetching to login information with '{}'", loginId);
		User user = new User();

		// todo: 추후 로그인 정보 연계 필요 (일단 하드코딩) (jm.lee)
		user.setLoginId(loginId);
		//user.setLoginId("jm.lee");

		// todo: 추후 쿼리 수정 필요 (권한(Role) 체계 확정 후, 권한정보 포함하여 조회) (jm.lee)
		user = super.selectOne(QUERY_ID_PREFIX + "findByLoginId", user);

		if (null == user) {
			throw new UsernameNotFoundException(loginId);
		}


		// todo: 케시 삭제????
		menuService.evictCache(loginId);

		// todo: 권한(Role) 체계 확정 후 구현 예정 (jm.lee)
//		List<String> roles = Splitter.on(",").splitToList(user.getAuthorities());
		List<String> roles = Splitter.on(",").splitToList("ROLE_MASTER,ROLE_MANAGER");
		List<UserRole> managerRoles = roles.stream().map(roleId -> {
			return new UserRole(loginId, roleId);
		}).collect(Collectors.toList());

		user.setRoles(managerRoles);
		return user;
	}

	/**
	 * 로그인 성공 처리
	 * 로그인 실패횟수 초기화
	 * @param userId 아이디
	 */
	@Transactional
	public void loginSuccess(String userId) {
		this.loginSuccess(this.getUserInfo(userId));

	}
	/**
	 * 로그인 성공 처리
	 * 로그인 실패횟수 초기화
	 * @param user
	 */
	@Transactional
	public void loginSuccess(User user) {
		// todo: 로그인 실패횟수 Update (초기화) 구현 필요 (jm.lee)
		//super.update(QUERY_ID_PREFIX, "InitLoginFail", user);
	}

	/**
	 * 로그인 실패
	 * 로그인 실패횟수 추가
	 * @param userId
	 */
	@Transactional
	public int updateFailCnt(String userId) {
		User user = this.getUserInfo(userId);
		return this.updateFailCnt(user);
	}
	/**
	 * 로그인 실패
	 * 로그인 실패횟수 추가
	 * @param user
	 */
	@Transactional
	public int updateFailCnt(User user) {
		if (null != user) {
			// todo: 로그인 실패횟수 Update
			//super.update(QUERY_ID_PREFIX, "LoginFail", user);
			return user.getLoginFail() + 1;
		} else {
			return 9999;
		}
	}

	/**
	 * 관리자 계정 블럭 처리
	 * 계정 블럭 처리
	 * @param userId
	 * @throws CloneNotSupportedException
	 */
	public void saveBlock (String userId) {
		User user = this.getUserInfo(userId);
		this.saveBlock(user);
	}
	/**
	 * 관리자 계정 블럭 처리
	 * 계정 블럭 처리
	 * @param user
	 * @throws CloneNotSupportedException
	 */
	public void saveBlock (User user) {
		// todo: 관리자 계정 블럭 처리 로직 구현 필요 (jm.lee)
	}

	/**
	 * 관리자 비밀번호 변경 임시키 설정
	 * @param user
	 */
	public void updateChangePwdKey (User user) {
		// todo: 관리자 비밀번호 변경 임시키 설정 로직 구현 필요 (jm.lee)
	}

	/**
	 * 관리자 임시 비밀번호 변경 대상 조회
	 * @param user
	 * @return
	 */
	public User findByChangePwd (User user) {
		// todo: 관리자 임시 비밀번호 변경 대상 조회 로직 구현 필요. (일단 신규 User 리턴으로 하드코딩) (jm.lee)
		return new User();
	}

	/**
	 * 관리자 비밀번호 변경처리
	 * @param manager
	 * @return
	 */
	public int updateChangePwd (User manager) {
		// todo: 관리자 비밀번호 변경처리 로직 구현 필요. (일단 신규 User 리턴으로 하드코딩) (jm.lee)
		return 0;
	}

	/**
	 * 관리자 이력 생성
	 * @param historyType 로그 종류 (로그인 성공, 실패사유, 차단 등등)
	 * @param loginId 관리자 아이디 (UUID)
	 * @param description 그 외 설명문구
	 */
	public void saveHistory(String historyType, String loginId, String description) {
		logger.debug("[LOGIN HISTORY] {}, {}, {}", historyType, loginId, description);
		// todo: 관리자 이력 생성 로직 구현 필요 (jm.lee)
	}
}
