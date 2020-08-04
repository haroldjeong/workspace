/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package go.gg.web.core.util;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Pagination Renderer
 * @apiNote 전자정부 프레임워크 제공 기본 페이징 렌더러 -> Web Template 변경 (jm.lee 2020/07/22)
 * @author 개발프레임웍크 실행환경 개발팀 (Copyright (C) by MOPAS All right reserved.)
 * @since 2009. 03.16
 * @version 1.0
 */
public class WebPaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{

	private ServletContext servletContext;

	public WebPaginationRenderer() {
		// no-op
	}

	public void initVariables() {
		firstPageLabel = "<li class=\"footable-page-arrow\"><a href=\"#first\" onclick=\"javascript:{0}({1}); return false;\">«</a></li>";
		previousPageLabel = "<li class=\"footable-page-arrow\"><a href=\"#prev\" onclick=\"javascript:{0}({1}); return false;\">‹</a></li>";
		currentPageLabel = "<li class=\"footable-page active\"><a href=\"#\">{0}</a></li>";
		otherPageLabel = "<li class=\"footable-page\"><a href=\"#\" onclick=\"javascript:{0}({1}); return false;\">{2}</a></li>";
		nextPageLabel = "<li class=\"footable-page-arrow\"><a href=\"#next\" onclick=\"javascript:{0}({1}); return false;\">›</a></li>";
		lastPageLabel = "<li class=\"footable-page-arrow\"><a href=\"#last\" onclick=\"javascript:{0}({1}); return false;\">»</a></li>";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}
}
