package ru.team24.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team24.service.dto.NotificationDto;
import ru.team24.database.entities.Notification;
import ru.team24.database.enums.NotificationState;
import ru.team24.database.repositories.NotificationRepository;
import ru.team24.database.repositories.RequestRepository;
import ru.team24.service.interfaces.EmailService;
import ru.team24.service.interfaces.NotificationService;
import ru.team24.service.mapper.NotificationMapper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationMapper notificationMapper;
    private final NotificationRepository notificationRepository;
    private final RequestRepository requestRepository;
    private final EmailService emailService;

    public NotificationDto finByNotificationId(long notificationId) {
        var notification = notificationRepository.findById(notificationId).orElse(null);
        return notificationMapper.entityToDto(notification);
    }

    public List<NotificationDto> finByNotificationState(String state){
        var notifications = notificationRepository.getByNotificationState(NotificationState.valueOf(state));
        return notifications.stream().map(notificationMapper::entityToDto).toList();
    }

    public void createNotification(NotificationDto notificationDto) {
        var request = requestRepository.findById(notificationDto.getNotificationId()).orElseThrow();
        try {
            emailService.sendEmail("noreply",
                    notificationDto.getNotificationText(),
                    request.getCandidate().getCandidateMail());
        } catch (Exception e) {
            notificationDto.setNotificationState(NotificationState.FAILED);
        }
        var notification = notificationMapper.dtoToEntity(notificationDto);
        notification.setNotificationId(null);//transfer to mapper
        notificationRepository.save(notification);
    }
}
