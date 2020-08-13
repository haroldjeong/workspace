package go.gg.cms.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import go.gg.cms.core.domain.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * DEEP.FINE 공통코드 유틸리티
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
public class DeepfineCodeUtils {
	private final static Logger logger = LoggerFactory.getLogger(DeepfineCodeUtils.class);

	/**
	 * 코드 정보 조회
	 * @param codeSet 코드셋
	 * @param codeId 코드 아이디 (uuid)
	 * @return 코드 정보 (Code Class)
	 */
	@NotNull
	public static Code findById(String codeSet, String codeId) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(codeSet);
		Code findCode = new Code();

		for (JsonNode code : jsonNode) {
			if (codeId.equals(code.get("id").asText())) {
				findCode = Code.create(
						code.get("parentId").asText()
						, code.get("cd").asText()
						, code.get("name").asText());
				break;
			}
		}

		return findCode;
	}

	/**
	 * 코드 그룹 정보 조회
	 * @param codeSet 코드셋
	 * @param pathCd 경로 코드 (path_cd)
	 * @return 코드 정보 (Code Class)
	 */
	@NotNull
	public static List<Code> findListByPathCd(String codeSet, String pathCd) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(codeSet);
		List<Code> findCodeList = Lists.newArrayList();

		for (JsonNode code : jsonNode) {
			if (code.get("pathCd").asText().startsWith(pathCd + ".")) {

				Code findCode = new Code(code.get("parentId").asText(), code.get("cd").asText(), code.get("name").asText());
				findCode.setId(code.get("id").asText());
				findCodeList.add(findCode);
			}
		}

		logger.debug("==>{}", findCodeList);
		return findCodeList;
	}

}


