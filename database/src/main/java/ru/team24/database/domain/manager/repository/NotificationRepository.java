package ru.team24.database.domain.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team24.database.domain.manager.entity.Notification;
import ru.team24.database.enums.NotificationState;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<Notification> findByNotificationId(long notificationId);
    List<Notification> getByNotificationState(NotificationState notificationState);
}
