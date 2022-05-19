package messengasesApi.api_messenges.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import messengasesApi.api_messenges.Model.Chats;
import messengasesApi.api_messenges.Model.Integrators;
import messengasesApi.api_messenges.Model.Users;



@Repository
public interface IIntegratorsRepository extends JpaRepository<Integrators, Long>{

	public List<Users> findByUserAndState(Users user, boolean states);
	public List<Integrators> findByChatAndState(Chats chat, boolean state);
}
