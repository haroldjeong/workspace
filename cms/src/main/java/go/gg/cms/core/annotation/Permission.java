package go.gg.cms.core.annotation;

import go.gg.cms.core.annotation.type.PERMISSION;
import java.lang.annotation.*;

/**
 * Custom Annotation for permission
 * @apiNote  @RequestMapping 하위에 추가
 * @author jm.lee (DEEP.FINE)
 * @since 2020.08-25
 * @version 1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
	PERMISSION[] value() default {PERMISSION.READ};
}
