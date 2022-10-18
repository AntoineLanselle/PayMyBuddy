package com.payMyBuddy.app.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class to configure authentication and authorization with Spring Security.
 * 
 * @author Antoine
 */
@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LogManager.getLogger(SpringSecurityConfig.class);

	/**
	 * 
	 *
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("springuser").password(passwordEncoder().encode("spring123"))
				.roles("USER").and().withUser("springadmin").password(passwordEncoder().encode("admin123"));
	}

	/**
	 * 
	 *
	 * @param http
	 * @throws Exception
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		logger.debug("Authentication Manager.");
		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN").antMatchers("/user").hasRole("USER")
				.anyRequest().authenticated().and().formLogin();
	}

	/**
	 * Method to encode password with BCrypt hash.
	 *
	 * @return encoded passeword.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		logger.debug("Encode password");
		return new BCryptPasswordEncoder();
	}
}