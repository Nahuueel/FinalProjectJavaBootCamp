package messengasesApi.api_messenges.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import messengasesApi.api_messenges.Model.Users;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long>{

	public List<Users> findByFullNameAndState(String fullName, boolean state);

	public boolean existsByUsernameAndState(String userName, boolean state);

	public boolean existsByIdAndState(long id, boolean state);

	public Users findByUsernameAndState(String userName, boolean state);

	public Users findByIdAndState(long id, boolean state);

	@Modifying
	@Query(value = "UPDATE users SET state = false WHERE id = :id", nativeQuery = true)
	public void deleteById(@Param("id") long id);
}
