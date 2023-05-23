package com.retobbva.challenge.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retobbva.challenge.controller.CurrencyController;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		AuthenticationCredentials authenticationCredentials = new AuthenticationCredentials();
		
		try {
			authenticationCredentials= new ObjectMapper().readValue(request.getReader(), AuthenticationCredentials.class);
		}catch (IOException e) {
		}
		
		UsernamePasswordAuthenticationToken usernamePassAuthTok= new UsernamePasswordAuthenticationToken(
				authenticationCredentials.getEmail(),
				authenticationCredentials.getPassword(),
				Collections.emptyList());

		return getAuthenticationManager().authenticate(usernamePassAuthTok);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		UserDetailsImpl userDetailsImpl=(UserDetailsImpl)authResult.getPrincipal();
		String token = TokenUtils.createToken(userDetailsImpl.getName(), userDetailsImpl.getUsername());
		CurrencyController.auditName=userDetailsImpl.getName();
		response.addHeader("Authorization","Bearer "+ token);
		response.getWriter().flush();
		super.successfulAuthentication(request, response, chain, authResult);
	}

}
