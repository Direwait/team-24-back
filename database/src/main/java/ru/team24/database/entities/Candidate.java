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
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="candidate_id")
    private long candidateId;

    @Column(nullable = false)
    private String candidateFirstName;

    @Column(nullable = false)
    private String candidateLastName;

    private String candidateFatherName;

    @Column(nullable = false, unique = true)
    private String candidateMail;

    @Column(nullable = false)
    private Date candidateBirthDate;

    @Column(nullable = false)
    private String candidatePhone;

    private Date candidateCreatedAt = new Date();
}
