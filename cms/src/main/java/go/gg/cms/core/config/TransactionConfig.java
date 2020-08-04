package go.gg.cms.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Spring Transaction 환경설정
 * @apiNote 기존 eGovFramework context-transaction.xml -> Java Config 방식으로 변경
 * @apiNote 전자정부 프레임워크에서 사용하던 tx:advice 스키마를 이용한 AOP 방식 -> annotation-driven 방식으로 변경 (@EnableTransactionManagement 어노테이션 추가) (jm.lee)
 *              * 변경 이유 : tx:advice 방식은 포인트 컷을 이용하여 해당 클래스 전체를 AOP로 구현하는 트랜잭션
 *              * annotation-driven 방식을 이용할 경우 기능 구현 시 필요에 따라 @Transactional 어노테이션으로 각각 설정 가능
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.20
 * @version 1.0.0
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"go.gg.cms.core.config"})   // @ComponentScan : Bean 등록을 하였음에도 Autowired 에서 자동으로 찾지 못하는 경우 직접 해당 위치(패키지)를 지정하여 스캔하도록 설정. eclipse는 이런 현상이 없으나 IntelliJ 내에서 종종 발생 (jm.lee)
public class TransactionConfig {

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource){
		DataSourceTransactionManager dstm = new DataSourceTransactionManager();
		dstm.setDataSource(dataSource);
		return dstm;
	}
}
