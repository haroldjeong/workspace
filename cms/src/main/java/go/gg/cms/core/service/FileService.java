package go.gg.cms.core.service;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import go.gg.cms.core.domain.DetailFile;
import go.gg.cms.core.domain.MasterFile;
import go.gg.cms.domain.Sample;
import go.gg.common.util.DeepfineUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 파일 업로드/다운로드 서비스
 * @apiNote 업로드 방식은 단일과 다중으로 별도의 서비스로 구현
 * @author jb.choi (DEPP.FINE)
 * @since 2020.08.13
 * @version 1.0.0
 */
@Service("core.file.service")
public class FileService extends EgovAbstractMapper {

	private final String QUERY_ID_PREFIX = "go.gg.cms.core.file.";

	/**
	 * 단일 업로드 파일 추가
	 * @param condition 도메인
	 * @param fileId 단일 업로드 마스터 파일 아이디
	 */
	@Transactional
	public void insertSingleFile(DetailFile condition, String fileId) {
		if(DeepfineUtils.isEmpty(fileId)) {
			fileId = DeepfineUtils.generateUUID();

			// 새로운 마스터 파일 도메인 생성
			MasterFile masterFile = createMasterFile(fileId);

			super.insert(QUERY_ID_PREFIX + "insertMasterFile", masterFile);
		}

		// 파일 상세 정보 설정
		condition.initId();
		condition.setParentId(fileId);
		condition.setFileSeq(0);

		super.insert(QUERY_ID_PREFIX + "insertDetailFile", condition);
	}

	/**
	 * 다중 업로드 파일 추가
	 * @param conditions 도메인
	 * @param selectedFileId 다중 업로드 마스터 파일 아이디
	 */
	@Transactional
	public void insertMultiFile(List<DetailFile> conditions, String selectedFileId) {
		if(DeepfineUtils.isEmpty(selectedFileId)) {
			selectedFileId = DeepfineUtils.generateUUID();

			// 새로운 마스터 파일 도메인 생성
			MasterFile masterFile = createMasterFile(selectedFileId);

			super.insert(QUERY_ID_PREFIX + "insertMasterFile", masterFile);
		}

		for(DetailFile condition : conditions) {
			// 파일 상세 정보 설정
			condition.initId();
			condition.setParentId(selectedFileId);

			// 파일 번호 설정
			Integer currentFileSequence = super.selectOne(QUERY_ID_PREFIX + "findCurrentFileSequence", selectedFileId);
			int fileSeq = currentFileSequence == null ? 0 : currentFileSequence + 1;
			condition.setFileSeq(fileSeq);

			super.insert(QUERY_ID_PREFIX + "insertDetailFile", condition);
		}
	}

//	/** TODO::필요없을 시 제거 (jb.choi)
//	 * 파일 아이디로 파일 상세 정보 조회
//	 * @param id 파일 아이디
//	 * @return 파일 상세 정보
//	 */
//	@Transactional
//	public DetailFile findFileById(String id) {
//		return super.selectOne(QUERY_ID_PREFIX + "findFileById", id);
//	}

	/**
	 * 마스터 파일 아이디로 파일 상세 정보 조회
	 * @param parentId 파일 마스터 아이디
	 * @return 파일 상세 정보
	 */
	@Transactional
	public DetailFile findFileByParentId(String parentId) {
		return super.selectOne(QUERY_ID_PREFIX + "findFileByParentId", parentId);
	}

	/**
	 * 마스터 파일 아이디로 파일 상세 정보 리스트 조회
	 * @param parentId 파일 마스터 아이디
	 * @return 파일 상세 정보 리스트
	 */
	@Transactional
	public List<DetailFile> findFileListByParentId(String parentId) {
		return super.selectList(QUERY_ID_PREFIX + "findFileListByParentId", parentId);
	}

	/**
	 * 단일 업로드 시 파일 삭제 여부 설정
	 * @param parentId 단일 업로드 마스터 파일 아이디
	 * @param uploadedFile 업로드된 파일
	 */
	@Transactional
	public void updateUploadFileState(String parentId, String uploadedFile) {
		// 업로드된 파일 모두 제거 설정
		super.update(QUERY_ID_PREFIX + "deleteAllFileByParentId", parentId);

		// 전달받은 파일만 삭제 여부 상태 변경 (Y -> N)
		if(DeepfineUtils.isNotEmpty(uploadedFile))
			super.update(QUERY_ID_PREFIX + "updateUploadFileState", uploadedFile);
	}

	/**
	 * 다중 업로드 시 파일 삭제 여부 설정
	 * @param parentId 다중 업로드 마스터 파일 아이디
	 * @param uploadedFileList 업로드된 파일 리스트
	 */
	@Transactional
	public void updateUploadFileListState(String parentId, List<String> uploadedFileList) {
		// 업로드된 파일 모두 제거 설정
		super.update(QUERY_ID_PREFIX + "deleteAllFileByParentId", parentId);

		// 전달받은 파일 리스트만 삭제 여부 상태 변경 (Y -> N)
		if(uploadedFileList != null)
			super.update(QUERY_ID_PREFIX + "updateUploadFileListState", uploadedFileList);
	}

	/**
	 * 새로운 마스터 파일 도메인 생성
	 * @param id 마스터 파일 아이디
	 * @return MasterFile 도메인
	 */
	public MasterFile createMasterFile(String id) {
		MasterFile masterFile = new MasterFile();

		masterFile.setId(id);
		masterFile.setGroupId("aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee");
		masterFile.setFileGroupCd("aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee");
		masterFile.setRegId();
		masterFile.setRegIp();
		masterFile.setRegAgent();

		return masterFile;
	}

}
