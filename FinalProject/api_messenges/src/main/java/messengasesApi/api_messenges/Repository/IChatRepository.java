package messengasesApi.api_messenges.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import messengasesApi.api_messenges.Model.Chats;


@Repository
public interface IChatRepository extends JpaRepository<Chats, Long>{

	@Query(value = "SELECT * FROM chats WHERE state = true", nativeQuery = true)
	public List<Chats> findAll();

	public boolean existsByIdAndState(long id, boolean state);

	public Chats getByIdAndState(long id, boolean state);

	@Modifying
	@Query(value = "UPDATE chats SET state = false WHERE id = :id", nativeQuery = true)
	public void deleteById(@Param("id") long id);

	public List<Chats> findByNameAndState(String name, boolean state);
}
