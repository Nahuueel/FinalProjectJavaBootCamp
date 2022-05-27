package messengasesApi.api_messenges.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Chats {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @Column(name = "createdDate",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Column(name = "updatedDate",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(name = "state", nullable = false)
    private boolean state;

    @SuppressWarnings("unused")
    @PrePersist
    private void onInsert() {
        this.createdDate = new Date();
        this.updatedDate = new Date();
        this.state = true;
    }

    @SuppressWarnings("unused")
    @PreUpdate
    private void onUpdate() {
        this.updatedDate = new Date();
    }

}
