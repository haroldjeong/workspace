package go.gg.cms.core.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * CMS FCM 서비스
 * @author jb.choi (DEEP.FINE)
 * @since 2020.08.24
 * @version 1.0.0
 */
@Service("core.fcm.service")
public class FcmService extends BaseService {

    // FCM 액세스 승인 요청 시 사용
    private static final String SCOPE = "https://www.googleapis.com/auth/firebase.messaging";

    // FCM 프로젝트 아이디
    private static final String PROJECT_ID = "testfcm-faf69";

    // FCM 서비스 계정 파일 경로
    private static final String SERVICE_ACCOUNT_PATH = "/fcm/service-account.json";

    /**
     * 알림 메시지 전송
     * @param deviceToken 디바이스 토큰
     * @param title 제목
     * @param body 내용
     * @return 성공 여부
     */
    public String notification(String deviceToken, String title, String body) {
        // FCM 사용 요청
        try {
            OkHttpClient httpClient = new OkHttpClient();

            // HTTP 요청 헤더에 액세스 토큰 추가
            Request request = new Request.Builder().url("https://fcm.googleapis.com/v1/projects/" + PROJECT_ID + "/messages:send")
                    .addHeader("Content-Type", "application/json; charset=UTF-8")
                    .addHeader("Authorization", "Bearer " + getAccessToken())
                    .post(RequestBody.create(MediaType.parse("application/json"), getMessage(deviceToken, title, body))).build();
            Response response = httpClient.newCall(request).execute();
            System.err.println(response);

            return "success";
        } catch (IOException e) {
            return "fail";
        }
    }

    /**
     * FCM 서비스 계정 파일을 통해 인증 토큰 생성
     * @return FCM 인증 토큰
     * @throws IOException FCM 서비스 계정 파일이 없는 경우
     */
    private String getAccessToken() throws IOException {
        // FCM 서비스 계정 JSON 파일을 가져와 인증 토큰 생성
        InputStream serviceAccount = new ClassPathResource(SERVICE_ACCOUNT_PATH).getInputStream();
        GoogleCredential googleCredential = GoogleCredential.fromStream(serviceAccount).createScoped(Arrays.asList(SCOPE));
        googleCredential.refreshToken();

        return googleCredential.getAccessToken();
    }

    /**
     * FCM 알림 표시 작업을 위한 메시지 생성
     * @param deviceToken 토큰
     * @param title 제목
     * @param body 내용
     * @return 알림 메시지
     */
    private String getMessage(String deviceToken, String title, String body) {
        // JSON 형태로 알림 메시지 설정
        JsonObject messageElement = new JsonObject();
        JsonObject notificationElement = new JsonObject();
        JsonObject message = new JsonObject();

        // 사용자 등록 토큰 추가
        messageElement.addProperty("token", deviceToken);

        // 알림 제목, 내용 추가
        notificationElement.addProperty("title", title);
        notificationElement.addProperty("body", body);
        messageElement.add("notification", notificationElement);

        message.add("message", messageElement);

        System.err.println("message : " + message);

        return message.toString();
    }

}
