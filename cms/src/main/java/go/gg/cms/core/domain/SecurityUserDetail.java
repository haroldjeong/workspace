package go.gg.cms.core.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import go.gg.cms.domain.User;

/**
 * Spring Security 관련 도메인 Override
 * @apiNote org.springframework.security.core.userdetails.User 상속받아야 함
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.29
 * @version 1.0.0
 */
public class SecurityUserDetail extends org.springframework.security.core.userdetails.User {

	private static final String ROLE_PREFIX = "ROLE_";

	private User user;

	public SecurityUserDetail (User user) {
		super(user.getLoginId()
				, user.getLoginPw()
				, true
				, true
				, true
				, true
				, null);
				//, makeGrantedAuthority(user.getRoles()));
				// todo: 임시로 권한 하드코딩 (null). 추후 권한체계 구현 시 반드시 수정 필요 (jm.lee)
		this.user = user;

		System.out.println("SecurityUserDetail ::: " + user.toString());
	}

	@Override
	public String getPassword() {
		return user.getLoginPw();
	}

	@Override
	public String getUsername() {
		return user.getLoginId();
	}

	/**
	 * 사용기간 만료 체크 LOGIN_FAIL_EXP
	 */
	@Override
	public boolean isAccountNonExpired() {
		return LocalDateTime.now().isBefore(user.getExpDate());
	}

	/**
	 * 차단 여부 검사 와 동일 하게 설정 : 임시 잠금에 대한 기능은 없음
	 */
	@Override
	public boolean isAccountNonLocked() {
		return "Y".equals(user.getUseYn());
	}

	/**
	 * 비밀번호 만료 검사
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// todo: 임시 하드코딩 (true). 추후 사용자 패스워드 만료일 구현 시 반드시 수정 필요 (jm.lee)
		return true;
		//return LocalDateTime.now().isBefore(//{todo: 사용자 패스워드 만료일});

	}

	/**
	 * 차단 여부 검사
	 */
	@Override
	public boolean isEnabled() {
		return !"N".equals(user.getUseYn()) && user.getLoginFail() < 5;
	}

	private static List<GrantedAuthority> makeGrantedAuthority (List<String> roles) {
		List<GrantedAuthority> list = new ArrayList<>();
		// todo: 임시로 권한 하드코딩 (MASTER). 추후 권한체계 구현 시 반드시 수정 필요 (jm.lee)
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + "MASTER")));
		return list;
	}

	public User getUserInfo () {
		return this.user;
	}
	public User getDetail() {
		return this.user;
	}
}
