package messengasesApi.api_messenges.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import messengasesApi.api_messenges.Model.Chats;
import messengasesApi.api_messenges.Model.Integrators;
import messengasesApi.api_messenges.Model.Users;
import messengasesApi.api_messenges.Repository.IIntegratorsRepository;
import messengasesApi.api_messenges.Repository.IUserRepository;


@Service
public class UserService {

	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private IIntegratorsRepository integratorsRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	public Users get(Long id) {
		return userRepo.findByIdAndState(id, true);
	}
	
	public Users getByUsername(String username) {
		return userRepo.findByUsernameAndState(username, true);
	}
	
	public List<Users> getAllByFullname(String fullName){
		return userRepo.findByFullNameAndState(fullName, true);
	}
	
	public boolean save(Users user) {
		if(userRepo.existsByUsernameAndState(user.getUsername(), true)) {
			return false;
		}
		String passwordHashed = encoder.encode(user.getPassword());
		user.setPassword(passwordHashed);
		userRepo.save(user);
		
		return true;
	}	
	
	public boolean update(Users user) {
		if(!userRepo.existsByIdAndState(user.getId(), true)) {
			return false;
		}
		String passwordHashed = encoder.encode(user.getPassword());
		user.setPassword(passwordHashed);
		userRepo.save(user);
		return true;
	}
	
	public boolean delete(Long id) {
		if(!userRepo.existsByIdAndState(id, true)) {
			return false;
		}
		userRepo.deleteById(id);
		return true;
	
	}

	public List<Chats> getAllChatsByUser(long id){
		if(!userRepo.existsByIdAndState(id, true)) return null;
		Users user = userRepo.findByIdAndState(id, true);
		List<Integrators> integrators = integratorsRepo.findByUserAndState(user, true);
		List<Chats> chats = new ArrayList<>();
		for(Integrators integrator: integrators){
			chats.add(integrator.getChat());
		}
		return chats;
	}

}
