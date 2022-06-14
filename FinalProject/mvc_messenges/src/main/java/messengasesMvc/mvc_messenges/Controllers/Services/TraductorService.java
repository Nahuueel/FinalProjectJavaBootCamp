package messengasesMvc.mvc_messenges.Controllers.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import messengasesMvc.mvc_messenges.Model.LetterModel;

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