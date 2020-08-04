package go.gg.web.core.config;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jndi.JndiTemplate;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.IOException;
import java.util.Properties;

/**
 * Spring DB 연결 및 Connection Pool 환경설정
 * @apiNote 기존 eGovFramework context-datasource.xml + context-mapper.xml -> Java Config 방식으로 변경
 * @apiNote iBatis 관련 부분 모두 삭제. mybatis 사용만을 권장
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.20
 * @version 1.0.0
 */
@Configuration
public class DataSourceConfig {

	@Value("${db.postgresql.driverClassName}")
	private String POSTGRESQL_DRIVER;

	@Value("${db.postgresql.url}")
	private String POSTGRESQL_URL;

	@Value("${db.postgresql.username}")
	private String POSTGRESQL_USERNAME;

	@Value("${db.postgresql.password}")
	private String POSTGRESQL_PW;

	// JNDI 사용 없이, DataSource 직접 사용. DB 접속정보 Property 별도 관리 (jm.lee)
	@Bean(destroyMethod="close")
	public DataSource dataSource(){
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(POSTGRESQL_DRIVER);
		basicDataSource.setUrl(POSTGRESQL_URL);
		basicDataSource.setUsername(POSTGRESQL_USERNAME);
		basicDataSource.setPassword(POSTGRESQL_PW);
		return basicDataSource;
	}

	/*
	// JNDI Look-up 사용 시 해당 소스 주석 해제 (jm.lee)
	@Bean
	public DataSource dataSourceBasic() throws DataSourceLookupFailureException {
		JndiDataSourceLookup jdsl = new JndiDataSourceLookup();
		jdsl.setResourceRef(true);
		DataSource dataSource = jdsl.getDataSource("jdbc/postgresql");
		return dataSource;
	}
	*/

	/*
	// JNDI Template 사용 시 해당 소스 주석 해제  (jm.lee)
	@Bean
	public DataSource dataSourceBasic() throws NamingException {
		JndiTemplate jndiTemplate = new JndiTemplate();
		DataSource dataSource = jndiTemplate.lookup("java:comp/env/jdbc/oracle", DataSource.class);
		return dataSource;
	}
	*/

	// Mybatis Mapper Bean
	@Bean
	public SqlSessionFactoryBean sqlSession(DataSource dataSource) throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(pmrpr.getResource("classpath:/mybatis/config.xml"));
		sqlSessionFactoryBean.setMapperLocations(pmrpr.getResources("classpath:/mybatis/mapper/**/*-mapper.xml"));
		return sqlSessionFactoryBean;
	}


	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
