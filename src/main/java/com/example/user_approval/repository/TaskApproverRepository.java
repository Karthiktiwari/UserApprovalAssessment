
import com.example.user_approval.entity.TaskApprover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskApproverRepository extends JpaRepository<TaskApprover, UUID> {
    List<TaskApprover> findByTaskId(UUID taskId);
    List<TaskApprover> findByApproverId(UUID approverId);
    Optional<TaskApprover> findByTaskIdAndApproverId(UUID taskId, UUID approverId);
}