package messengasesApi.api_messenges.Model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChatsList {
	private List<Chats> chats;
	
	public ChatsList() {
		chats = new ArrayList<Chats>();
	}
	
	
	
}
