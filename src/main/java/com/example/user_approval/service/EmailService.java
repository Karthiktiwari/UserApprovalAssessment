
import com.example.user_approval.entity.Task;
import com.example.user_approval.entity.User;

import java.util.UUID;

public interface EmailService {
    void sendTaskAssignmentEmail(UUID recipientId, Task task);
    void sendTaskStatusUpdateEmail(UUID recipientId, Task task, String message);
    void sendCommentNotificationEmail(UUID recipientId, Task task, String commentContent);
}