package go.gg.cms.core.aspect;

import egovframework.rte.fdl.cmmn.aspect.ExceptionTransfer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 관리자 권한처리 관련 AOP
 * @author jm.lee (DEEP.FINE)
 * @since 2020.08.25
 * @version 1.0.0
 */
@Component
@Aspect
public class ExceptionAspect {
	private Logger logger = LoggerFactory.getLogger("Exception Aspect");

	private ExceptionTransfer exceptionTransfer;

	public void setExceptionTransfer(ExceptionTransfer exceptionTransfer) {
		this.exceptionTransfer = exceptionTransfer;
	}

	@Pointcut("execution(* go.gg.cms..*(..))")
	private void exceptionTransferService() {}

	@AfterThrowing(pointcut="exceptionTransferService()", throwing="ex")
	public void exceptionAspect (final JoinPoint joinPoint, Exception ex) throws Throwable {
		logger.debug("error message: {}", ex.getMessage());
		ex.printStackTrace();

		// todo: Exception Logic
		// todo: 시스템 오류 로그 테이블 Insert
		// todo: Exception Handler로 전달하여 오류 공통처리
//		exceptionTransfer.transfer(joinPoint, ex);
	}
}
