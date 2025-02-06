package com.example.user_approval.service.impl;

import com.example.user_approval.dto.request.CreateTaskRequest;
import com.example.user_approval.dto.response.TaskResponse;
import com.example.user_approval.dto.response.CommentResponse;
import com.example.user_approval.entity.Task;
import com.example.user_approval.entity.TaskApprover;
import com.example.user_approval.entity.Comment;
import com.example.user_approval.enums.TaskStatus;
import com.example.user_approval.enums.TaskApproverStatus;
import com.example.user_approval.repository.TaskRepository;
import com.example.user_approval.repository.TaskApproverRepository;
import com.example.user_approval.repository.CommentRepository;
import com.example.user_approval.service.TaskService;
import com.example.user_approval.service.NotificationService;
import com.example.user_approval.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskApproverRepository taskApproverRepository;
    private final CommentRepository commentRepository;
    private final NotificationService notificationService;
    private final EmailService emailService;

    @Override
    @Transactional
    public TaskResponse createTask(UUID creatorId, CreateTaskRequest request) {
        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setCreatorId(creatorId);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());

        taskRepository.save(task);

        List<TaskApprover> approvers = request.getApprovers().stream()
                .map(approverId -> new TaskApprover(UUID.randomUUID(), task.getId(), approverId, TaskApproverStatus.PENDING))
                .collect(Collectors.toList());
        taskApproverRepository.saveAll(approvers);

        notificationService.notifyApprovers(approvers);
        emailService.sendTaskAssignmentEmail(creatorId, task);

        return new TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), approvers);
    }

    @Override
    public TaskResponse getTask(UUID taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        List<TaskApprover> approvers = taskApproverRepository.findByTaskId(taskId);
        return new TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), approvers);
    }

    @Override
    public List<TaskResponse> getTasksByUser(UUID userId) {
        return taskRepository.findByCreatorId(userId).stream()
                .map(task -> getTask(task.getId()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void approveTask(UUID taskId, UUID approverId) {
        TaskApprover taskApprover = taskApproverRepository.findByTaskIdAndApproverId(taskId, approverId)
                .orElseThrow(() -> new RuntimeException("Approver not found"));
        taskApprover.setStatus(TaskApproverStatus.APPROVED);
        taskApproverRepository.save(taskApprover);
    }

    @Override
    @Transactional
    public void rejectTask(UUID taskId, UUID approverId) {
        TaskApprover taskApprover = taskApproverRepository.findByTaskIdAndApproverId(taskId, approverId)
                .orElseThrow(() -> new RuntimeException("Approver not found"));
        taskApprover.setStatus(TaskApproverStatus.REJECTED);
        taskApproverRepository.save(taskApprover);
    }

    @Override
    @Transactional
    public CommentResponse addComment(UUID taskId, String commentText) {
        Comment comment = new Comment(UUID.randomUUID(), taskId, commentText, LocalDateTime.now());
        commentRepository.save(comment);
        return new CommentResponse(comment.getId(), comment.getTaskId(), comment.getText(), comment.getCreatedAt());
    }
}
