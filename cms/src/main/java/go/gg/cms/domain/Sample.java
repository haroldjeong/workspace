package go.gg.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import go.gg.cms.core.domain.Database;
import go.gg.common.annotation.MlProperty;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;

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
	private String fileId2;
	private String fileId3;
	private String dispSeq;
	private LocalDateTime testDate;

	private String uploadedFile;
	private List<String> uploadedFileList;
	private List<String> uploadedFileList2;

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

	public String getFileId2() {
		return fileId2;
	}

	public void setFileId2(String fileId2) {
		this.fileId2 = fileId2;
	}

	public String getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(String uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public List<String> getUploadedFileList() {
		return uploadedFileList;
	}

	public void setUploadedFileList(List<String> uploadedFileList) {
		this.uploadedFileList = uploadedFileList;
	}

	public List<String> getUploadedFileList2() {
		return uploadedFileList2;
	}

	public void setUploadedFileList2(List<String> uploadedFileList2) {
		this.uploadedFileList2 = uploadedFileList2;
	}

	public String getFileId3() {
		return fileId3;
	}

	public void setFileId3(String fileId3) {
		this.fileId3 = fileId3;
	}
}
