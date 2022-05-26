package messengasesMvc.mvc_messenges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import messengasesMvc.mvc_messenges.Model.UserModel;
import messengasesMvc.mvc_messenges.services.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
class MvcMessengesApplicationTests {

	
	@Mock
	private UserService userService;
	

	
	private UserModel usuario1 = new UserModel(1l, "pepe97", "1234", "Pepito Guerrero", "es");
	
	
	@Test
	public void GET() {
		when(userService.getUserById(usuario1.getId())).thenReturn(usuario1);
		UserModel userFound = userService.getUserById(usuario1.getId());
		assertNotNull(userFound);
		assertEquals(usuario1, userFound);
	}
	
	@Test
	public void CREATE() {
		when(userService.createUser(usuario1)).thenReturn(true);
		boolean result = userService.createUser(usuario1);
		assertTrue(result);
	}
	
	
	@Test
	public void UPDATE() {
		when(userService.getUserById(usuario1.getId())).thenReturn(usuario1);
		when(userService.createUser(usuario1)).thenReturn(true);
		userService.updateUser(usuario1);
		boolean result = userService.createUser(usuario1);
		assertTrue(result);
	}
	
	@Test
	public void UPDATE_FAIL() {
		when(userService.getUserById(usuario1.getId())).thenReturn(null);
		when(userService.createUser(usuario1)).thenReturn(false);
		boolean result = userService.createUser(usuario1);
		assertFalse(result);
	}
	
	@Test
	void contextLoads() {
	}

}
