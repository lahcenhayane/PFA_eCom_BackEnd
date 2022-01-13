package com.project.app.Security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.project.app.Exceptions.AuthException;
import com.project.app.Exceptions.App.AppErrors;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

		String Header = request.getHeader(ConstSecurity.HEADER_TOKEN);
		if (Header == null || !Header.startsWith(ConstSecurity.PREFIX_TOKEN)) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken auth = getAuth(request);
		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuth(HttpServletRequest request){
		String token = request.getHeader(ConstSecurity.HEADER_TOKEN);
		if (token == null) throw new AuthException(AppErrors.JWT);

		token = token.replace(ConstSecurity.PREFIX_TOKEN, "");
		String user = null;
		try {
			user = Jwts.parser().setSigningKey(ConstSecurity.SECRET_TOKEN)
					.parseClaimsJws(token).getBody().getSubject();
		} catch (ExpiredJwtException | SignatureException e) {
			 throw new AuthException(AppErrors.JWT);
		}

		if (user != null) {
			return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
		}
		return null;
	}



}
