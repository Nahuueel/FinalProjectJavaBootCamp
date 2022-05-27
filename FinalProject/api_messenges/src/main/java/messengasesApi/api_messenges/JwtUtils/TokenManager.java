package messengasesApi.api_messenges.JwtUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class TokenManager implements Serializable{
private static final long serialVersionUID = 3623519534211374245L;
	
	@Value("${secret}")
	private String jwtSecret;
	public static final long TOKEN_VALIDITY = 1800;
	
	public String generateToken(UserDetails user) {
		Map<String, Object> claims = new HashMap<>();
		String token = Jwts.builder()
				.setClaims(claims)
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS256, jwtSecret)
				.compact();
		return token;
	}
	
	public boolean validateToken(String token, UserDetails user) {
		String username = getUsernameFromToken(token);
		final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		boolean isTokenExpired = claims.getExpiration().before(new Date());
		return ((username.equals(user.getUsername())) && !isTokenExpired);
	}
	
	public String getUsernameFromToken(String token) {
		final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
}
