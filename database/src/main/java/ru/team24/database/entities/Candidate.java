package ru.team24.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
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
    private Date candidateBirthDate;

    @Column(name = "candidate_phone", nullable = false)
    private String candidatePhone;

    @Column(name = "candidate_created_at")
    private Date candidateCreatedAt = new Date();

    @Column(name = "candidate_is_active")
    private boolean candidateIsActive = true;
}
