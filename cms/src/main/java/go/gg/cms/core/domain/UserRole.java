package go.gg.cms.core.domain;

/**
 * CMS 관리자 권한 도메인
 * @author jm.lee (DEEP.FINE)
 * @since 2020.08.08
 * @version 1.0.0
 */
public class UserRole {

	// 관리자 아이디
	private String userId;

	// 권한 아이디
	private String roleId;

	public UserRole() {}

	public UserRole(String roleId) {
		this.roleId = roleId;
	}

	public UserRole(String userId, String roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
