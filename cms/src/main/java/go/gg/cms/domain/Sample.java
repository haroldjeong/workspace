package go.gg.cms.domain;

import go.gg.cms.core.domain.Database;
import go.gg.common.annotation.MlProperty;

import java.time.LocalDateTime;

/**
 * CMS 샘플 도메인 (tb_sample, tb_sample_ml)
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
public class Sample extends Database {

	private static final long serialVersionUID = 7308544418329763475L;

	private String groupId;
	private String groupName;

	private String categoryCd;
	private String categoryName;
	private String fileId;
	private String dispSeq;
	private LocalDateTime testDate;

	@MlProperty
	private String name;

	@MlProperty
	private String shortDesc;

	@MlProperty
	private String longDesc;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getDispSeq() {
		return dispSeq;
	}

	public void setDispSeq(String dispSeq) {
		this.dispSeq = dispSeq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public LocalDateTime getTestDate() {
		return testDate;
	}

	public void setTestDate(LocalDateTime testDate) {
		this.testDate = testDate;
	}
}
