
import com.example.user_approval.dto.request.CreateTaskRequest;
import com.example.user_approval.dto.response.TaskResponse;
import com.example.user_approval.dto.response.CommentResponse;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponse createTask(UUID creatorId, CreateTaskRequest request);
    TaskResponse getTask(UUID taskId);
    List<TaskResponse> getTasksByUser(UUID userId);
    TaskResponse approveTask(UUID taskId, UUID approverId);
    TaskResponse rejectTask(UUID taskId, UUID approverId);
    CommentResponse addComment(UUID taskId, UUID userId, String content);
}