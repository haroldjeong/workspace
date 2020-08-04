package go.gg.cms.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Spring Security Authentication Provider
 * @apiNote 로그인 인증 관련 상세 로직 구현 (Spring Security DaoAuthenticationProvider 상속)
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.29
 * @version 1.0.0
 */
public class SecurityAuthProvider extends DaoAuthenticationProvider {

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
	public Authentication authenticate(Authentication authentication) {
		String id  = authentication.getName();

		System.out.println("[Spring Security] authentication.getName() ::: " + id);

		try {
			return super.authenticate(authentication);
		} catch (UsernameNotFoundException e) {		// 관리자 아이디 없음: LOGIN_FAIL_ID
			securityUserService.saveHistory(
					"LOGIN_FAIL_ID"
					, id
					, "The attempted ID is " + id + ".");
			throw new UsernameNotFoundException("아이디, 비밀번호를 확인해주세요.");

		} catch (BadCredentialsException e) {


			int failCnt = securityUserService.updateFailCnt(id);
			securityUserService.saveHistory(
					"LOGIN_FAIL_PWD"
					, id
					, "LOGIN FAIL COUNT[ " +failCnt + " ]");
			if (failCnt < 5) {
				if (failCnt == 0) {
					throw new UsernameNotFoundException("아이디, 비밀번호를 확인해주세요.");
				}
				throw new BadCredentialsException("아이디, 비밀번호를 확인해주세요."
						+ "비밀번호와 IP 오류가 5회 누적되면 사용이 정지되니 주의해주시길 바랍니다.");
			} else {
				// 5회이상 틀릴 시 계정 차단처리
				securityUserService.saveBlock(id);
				securityUserService.saveHistory(
					"LOGIN_SET_BLOCK"
					, id
					, "LOGIN_SET_BLOCK");
				throw new LockedException("비밀번호 5회 실패로 사용정지 중인 계정입니다. 관리자에게 문의해주세요.");
			}
		} catch (LockedException | DisabledException e) {

			securityUserService.saveHistory(
					"LOGIN_FAIL_BLOCK"
					, id
					, "LOGIN_FAIL_BLOCK");
			throw new LockedException("사용정지 중인 계정입니다. 관리자에게 문의해주세요.");
		} catch (CredentialsExpiredException e) {	// 비밀번호 만료: LOGIN_FAIL_EXPIRES

			// user.isPwInit() = true 일 경우 사용정지 // 관리자가 비밀번호 부여 시켰을 시 3시간 유효기간 //
			if(securityUserService.getUserInfo(id).isPwInit()) {
				securityUserService.saveHistory(
					"LOGIN_FAIL_PWD_BLOCK"
					, id
					, "LOGIN_FAIL_PWD_BLOCK");
				throw new LockedException("사용정지된 계정입니다. 관리자에게 문의해주세요.");
			}
			securityUserService.saveHistory(
						"LOGIN_FAIL_PWD_EXP"
						, id
						, "LOGIN_FAIL_PWD_EXP");
			throw new CredentialsExpiredException("마지막 비밀번호 변경일로부터 90일 경과되었습니다. 비밀번호 변경 후 사용 가능합니다.");
		} catch (AccountExpiredException e) {
			securityUserService.saveHistory(
					"LOGIN_FAIL_EXP"
					, id
					, "LOGIN_FAIL_EXP");
			throw new AccountExpiredException("사용이 만료된 계정입니다. 관리자에게 문의해주세요.");
		}
	}
}
