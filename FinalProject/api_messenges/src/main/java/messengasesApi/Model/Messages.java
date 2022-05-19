package messengasesApi.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Messages {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    
    @ManyToOne
    private Users user;

    @ManyToOne
    private Chats chat;

    private String content;

    private boolean state;
}
