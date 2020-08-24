package go.gg.cms.core.domain;

public class MasterFile extends Database {

	private static final long serialVersionUID = -2970800727120074618L;

	private String groupId;
	private String fileGroupCd;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getFileGroupCd() {
		return fileGroupCd;
	}

	public void setFileGroupCd(String fileGroupCd) {
		this.fileGroupCd = fileGroupCd;
	}
}
