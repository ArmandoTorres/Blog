package com.blog.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/** * @author Armando */
public class EmailSender {
    
    private static final String user    = "ProgrammerWebBlog@gmail.com";
    private static final String pass    = "BlogWebProgrammer";
    private static final String host    = "smtp.gmail.com";
    private static Properties properties; 
    private static Session emailSession;
    private static MimeMessage message;

    //Paso1 establecer propiedades de envio propiedades
    private static void setEmailProperties(){
        properties = new Properties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port","587");
        properties.setProperty("mail.smtp.user",user);
        properties.setProperty("mail.smtp.auth", "true");
        emailSession = Session.getDefaultInstance(properties);
        message = new MimeMessage(emailSession);
    }
    
    public static void sendEmail(String to, String subject, String msj) {
        if (properties == null) {
            setEmailProperties();
        }
        
        try {
            //Cuenta de correo de la cual saldra el mail.
            message.setFrom(new InternetAddress(user));
            //Cuenta de correo a la cual sera enviado el mail.
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //Asunto del correo.
            message.setSubject(subject);
            //Mensaje del correo
            message.setText(msj);
            
            //Objeto que se encarga del envio del mensaje
            Transport t = emailSession.getTransport("smtp");
            //Conectando con la cuenta del correo    
            t.connect(user, pass);
            //Enviando el mensaje a los destinatarios.
            t.sendMessage(message, message.getAllRecipients());
            //Cerrando la coneccion    
            t.close();
            
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public static void sendEmail(String[] to, String subject, String msj) {
        
        if (properties == null) {
            setEmailProperties();
        }
        
        try {
            InternetAddress[] address = new InternetAddress[to.length];
        
            for (int i = 0; i < to.length; i++){
                address[i] = new InternetAddress(to[i]);
            }
            
            //Cuenta de correo de la cual saldra el mail.
            message.setFrom(new InternetAddress(user));
            //Cuenta de correo a la cual sera enviado el mail.
            message.setRecipients(Message.RecipientType.TO, address);
            //Asunto del correo.
            message.setSubject(subject);
            //Mensaje del correo
            message.setText(msj);
            
            //Objeto que se encarga del envio del mensaje
            Transport t = emailSession.getTransport("smtp");
            //Conectando con la cuenta del correo    
            t.connect(user, pass);
            //Enviando el mensaje a los destinatarios.
            t.sendMessage(message, message.getAllRecipients());
            //Cerrando la coneccion    
            t.close();
            
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean validateEmail(String mail){
        try {
            InternetAddress email = new InternetAddress(mail);
            email.validate();
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }
}