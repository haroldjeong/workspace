package go.gg.cms.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import go.gg.cms.core.domain.SecurityUserDetail;
import go.gg.cms.core.domain.User;
import go.gg.common.util.DeepfineUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * DEEP.FINE 관리자 정보 유틸리티
 * @author jm.lee (DEEP.FINE)
 * @since 2020.08.08
 * @version 1.0.0
 */
public class UserUtils {
	private final static Logger logger = LoggerFactory.getLogger(UserUtils.class);

	/**
	 * 캐싱된 관리자 정보 조회
	 * @return 관리자 정보 (User Class)
	 */
	public static User getCachedUser (String userSet, String key) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(userSet);
		User findUser = new User();

		for (JsonNode user : jsonNode) {
			if (key.equals(user.get("id").asText())) {
				findUser = User.create(
						user.get("id").asText()
						, user.get("loginId").asText()
						, user.get("name").asText()
						, user.get("userType").asText()
						, user.get("groupId").asText()
						, user.get("groupName").asText());
				break;
			}
		}

		return findUser;
	}

	/**
	 * 로그인 관리자 정보 조회
	 * @return 로그인 관리자 정보 (User Class)
	 */
	public static User getUserInfo () {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			return null;
		} else {
			try {
				SecurityUserDetail userDetails = (SecurityUserDetail) authentication.getDetails();
				return userDetails.getUserInfo();
			} catch (ClassCastException e) {
				logger.debug("UserUtils ::: getUserInfo ::: ClassCastException");
				return null;
			}
		}
	}

	public static boolean isSystem () {
		User user = UserUtils.getUserInfo();

		if (null == user) {
			return false;
		}
		try {
			return UserUtils.hasRole("SYSTEM");
		} catch(Exception e) {
			return false;
		}
	}

	public static boolean hasRole (String ROLE) {
		User user = UserUtils.getUserInfo();
		if (null == user) {
			return false;
		}
		try {
			return user.getRoles().stream().filter(role -> DeepfineUtils.containsIgnoreCase(role.getRoleId(), ROLE)).count() > 0;
		} catch(Exception e) {
			return false;
		}
	}

}


