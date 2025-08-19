package ru.team24.service.payload.request;

import lombok.Getter;
import lombok.Setter;
import ru.team24.database.enums.RequestState;

import java.util.Date;

@Getter
@Setter
public class CandidateResponse {
    private String candidateFirstName;
    private String candidateLastName;
    private String candidateFatherName;
    private Date candidateBirthDate;
    private String candidatePhone;

    private RequestState requestState;
    private String requestToken;
}
