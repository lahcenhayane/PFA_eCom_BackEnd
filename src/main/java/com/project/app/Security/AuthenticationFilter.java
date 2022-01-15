package com.project.app.Security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.app.DTO.Requests.LoginRequest;
import com.project.app.Entities.UserEntity;
import com.project.app.Exceptions.AuthException;
import com.project.app.Exceptions.NotFoundException;
import com.project.app.Repositories.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;

	public AuthenticationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
		try {
			LoginRequest login = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

			if (login.getEmail() == null || login.getPassword() == null) throw new NotFoundException("email or password is empty.");

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
		}catch (AuthenticationException | IOException ex) {
			throw new AuthException(ex.getMessage());
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		UserEntity user = userRepository.findByEmail(authResult.getName());

		String TOKEN = Jwts.builder()
							.setSubject(user.getEmail())
							.setExpiration(new Date(System.currentTimeMillis() + ConstSecurity.DTAE_EXPERATION_TOKEN))
							.signWith(SignatureAlgorithm.HS512, ConstSecurity.SECRET_TOKEN)
							.compact();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.addHeader(ConstSecurity.HEADER_TOKEN, ConstSecurity.PREFIX_TOKEN+""+TOKEN);
//		String _Token = ConstSecurity.PREFIX_TOKEN+""+TOKEN;
		response.getWriter().write("{\"" +ConstSecurity.HEADER_TOKEN + "\":\""+ TOKEN +"\" ,"
				+ "\"" +"username"+ "\":\""+ user.getEmail() +"\","
				+ "\"" +"user"+ "\":\""+ user.getUsername() +"\"}");
	}

}
