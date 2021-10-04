package com.project.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.project.security.CustomLoginSuccessHandler;
import com.project.security.CustomUserDetailsService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Setter(onMethod_ = {@Autowired})
	private DataSource dataSource;
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("configure...................");
		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("member")
			.password("$2a$10$bewZx3fHyzFh5asj.HyDM.sP0BZIse85qvwDhAsjjCWhys99pqQKS").roles("MEMBER");
	}
	*/
	@Bean
	public UserDetailsService customUserService() {
		return new CustomUserDetailsService();
	}

	// in custom userdetails
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService()).
		passwordEncoder(passwordEncoder());
		/**********
		 *UserDetailsService 사용하기 전 코드
		 *
		log.info("configure JDBC..................................................");
		String queryUser = "select userid, userpw , enabled from tb_member where userid = ? ";
		String queryDetails = "select userid, auth from tb_member_auth where userid = ? ";
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passwordEncoder())
		.usersByUsernameQuery(queryUser)
		.authoritiesByUsernameQuery(queryDetails);
		****************/
	}
	
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/exam/all").permitAll()
			.antMatchers("/exam/admin").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/exam/member").access("hasRole('ROLE_MEMBER')");
		http.formLogin()
			.loginPage("/customLogin")
			.loginProcessingUrl("/login");
			// SaveRequestAwareAuthenticationSuccessHandler를 사용하는 경우 successHandler 주석
			//.successHandler(loginSuccessHandler()); 
		http.logout()
			.logoutUrl("/customLogout")
			.invalidateHttpSession(true)
			.deleteCookies("remember-me", "JSESSION_ID");
		http.rememberMe().key("project").tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(604800);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}
	
	
}
