package messengasesMvc.mvc_messenges.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;




@NoArgsConstructor
@AllArgsConstructor
@Data
public class LetterModel {
	private long id;
    private long idUser;
    private long idChat;
    private String content;
    
    /*
    public void translate() {
    	Translate translate = TranslateOptions.newBuilder().setApiKey("AIzaSyCZsohvyk3oB4aJdKNnjwYK02wKq2z7UvY").build().getService();
    	Translation translation = translate.translate(this.content,
    			TranslateOption.sourceLanguage(this.content),
    			TranslateOption.targetLanguage(this.user.getLanguage()));
    	
    	this.content = translation.getTranslatedText();
    }
    */
}
