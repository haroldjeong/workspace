package go.gg.web.core.service;

import com.google.common.collect.Lists;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import go.gg.web.core.domain.Database;
import go.gg.common.annotation.MlProperty;
import go.gg.common.util.DeepfineUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * WEB 기본 서비스 (추상 클래스)
 * @apiNote WEB 내 모든 서비스는 본 서비스를 상속받아 사용
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
public abstract class BaseService<T extends Database, K extends Serializable> extends EgovAbstractMapper {
	protected static final Logger logger = LoggerFactory.getLogger("service");

	/**
	 * Database Access & Data Object Mapping
	 */
	private static final String PREFIX_BASE_PREFIX = "go.gg.web.apps.";

	private final static String PREFIX_FIND_SINGLE = ".findBy";
	private final static String PREFIX_FIND_ALL = ".find";
	private final static String PREFIX_FIND_COUNT = ".count";
	private final static String PREFIX_INSERT = ".insert";
	private final static String PREFIX_UPDATE = ".update";
	private final static String PREFIX_DELETE = ".delete";


	protected List<T> find (String prefixId, String id, T condition) {
		return selectList(PREFIX_BASE_PREFIX + prefixId + PREFIX_FIND_ALL + id, condition);
	}

	protected Integer count (String prefixId, String id, T condition) {
		return (Integer) selectOne(PREFIX_BASE_PREFIX + prefixId + PREFIX_FIND_COUNT + id, condition);
	}

	protected T findBy (String prefixId, String id, T condition) {
		return selectOne(PREFIX_BASE_PREFIX + prefixId + PREFIX_FIND_SINGLE + id, condition);
	}

	protected Integer insert (String prefixId, String id, T condition) {
		return insert(PREFIX_BASE_PREFIX + prefixId + PREFIX_INSERT + id, condition);
	}

	protected Integer update (String prefixId, String id, T condition) {
		return update(PREFIX_BASE_PREFIX + prefixId + PREFIX_UPDATE + id, condition);
	}

	protected Integer delete (String prefixId, String id, T condition) {
		return delete(PREFIX_BASE_PREFIX + prefixId + PREFIX_DELETE + id, condition);
	}

	// todo: 디바이스별 채널/다국가/다국어 확장 시 여기에 공통 parsing 및 re-serializing 로직 구현 예정 (jm.lee)
	// todo: 로그인 사용자(관리자)의 권한 및 유효성 검증에 대한 공통 validation 로직 구현 예정 (jm.lee)
	// todo: AOP 관련 Annotation 생성 후, AOP Handler 연동 예정 (jm.lee)

	/**
	 * Multi Language Parsing & Serializing
	 */
	protected T parseMl(List<T> list, T result) throws Exception {

		if (list.size() > 0) {
			result = list.get(0);
			List<String> fieldList = getMlPropertyFields(result);

			System.out.println("?????????????================");
			System.out.println(fieldList.size());
			System.out.println(fieldList.get(0));
			System.out.println("?????????????================");

			System.out.println("333333333333");
			System.out.println(result);
		} else {
			String grpNo = DeepfineUtils.generateUUID();

		}
		return result;
	}

	private List<String> getMlPropertyFields(T domain) throws Exception {
		Class clazz = domain.getClass();
		List<String> resultList = Lists.newArrayList();
		Field[] declaredFields = clazz.getDeclaredFields();

		for (Field field : declaredFields) {
			if (field.getAnnotation(MlProperty.class) != null) {
				resultList.add(field.getName());
			}
		}
		return resultList;
	}

}
