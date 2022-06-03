package messengasesApi.api_messenges.JwtUtils.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import messengasesApi.api_messenges.JwtUtils.TokenManager;
import messengasesApi.api_messenges.JwtUtils.Model.JwtResponse;
import messengasesApi.api_messenges.JwtUtils.Model.JwtUser;
import messengasesApi.api_messenges.JwtUtils.Service.JwtUserDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class JwtController {

	@Autowired
	private JwtUserDetailsService service;
	
	@Autowired 
	private AuthenticationManager authManager;

	@Autowired
	private TokenManager tokenManager;
	
	@PostMapping("/login")
	public ResponseEntity<?> auth(@RequestBody JwtUser user) throws Exception{
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		}catch(DisabledException e) {
			throw new Exception("DISABLED_EXCEPTION", e);
		}catch(BadCredentialsException e){
			throw new Exception("BAD_CREDENTIALS_EXCEPTION", e);
		}
		final UserDetails userDetail = service.loadUserByUsername(user.getUsername());
		final String token = tokenManager.generateToken(userDetail);
		return ResponseEntity.status(200).body((new JwtResponse(token)).getToken());
	}
}
