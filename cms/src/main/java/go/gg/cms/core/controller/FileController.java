package go.gg.cms.core.controller;

import com.sun.media.jfxmediaimpl.MediaUtils;
import go.gg.cms.core.domain.DetailFile;
import go.gg.cms.core.service.FileService;
import go.gg.common.util.DeepfineUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 파일 업로드 컨트롤러
 * @author jb.choi (DEEP.FINE)
 * @since 2020.08.13
 * @version 1.0.0
 */
@Controller("core.file.controller")
@RequestMapping({"file"})
public class FileController extends BaseController {

	@Autowired
	private FileService fileService;

	@Value("${file.path.upload}")
	private String PATH_UPLOAD;

	/**
	 * 샘플 게시판 > 등록/수정 > 단일 업로드
	 * @param selectedFile 사용자가 선택한 파일
	 * @param fileId 단일 업로드 마스터 파일 아이디
	 * @return 업로드된 파일의 상세 정보
	 * @throws IOException 파일 처리 관련 예외
	 */
	@ResponseBody
	@RequestMapping(value = "singleUpload.do", method = RequestMethod.POST)
	public DetailFile singleUpload(MultipartFile selectedFile, String fileId) throws IOException {
		// 파일의 각종 정보를 저장할 도메인
		DetailFile detailFile = new DetailFile();

		// 서버에 업로드 후 파일의 각종 정보 복사
		copySelectedFile(selectedFile, detailFile);

		// 단일 업로드 서비스 수행
		fileService.insertSingleFile(detailFile, fileId);

		return detailFile;
	}

	/**
	 * 샘플 게시판 > 등록/수정 > 다중 업로드
	 * @param selectedFiles 사용자가 선택한 파일 리스트
	 * @param selectedFileId 다중 업로드 마스터 파일 아이디
	 * @return 업로드된 파일의 상세 정보 리스트
	 * @throws IOException 파일 처리 관련 예외
	 */
	@ResponseBody
	@RequestMapping(value = "multiUpload.do", method = RequestMethod.POST)
	public List<DetailFile> multiUpload(List<MultipartFile> selectedFiles, String selectedFileId) throws IOException {
		// 업로드하는 파일들의 정보를 저장하는 리스트
		List<DetailFile> detailFiles = new ArrayList<>();

		for(int i = 0; i < selectedFiles.size(); i++) {
			// 파일의 각종 정보를 저장할 도메인
			DetailFile detailFile = new DetailFile();

			// 서버에 업로드 후 파일의 각종 정보 복사
			copySelectedFile(selectedFiles.get(i), detailFile);

			// 파일 상세 정보 리스트에 추가
			detailFiles.add(detailFile);
		}

		// 다중 업로드 서비스 수행
		fileService.insertMultiFile(detailFiles, selectedFileId);

		return detailFiles;
	}

//	/** TODO::필요없을 시 제거 (jb.choi)
//	 * 샘플 게시판 > 상세 > 파일 다운로드
//	 * @param id 파일 아이디
//	 * @param response 파일 다운로드 응답 객체
//	 * @throws Exception 파일 처리 관련 예외
//	 */
//	@RequestMapping("download.do")
//	public String downloadFile(String id, HttpServletResponse response) throws IOException {
//		// 파일 상세 정보 조회
//		DetailFile detailFile = fileService.findFileById(id);
//
//		// 서버에 업로드된 파일 생성
//		File downloadFile = new File(PATH_UPLOAD, detailFile.getFileName());
//
//		// 파일명 인코딩
//		String encodingFileName = new String(detailFile.getOriginalName().getBytes("UTF-8"), "ISO-8859-1");
//
//		response.setCharacterEncoding("UTF-8");
//
//		String mType = Files.probeContentType(Paths.get(detailFile.getPath()));
//		response.setContentType(mType);
//
////		response.setContentType("application/octet-stream;");
//		response.setContentLength((int)downloadFile.length());
//		response.setHeader("Content-Disposition", "attachment; filename=\"" + encodingFileName + "\"");
//		FileCopyUtils.copy(FileUtils.readFileToByteArray(downloadFile), response.getOutputStream());
//
//		response.getOutputStream().flush();
//		response.getOutputStream().close();
//
//		return "success";
//	}
//	@RequestMapping("download.do")
//	public void downloadFile(String id, HttpServletResponse response) throws IOException {
//		// 파일 상세 정보 조회
//		DetailFile detailFile = fileService.findFileById(id);
//
//		// 파일명 인코딩
//		String encodingFileName = new String(detailFile.getOriginalName().getBytes("UTF-8"), "ISO-8859-1");
//
//		// 서버에 업로드된 파일 생성
//		File downloadFile = new File(PATH_UPLOAD, detailFile.getFileName());
//
//		// 파일 다운로드 설정
//		response.setContentType("text/html");
//		response.setHeader("Content-Disposition", "attachment; filename=\"" + encodingFileName + "\"");
//		response.setBufferSize((int)downloadFile.length());
//		response.setContentLength((int)downloadFile.length());
//
//		// 서버에 업로드된 파일 복사
//		FileCopyUtils.copy(FileUtils.readFileToByteArray(downloadFile), response.getOutputStream());
//		response.getOutputStream().flush();
//		response.getOutputStream().close();
//	}

	/**
	 * 파일을 서버에 업로드하고 파일의 각종 정보를 도메인에 복사한다.
	 * @param selectedFile 서버에 업로드할 파일
	 * @param detailFile 파일 상세 정보가 복사될 도메인
	 * @throws IOException 파일 처리 관련 예외
	 */
	public void copySelectedFile(MultipartFile selectedFile, DetailFile detailFile) throws IOException {
		// 서버의 지정된 경로에 파일 복사
		String tempFileName = DeepfineUtils.generateUUID() + "." + FilenameUtils.getExtension(selectedFile.getOriginalFilename());
		File tempFile = new File(PATH_UPLOAD, tempFileName);
		FileCopyUtils.copy(selectedFile.getBytes(), tempFile);

		// 파일의 각종 정보 추출
		String originalName = selectedFile.getOriginalFilename();
		String fileName 	= tempFileName;
		String destination  = PATH_UPLOAD;
		String path 		= destination + "/" + fileName;
		String mimetype	 	= Files.probeContentType(Paths.get(path));
		int    size 		= (int)selectedFile.getSize();

		// 도메인에 파일 정보 설정
		detailFile.setOriginalName(originalName);
		detailFile.setMimetype(mimetype);
		detailFile.setFileName(fileName);
		detailFile.setDestination(destination);
		detailFile.setPath(path);
		detailFile.setSize(size);
	}

}
