package gestion.scolaire.service;

import jakarta.mail.MessagingException; // Ou javax.mail.MessagingException selon votre version Spring Boot
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Value;

@Service
public class EmailService {
    
    @Autowired 
    private JavaMailSender javaMailSender;
 
     @Value("unigest.univ@gmail.com") 
    private String sender;

    // Nouvelle méthode pour envoyer du HTML
    public void envoyerEmailHtml(String to, String sujet, String htmlContent) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom(sender);
            helper.setTo(to);
            helper.setSubject(sujet);
            helper.setText(htmlContent, true); // Le 'true' active le mode HTML

            javaMailSender.send(message);
        } catch (MessagingException e) {
            // Loggez l'erreur pour ne pas bloquer votre application
            System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
    }
}