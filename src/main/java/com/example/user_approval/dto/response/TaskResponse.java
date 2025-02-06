
import lombok.Builder;
import lombok.Data;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
@Builder
public class TaskResponse {
    private UUID id;
    private String title;
    private String description;
    private TaskStatus status;
    private UUID creatorId;
    private LocalDateTime createdAt;
    private List<TaskApproverResponse> approvers;
    private List<CommentResponse> comments;
}