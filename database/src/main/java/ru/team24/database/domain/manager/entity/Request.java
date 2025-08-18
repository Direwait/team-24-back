package ru.team24.database.domain.manager.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.team24.database.domain.admin.entity.Sopd;
import ru.team24.database.domain.admin.entity.Template;
import ru.team24.database.domain.general.entity.User;
import ru.team24.database.enums.RequestState;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Request implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "candidate_id", nullable = false)
    private Candidate candidate;

    @OneToOne
    @JoinColumn(name = "template_id", referencedColumnName = "template_id", nullable = false)
    private Template template;

    @OneToOne
    @JoinColumn(name = "sopd_id", referencedColumnName = "sopd_id", nullable = false)
    private Sopd sopd;

    @Column(nullable = false, unique = true)
    private String requestToken;

    @Enumerated(EnumType.STRING)
    private RequestState requestState;

    @Builder.Default
    private Date requestDate = new Date();

    public long getUserId(){
        return user.getUserId();
    }
    public long getCandidateId(){
        return candidate.getCandidateId();
    }
    public long getTemplateId(){
        return template.getTemplateId();
    }
}
