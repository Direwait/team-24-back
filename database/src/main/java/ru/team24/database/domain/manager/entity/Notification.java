package ru.team24.database.domain.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.team24.database.enums.NotificationState;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @OneToOne
    @JoinColumn(name = "request_id", referencedColumnName = "request_id")
    private Request request;

    @Column(name = "notification_text")
    private String notificationText;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_state")
    private NotificationState notificationState;

    @Column(name = "notification_created_at")
    private Date notificationCreatedAt = new Date();

    @Column(name = "notification_created_read_at")
    private Date notificationReadAt;

    public long getRequestId() {
        return request.getRequestId();
    }
}
