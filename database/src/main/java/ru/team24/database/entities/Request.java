package ru.team24.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.team24.database.enums.RequestState;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    private long requestId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "candidate_id", nullable = false)
    private Candidate candidate;

    @OneToOne
    @JoinColumn(name = "template_id", referencedColumnName = "template_id", nullable = false)
    private Template template;

    @Column(nullable = false, unique = true)
    private String requestToken;

    @Column(nullable = false)
    private RequestState requestState;

    private Date requestDate = new Date();

    public long getUserId(){
        return user.getUserId();
    }
    public void setUserId(long userId){
        user.setUserId(userId);
    }
    public long getCandidateId(){
        return candidate.getCandidateId();
    }
    public void setCandidateId(long candidateId){
        candidate.setCandidateId(candidateId);
    }
    public long getTemplateId(){
        return template.getTemplateId();
    }
}
