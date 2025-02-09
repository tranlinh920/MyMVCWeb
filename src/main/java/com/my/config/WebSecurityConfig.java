package com.my.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
//@PropertySource(value= {"classpath:application.properties"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

//    @Autowired
//    private DataSource dataSource;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// Setting Service to find User in the database.
		// And Setting PassswordEncoder
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		// The pages does not require login
		http.authorizeRequests().antMatchers("/", "/login", "/logout", "/admin/dang-nhap").permitAll();

		// /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
		// If no login, it will redirect to /login page.
		http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

		// For ADMIN only.
		http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");

		// For API only
		http.authorizeRequests()//
				.antMatchers(HttpMethod.GET, "/products/**").permitAll()//
				.antMatchers(HttpMethod.POST, "/products/add-to-cart/**").permitAll()//
				.antMatchers(HttpMethod.DELETE, "/products/del-from-cart/**").permitAll()//
				.antMatchers(HttpMethod.POST, "/products/**").hasRole("ADMIN")//
				.antMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN")//
				.antMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")//
				.antMatchers("/bills/**").permitAll()//
				.antMatchers(HttpMethod.DELETE, "/bills/**").hasRole("ADMIN")//
				.antMatchers("/bills/status/**").hasRole("ADMIN")//
				.antMatchers(HttpMethod.GET, "/bills/status/**").permitAll();//

		// When the user has logged in as XX.
		// But access a page that requires role YY,
		// AccessDeniedException will be thrown.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// Config for Login Form
		http.authorizeRequests().and().formLogin()//
				// Submit URL of login page.
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/admin/dang-nhap")//
				.defaultSuccessUrl("/admin")//
				.failureUrl("/admin/dang-nhap?error=true")//
				.usernameParameter("username")//
				.passwordParameter("password")
				// Config for Logout Page
				.and().logout().logoutUrl("/dang-xuat").logoutSuccessUrl("/trang-chu");

		// Config Remember Me.
//		http.authorizeRequests().and() //
//				.rememberMe().tokenRepository(this.persistentTokenRepository()) //
////				.tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
//				.tokenValiditySeconds(1 * 1 * 1 * 60); // 15p

	}

//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//		db.setDataSource(dataSource);
//		return db;
//	}

//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//		InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
//		return memory;
//	}

}