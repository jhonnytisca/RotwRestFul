/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.service;

import org.apache.velocity.VelocityContext;

/**
 *
 * @author mvillavicencio
 */
public interface MailService {
    public void sendEmail(String []to, String subject, String template, VelocityContext context) throws Exception;
}
