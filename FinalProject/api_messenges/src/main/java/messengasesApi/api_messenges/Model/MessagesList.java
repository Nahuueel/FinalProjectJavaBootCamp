package messengasesApi.api_messenges.Model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MessagesList {
	private List<Messages> msgs;
	
	public MessagesList() {
		msgs = new ArrayList<Messages>();
	}
	
	
	
}
