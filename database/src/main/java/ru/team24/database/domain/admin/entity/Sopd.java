package ru.team24.database.domain.admin.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.team24.database.domain.general.entity.User;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sopd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sopd_id")
    private long sopdId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name= "sopd_text", nullable = false)
    private String sopdText;

    @Column(name = "sopd_created_at")
    private Date sopdCreatedAt = new Date();

    @Column(name = "sopd_updated_at")
    private Date sopdUpdatedAt;

    @Column(name = "sopd_last_review")
    private Date sopdLastReview;

    @Column(name = "sopd_is_active")
    private boolean sopdIsActive = true;
}
