package com.hsc.cat.security;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hsc.cat.service.UserService;
import com.hsc.cat.utilities.CATConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	UserService userDetailsService;
	
//	private static final Logger LOGGER = (Logger) LogManager.getLogger(JwtFilter.class.getName());

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException  {

		final String authHeader = request.getHeader("authorization");
			//String ip=InetAddress.getLocalHost().getHostAddress().trim();
		 String ip=request.getRemoteAddr();
		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);

			chain.doFilter(request, response);
		} else {
			if(!request.getRequestURL().toString().contains("cat")
					|| request.getRequestURL().toString().contains("auth")) {
				chain.doFilter(request, response);
			}
			else {
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				throw new ServletException("Missing or invalid Authorization header");
			}
			final String token = authHeader.substring(7);

			try {
				String user = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
				Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
				Map<String, Object> localMap = new HashMap<String, Object>();
				localMap.put(CATConstants.KEY_ROLE, claims.get("roles"));
				localMap.put(CATConstants.KEY_USER_GRP, claims.get("userGrp"));
				localMap.put(CATConstants.KEY_IP, ip);
				localMap.put(CATConstants.KEY_USER, user);
				AppThreadLocal.set(localMap);
				//LOGGER.info("checking authentication for user " + user);
		        if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		            UserDetails userDetails = userDetailsService.loadUserByUsername(user);
	                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                //LOGGER.info("authenticated user " + user + ", setting security context");
	                SecurityContextHolder.getContext().setAuthentication(authentication);
		        }

			} catch (final SignatureException e) {
				throw new ServletException("Invalid token");
			}

			chain.doFilter(request, response);
			}
		}
	}
}
