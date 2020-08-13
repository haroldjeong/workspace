package go.gg.cms.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import go.gg.cms.core.domain.Code;
import go.gg.common.util.DeepfineUtils;
import go.gg.common.util.JsonUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * CMS 공통코드 서비스
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
@Service("core.code.service")
public class CodeService extends EgovAbstractMapper {

	private final String QUERY_ID_PREFIX = "go.gg.cms.core.commonCode.";

	/**
	 * 전체 공통코드 목록
	 * @return 계층형 Tree 구조 JSON String
	 */
	public String findCodeCacheHierarchy () {

		List<Code> codeList = super.selectList(QUERY_ID_PREFIX + "findCodeCache");

		// ROOT Code ID
		String rootId = codeList.stream().filter(
				code -> "ROOT".equals(code.getCd())&& "".equals(DeepfineUtils.defaultString(code.getParentId())))
				.findFirst().get().getId();

		List<Map<String, Object>> resultList = JsonUtils.convertorTreeMap(codeList, rootId, "id", "parentId", "cd", "seq");
		String result = "";

		try {
			result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(resultList);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}

//		logger.debug(result);
		return result;
	}

	/**
	 * 전체 공통코드 목록
	 * @return 2차원 배열 JSON String
	 */
	@Cacheable(cacheNames="codeCache", key="#root.methodName")
	public String findCodeCache() {
		Code condition = new Code();
		condition.setSearchUseYn("Y");

		List<Code> codeList = super.selectList(QUERY_ID_PREFIX + "findCodeCache", condition);
		String result = "";

		try {
			result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(codeList);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}

		logger.debug(result);
		return result;
	}

	/**
	 * 전체 공통코드 목록
	 * @return 코드 목록
	 */
	public List<Code> findAll() {
		return super.selectList(QUERY_ID_PREFIX + "findCodeCache");
	}

	/**
	 * 코드 상세정보
	 * @return 코드 상세정보
	 */
	public Code findById(Code condition) {
		return super.selectOne(QUERY_ID_PREFIX + "findById", condition);
	}

	/**
	 * 하위코드 추가
	 * @return 코드 상세정보
	 */
	@Transactional
	public Code insert(Code condition) {
		condition.setParentId(condition.getId());
		condition.initId();
		condition.setSeq(condition.getSeq() + 1);
		condition.setRegId();
		condition.setRegIp();
		condition.setRegAgent();
		super.insert(QUERY_ID_PREFIX + "insertMaster", condition);
		super.insert(QUERY_ID_PREFIX + "insertMl", condition);

		return condition;
	}

	/**
	 * 최상위 코드 추가
	 * @return 코드 상세정보
	 */
	@Transactional
	public Code insertTop(Code condition) {

		// 최상위 코드의 부모코드는 ROOT 코드
		Code root = super.selectOne(QUERY_ID_PREFIX + "findRooTCode");

		condition.setParentId(root.getId());
		condition.initId();
		condition.setSeq(condition.getSeq() + 1);
		condition.setRegId();
		condition.setRegIp();
		condition.setRegAgent();
		super.insert(QUERY_ID_PREFIX + "insertMaster", condition);
		super.insert(QUERY_ID_PREFIX + "insertMl", condition);

		return condition;
	}

	/**
	 * 코드정보 수정
	 */
	@Transactional
	public void update(Code condition) {
		condition.setModId();
		condition.setModIp();
		condition.setModAgent();
		super.update(QUERY_ID_PREFIX + "updateMaster", condition);
		super.delete(QUERY_ID_PREFIX + "deleteMl", condition);
		super.update(QUERY_ID_PREFIX + "insertMl", condition);
	}

	/**
	 * 코드정보 삭제
	 */
	@Transactional
	public void delete(Code condition) {
		condition.setModId();
		condition.setModIp();
		condition.setModAgent();
		super.delete(QUERY_ID_PREFIX + "delete", condition);
	}

	/**
	 * 코드 이동 및 순서변경
	 */
	@Transactional
	public void saveSeq(List<Code> condition) {
		if (condition != null && !condition.isEmpty()) {
			Code code = new Code();
			code.setCodes(condition);
			code.setModId();
			code.setModIp();
			code.setModAgent();
			super.update(QUERY_ID_PREFIX + "updateSeq", code);
		}
	}
}
