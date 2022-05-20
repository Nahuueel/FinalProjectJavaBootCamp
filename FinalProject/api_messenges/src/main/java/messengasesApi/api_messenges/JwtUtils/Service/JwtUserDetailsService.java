package messengasesApi.api_messenges.JwtUtils.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import messengasesApi.api_messenges.Model.Users;
import messengasesApi.api_messenges.Repository.IUserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired 
	private IUserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user = repository.findByUsernameAndState(username,true);
		if(user == null) throw new UsernameNotFoundException("User doesn't exists");
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

}
