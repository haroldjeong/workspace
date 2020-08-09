package go.gg.cms.core.service;

import go.gg.cms.core.domain.SecurityUserDetail;
import go.gg.cms.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 사용자 상세정보 조회 및 인증관련 서비스 로직 Override (Spring Security 전용)
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.29
 * @version 1.0.0
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	SecurityUserService securityUserService;

	@Override
	public UserDetails loadUserByUsername(String userId) {
		User user = securityUserService.getUserInfo(userId);

		if (user == null) {
			throw new UsernameNotFoundException(userId);
		}

		return new SecurityUserDetail(user);
	}
}
