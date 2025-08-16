package ru.team24.service.dto;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SopdDto {
    private long sopdId;

    private long userId;

    private String sopdText;

    private Date sopdCreatedAt;

    private Date sopdUpdatedAt;

    private Date sopdLastReview;

    private boolean sopdIsActive;
}



