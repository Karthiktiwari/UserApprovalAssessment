
package com.example.user_approval.service.impl;

import com.example.user_approval.entity.Task;
import com.example.user_approval.entity.User;
import com.example.user_approval.service.EmailService;
import com.example.user_approval.util.EmailTemplateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final EmailTemplateUtils emailTemplateUtils;

    @Override
    @Async
    public void sendTaskAssignmentEmail(UUID recipientId, Task task) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            User recipient = emailTemplateUtils.getUserById(recipientId);
            String content = emailTemplateUtils.generateTaskAssignmentEmailContent(task);

            helper.setTo(recipient.getEmail());
            helper.setSubject("New Task Assignment");
            helper.setText(content, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email");
        }
    }
}