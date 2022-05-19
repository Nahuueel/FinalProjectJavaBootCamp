package messengasesApi.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"})
    }
)
public class Users{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String username;
    
    private String password;
    
    private String fullName;
    
    private boolean state;
}