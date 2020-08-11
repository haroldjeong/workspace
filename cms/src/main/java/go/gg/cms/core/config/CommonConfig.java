package go.gg.cms.core.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import egovframework.rte.fdl.cmmn.trace.LeaveaTrace;
import egovframework.rte.fdl.cmmn.trace.handler.DefaultTraceHandler;
import egovframework.rte.fdl.cmmn.trace.handler.TraceHandler;
import egovframework.rte.fdl.cmmn.trace.manager.DefaultTraceHandleManager;
import egovframework.rte.fdl.cmmn.trace.manager.TraceHandlerService;

/**
 * Spring 공통(기본) 환경설정 (Root Context)
 * @apiNote 기존 eGovFramework context-common.xml -> Java Config 방식으로 변경
 * @apiNote 서버 시작 시 web.xml 설정에 따라 이 Config 파일이 먼저 로드되고, @Import 어노테이션을 통해 그 외 Config 파일을 로드한다.
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.20
 * @version 1.0.0
 */
@Configuration
@ComponentScan(
		basePackages="go.gg.cms",
		includeFilters={
				@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Service.class)
				, @ComponentScan.Filter(type=FilterType.ANNOTATION, value=Repository.class)
				, @ComponentScan.Filter(type=FilterType.ANNOTATION, value=Controller.class)
				, @ComponentScan.Filter(type=FilterType.ANNOTATION, value=Configuration.class)
		}
)
@Import({AspectConfig.class, DataSourceConfig.class, PropertiesConfig.class, TransactionConfig.class, CacheConfig.class})
public class CommonConfig {

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource rrbms = new ReloadableResourceBundleMessageSource();
		// 메세지 프로퍼티파일의 위치와 이름을 지정한다.
		rrbms.setBasename("classpath:/message/message-common");
		// 기본 인코딩을 지정한다.
		rrbms.setDefaultEncoding("UTF-8");
		// 프로퍼티 파일의 변경을 감지할 시간 간격을 지정한다.
		rrbms.setCacheSeconds(60);
		// 없는 메세지일 경우 예외를 발생시키는 대신 코드를 기본 메세지로 한다.
		rrbms.setUseCodeAsDefaultMessage(true);
		return rrbms;
	}


	@Bean
	public LeaveaTrace leaveaTrace(DefaultTraceHandleManager traceHandlerService){
		LeaveaTrace leaveaTrace = new LeaveaTrace();
		leaveaTrace.setTraceHandlerServices(new TraceHandlerService[]{traceHandlerService});
		return leaveaTrace;
	}

	@Bean
	public DefaultTraceHandleManager traceHandlerService(AntPathMatcher antPathMater, DefaultTraceHandler defaultTraceHandler){
		DefaultTraceHandleManager defaultTraceHandleManager = new DefaultTraceHandleManager();
		defaultTraceHandleManager.setReqExpMatcher(antPathMater);
		defaultTraceHandleManager.setPatterns(new String[]{"*"});
		defaultTraceHandleManager.setHandlers(new TraceHandler[]{defaultTraceHandler});
		return defaultTraceHandleManager;
	}

	@Bean
	public AntPathMatcher antPathMater(){
		AntPathMatcher antPathMatcher = new AntPathMatcher();
		return antPathMatcher;
	}

	@Bean
	public DefaultTraceHandler defaultTraceHandler(){
		DefaultTraceHandler defaultTraceHandler = new DefaultTraceHandler();
		return defaultTraceHandler;
	}

}
