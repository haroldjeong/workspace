package go.gg.cms.core.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import go.gg.common.util.DeepfineUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;
import org.springframework.data.domain.Sort;
import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * CMS 기본 컨트롤러
 * @apiNote CMS 내 모든 컨트롤러는 본 컨트롤러를 상속받아 사용
 * @apiNote Web Binding Initializer 포함 (전자정부의 EgovBindingInitializer 역할)
 * @apiNote Properties 후처리를 위한 Initializing Bean 추가 구현
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
@JsonIgnoreProperties(value={"파싱에서", "제외시킬", "프로퍼티", "여기에", "등록"})
public class BaseController implements WebBindingInitializer, InitializingBean {
	protected Logger logger = LoggerFactory.getLogger("controller");
	protected String VIEW_PATH;

	@Autowired
	private HttpServletRequest request;

	private final static String SORT_QUERY_PATTERN = "^[a-zA-Z0-9_.]*:(ASC|DESC)$";
	private final static Pattern sortPattern = Pattern.compile(SORT_QUERY_PATTERN);

	// 데이터 정렬기준 유효성 검사
	private static boolean validateSort(String sortQuery) {
		return sortPattern.matcher(sortQuery).matches();
	}

	@Override
	public void afterPropertiesSet() {
		Class clazz = this.getClass();

		if (clazz.getAnnotation(RequestMapping.class) != null) {
			RequestMapping requestMappingAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
			this.VIEW_PATH = requestMappingAnnotation.value()[0].replaceAll("-", "_");
		}
	}

	/**
	 * 초기화 바인딩
	 * @apiNote CMS 공통적으로 관리할 파라미터 binding 정의
	 * @param binder 데이터 바인더
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// todo: Request 되는 모든 데이터에 대한 초기화 (Initialize Binding) 로직 구현 예정. 일단 날짜 포맷만 초기화 (jm.lee)
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));

		// 게시물 정렬 데이터 공통화 구현
		binder.registerCustomEditor(Sort.class, "sort", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				List<String> sortList = Splitter.on(',').splitToList(text);
				List<Sort.Order> orderList = Lists.newArrayList();
				for (String s : sortList) {
					// 검사
					if (validateSort(s)) {
						if (s.contains(":")) {
							List<String> sList = Splitter.on(':').splitToList(s);
							orderList.add(new Sort.Order(
									Sort.Direction.fromString(sList.get(1))
									, DeepfineUtils.trim(sList.get(0))
							));
						} else {
							orderList.add(new Sort.Order(Sort.Direction.fromString(DeepfineUtils.trim(s)), "ASC"));
						}
						super.setValue(new Sort(orderList));
					}
				}
			}
		});
	}
}
