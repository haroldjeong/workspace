package go.gg.cms.core.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 유틸리티
 * @author mr.kim (DEEP.FINE)
 * @since 2020.08.20
 * @version 1.0.0
 */
@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    public static String ENC_KEY = "69cec889bb1ca8162a5ee6ef2c91f718";

    public static String createToken() {
        String jwt = "";
        try {
            Long currentTime = System.currentTimeMillis();  // 토큰 생성시간
            Long expiredTime = 1000 * 3L;  // 토큰 유효시간 (3초)

            // Header 부분 설정
            Map<String, Object> headers = new HashMap<>();
            headers.put("typ", "JWT");
            headers.put("alg", "HS256");

            // Payload 부분 설정
            Map<String, Object> payloads = new HashMap<>();
            payloads.put("id", UserUtils.getUserInfo().getId());
            payloads.put("currentTime", currentTime);

            // Token Builder
            jwt = Jwts.builder()
                    .setHeader(headers)
                    .setClaims(payloads)
                    .setIssuedAt(new Date(currentTime))
                    .setExpiration(new Date(currentTime + expiredTime))
                    .signWith(SignatureAlgorithm.HS256, generateKey())
                    .compact();
        } catch (Exception e) {
            LOGGER.error("JwtService createToken Error :: " + e);
            e.printStackTrace();
        }
        return jwt;
    }

    private static byte[] generateKey() {
        byte[] key = null;
        try {
            key = JwtUtils.ENC_KEY.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e){
            LOGGER.error("JwtService generateKey Error :: " + e);
        }
        return key;
    }

//    public static void verifyToken(String jwt) throws Exception {
//        Claims claims = null;
//        try {
//            claims = Jwts.parser()
//                    .setSigningKey(generateKey())
//                    .parseClaimsJws(jwt)
//                    .getBody();
//        } catch (ExpiredJwtException e) {
//            LOGGER.error("JWT 기간 만료");
//        }
//    }

//    public static void main(String[] arg) throws Exception {
//        System.out.println("token :: " + createToken());
//        verifyToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhIjoi7J6E7Iuc642w7J207YSwIiwiZXhwIjoxNTk3NzE4OTEwLCJpYXQiOjE1OTc3MTg4NTB9.j_g_WeLQ015l75yq0KnzBz2-Ur9lCOp1bXZlpF_p1pk");
//    }
}
