package ru.team24.service.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDto {

    private long candidateId;

    @NotBlank(message = "Candidate first name is required")
    @Length(min=2, max = 50, message = "First name must between 2 and 50 characters")
    private String candidateFirstName;

    @NotBlank(message = "Candidate last name is required")
    @Length(min=2, max = 50, message = "Last name must between 2 and 50 characters")
    private String candidateLastName;

    @NotBlank(message = "Candidate middle name is required")
    @Length(min=4, max = 50, message = "Father name must between 4 and 50 characters")
    private String candidateFatherName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String candidateMail;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private Date candidateBirthDate;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9\\-\\s()]{7,20}$",
            message = "Invalid phone number format")
    private String candidatePhone;

    private Date candidateCreatedAt;
}
