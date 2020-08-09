package go.gg.cms.core.domain;

import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.List;

/**
 * CMS 관리자 도메인
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
@Alias("CmsUser")
public class User extends Database {

	private static final long serialVersionUID = 6803271531794132035L;

	// 임시 키
	private String tempKey;

	// 임시 키 만료 일자
	private LocalDateTime tempKeyExpireDt;

	// 접속 허용 아이피
	private String allowIp;

	// 비밀번호 초기화 여부
	private boolean isPwInit;

	// 관리자

	// 관리자 권한 목록
	private List<UserRole> roles;

	// 검색조건
	private String searchChannel;

	private String loginId;
	private String loginPw;
	private String groupId;
	private String groupName;
	private String userType;
	private String profileFileId;
	private String name;
	private String email;
	private String mobile;
	private int loginFail;
	private String salt;
	private String status;
	private LocalDateTime expDate;

	public String getTempKey() {
		return tempKey;
	}

	public void setTempKey(String tempKey) {
		this.tempKey = tempKey;
	}

	public LocalDateTime getTempKeyExpireDt() {
		return tempKeyExpireDt;
	}

	public void setTempKeyExpireDt(LocalDateTime tempKeyExpireDt) {
		this.tempKeyExpireDt = tempKeyExpireDt;
	}

	public String getAllowIp() {
		return allowIp;
	}

	public void setAllowIp(String allowIp) {
		this.allowIp = allowIp;
	}

	public boolean isPwInit() {
		return isPwInit;
	}

	public void setPwInit(boolean pwInit) {
		isPwInit = pwInit;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPw() {
		return loginPw;
	}

	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}

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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getProfileFileId() {
		return profileFileId;
	}

	public void setProfileFileId(String profileFileId) {
		this.profileFileId = profileFileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getLoginFail() {
		return loginFail;
	}

	public void setLoginFail(int loginFail) {
		this.loginFail = loginFail;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getExpDate() {
		return expDate;
	}

	public void setExpDate(LocalDateTime expDate) {
		this.expDate = expDate;
	}

	public String getSearchChannel() {
		return searchChannel;
	}

	public void setSearchChannel(String searchChannel) {
		this.searchChannel = searchChannel;
	}
}
