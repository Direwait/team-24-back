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

    private String candidateFirstName;

    private String candidateLastName;

    private String candidateFatherName;

    private String candidateMail;

    private Date candidateBirthDate;

    private String candidatePhone;

    private Date candidateCreatedAt = new Date();
}
