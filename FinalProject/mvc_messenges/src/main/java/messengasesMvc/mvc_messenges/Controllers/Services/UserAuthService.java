package messengasesMvc.mvc_messenges.Controllers.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import messengasesMvc.mvc_messenges.Model.UserAuthModel;
import messengasesMvc.mvc_messenges.Model.Repository.IUserAuthRepository;

@Service
public class UserAuthService {
    
    @Autowired
    private IUserAuthRepository repository;

    public void saveToken(UserAuthModel user){
        if(repository.findByUsername(user.getUsername()) != null ){
            UserAuthModel userToModify = repository.findByUsername(user.getUsername());
            userToModify.setToken(user.getToken());
            repository.save(userToModify);
        }
        else{
            repository.save(user);
        }
    }

    public String getToken(String username){
        UserAuthModel user = repository.findByUsername(username);
        String token = user.getToken();
        return token;
    }
}
