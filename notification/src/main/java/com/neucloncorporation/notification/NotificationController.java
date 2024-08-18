package com.neucloncorporation.notification;

import com.neucloncorporation.clients.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/notifications")
public record NotificationController(NotificationService notificationService) {

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
        log.info("customer notification {}", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
