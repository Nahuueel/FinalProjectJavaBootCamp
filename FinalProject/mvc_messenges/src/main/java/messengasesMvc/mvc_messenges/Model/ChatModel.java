package messengasesMvc.mvc_messenges.Model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatModel {
    private long id;
    private String name;
    private Date creationDate;
}
