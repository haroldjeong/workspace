package go.gg.web.apps.main.service;

import go.gg.web.core.service.BaseService;
import go.gg.web.domain.Main;
import org.springframework.stereotype.Service;

/**
 * 메인 서비스
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
@Service("apps.main.service")
public class MainService extends BaseService<Main, String> {

	private final String QUERY_ID_SUB_PREFIX = ".main";

}
