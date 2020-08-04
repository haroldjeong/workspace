package go.gg.cms.core.handler;

import egovframework.rte.fdl.cmmn.aspect.ExceptionTransfer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * AOP Exception Transfer (AOP 시점 사용자 정의)
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.20
 * @version 1.0.0
 */
@Aspect
public class AopDefaultHandler {

	private ExceptionTransfer exceptionTransfer;

	public void setExceptionTransfer(ExceptionTransfer exceptionTransfer) {
		this.exceptionTransfer = exceptionTransfer;
	}

	/* ↓↓↓↓↓↓↓ 필요 AOP 시점 추가 (반드시 추가자 명시!!!) Start ↓↓↓↓↓↓↓ */
	// AOP 설정 : 필요에 따라 AOP 실행 시점 추가

	// 예시 : go.gg.cms.apps 패키지 하위에 controller 패키지에 속하고 Impl로 끝나는 모든 클래스의 모든 메소드(파라미터 갯수가 0개 이상이며 어떠한 타입의 어떠한 이름의 파라미터여도 상관없음) 실행 시 아래 로직 실행 (jm.lee)
	@Pointcut("execution(* go.gg.cms.apps..controller.*Impl.*(..))")
	private void exceptionTransferService() {}

	/* ↑↑↑↑↑↑↑ 필요 Property 추가 (반드시 추가자 명시!!!) End ↑↑↑↑↑↑↑ */

	@AfterThrowing(pointcut = "exceptionTransferService()", throwing="ex")
	public void doAfterThrowingExceptionTransferService(JoinPoint thisJoinPoint, Exception ex) throws Exception{
		exceptionTransfer.transfer(thisJoinPoint, ex);
	}
}
