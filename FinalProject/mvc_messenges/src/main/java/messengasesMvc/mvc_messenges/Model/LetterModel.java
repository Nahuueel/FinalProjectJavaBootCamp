package messengasesMvc.mvc_messenges.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LetterModel {
    private long id;
    private UserModel user;
    private ChatModel chat;
    private String content;
}
