package messengasesApi.api_messenges.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import messengasesApi.api_messenges.Model.Messages;
import messengasesApi.api_messenges.Repository.IMessageRepository;



@Service
public class MessageService {

	@Autowired
	private IMessageRepository messageRepo;
	
	public List<Messages> getAll(){
		return messageRepo.findAll();
	}
	
	
	public Messages get(Long id) {
		return messageRepo.getById(id);
	}
	
	public List<Messages> getByUser(Long userId) {
		return messageRepo.findByUser(userId);
	}

	public List<Messages> getAllByChat(Long chatId) {
		return messageRepo.findByChat(chatId);
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
