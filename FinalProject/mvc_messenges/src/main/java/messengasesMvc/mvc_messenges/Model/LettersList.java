package messengasesMvc.mvc_messenges.Model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class LettersList {
	private List<LetterModel> msgs;
	
	public LettersList() {
		msgs = new ArrayList<LetterModel>();
	}
	
	
	
}
