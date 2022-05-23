package messengasesApi.api_messenges.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.google.cloud.translate.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Messages {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chats chat;

    private String content;
    
    
    public String getMessageTraduced(String mensaje) {
    	Translate translate = TranslateOptions.getDefaultInstance().getService();
    	Translation traduccion = translate.translate(mensaje);
    	return traduccion.getTranslatedText();
    	
    }
    
}
