package go.gg.cms.core.config;

import egovframework.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationManager;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;
import go.gg.cms.core.controller.BaseController;
import go.gg.cms.core.interceptor.CmsInterceptor;
import go.gg.cms.core.util.CmsPaginationRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import java.util.*;

/**
 * Spring Servlet Context
 * @apiNote 기존 eGovFramework dispatcher-servlet.xml -> Java Config 방식으로 변경
 * @apiNote 이 Config 파일이 Root Context인 CommonConfig.java와 함께 서버 기동 시 먼저 로드 (web.xml 정의)
 * @apiNote Spring MVC 환경 자체는 @EnableWebMvc 어노테이션으로 간단하게 자동 구성이 가능하나 전자정부 프레임워크는 그렇게 구성되어 있지 않기 때문에 (<mvc:annotation-driven /> 미사용) Spring WebMvcConfigurationSupport 클래스를 직접 상속받아 필요한 부분만 override, bean 등록 (jm.lee)
 * @apiNote 전자정부 기본 WebBindingInitializer 삭제 (사용하지 않을 기능 제거하기 위함), Init Binding 자체는 BaseContoller.java 에서 수행
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.20
 * @version 1.0.0
 */
@Configuration
@ComponentScan(
		basePackages="go.gg.cms",
		useDefaultFilters=false,
		includeFilters={
				@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Controller.class)
		},
		excludeFilters = {
				@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Service.class)
				, @ComponentScan.Filter(type=FilterType.ANNOTATION, value=Repository.class)
				, @ComponentScan.Filter(type=FilterType.ANNOTATION, value=Configuration.class)
		}
)
public class ServletConfig extends WebMvcConfigurationSupport {

	/** Request Mapping Handler (URL - Controller 연결) */
	@Bean
	@Override
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		// TODO Auto-generated method stub
		RequestMappingHandlerAdapter rmha = super.requestMappingHandlerAdapter();
		rmha.setWebBindingInitializer(new BaseController());
		return rmha;
	}

	/** 사용할 Interceptor 설정 */
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		registry
				.addInterceptor(cmsInterceptor())
				.excludePathPatterns(commonExcludes);
	}

	@Bean
	public HandlerInterceptor cmsInterceptor() {
		return new CmsInterceptor();
	}

	@Bean
	public SessionLocaleResolver localeResolver(){
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		// Set default locale in session.
		localeResolver.setDefaultLocale(Locale.KOREAN);
		return localeResolver;
	}

	/*
	// 쿠키를 이용한 Locale 이용시 이 부분을 주석처리를 풀어서 사용하고 위에 있는 SessionLocaleResolver 클래스를 사용하는
	// @Bean 메소드는 주석처리 한다
	@Bean
	public CookieLocaleResolver localeResolver(){
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		return cookieLocaleResolver;
	}
	 */

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor(){
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}


	/**
	 * WebMvcConfigurationSupport 클래스의 handlerExceptionResolver 메소드(@Bean 어노테이션이 선언되어 있기 때문에 Spring Bean을 등록하는 메소드이다)에서
	 * 파라미터로 넘긴 exceptionResolvers에 들어있는 ExceptionResolver들을 모두 관리하는 HandlerExceptionResolverComposite 클래스를 Spring Bean으로 등록한다.
	 * 그렇기때문에 특정 exceptionResolvers에 자신이 사용하고자 하는 ExceptionResolver들을 넣으면 된다
	 * 만약 이 작업을 하지 않으면 WebMvcConfigurationSupport 클래스의 handlerExceptionResolver 메소드는
	 * ExceptionHandlerExceptionResolver 클래스 객체와 ResponseStatusExceptionResolver 클래스 객체만을 ExceptionResolver로 사용한다
	 */
	@Override
	protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// TODO Auto-generated method stub
		SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
		smer.setDefaultErrorView("cmmn/egovError");
		Properties mappings = new Properties();
		mappings.setProperty("org.springframework.dao.DataAccessException", "cmmn/dataAccessFailure");
		mappings.setProperty("org.springframework.transaction.TransactionException", "cmmn/transactionFailure");
		mappings.setProperty("egovframework.rte.fdl.cmmn.exception.EgovBizException", "cmmn/egovError");
		mappings.setProperty("org.springframework.security.AccessDeniedException", "cmmn/egovError");
		smer.setExceptionMappings(mappings);
		exceptionResolvers.add(smer);
	}


	/** View Resolver 설정 & JSON Mapping Resolver 설정 (View Page 우선순위 : Tiles > JSP) */
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);

		// Define all possible view resolvers
		List<ViewResolver> resolvers = new ArrayList<>();

		resolvers.add(jsonViewResolver());
		resolvers.add(tilesViewResolver());
		resolvers.add(baseViewResolver());

		resolver.setViewResolvers(resolvers);
		return resolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions(new String[] {"/WEB-INF/tiles.xml"});
		return tiles;
	}

	@Bean
	public UrlBasedViewResolver tilesViewResolver(){
		UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
		urlBasedViewResolver.setOrder(1);
		urlBasedViewResolver.setViewClass(TilesView.class);
		urlBasedViewResolver.setExposedContextBeanNames("classpath:properties/globals.${spring.profiles.active}.properties");
		return urlBasedViewResolver;
	}

	@Bean
	public UrlBasedViewResolver baseViewResolver(){
		UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
		urlBasedViewResolver.setOrder(2);
		urlBasedViewResolver.setViewClass(JstlView.class);
		urlBasedViewResolver.setPrefix("/WEB-INF/jsp/view/");
		urlBasedViewResolver.setSuffix(".jsp");
		urlBasedViewResolver.setExposedContextBeanNames("classpath:properties/globals.${spring.profiles.active}.properties");
		return urlBasedViewResolver;
	}

	@Bean
	public ViewResolver jsonViewResolver() {
		return (viewName, locale) -> {
			MappingJackson2JsonView view = new MappingJackson2JsonView();
			view.setPrettyPrint(true);
			return view;
		};
	}

	@Bean
	public CmsPaginationRenderer cmsPageRenderer(){
		return new CmsPaginationRenderer();
	}

	@Bean
	public DefaultPaginationManager paginationManager(CmsPaginationRenderer cmsPageRenderer){
		DefaultPaginationManager defaultPaginationManager = new DefaultPaginationManager();
		Map<String, PaginationRenderer> rendererType = new HashMap<>();
		rendererType.put("cmsPage", cmsPageRenderer);
		defaultPaginationManager.setRendererType(rendererType);
		return defaultPaginationManager;
	}

	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addViewController("/cmmn/validator.do").setViewName("cmmn/validator");
	}

	/**
	 * 공통 Exclude 패턴
	 */
	private final String[] commonExcludes = {
			"/static/**"
			, "/**/error/**"
			, "/robots", "/robot", "/robot.txt", "/robots.txt", "/null"
			, "/WEB-INF/**"
			, "/common/file/stream/**"
	};
}
