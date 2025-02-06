
import com.example.user_approval.dto.response.NotificationResponse;
import com.example.user_approval.entity.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    Notification createNotification(Notification notification);
    List<NotificationResponse> getUserNotifications(UUID userId);
    long getUnreadNotificationCount(UUID userId);
    void markNotificationAsRead(UUID notificationId);
}