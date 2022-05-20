package messengasesApi.api_messenges.JwtUtils.Model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 2008058871798420861L;
	
	@Getter @Setter private String token;
	
	public JwtResponse(String t) {
		token = t;
	}
}
