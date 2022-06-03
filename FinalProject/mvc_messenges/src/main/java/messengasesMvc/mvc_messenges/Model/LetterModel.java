package messengasesMvc.mvc_messenges.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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
    private UserModel user;
    private ChatModel chat; 
    private String content;
    private Date sendedDate;
    private Date updatedDate; 
    

}
