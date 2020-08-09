package go.gg.cms.core.config;

import go.gg.cms.core.security.CmsAccessDeniedHandler;
import go.gg.cms.core.security.CmsLoginFailureHandler;
import go.gg.cms.core.security.CmsLoginSuccessHandler;
import go.gg.cms.core.service.SecurityAuthProvider;
import go.gg.cms.core.service.SecurityUserDetailsService;
import go.gg.cms.core.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security 환경설정
 * @apiNote 전자정부 프레임워크 시큐리티 구조(egoframework.rte.ptl.security)를 사용하지 않음
 * @apiNote Java Configuration 구조와 일부 호환성 맞지 않음. 버전 호환성 이슈도 존재
 * @apiNote Spring Security 4.x.x 버전 직접 적용
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.29
 * @version 1.0.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	SecurityUserDetailsService securityUserDetailsService;

	@Autowired
	private SecurityUserService securityUserService;

	/* Security 제외 패턴 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/favicon.ico", "/static/**", "/upload/**", "/error");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.formLogin()
					.loginPage("/login.do").permitAll()
					.loginProcessingUrl("/login-proc.do")
					.usernameParameter("loginId")
					.passwordParameter("loginPw")
					.successHandler(cmsLoginSuccessHandler())
					.failureHandler(cmsLoginFailureHandler())
				.and()
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout.do")).permitAll()
					.logoutSuccessUrl("/login.do")
					.invalidateHttpSession(true)
				.and()
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
				.and()
				// todo: 로그인 처리 구현 필요. (jm.lee)
//				.authorizeRequests()
//					.antMatchers().permitAll()
//					.anyRequest().anonymous()
				.authorizeRequests()
					.antMatchers("/login*", "/login/*", "/logout*").permitAll()
					.anyRequest().authenticated()
//					.anyRequest().hasRole("ROLE_MANAGER")
				.and()
				.csrf().disable()
					//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				//.and()
				.headers()
					.frameOptions()
						.sameOrigin();
	}


	@Bean
	public AccessDeniedHandler accessDeniedHandler () {
		return new CmsAccessDeniedHandler();
	}

	@Bean
	public CmsLoginSuccessHandler cmsLoginSuccessHandler () {
		CmsLoginSuccessHandler cmsLoginSuccessHandler = new CmsLoginSuccessHandler();
		cmsLoginSuccessHandler.setSecurityUserService(securityUserService);

		return cmsLoginSuccessHandler;
	}

	@Bean
	public AuthenticationFailureHandler cmsLoginFailureHandler () {
		CmsLoginFailureHandler cmsLoginFailureHandler = new CmsLoginFailureHandler();
		cmsLoginFailureHandler.setSecurityUserService(securityUserService);

		return cmsLoginFailureHandler;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new SecurityAuthProvider();
		authProvider.setUserDetailsService(securityUserDetailsService);
		// authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}