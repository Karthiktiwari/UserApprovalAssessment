package com.example.user_approval.service.impl;

import com.example.user_approval.dto.response.NotificationResponse;
import com.example.user_approval.entity.Notification;
import com.example.user_approval.repository.NotificationRepository;
import com.example.user_approval.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public Notification createNotification(Notification notification) {
        notification.setCreatedAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    @Override
    public List<NotificationResponse> getAllNotifications(UUID userId) {
        return notificationRepository.findByUserId(userId).stream()
                .map(notification -> new NotificationResponse(notification.getId(), notification.getUserId(), notification.getMessage(), notification.getCreatedAt()))
                .collect(Collectors.toList());
    }
}