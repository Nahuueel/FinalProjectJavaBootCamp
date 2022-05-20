package messengasesApi.api_messenges.Users;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import messengasesApi.api_messenges.Model.Chats;
import messengasesApi.api_messenges.Model.Users;
import messengasesApi.api_messenges.Repository.IIntegratorsRepository;
import messengasesApi.api_messenges.Repository.IUserRepository;
import messengasesApi.api_messenges.Services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private IUserRepository repositoryMock;

    @Mock
    private IIntegratorsRepository repositoryIntegrator;
    
    private Users user = new Users(1l, "arielS", "1234", "Ariel Santangelo", null, Date.valueOf("2022-05-19"), Date.valueOf("2022-05-19"), true);

    @Test
    public void GET(){
        when(repositoryMock.findByIdAndState(user.getId(), true)).thenReturn(user);
        Users userFound = service.get(user.getId());
        assertNotNull(userFound);
        assertEquals(user, userFound);
    }

    @Test
    public void GET_NULL(){
        when(repositoryMock.findByIdAndState(user.getId(), true)).thenReturn(null);
        Users userFound = service.get(user.getId());
        assertNull(userFound);
    }

    @Test
    public void GET_BY_USERNAME(){
        when(repositoryMock.findByUsernameAndState(user.getUsername(), true)).thenReturn(user);
        Users userFound = service.getByUsername(user.getUsername());
        assertNotNull(userFound);
        assertEquals(user, userFound);
    }

    @Test
    public void GET_BY_USERNAME_NULL(){
        when(repositoryMock.findByUsernameAndState(user.getUsername(), true)).thenReturn(null);
        Users userFound = service.getByUsername(user.getUsername());
        assertNull( userFound);
    }

    @Test
    public void GET_ALL_BY_FULLNAME(){
        when(repositoryMock.findByFullNameAndState(user.getFullName(), true)).thenReturn(new ArrayList<>());
        List<Users> usersFound = service.getAllByFullname(user.getFullName());
        assertNotNull(usersFound);
    }

    @Test
    public void GET_ALL_BY_FULLNAME_NULL(){
        when(repositoryMock.findByFullNameAndState(user.getFullName(), true)).thenReturn(null);
        List<Users> usersFound = service.getAllByFullname(user.getFullName());
        assertNull(usersFound);
    }

    @Test
    public void SAVE(){
        when(repositoryMock.existsByUsernameAndState(user.getUsername(), true)).thenReturn(false);
        when(repositoryMock.save(user)).thenReturn(user);
        boolean result = service.save(user);
        assertTrue(result);
    }
    
    @Test
    public void SAVE_BUT_USER_ALREADY_EXISTS(){
        when(repositoryMock.existsByUsernameAndState(user.getUsername(), true)).thenReturn(true);
        boolean result = service.save(user);
        assertFalse(result);
    }

    @Test
    public void UPDATE(){
        when(repositoryMock.existsByIdAndState(user.getId(), true)).thenReturn(true);
        when(repositoryMock.save(user)).thenReturn(user);
        boolean result = service.update(user);
        assertTrue(result);
    }

    @Test
    public void UPDATE_BUT_USER_DOES_NOT_EXISTS(){
        when(repositoryMock.existsByIdAndState(user.getId(), true)).thenReturn(false);
        boolean result = service.update(user);
        assertFalse(result);
    }

    @Test
    public void DELETE(){
        when(repositoryMock.existsByIdAndState(user.getId(), true)).thenReturn(true);
        boolean result = service.delete(user.getId());
        assertTrue(result);
    }

    @Test
    public void DELETE_BUT_USER_DOES_NOT_EXISTS(){
        when(repositoryMock.existsByIdAndState(user.getId(), true)).thenReturn(false);
        boolean result = service.delete(user.getId());
        assertFalse(result);
    }

    @Test
    public void GET_ALL_CHATS_BY_USER(){
        when(repositoryMock.existsByIdAndState(user.getId(), true)).thenReturn(true);
        when(repositoryMock.findByIdAndState(user.getId(), true)).thenReturn(user);
        when(repositoryIntegrator.findByUserAndState(user, true)).thenReturn(new ArrayList<>());
        List<Chats> chats = service.getAllChatsByUser(user.getId());
        assertNotNull(chats);
    }

    @Test
    public void GET_ALL_CHATS_BY_USER_BUT_HE_DOES_NOT_EXISTS(){
        when(repositoryMock.existsByIdAndState(user.getId(), true)).thenReturn(false);
        List<Chats> chats = service.getAllChatsByUser(user.getId());
        assertNull(chats);
    }
}