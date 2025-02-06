
import dto.request.*;
import dto.response.*;
import service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestBody CreateTaskRequest request) {
        return ResponseEntity.ok(taskService.createTask(userId, request));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable UUID taskId) {
        return ResponseEntity.ok(taskService.getTask(taskId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<TaskResponse>> getUserTasks(
            @RequestHeader("X-User-Id") UUID userId) {
        return ResponseEntity.ok(taskService.getTasksByUser(userId));
    }

    @PostMapping("/{taskId}/approve")
    public ResponseEntity<TaskResponse> approveTask(
            @PathVariable UUID taskId,
            @RequestHeader("X-User-Id") UUID userId) {
        return ResponseEntity.ok(taskService.approveTask(taskId, userId));
    }

    @PostMapping("/{taskId}/reject")
    public ResponseEntity<TaskResponse> rejectTask(
            @PathVariable UUID taskId,
            @RequestHeader("X-User-Id") UUID userId) {
        return ResponseEntity.ok(taskService.rejectTask(taskId, userId));
    }

    @PostMapping("/{taskId}/comments")
    public ResponseEntity<CommentResponse> addComment(
            @PathVariable UUID taskId,
            @RequestHeader("X-User-Id") UUID userId,
            @Valid @RequestBody AddCommentRequest request) {
        return ResponseEntity.ok(taskService.addComment(taskId, userId, request.getContent()));
    }
}