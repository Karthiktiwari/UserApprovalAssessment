
import lombok.Builder;
import lombok.Data;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
@Builder
public class TaskApproverResponse {
    private UUID id;
    private UUID approverId;
    private String approverName;
    private TaskApproverStatus status;
}