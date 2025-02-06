
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
public class CreateTaskRequest {
    @NotBlank(message = "Title should not be blank")
    private String title;
    private String description;
    private List<UUID> approverIds;
}