package messengasesApi.api_messenges.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import messengasesApi.api_messenges.Model.Chats;
import messengasesApi.api_messenges.Model.Integrators;
import messengasesApi.api_messenges.Model.Users;
import messengasesApi.api_messenges.Repository.IChatRepository;
import messengasesApi.api_messenges.Repository.IIntegratorsRepository;
//import messengasesApi.api_messenges.Repository.IUserRepository;



@Service
public class ChatService {

	//@Autowired
	//private IUserRepository userRepo;
	
	@Autowired
	private IIntegratorsRepository integratorsRepo;
	
	@Autowired
	private IChatRepository chatRepo;
	
	
	public List<Chats> getAll(){
		return chatRepo.findAll();
	}
	
	public Chats get(Long id) {
		return chatRepo.getById(id);
	}
	
	public List<Chats> getByName(String name){
		return chatRepo.findByNameAndState(name, true);
	}
	
	public List<Users> getAllUserByChat(Long chatId){
		if(!chatRepo.existsByIdAndState(chatId, true)) return null;
		Chats chat = chatRepo.getByIdAndState(chatId, true);
		List<Integrators> integrators = integratorsRepo.findByChatAndState(chat, true);
		List<Users> users = new ArrayList<>();
		for(Integrators integrator: integrators){
			users.add(integrator.getUser());
		}
		return users;
	}

	public boolean save(Chats user) {
		if(chatRepo.existsByIdAndState(user.getId(), true)) {
			return false;
		}
		chatRepo.save(user);
		return true;
	}
	
	
	public boolean update(Chats user) {
		if(!chatRepo.existsByIdAndState(user.getId(), true)) {
			return false;
		}
		chatRepo.save(user);
		return true;
	}
	
	public boolean delete(Long id) {
		if(chatRepo.existsByIdAndState(id, true)) {
			chatRepo.deleteById(id);
			return true;
		}
		return false;
	}
}
