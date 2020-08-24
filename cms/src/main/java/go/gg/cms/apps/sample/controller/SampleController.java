package go.gg.cms.apps.sample.controller;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import go.gg.cms.apps.sample.service.SampleService;
import go.gg.cms.core.controller.BaseController;
import go.gg.cms.core.domain.DetailFile;
import go.gg.cms.core.service.FileService;
import go.gg.cms.core.util.UserUtils;
import go.gg.common.util.DeepfineUtils;
import go.gg.cms.domain.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * CMS 샘플 게시판 컨트롤러
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
@Controller("apps.sample.controller")
@RequestMapping({"sample"})
public class SampleController extends BaseController {

	@Autowired
	private SampleService sampleService;

	@Autowired
	private FileService fileService;

	/**
	 * 샘플 게시판 > 목록
	 * @param condition 도메인
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("list.do")
	public String list(@ModelAttribute("condition") Sample condition, Model model) {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(condition.getPageIndex());
		paginationInfo.setRecordCountPerPage(condition.getPageUnit());
		paginationInfo.setPageSize(condition.getPageSize());

		List<Sample> resultList = sampleService.find(condition);
		paginationInfo.setTotalRecordCount(sampleService.count(condition));

		model.addAttribute("resultList", resultList);
		model.addAttribute("paginationInfo", paginationInfo);

		System.out.println("UserUtils.getUserInfo().getEmail():::"+UserUtils.getUserInfo().getEmail());

		return VIEW_PATH + "/list.tiles.default";
	}

	/**
	 * 샘플 게시판 > 상세
	 * @param condition 도메인
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("detail.do")
	public String detail(@ModelAttribute("condition") Sample condition, Model model) throws Exception {
//		model.addAttribute("result", sampleService.detail(condition));
		Sample result = sampleService.detail(condition);

		// 단일 업로드 파일 상세 정보 조회
		DetailFile uploadedFile = fileService.findFileByParentId(result.getFileId());
		// 다중 업로드 파일 상세 정보 조회
		List<DetailFile> uploadedFileList = fileService.findFileListByParentId(result.getFileId2());
		List<DetailFile> uploadedFileList2 = fileService.findFileListByParentId(result.getFileId3());

		model.addAttribute("result", result);
		model.addAttribute("uploadedFile", uploadedFile);
		model.addAttribute("uploadedFileList", uploadedFileList);
		model.addAttribute("uploadedFileList2", uploadedFileList2);

		return VIEW_PATH + "/detail.tiles.default";
	}

	/**
	 * 샘플 게시판 > 등록/수정
	 * @param condition 도메인
	 * @param model 모델
	 * @return View Page (JSP)
	 */
	@RequestMapping("form.do")
	public String form(@ModelAttribute("condition") Sample condition, Model model) throws Exception {
		Sample result = new Sample();

		DetailFile uploadedFile = null;
		List<DetailFile> uploadedFileList = new ArrayList<>();
		List<DetailFile> uploadedFileList2 = new ArrayList<>();

		if (!DeepfineUtils.isEmpty(condition.getId())) {
			result = sampleService.detail(condition);
			// 단일 업로드 파일 상세 정보 조회
			uploadedFile = fileService.findFileByParentId(result.getFileId());
			// 다중 업로드 파일 상세 정보 조회
			uploadedFileList = fileService.findFileListByParentId(result.getFileId2());
			uploadedFileList2 = fileService.findFileListByParentId(result.getFileId3());
		}

		model.addAttribute("result", result);
		model.addAttribute("uploadedFile", uploadedFile);
		model.addAttribute("uploadedFileList", uploadedFileList);
		model.addAttribute("uploadedFileList2", uploadedFileList2);

		return VIEW_PATH + "/form.tiles.default";
	}

	/**
	 * 샘플 게시판 > 데이터 저장
	 * @param condition 도메인
	 * @return View Page (JSP)
	 */
	@ResponseBody
	@RequestMapping("save.do")
	public Sample save(@RequestBody Sample condition) {
		// 단일 업로드 마스터 파일 아이디를 가지고 있는 경우
		// TODO::재설정 필요 (jb.choi)
		if(DeepfineUtils.isNotEmpty(condition.getFileId())) {
			// 파일 삭제 여부 설정
			fileService.updateUploadFileState(condition.getFileId(), condition.getUploadedFile());
		} else {
			condition.setFileId(null);
		}

		// 다중 업로드 마스터 파일 아이디를 가지고 있는 경우
		// TODO::재설정 필요 (jb.choi)
		if(DeepfineUtils.isNotEmpty(condition.getFileId2())) {
			// 파일 삭제 여부 설정
			fileService.updateUploadFileListState(condition.getFileId2(), condition.getUploadedFileList());
		} else {
			condition.setFileId2(null);
		}
		if(DeepfineUtils.isNotEmpty(condition.getFileId3())) {
			// 파일 삭제 여부 설정
			fileService.updateUploadFileListState(condition.getFileId3(), condition.getUploadedFileList2());
		} else {
			condition.setFileId3(null);
		}

		if (DeepfineUtils.isEmpty(condition.getId())) {
			condition.initId();
			sampleService.insert(condition);
		} else {
			sampleService.update(condition);
		}

		return condition;
	}

	/**
	 * 샘플 게시판 > 데이터 삭제
	 * @param condition 도메인
	 * @return View Page (JSP)
	 */
	@ResponseBody
	@RequestMapping("remove.do")
	public Sample remove(@RequestBody Sample condition) {
		if (!DeepfineUtils.isEmpty(condition.getId())) {
			sampleService.delete(condition);
		}
		return condition;
	}







	@RequestMapping("template_table.do")
	public String template_table(@ModelAttribute("condition") Sample condition, Model model) {
		logger.debug(VIEW_PATH + "/template_table.tiles.default");
		return VIEW_PATH + "/template_table.tiles.default";
	}

	@RequestMapping("template_form.do")
	public String template_form(@ModelAttribute("condition") Sample condition, Model model) {
		logger.debug(VIEW_PATH + "/template_form.tiles.default");
		return VIEW_PATH + "/template_form.tiles.default";
	}

	@RequestMapping("template_modal.do")
	public String template_modal(@ModelAttribute("condition") Sample condition, Model model) {
		logger.debug(VIEW_PATH + "/template_modal.tiles.default");
		return VIEW_PATH + "/template_modal.tiles.default";
	}

}
