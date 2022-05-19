package messengasesApi.api_messenges.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import messengasesApi.api_messenges.Model.Messages;



@Repository
public interface IMessageRepository extends JpaRepository<Messages, Long>{

	public List<Messages> findByUser(Long userId);
	public List<Messages> findByChat(Long chatId);
}
