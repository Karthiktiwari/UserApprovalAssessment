import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(
    scanBasePackages = {
        "com.example.user_approval.controller",
        "com.example.user_approval.service",
        "com.example.user_approval.config"
    }
)
@EnableJpaRepositories(basePackages = "com.example.user_approval.repository")
@EntityScan(basePackages = "com.example.user_approval.entity")
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
public class UserApprovalApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApprovalApplication.class, args);
    }
}