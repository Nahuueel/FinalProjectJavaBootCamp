package messengasesMvc.mvc_messenges.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LetterDTO {
	private long id;
    private long idUser;
    private long idChat;
    private String content;
    
}
