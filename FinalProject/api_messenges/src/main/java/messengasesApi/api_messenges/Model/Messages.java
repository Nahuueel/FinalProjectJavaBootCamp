package messengasesApi.api_messenges.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Messages {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chats chat;

    @Column(name = "sendedDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date sendedDate;

    @Column(name = "updatedDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    
    @Column(name = "content",nullable = false)
    private String content;

    @SuppressWarnings("unused")
    @PrePersist
    private void onInsert(){
        this.sendedDate = new Date();
        this.updatedDate = new Date();
    }

    @SuppressWarnings("unused")
    @PreUpdate
    private void onUpdate(){
        this.updatedDate = new Date();
    }
}
