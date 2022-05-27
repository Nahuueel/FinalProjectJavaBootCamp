package messengasesMvc.mvc_messenges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import messengasesMvc.mvc_messenges.Model.ChatModel;
import messengasesMvc.mvc_messenges.Model.LetterModel;
import messengasesMvc.mvc_messenges.Model.UserModel;
import messengasesMvc.mvc_messenges.services.LetterService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLetter {

	
	@Mock
	private LetterService servicio;
	
	private UserModel usuario1 = new UserModel(1l, "pepe97", "1234", "Pepito Guerrero", "es");
	private ChatModel chat = new ChatModel();
	private LetterModel mensaje = new LetterModel(1l,usuario1 ,chat , "Soy un mensaje");
	
	
	@Test
	public void GET() {
		when(servicio.getLetterById(mensaje.getId())).thenReturn(mensaje);
		LetterModel msg = servicio.getLetterById(mensaje.getId());
		assertNotNull(msg);
		assertEquals(msg, mensaje);
	}
	
	@Test
	public void CREATE() {
		when(servicio.createLetter(mensaje)).thenReturn(true);
		boolean result = servicio.createLetter(mensaje);
		assertTrue(result);
	
	}
	
	
	@Test
	void Context_Load() {
		
	}
}
