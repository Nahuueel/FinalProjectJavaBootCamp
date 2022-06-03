package messengasesMvc.mvc_messenges.Controllers.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.cloud.translate.*;
import com.google.cloud.translate.Translate.TranslateOption;

import messengasesMvc.mvc_messenges.Model.LetterModel;
import messengasesMvc.mvc_messenges.Model.UserModel;

@Service
public class TraductorService {
	
	public List<LetterModel> translateLetters(List<LetterModel> letters, String lenguage) {
    	Translate translate = TranslateOptions.newBuilder().setApiKey("AIzaSyCZsohvyk3oB4aJdKNnjwYK02wKq2z7UvY").build().getService();
    	List<LetterModel> translates = new ArrayList<LetterModel>();
    	
    	for(LetterModel letter: letters) {
    		Detection detection = translate.detect(letter.getContent());
    		Translation translation = translate.translate(letter.getContent(),
    				TranslateOption.sourceLanguage(detection.getLanguage()),
    				TranslateOption.targetLanguage(lenguage));
	    		letter.setContent(translation.getTranslatedText());
	    		translates.add(letter);
    	}
    	return translates;
    		
    }

}
