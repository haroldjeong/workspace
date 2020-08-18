package go.gg.cms.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Sets;
import go.gg.common.util.DeepfineUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * CMS 메뉴 도메인 (tb_menu)
 * @author jh.park (DEEP.FINE)
 * @since 2020.08.11
 * @version 1.0.0
 */

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Alias("CommonMenu")
public class Menu extends Database implements Comparable<Menu>{

	private static final long serialVersionUID = -467867494507513247L;

	private String parentId;			// 부모 코드
	private String cd;					// 코드
	private String depth;				// Depth (Level)
	private String name;				// 이름
	private String linkUrl;				// 링크 URL
	private String linkType;			// 링크타입
	private String useYn;				// 사용 여부

	private String iconType;				// 아이콘 타입

	private String longDesc;			// 긴 이름
	private String shrtDesc;			// 짧은 이름

	private int seq = 1;				// 순번
	private int childCnt = 0;			// 하위 코드 갯수

	private String pathId;
	private String pathCd;
	private String pathName;


	private List<Menu> menus;

	private String roleId;
	private String readYn;
	private String writeYn;
	private String downYn;
	private String deleteYn;



	private Map<String, String> nameMap;	// 다국어맵
	private Set<Menu> children = Sets.newTreeSet();		// 자식코드


	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	@Override
	public String getUseYn() {
		return useYn;
	}

	@Override
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getIconType() {
		return iconType;
	}

	public void setIconType(String iconType) {
		this.iconType = iconType;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public String getShrtDesc() {
		return shrtDesc;
	}

	public void setShrtDesc(String shrtDesc) {
		this.shrtDesc = shrtDesc;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getChildCnt() {
		return childCnt;
	}

	public void setChildCnt(int childCnt) {
		this.childCnt = childCnt;
	}

	public String getPathId() {
		return pathId;
	}

	public void setPathId(String pathId) {
		this.pathId = pathId;
	}

	public String getPathCd() {
		return pathCd;
	}

	public void setPathCd(String pathCd) {
		this.pathCd = pathCd;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Map<String, String> getNameMap() {
		return nameMap;
	}

	public void setNameMap(Map<String, String> nameMap) {
		this.nameMap = nameMap;
	}

	public Set<Menu> getChildren() {
		return children;
	}

	public void setChildren(Set<Menu> children) {
		this.children = children;
	}


	@Override
	public String toString() {
		return StringUtils.defaultString(this.name, this.cd);
	}

	@Override
	public int compareTo(Menu o) {
		if (this.getId().equals(o.getId())) {
			return 0;
		}

		return this.getSeq() > o.getSeq() ? 1 : -1;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Menu)) {
			return false;
		}

		Menu target = (Menu) obj;
		return DeepfineUtils.equalsIgnoreCase(this.getId(), target.getId());
	}

	@Override
	public int hashCode() {
		return cd.hashCode();
	}


	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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

	public String getDownYn() {
		return downYn;
	}

	public void setDownYn(String downYn) {
		this.downYn = downYn;
	}

	public String getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
}
