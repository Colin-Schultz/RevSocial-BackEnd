package com.network.email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ResetEmailConfig {
	// Replace sender@example.com with your "From" address.
    // This address must be verified.
    static final String FROM = "sender@example.com";
    // Change to email sender name
    static final String FROMNAME = "Sender name";
	
    static String TO;
    static String BODY;
    
    // Replace smtp_username with your Amazon SES SMTP user name.
    static final String SMTP_USERNAME = "smtp_username";
    
    // Replace smtp_password with your Amazon SES SMTP password.
    static final String SMTP_PASSWORD = "smtp_password";
    
    // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
    // See https://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
    // for more information.
    static final String HOST = "email-smtp.us-west-2.amazonaws.com";
    
    // The port you will connect to on the Amazon SES SMTP endpoint.
    static final int PORT = 587;
    
    static final String SUBJECT = "Email Subject";
    
    public static void setToEmail(String toEmail) {
    	TO = toEmail;
    }
    
    public static String generatePassword() {
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	 
	    String generatedString = new Random().ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    	    
	    BODY = String.join(System.getProperty("line.separator"),
	    	"<h3>Subject Title:</h3>",
	        "<p>Your password has been reset to: ", 
	        generatedString
	    );
	 
	    return generatedString;
	}
        
    public static void sendEmail() throws UnsupportedEncodingException, MessagingException {
        // Create a Properties object to contain connection configuration information.
    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.port", PORT); 
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.auth", "true");

        // Create a Session object to represent a mail session with the specified properties. 
    	Session session = Session.getDefaultInstance(props);

        // Create a message with the specified information. 
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM,FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY,"text/html");
        
        // Create a transport.
        Transport transport = session.getTransport();
        
        // Send the message.
        try
        {
            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        	
            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
        finally
        {
            // Close and terminate the connection.
            transport.close();
        }
    }
}
