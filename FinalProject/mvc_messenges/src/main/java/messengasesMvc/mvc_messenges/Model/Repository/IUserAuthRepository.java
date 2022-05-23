package messengasesMvc.mvc_messenges.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import messengasesMvc.mvc_messenges.Model.UserAuthModel;

@Repository
public interface IUserAuthRepository extends JpaRepository<UserAuthModel, Long> {
    public UserAuthModel findByUsername(String username);
}
