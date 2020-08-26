package go.gg.cms.core.config;

import egovframework.rte.fdl.cmmn.aspect.ExceptionTransfer;
import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;
import egovframework.rte.fdl.cmmn.exception.manager.DefaultExceptionHandleManager;
import go.gg.cms.core.handler.AopDefaultHandler;
import go.gg.cms.core.handler.DefaultExceptionHandler;
import go.gg.cms.core.handler.OthersExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.AntPathMatcher;

/**
 * Spring Aspect 관련 환경설정
 * @apiNote 기존 eGovFramework context-aspect.xml -> Java Config 방식으로 변경
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.20
 * @version 1.0.0
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class AspectConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger("configuration");

	@Bean
	public AopDefaultHandler aopExceptionTransfer(ExceptionTransfer exceptionTransfer){
		AopDefaultHandler aopExceptionTransfer = new AopDefaultHandler();
		aopExceptionTransfer.setExceptionTransfer(exceptionTransfer);
		return aopExceptionTransfer;
	}

	@Bean
	public ExceptionTransfer exceptionTransfer(@Qualifier("defaultExceptionHandleManager") DefaultExceptionHandleManager defaultExceptionHandleManager
			, @Qualifier("otherExceptionHandleManager") DefaultExceptionHandleManager otherExceptionHandleManager){

		ExceptionTransfer exceptionTransfer = new ExceptionTransfer();
		exceptionTransfer.setExceptionHandlerService(new DefaultExceptionHandleManager [] {defaultExceptionHandleManager, otherExceptionHandleManager});
		return exceptionTransfer;
	}

	@Bean
	public DefaultExceptionHandleManager defaultExceptionHandleManager(AntPathMatcher antPathMater, DefaultExceptionHandler defaultHandler){
		LOGGER.debug("AspectConfig ::: DefaultExceptionHandleManager > defaultExceptionHandleManager");
		DefaultExceptionHandleManager defaultExceptionHandleManager = new DefaultExceptionHandleManager();
		defaultExceptionHandleManager.setReqExpMatcher(antPathMater);
		defaultExceptionHandleManager.setPatterns(new String[]{"**controller.*"});
		defaultExceptionHandleManager.setHandlers(new ExceptionHandler[]{defaultHandler});
		return defaultExceptionHandleManager;
	}

	@Bean
	public DefaultExceptionHandleManager otherExceptionHandleManager(AntPathMatcher antPathMater, OthersExceptionHandler otherHandler){
		LOGGER.debug("AspectConfig ::: DefaultExceptionHandleManager > otherExceptionHandleManager");
		DefaultExceptionHandleManager otherExceptionHandleManager = new DefaultExceptionHandleManager();
		otherExceptionHandleManager.setReqExpMatcher(antPathMater);
		otherExceptionHandleManager.setPatterns(new String[]{"**controller.*"});
		otherExceptionHandleManager.setHandlers(new ExceptionHandler[]{otherHandler});
		return otherExceptionHandleManager;

	}

	@Bean
	public DefaultExceptionHandler defaultHandler(){
		return new DefaultExceptionHandler();
	}

	@Bean
	public OthersExceptionHandler otherHandler(){
		return new OthersExceptionHandler();
	}
}
