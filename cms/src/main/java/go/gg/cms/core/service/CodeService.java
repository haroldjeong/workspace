package go.gg.cms.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import go.gg.cms.core.domain.Code;
import go.gg.common.util.DeepfineUtils;
import go.gg.common.util.JsonUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * CMS 공통코드 서비스
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
@Service("core.service.code")
public class CodeService extends EgovAbstractMapper {

	/**
	 * 전체 공통코드 목록
	 * @return 계층형 Tree 구조 JSON String
	 */
	public String findCodeCacheHierarchy () {

		List<Code> codeList = selectList("go.gg.cms.core.commonCode.findCodeCache");

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
		List<Code> codeList = selectList("go.gg.cms.core.commonCode.findCodeCache");
		String result = "";

		try {
			result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(codeList);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}

		logger.debug(result);
		return result;
	}
}
