package go.gg.cms.core.domain;

import java.util.List;

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

	private List<String> menus;

	private String readYn;
	private String writeYn;
	private String deleteYn;
	private String downYn;


	public List<String> getMenus() {
		return menus;
	}

	public void setMenus(List<String> menus) {
		this.menus = menus;
	}

	public String getReadYn() {
		return readYn;
	}

	public void setReadYn(String readYn) {
		this.readYn = readYn;
	}

	public String getWriteYn() {
		return writeYn;
	}

	public void setWriteYn(String writeYn) {
		this.writeYn = writeYn;
	}

	public String getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

	public String getDownYn() {
		return downYn;
	}

	public void setDownYn(String downYn) {
		this.downYn = downYn;
	}

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
