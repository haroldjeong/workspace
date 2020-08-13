package go.gg.cms.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.Sets;
import go.gg.common.util.DeepfineUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 공통 코드 도메인
 * @apiNote 코드 비교 및 정렬 편의성을 위해 Comparable interface 사용
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(Include.NON_EMPTY)
@Alias("CommonCode")
public class Code extends Database implements Comparable<Code>{

	private static final long serialVersionUID = 1170130672008639172L;

	private String parentId;			// 부모 코드
	private String cd;					// 코드
	private String depth;				// Depth (Level)
	private String name;				// 이름
	private String longDesc;			// 긴 이름
	private String shrtDesc;			// 짧은 이름
	private int seq = 1;				// 순번
	private int childCnt = 0;			// 하위 코드 갯수

	private String customField1;		// 사용자 정의 값 1
	private String customField2;		// 사용자 정의 값 2
	private String customField3;		// 사용자 정의 값 3
	private String useYn;				// 사용 여부

	private String pathId;
	private String pathCd;
	private String pathName;

	private List<Code> codes;

	private Map<String, String> nameMap;	// 다국어맵
	private Set<Code> children = Sets.newTreeSet();		// 자식코드

	public Code() {}

	public Code(String cd) {
		this.cd = cd;
	}

	public Code(String parentId, String cd) {
		this.cd = cd;
		this.parentId = parentId;
	}

	public Code(String parentId, String cd, String name) {
		this.cd = cd;
		this.parentId = parentId;
		this.name = name;
	}

	public Code(String parentId, String cd, String name, int seq) {
		this.cd = cd;
		this.parentId = parentId;
		this.name = name;
		this.seq = seq;
	}

	public Code(String parentId, String cd, String name, int seq, Set<Code> children) {
		this(parentId, cd, name, seq);
		this.setChildren(children);
	}

	public Code(String parentId, String cd, String name
			, String customField1, String customField2, String customField3) {
		this.parentId = parentId;
		this.cd = cd;
		this.name = name;
		this.customField1 = customField1;
		this.customField2 = customField2;
		this.customField3 = customField3;
	}

	public Code(String parentId, String cd, String name, String longDesc, String shrtDesc) {
		this.parentId = parentId;
		this.cd = cd;
		this.name = name;
		this.longDesc = longDesc;
		this.shrtDesc = shrtDesc;
	}

	public Code(String parentId, String cd, String name, String longDesc, String shrtDesc
			, String customField1, String customField2, String customField3) {
		this.parentId = parentId;
		this.cd = cd;
		this.name = name;
		this.longDesc = longDesc;
		this.shrtDesc = shrtDesc;
		this.customField1 = customField1;
		this.customField2 = customField2;
		this.customField3 = customField3;
	}

	public Code(String parentId, String cd, String name, int seq
			, String customField1, String customField2, String customField3) {
		this.parentId = parentId;
		this.cd = cd;
		this.name = name;
		this.seq = seq;
		this.customField1 = customField1;
		this.customField2 = customField2;
		this.customField3 = customField3;
	}

	public Code(String parentId, String cd, String name, String longDesc, String shrtDesc, int seq
			, String customField1, String customField2, String customField3) {
		this.parentId = parentId;
		this.cd = cd;
		this.name = name;
		this.longDesc = longDesc;
		this.shrtDesc = shrtDesc;
		this.seq = seq;
		this.customField1 = customField1;
		this.customField2 = customField2;
		this.customField3 = customField3;
	}

	public Code(String parentId, String cd, String name, int seq, String customField1,
	            String customField2, String customField3, Set<Code> children) {
		this(parentId, cd, name, seq, customField1, customField2, customField3);
		this.setChildren(children);
	}

	public Code(String parentId, String cd, String name, int seq, String customField1,
	            String customField2, String customField3, Map<String, String> nameMap) {
		this(parentId, cd, name, seq, customField1, customField2, customField3);
		this.setNameMap(nameMap);
	}

	public Code(String parentId, String cd, String name, String longDesc, String shrtDesc, int seq, String customField1,
	            String customField2, String customField3, Map<String, String> nameMap) {
		this(parentId, cd, name, longDesc, shrtDesc, seq, customField1, customField2, customField3);
		this.setNameMap(nameMap);
	}

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

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getCustomField1() {
		return customField1;
	}

	public void setCustomField1(String customField1) {
		this.customField1 = customField1;
	}

	public String getCustomField2() {
		return customField2;
	}

	public void setCustomField2(String customField2) {
		this.customField2 = customField2;
	}

	public String getCustomField3() {
		return customField3;
	}

	public void setCustomField3(String customField3) {
		this.customField3 = customField3;
	}

	public Set<Code> getChildren() {
		return children;
	}

	public void setChildren(Set<Code> children) {
		this.children = children;
	}

	public Map<String, String> getNameMap() {
		return nameMap;
	}

	public void setNameMap(Map<String, String> nameMap) {
		this.nameMap = nameMap;
	}

	@Override
	public String toString() {
		return StringUtils.defaultString(this.name, this.cd);
	}

	@Override
	public int compareTo(Code o) {
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

		if (!(obj instanceof Code)) {
			return false;
		}

		Code target = (Code) obj;
		return DeepfineUtils.equalsIgnoreCase(this.getId(), target.getId());
	}

	@Override
	public int hashCode() {
		return cd.hashCode();
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public List<Code> getCodes() {
		return codes;
	}

	public void setCodes(List<Code> codes) {
		this.codes = codes;
	}

	public static Code create(String parentId, String cd, String name) {
		return new Code(parentId, cd, name);
	}

	public static Code create (String parentId, String cd, String name, String customField1, String customField2, String customField3) {
		return new Code(parentId, cd, name, customField1, customField2, customField3);
	}
}
