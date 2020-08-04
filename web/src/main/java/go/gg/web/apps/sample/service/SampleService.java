package go.gg.web.apps.sample.service;

import go.gg.web.core.service.BaseService;
import go.gg.web.domain.Sample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * WEB 샘플 게시판 서비스
 * @apiNote 관리자 그룹 및 메뉴권한 등은 별도 서비스로 구현
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
@Service("apps.sample.service")
public class SampleService extends BaseService<Sample, String> {

	private final String QUERY_ID_PREFIX = "sample";

	public List<Sample> find(Sample condition) {
		return super.find(QUERY_ID_PREFIX, "List", condition);
	}

	public int count(Sample condition) {
		return super.count(QUERY_ID_PREFIX, "List", condition);
	}

	public Sample detail(Sample condition) throws Exception {
		return super.find(QUERY_ID_PREFIX, "Detail", condition).get(0);
//		return parseMl(super.find(QUERY_ID_PREFIX, "Detail", condition), new Sample());
	}

	@Transactional
	public void insert(Sample condition) {
		super.insert(QUERY_ID_PREFIX, "Master", condition);
		super.insert(QUERY_ID_PREFIX, "Ml", condition);
	}

	@Transactional
	public void update(Sample condition) {
		super.update(QUERY_ID_PREFIX, "Master", condition);
		super.update(QUERY_ID_PREFIX, "Ml", condition);
	}

	@Transactional
	public void delete(Sample condition) {
		super.delete(QUERY_ID_PREFIX, "", condition);
	}

}
