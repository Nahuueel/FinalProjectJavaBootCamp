package messengasesMvc.mvc_messenges.Model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private long id;
    private String username;
    private String password;
    private String fullName;
    private String language;    
    private Date updatedDate;
    private boolean state;
    private Date createdDate;
}
