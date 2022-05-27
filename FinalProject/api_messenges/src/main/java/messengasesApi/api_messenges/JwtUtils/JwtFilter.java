package messengasesApi.api_messenges.JwtUtils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import messengasesApi.api_messenges.JwtUtils.Service.JwtUserDetailsService;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	 @Autowired
	 private JwtUserDetailsService service;
	 
	 @Autowired
	 private TokenManager manager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String tokenHeader = request.getHeader("Authorization");
		String username = null;
		String token = null;
		if(tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			token = tokenHeader.substring(7);
			try {
				username = manager.getUsernameFromToken(token);
			}catch(IllegalArgumentException e) {
				System.out.println("Unable to get token: "+ e.getMessage());
			}catch(ExpiredJwtException e) {
				System.out.println("Expired token");
			}
		}
		else {
			System.out.println("Token bearer not found");
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetail = service.loadUserByUsername(username);
			if(manager.validateToken(token, userDetail)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetail,
						null,
						userDetail.getAuthorities()
				);
				authenticationToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request)
				);
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}


}
