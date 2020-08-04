package go.gg.cms.core.handler;

import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 기타 Exception 핸들러
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.20
 * @version 1.0.0
 */
public class OthersExceptionHandler implements ExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger("ExceptionHandler");

	@Override
	public void occur(Exception ex, String packageName) {
		LOGGER.debug(" Default ExceptionHandler");
	}
}
