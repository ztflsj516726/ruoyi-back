package com.ruoyi.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ClassName:EmailUtil
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author ztf
 * @Create 2025/7/1-14:20
 * @Version 1.0
 */

@Component
public class EmailUtil {

    // 静态变量，供静态方法调用
    private static JavaMailSender staticMailSender;

    // Spring 启动时自动注入，并赋值给静态变量
    @Autowired
    public void setJavaMailSender(JavaMailSender mailSender) {
        EmailUtil.staticMailSender = mailSender;
    }

    /**
     * 静态邮件发送方法
     * @param toEmail 收件人邮箱
     * @param subject 邮件标题
     * @param text    邮件内容
     */
    public static void sendApproveMail(String toEmail, String subject, String text) {
        if (staticMailSender == null) {
            throw new IllegalStateException("JavaMailSender未初始化，无法发送邮件");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("821477928@qq.com");  // 确保和配置文件username一致
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(text);

        staticMailSender.send(message);
    }
}