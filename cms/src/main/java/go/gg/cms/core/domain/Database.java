package go.gg.cms.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import go.gg.cms.core.util.ClientUtils;
import go.gg.cms.core.util.UserUtils;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

/**
 * Database 기본 도메인 추상 클래스
 * @apiNote CMS 내 모든 DB 연계 도메인은 본 추상 도메인을 상속받아 사용
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Database extends CmsBase {
	private static final long serialVersionUID = 6776029774807536998L;

	public Database () {}

	// 페이징 관련
	private int pageIndex = 1;							// 페이지 번호
	private int pageSize = 10;							// 페이지 사이즈 (페이징 부분의 페이지 번호 갯수)
	private int pageUnit = 20;							// 페이지 블럭 사이즈 (한 페이지당 보이는 게시물의 수)
	private int count = 0;								// 건수

	private LocalDateTime regDate;						// 등록 일자
	private String regId;								// 등록 아이디
	private String regIP;								// 등록 IP주소
	private String regAgent;							// 등록 User Agent
	private LocalDateTime modDate;						// 수정 일자
	private String modId;								// 수정 아이디
	private String modIp;								// 수정 IP주소
	private String modAgent;							// 수정 User Agent
	private String useYn;								// 사용여부
	private String delYn;								// 삭제여부
	private String regIp;								// 접속 IP Address
	private String userAgent;							// User Agent

	// 정렬조건 관련
	private Sort sort;

	// 검색조건 관련
	private String searchCondition;
	private String searchCondition1;
	private String searchCondition2;
	private String searchCondition3;
	private String searchKeyword;
	private String searchKeyword1;
	private String searchKeyword2;
	private String searchKeyword3;
	private String searchGroup;
	private String searchGroup1;
	private String searchGroup2;
	private String searchGroup3;
	private String searchCategory;
	private String searchCategory1;
	private String searchCategory2;
	private String searchCategory3;
	private String searchStatus;
	private String searchStatus1;
	private String searchStatus2;
	private String searchStatus3;
	private String searchCheckYn;
	private String searchCheckYn1;
	private String searchCheckYn2;
	private String searchCheckYn3;
	private String searchUseYn;
	private String searchStartDateStr;
	private String searchEndDateStr;
	private LocalDateTime searchStartDateTime;
	private LocalDateTime searchEndDateTime;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId() {
		this.regId = UserUtils.getUserInfo().getId();
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public LocalDateTime getModDate() {
		return modDate;
	}

	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}

	public String getModId() {
		return modId;
	}

	public void setModId() {
		this.modId = UserUtils.getUserInfo().getId();
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchCondition1() {
		return searchCondition1;
	}

	public void setSearchCondition1(String searchCondition1) {
		this.searchCondition1 = searchCondition1;
	}

	public String getSearchCondition2() {
		return searchCondition2;
	}

	public void setSearchCondition2(String searchCondition2) {
		this.searchCondition2 = searchCondition2;
	}

	public String getSearchCondition3() {
		return searchCondition3;
	}

	public void setSearchCondition3(String searchCondition3) {
		this.searchCondition3 = searchCondition3;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchKeyword1() {
		return searchKeyword1;
	}

	public void setSearchKeyword1(String searchKeyword1) {
		this.searchKeyword1 = searchKeyword1;
	}

	public String getSearchKeyword2() {
		return searchKeyword2;
	}

	public void setSearchKeyword2(String searchKeyword2) {
		this.searchKeyword2 = searchKeyword2;
	}

	public String getSearchKeyword3() {
		return searchKeyword3;
	}

	public void setSearchKeyword3(String searchKeyword3) {
		this.searchKeyword3 = searchKeyword3;
	}

	public String getSearchGroup() {
		return searchGroup;
	}

	public void setSearchGroup(String searchGroup) {
		this.searchGroup = searchGroup;
	}

	public String getSearchGroup1() {
		return searchGroup1;
	}

	public void setSearchGroup1(String searchGroup1) {
		this.searchGroup1 = searchGroup1;
	}

	public String getSearchGroup2() {
		return searchGroup2;
	}

	public void setSearchGroup2(String searchGroup2) {
		this.searchGroup2 = searchGroup2;
	}

	public String getSearchGroup3() {
		return searchGroup3;
	}

	public void setSearchGroup3(String searchGroup3) {
		this.searchGroup3 = searchGroup3;
	}

	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}

	public String getSearchCategory1() {
		return searchCategory1;
	}

	public void setSearchCategory1(String searchCategory1) {
		this.searchCategory1 = searchCategory1;
	}

	public String getSearchCategory2() {
		return searchCategory2;
	}

	public void setSearchCategory2(String searchCategory2) {
		this.searchCategory2 = searchCategory2;
	}

	public String getSearchCategory3() {
		return searchCategory3;
	}

	public void setSearchCategory3(String searchCategory3) {
		this.searchCategory3 = searchCategory3;
	}

	public String getSearchUseYn() {
		return searchUseYn;
	}

	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	public String getSearchStartDateStr() {
		return searchStartDateStr;
	}

	public void setSearchStartDateStr(String searchStartDateStr) {
		this.searchStartDateStr = searchStartDateStr;
	}

	public String getSearchEndDateStr() {
		return searchEndDateStr;
	}

	public void setSearchEndDateStr(String searchEndDateStr) {
		this.searchEndDateStr = searchEndDateStr;
	}

	public LocalDateTime getSearchStartDateTime() {
		return searchStartDateTime;
	}

	public void setSearchStartDateTime(LocalDateTime searchStartDateTime) {
		this.searchStartDateTime = searchStartDateTime;
	}

	public LocalDateTime getSearchEndDateTime() {
		return searchEndDateTime;
	}

	public void setSearchEndDateTime(LocalDateTime searchEndDateTime) {
		this.searchEndDateTime = searchEndDateTime;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp() {
		this.regIp = ClientUtils.getRemoteIP();
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

	public String getSearchStatus1() {
		return searchStatus1;
	}

	public void setSearchStatus1(String searchStatus1) {
		this.searchStatus1 = searchStatus1;
	}

	public String getSearchStatus2() {
		return searchStatus2;
	}

	public void setSearchStatus2(String searchStatus2) {
		this.searchStatus2 = searchStatus2;
	}

	public String getSearchStatus3() {
		return searchStatus3;
	}

	public void setSearchStatus3(String searchStatus3) {
		this.searchStatus3 = searchStatus3;
	}

	public String getSearchCheckYn() {
		return searchCheckYn;
	}

	public void setSearchCheckYn(String searchCheckYn) {
		this.searchCheckYn = searchCheckYn;
	}

	public String getSearchCheckYn1() {
		return searchCheckYn1;
	}

	public void setSearchCheckYn1(String searchCheckYn1) {
		this.searchCheckYn1 = searchCheckYn1;
	}

	public String getSearchCheckYn2() {
		return searchCheckYn2;
	}

	public void setSearchCheckYn2(String searchCheckYn2) {
		this.searchCheckYn2 = searchCheckYn2;
	}

	public String getSearchCheckYn3() {
		return searchCheckYn3;
	}

	public void setSearchCheckYn3(String searchCheckYn3) {
		this.searchCheckYn3 = searchCheckYn3;
	}

	public String getRegIP() {
		return regIP;
	}

	public void setRegIP(String regIP) {
		this.regIP = regIP;
	}

	public String getRegAgent() {
		return regAgent;
	}

	public void setRegAgent() {
		this.regAgent = ClientUtils.getUserAgent();
	}

	public void setRegAgent(String regAgent) {
		this.regAgent = regAgent;
	}

	public String getModIp() {
		return modIp;
	}

	public void setModIp() {
		this.modIp = ClientUtils.getRemoteIP();;
	}

	public void setModIp(String modIp) {
		this.modIp = modIp;
	}

	public String getModAgent() {
		return modAgent;
	}

	public void setModAgent() {
		this.modAgent = ClientUtils.getUserAgent();
	}

	public void setModAgent(String modAgent) {
		this.modAgent = modAgent;
	}
}
