package messengasesMvc.mvc_messenges.Model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class ChatsList {
	private List<ChatModel> chats;
	
	public ChatsList() {
		chats = new ArrayList<ChatModel>();
	}
	
}