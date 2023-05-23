package com.retobbva.challenge.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String bearer= request.getHeader("Authorization");
		
		if (bearer!=null&&bearer.startsWith("Bearer ")) {
			String token = bearer.replace("Bearer ", "");
			UsernamePasswordAuthenticationToken usernamePassAuthTok= TokenUtils.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(usernamePassAuthTok);
		}
		filterChain.doFilter(request, response);

		
	}

}
