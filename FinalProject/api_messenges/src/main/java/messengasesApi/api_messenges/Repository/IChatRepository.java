package messengasesApi.api_messenges.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import messengasesApi.api_messenges.Model.Chats;
import messengasesApi.api_messenges.Model.Users;


@Repository
public interface IChatRepository extends JpaRepository<Chats, Long>{
	
	public Chats findByIdAndState(long id, boolean state);

	public Page<Chats> findByState(boolean state, Pageable page);

	public boolean existsByIdAndState(long id, boolean state);

	@Modifying
	@Query(value = "UPDATE chats SET state = false WHERE id = :id", nativeQuery = true)
	public void deleteById(@Param("id") long id);

	public List<Chats> findByNameAndState(String name, boolean state);
}
