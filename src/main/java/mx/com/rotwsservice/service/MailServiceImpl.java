/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.service;

import java.io.StringWriter;
import java.util.Properties;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author mvillavicencio
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String[] to, String subject, String vmTemplate, VelocityContext context) throws Exception {
        System.out.println("Enviando mensaje...");
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(p);
        StringWriter writer = new StringWriter();
        System.out.println("Template :::::: " + vmTemplate);
        Template template = Velocity.getTemplate(vmTemplate);
        template.merge(context, writer);
//        template.merge(context, map);
//        String text = VelocityEngineUtils.mergeTemplateIntoString(
//                velocityEngine, "templates/templateExample.vm", "UTF-8", model);
//        System.out.println("Cadena :: "+text);
        mimeMessage.setContent(writer.toString(), "text/html");
//        SimpleMailMessage message = new SimpleMailMessage();
        //log.info("TO ::: {}", to);
        System.out.println("TO :::: " + to);
        helper.setTo(to);
        helper.setSubject(subject);
        //message.setText("Mensaje de prueba enviado");
//        message.setText("<h1 style='color: blue;'>Hola Mundo!!!</h1>");
        helper.setFrom("no-reply@roomiesoftheworld.com");
        mailSender.send(mimeMessage);
    }

    private class SuggestedPodcast {

        private String name;
        private String mail;
        private String SuggestedPodcast;
        private String message;

        public SuggestedPodcast() {
        }

        public SuggestedPodcast(String name, String mail, String SuggestedPodcast, String message) {
            this.name = name;
            this.mail = mail;
            this.SuggestedPodcast = SuggestedPodcast;
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getSuggestedPodcast() {
            return SuggestedPodcast;
        }

        public void setSuggestedPodcast(String SuggestedPodcast) {
            this.SuggestedPodcast = SuggestedPodcast;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
