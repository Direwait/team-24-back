package ru.team24.service.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.team24.database.domain.general.entity.User;
import ru.team24.database.domain.manager.entity.Candidate;
import ru.team24.database.enums.RequestState;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestWithCandidateAndManagerDto {
    private Long requestId;

    @NotNull(message = "User information is required")
    private User user;

    @NotNull(message = "Candidate information is required")
    private Candidate candidate;

    @NotNull(message = "Template information is required")
    private long templateId;

    @NotNull(message = "Template information is required")
    private long sopdId;

    @NotBlank(message = "Request token is required")
    @Size(min = 16, max = 64, message = "Request token must be between 16 and 64 characters")
    private String requestToken;

    @NotNull(message = "Request state is required")
    private RequestState requestState;

    @NotNull(message = "Request date is required")
    @PastOrPresent(message = "Request date must be in the past or present")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date requestDate;
}
