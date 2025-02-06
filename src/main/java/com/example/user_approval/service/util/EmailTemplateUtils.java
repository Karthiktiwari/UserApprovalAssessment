import org.springframework.stereotype.Component;
import org.springframework.core.io.ClassPathResource;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class EmailTemplateUtils {
    public String loadTemplate(String templateName) {
        try {
            return new String(Files.readAllBytes(Paths.get(
                new ClassPathResource("email-templates/" + templateName).getFile().getAbsolutePath()
            )));
        } catch (Exception e) {
            throw new RuntimeException("Could not read email template", e);
        }
    }

    public String processTemplate(String template, Object... params) {
        // Simple template processing logic
        return String.format(template, params);
    }
}