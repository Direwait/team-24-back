package ru.team24.database.entities;

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
    private long notificationId;

    @OneToOne
    @JoinColumn(name = "request_id", referencedColumnName = "request_id")
    private Request request;

    @Column(nullable = false)
    private String notificationText;

    @Column(nullable = false)
    private NotificationState notificationState;

    @Column(nullable = false)
    private Date notificationCreatedAt = new Date();

    private Date notificationReadAt;

}
