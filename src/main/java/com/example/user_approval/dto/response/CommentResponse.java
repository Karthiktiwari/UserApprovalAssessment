
import lombok.Builder;
import lombok.Data;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponse {
    private UUID id;
    private UUID userId;
    private String userName;
    private String content;
    private LocalDateTime createdAt;
}