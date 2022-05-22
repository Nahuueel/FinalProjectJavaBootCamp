package messengasesApi.api_messenges.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import messengasesApi.api_messenges.Model.Chats;
import messengasesApi.api_messenges.Model.Messages;
import messengasesApi.api_messenges.Model.Users;
import messengasesApi.api_messenges.Repository.IChatRepository;
import messengasesApi.api_messenges.Repository.IMessageRepository;
import messengasesApi.api_messenges.Repository.IUserRepository;



@Service
public class MessageService {

	@Autowired
	private IMessageRepository messageRepo;
	
	@Autowired
	private IUserRepository userRepo;

	@Autowired 
	private IChatRepository chatRepo;

	public List<Messages> getAll(){
		return messageRepo.findAll();
	}
	
	
	public Optional<Messages> get(Long id) {
		return messageRepo.findById(id);
	}
	
	public List<Messages> getByUser(Long userId) {
		if(!userRepo.existsByIdAndState(userId, true)) return null;
		Users user = userRepo.findByIdAndState(userId, true);
		return messageRepo.findByUser(user);
	}

	public List<Messages> getAllByChat(Long chatId) {
		if(chatRepo.getByIdAndState(chatId, true) == null) return null;
		Chats chat = chatRepo.getByIdAndState(chatId, true); 
		return messageRepo.findByChat(chat);
	}
	

	public boolean save(Messages message) {
		if(messageRepo.existsById(message.getId())) {
			return false;
		}
		messageRepo.save(message);
		return true;
	}
	
	
	public boolean update(Messages user) {
		if(!messageRepo.existsById(user.getId())) {
			return false;
		}
		messageRepo.save(user);
		return true;
	}
	
	public boolean delete(Long id) {
		if(messageRepo.existsById(id)) {
			messageRepo.deleteById(id);
			return true;
		}
		return false;
	}

}
