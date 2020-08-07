package go.gg.cms.core.domain;

/**
 * CMS 룰 도메인 (tb_role)
 * @author jh.park (DEEP.FINE)
 * @since 2020.08.05
 * @version 1.0.0
 */
public class Role extends Database {


	private static final long serialVersionUID = 1681072909830176927L;

	private String roleCd;
	private String name;
	private String roleDesc;

	public String getRoleCd() {
		return roleCd;
	}

	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
}
