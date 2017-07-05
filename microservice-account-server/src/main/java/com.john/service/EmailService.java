package com.john.service;

import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * Created by cjl20 on 6/4/2017.
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public void sendCodeTemplateMail(String name, String emailAddress, String code) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(emailFrom);
        helper.setTo(emailAddress);
        helper.setSubject("主题：验证码");
        Map<String, Object> model = new HashedMap();
        model.put("name", name);
        model.put("code", code);
        String text = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "register.vm", "UTF-8", model);
        helper.setText(text, true);
        mailSender.send(mimeMessage);
    }

}
