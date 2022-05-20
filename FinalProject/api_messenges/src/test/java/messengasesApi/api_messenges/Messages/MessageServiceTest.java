package messengasesApi.api_messenges.Messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import messengasesApi.api_messenges.Model.Chats;
import messengasesApi.api_messenges.Model.Messages;
import messengasesApi.api_messenges.Model.Users;
import messengasesApi.api_messenges.Repository.IMessageRepository;
import messengasesApi.api_messenges.Services.MessageService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageServiceTest {
    /*
    @InjectMocks
    private MessageService service;

    @Mock
    private IMessageRepository repositoryMock;

    private Messages message = new Messages(5l, new Users(), new Chats(), "loremloremlorem");

    @Test
    public void GET_ALL(){
        when(repositoryMock.findAll()).thenReturn(new ArrayList<>());
        List<Messages> messages = service.getAll();
        assertNotNull(messages);
    }

    @Test
    public void GET_ALL_NULL(){
        when(repositoryMock.findAll()).thenReturn(null);
        List<Messages> messages = service.getAll();
        assertNull(messages);
    }

    @Test
    public void GET(){
        when(repositoryMock.getById(message.getId())).thenReturn(message);
        Messages messageFound = service.get(message.getId());
        assertNotNull(messageFound);
        assertEquals(messageFound, message);
    }

    @Test
    public void GET_NULL(){
        when(repositoryMock.getById(message.getId())).thenReturn(null);
        Messages messageFound = service.get(message.getId());
        assertNull(messageFound);
    }

    public void GET_BY_USER(){
        
    }
    */
}
