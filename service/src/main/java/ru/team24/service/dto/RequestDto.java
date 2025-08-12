package ru.team24.service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.team24.database.enums.RequestState;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    private long requestId;

    @NotNull(message = "User information is required")
    private long userId;

    @NotNull(message = "Candidate information is required")
    private long candidateId;

    @NotNull(message = "Template information is required")
    private long templateId;

    @NotBlank(message = "Request token is required")
    @Size(min = 16, max = 64, message = "Request token must be between 16 and 64 characters")
    private String requestToken;

    @NotNull(message = "Request state is required")
    private RequestState requestState;

    @NotNull(message = "Request date is required")
    @PastOrPresent(message = "Request date must be in the past or present")
    private Date requestDate;
}
