package com.axxes.timesheet.time.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@ComponentScan("com.axxes")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static String REALM = "MY_TEST_REALM";

	@Autowired
	private MyUserDetailsService userDetailsService;

	private MyPasswordEncoder myPasswordEncoder = new MyPasswordEncoder();

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user").password("password").roles("ADMIN");
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(myPasswordEncoder);
		return authProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/*").hasRole("ADMIN").and() //
				.httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint()).and() //
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		return new CustomBasicAuthenticationEntryPoint();
	}

	// /* To allow Pre-flight [OPTIONS] request from browser */
	// @Override
	// public void configure(WebSecurity web) throws Exception {
	// web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	// }
}
