package ru.team24.database.domain.manager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Candidate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long candidateId;
  
    @Column(name = "candidate_first_name", nullable = false)
    private String candidateFirstName;

    @Column(name= "candidate_last_name", nullable = false)
    private String candidateLastName;

    @Column(name = "candidate_father_name")
    private String candidateFatherName;
  
    @Column(name = "candidate_mail", nullable = false, unique = true)
    private String candidateMail;

    @Column(name = "candidate_birth_date", nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date candidateBirthDate;

    @Column(name = "candidate_phone", nullable = false)
    private String candidatePhone;

    @Builder.Default
    @Column(name = "candidate_created_at")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date candidateCreatedAt = new Date();
}
