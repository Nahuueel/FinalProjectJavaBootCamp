package messengasesApi.api_messenges.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

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
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotNull
    @Column(name = "username")
    private String username;
    
    @NotNull
    @Column(name = "password")
    private String password;
    
    @NotNull
    @Column(name = "fullName")
    private String fullName;

    @Column(name="language")
    private String language;
    
    @Column(name = "createdDate")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(name = "state")
    private boolean state;

    @SuppressWarnings("unused")
    @PrePersist
    private void onInsert(){
        if(this.language == null){
            this.language = "en";
        }
        this.createdDate = new Date();
        this.updatedDate = new Date();
        this.state = true;
    }

    @SuppressWarnings("unused")
    @PreUpdate
    private void onUpdate(){
        this.updatedDate = new Date();
    }
}