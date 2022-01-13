package com.project.app.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.app.Exceptions.App.AppAccessDeniedHandler;
import com.project.app.Exceptions.App.AppAuthenticationEntryPoint;
import com.project.app.Repositories.UserRepository;
import com.project.app.Services.IUserService;

@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder getPasswordEncoder;
	@Autowired
	private IUserService userService;

	@Autowired
	private UserRepository userRepository;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(getPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.exceptionHandling()
				.accessDeniedHandler(new AppAccessDeniedHandler())
				.authenticationEntryPoint(new AppAuthenticationEntryPoint())
			.and()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/users").permitAll()
//				.antMatchers("/users").hasRole(Roles.ADMIN.name())
				.anyRequest().authenticated().and()
			.addFilter(new AuthenticationFilter(authenticationManager(), userRepository))
			.addFilter(new AuthorizationFilter(authenticationManager()));
	}

}
