package go.gg.cms.core.service;

import go.gg.cms.core.domain.SecurityUserDetail;
import go.gg.cms.core.domain.User;
import go.gg.common.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring Security Authentication Provider
 * @apiNote 로그인 인증 관련 상세 로직 구현 (Spring Security DaoAuthenticationProvider 상속)
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.29
 * @version 1.0.0
 */
public class SecurityAuthProvider extends DaoAuthenticationProvider {

	protected Logger logger = LoggerFactory.getLogger("SecurityAuthProvider");
	private SecurityUserDetailsService securityUserDetailsService;
	private SecurityUserService securityUserService;


	@Autowired
	public void setUserDetailsService(SecurityUserDetailsService securityUserDetailsService) {
		this.securityUserDetailsService = securityUserDetailsService;
	}

	@Autowired
	public void setUserService(SecurityUserService securityUserService) {
		this.securityUserService = securityUserService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String loginId = (String) authentication.getPrincipal();
		String loginPw = (String) authentication.getCredentials();

		User loginUser = null;

		try {
			logger.debug("SecurityAuthProvider : authenticate **********************");
			logger.debug("Try login with (ID: '{}')", loginId);
			loginUser = securityUserService.getUserInfo(loginId);

		} catch (UsernameNotFoundException e) {
			// 관리자 아이디 없음: LOGIN_FAIL_ID
			securityUserService.saveHistory(
					"LOGIN_FAIL_ID"
					, loginId
					, "Invalid Login ID: '" + loginId + "'.");
			throw new UsernameNotFoundException("로그인 정보가 유효하지 않습니다.\n아이디와 비밀번호를 확인해주세요.");
		}

		try {
			if (loginUser.getLoginPw().equals(SecurityUtils.sha512BySalt(loginPw, loginUser.getSalt()))) {
				List<GrantedAuthority> roles = new ArrayList<>();

				loginUser.getRoles().stream().forEach(role -> {
					roles.add(new SimpleGrantedAuthority(role.getRoleId()));
				});

				UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(loginId, loginPw, roles);
				result.setDetails(new SecurityUserDetail(loginUser));
				return result;

			} else {
				throw new BadCredentialsException("로그인 정보가 유효하지 않습니다.\n아이디와 비밀번호를 확인해주세요.");
			}
		} catch (BadCredentialsException e) {
			// 비밀번호 불일치 : 로그인 실패 건수 1 증가
			int failCnt = securityUserService.updateFailCnt(loginId);

			securityUserService.saveHistory(
					"LOGIN_FAIL_PWD"
					, loginId
					, "LOGIN FAIL COUNT[" +failCnt + "]");

			if (failCnt < 5) {
				if (failCnt == 1) {
					throw new BadCredentialsException("로그인 정보가 유효하지 않습니다.\n아이디와 비밀번호를 확인해주세요.");
				}
				throw new BadCredentialsException("로그인 정보가 유효하지 않습니다."
						+ "\n로그인 5회 실패 시 계정의 사용이 정지되니 주의해주시기 바랍니다.");
			} else {
				// 5회이상 실패 시 계정 차단
				securityUserService.saveBlock(loginId);
				securityUserService.saveHistory(
						"LOGIN_SET_BLOCK"
						, loginId
						, "LOGIN_SET_BLOCK");
				throw new LockedException("로그인 5회 실패로 사용이 정지되었습니다. 관리자에게 문의해주세요.");
			}
		} catch (LockedException | DisabledException e) {
			securityUserService.saveHistory(
					"LOGIN_FAIL_BLOCK"
					, loginId
					, "LOGIN_FAIL_BLOCK");
			throw new LockedException("사용정지 중인 계정입니다. 관리자에게 문의해주세요.");
		} catch (CredentialsExpiredException e) {	// 비밀번호 만료: LOGIN_FAIL_EXPIRES
			securityUserService.saveHistory(
					"LOGIN_FAIL_PWD_EXP"
					, loginId
					, "LOGIN_FAIL_PWD_EXP");
			throw new CredentialsExpiredException("마지막 비밀번호 변경일로부터 90일 경과되었습니다. 비밀번호 변경 후 사용 가능합니다.");
		} catch (AccountExpiredException e) {
			securityUserService.saveHistory(
					"LOGIN_FAIL_EXP"
					, loginId
					, "LOGIN_FAIL_EXP");
			throw new AccountExpiredException("사용이 만료된 계정입니다. 관리자에게 문의해주세요.");
		}

	}
}
