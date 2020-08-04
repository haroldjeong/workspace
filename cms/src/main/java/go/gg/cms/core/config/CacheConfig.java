package go.gg.cms.core.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@EnableCaching(proxyTargetClass = true, mode = AdviceMode.PROXY)
public class CacheConfig {

	/** EHCache 기반 CacheManager 설정 */
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheManagerFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:/ehcache.xml"));
		ehCacheManagerFactoryBean.setShared(true);
		return ehCacheManagerFactoryBean;
	}

	/** ehcache.xml 설정 로드 */
	@Bean
	public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean ehCacheManagerFactoryBean) {
		EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
		ehCacheCacheManager.setCacheManager(ehCacheManagerFactoryBean.getObject());
		return ehCacheCacheManager;
	}
}
