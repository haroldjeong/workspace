package go.gg.cms.core.controller;

import go.gg.cms.core.service.FcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CMS FCM 컨트롤러
 * @author jb.choi (DEEP.FINE)
 * @since 2020.08.24
 * @version 1.0.0
 */
@RestController("core.fcm.controller")
@RequestMapping({"fcm"})
public class FcmController extends BaseController {

    @Autowired
    private FcmService fcmService;

    /**
     * FCM 테스트
     * @return 성공 여부
     */
    @RequestMapping("push.do")
    public String push() {
        String deviceToken = "dmCZCwd-SDmNqhkrvzltzt:APA91bHNQJOllEuzPxZSvp6dDWTUKLGptCkf_W9ZsfVXtXK4XK6PHoRyrMipq2OnHSFsYn_rke4NtNCWQufGIVbhewHkvVB_pQzd9Eh8weukMy3LLCUrwL4tlxcwct8qdKCOZnceiYmA";
        String result = fcmService.notification(deviceToken, "제목", "내용");

        return result;
    }

}
