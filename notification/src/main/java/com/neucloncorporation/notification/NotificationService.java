package com.neucloncorporation.notification;

import com.neucloncorporation.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    // here you can implement notification services like twilio or firebase
    public void send(NotificationRequest notificationRequest) {
        Notification notification = Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .sender("Stephen")
                .message(notificationRequest.message())
                .sentAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
    }
}
