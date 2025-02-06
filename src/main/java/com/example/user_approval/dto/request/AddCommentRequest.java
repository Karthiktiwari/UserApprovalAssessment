
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class AddCommentRequest {
    @NotBlank(message = "Comment content cannot be blank")
    @Size(min = 1, max = 500, message = "Comment must be between 1 and 500 characters")
    private String content;
}