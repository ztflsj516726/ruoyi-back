package com.ruoyi.corework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * ClassName:NotificationService
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/7/1-16:00
 * @Version 1.0
 */
@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;


    @Async
    public void sendApproveMail(String toEmail, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("821477928@qq.com");  // 必须和配置一致
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
        System.out.println("邮件已异步发送, 当前线程: " + Thread.currentThread().getName());
    }
}
