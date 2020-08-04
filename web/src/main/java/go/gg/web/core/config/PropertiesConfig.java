package go.gg.web.core.config;

import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.property.impl.EgovPropertyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring Property 환경설정
 * @apiNote 기존 eGovFramework context-properties.xml -> Java Config 방식으로 변경
 * @apiNote @PropertySource 어노테이션을 통해 실제 사용할 globals.properties 파일을 로드한다.
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.20
 * @version 1.0.0
 */
@Configuration
@PropertySource("classpath:properties/globals.${spring.profiles.active}.properties")
public class PropertiesConfig {

	@Bean(destroyMethod="destroy")
	public EgovPropertyServiceImpl propertiesService() throws FdlException{
		Map<String, String> properties = new HashMap<>();
		properties.put("pageUnit", "10");
		properties.put("pageSize", "10");

		EgovPropertyServiceImpl egovPropertyServiceImpl = new EgovPropertyServiceImpl();
		egovPropertyServiceImpl.setProperties(properties);

		return egovPropertyServiceImpl;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
