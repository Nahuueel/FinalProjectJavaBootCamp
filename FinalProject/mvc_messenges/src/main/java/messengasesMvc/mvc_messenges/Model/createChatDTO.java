package messengasesMvc.mvc_messenges.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data 
public class createChatDTO {
	
	private String chatName;
	private String integratorUsername;
	private UserModel user;
}
	