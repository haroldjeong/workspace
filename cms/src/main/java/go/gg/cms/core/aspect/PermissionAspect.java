package go.gg.cms.core.aspect;

import go.gg.cms.core.annotation.Permission;
import go.gg.cms.core.annotation.type.PERMISSION;
import go.gg.cms.core.domain.Menu;
import go.gg.cms.core.service.MenuService;
import go.gg.cms.core.util.UserUtils;
import go.gg.common.util.DeepfineUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 관리자 권한처리 관련 AOP
 * @author jm.lee (DEEP.FINE)
 * @since 2020.08.25
 * @version 1.0.0
 */
@Component
@Aspect
public class PermissionAspect {
	private Logger logger = LoggerFactory.getLogger("Permission Aspect");

	@Autowired
	HttpServletRequest request;

	@Autowired
	MenuService menuService;

	@Pointcut("execution(* go.gg.cms..controller.*Controller.*(..)) && @annotation(go.gg.cms.core.annotation.Permission)")
	public void permissionPointcut() {}


	/**
	 * 관리자 권한 체크 Aspect
	 * @apiNote Advice 시점: *Controller Class 하위 모든 메소드 중 @Permission Annotation 적용된 메소드 실행 전후
	 * @param joinPoint Advice 가 적용될 지점 (Method)
	 * @return Object 해당 Method 수행
	 * @throws Throwable
	 */
	@Around("permissionPointcut()")
	public Object permissionAspect (final ProceedingJoinPoint joinPoint) throws Throwable {

		int myAllowCnt = 0;
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		Object currMenu = request.getAttribute("currMenu");
		Object menuSet = request.getAttribute("menuSet");
		String currMenuId = "".equals(DeepfineUtils.defaultString(request.getAttribute("currMenu"))) ? "" : ((Menu) currMenu).getId();

		if (UserUtils.isSystem()) {
			joinPoint.proceed();
		} else {
			// logger.debug("Check Permission: (Current Menu:{})");
			// logger.debug("=> Current Menu:{}", currMenuId);
			// logger.debug("AOP - Permission--111");
			Permission permissions = method.getAnnotation(Permission.class);

			if (permissions != null && !"".equals(currMenuId) && menuSet != null) {
				List<Menu> menuList = menuService.findMenuCache(UserUtils.getUserInfo().getId());
				List<PERMISSION> permissionList = Arrays.stream(permissions.value()).collect(Collectors.toList());

				boolean hasReadPermission = menuList.stream().filter(menu -> menu.getId().equals(currMenuId)).count() > 0;

				// logger.debug("permissionList.size():{}", permissionList.size());
				for (PERMISSION permission : permissionList) {
					// logger.debug("permission.getValue():{}", permission.getValue());
					/**
					 * PERMISSION
					 * READ("R"), WRITE("W"), DOWNLOAD("S"), DELETE("D"), MASTER("M")
					 */
					if (PERMISSION.MASTER.getValue().equals(permission.getValue()) && UserUtils.isSystem()) {
						myAllowCnt++;
					}
					if (PERMISSION.READ.getValue().equals(permission.getValue()) && hasReadPermission) {
						myAllowCnt++;
					} else {
						Menu myPermission = menuList.stream().filter(menu -> menu.getId().equals(currMenuId)).findFirst().get();

						if (PERMISSION.WRITE.getValue().equals(permission.getValue()) && "Y".equals(myPermission.getWriteYn())) {
							myAllowCnt++;
						}
						if (PERMISSION.DOWNLOAD.getValue().equals(permission.getValue()) && "Y".equals(myPermission.getDownYn())) {
							myAllowCnt++;
						}
						if (PERMISSION.DELETE.getValue().equals(permission.getValue()) && "Y".equals(myPermission.getDeleteYn())) {
							myAllowCnt++;
						}
					}
				}

				// logger.debug("myAllowCnt:{}", myAllowCnt);
				if (myAllowCnt == permissionList.size()) {
					logger.debug("Permission Allowed");
					joinPoint.proceed();
				} else {
					throw new RuntimeException("Permission Denied!");
				}
			} else {
				joinPoint.proceed();
			}
		}

		return joinPoint.proceed();
	}
}
