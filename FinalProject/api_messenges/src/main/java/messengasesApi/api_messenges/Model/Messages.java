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
import com.google.cloud.translate.Translate.TranslateOption;

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
    
    
    public String getMessageTraduced() {
    	Translate translate = TranslateOptions.newBuilder().setApiKey("AIzaSyCZsohvyk3oB4aJdKNnjwYK02wKq2z7UvY").build().getService();
    	Detection deteccion = translate.detect(this.content);
    	String lenguajeMensaje = deteccion.getLanguage();
    	Translation traduccion = translate.translate(this.content,
    			TranslateOption.sourceLanguage(lenguajeMensaje),
    			TranslateOption.targetLanguage(this.user.getLanguage()));
    	return traduccion.getTranslatedText();
    	
    }
    
}
