package mail;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import User.UserDao;
import User.UserVO;

public class MailMainEx01 {
	UserVO vo=new UserVO();
	UserDao dao=new UserDao();
//   public static void main(String[] args) {
//      naverMailSend();
//   }

   public static void naverMailSendpw(String pw,String em) {
	  
      String host ="smtp.naver.com";
      String user ="ehdrhr0304@naver.com";
      String password = "789456123zz"; 
      
      Properties props=new Properties();
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port",465);
      props.put("mail.smtp.auth","true");
      props.put("mail.smtp.ssl.enable","true");
      props.put("mail.smtp.ssl.trust",host);
      
      Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password);
            
         }
      });
      
      MimeMessage  message =new MimeMessage(session);
      try {
     
         message.setFrom(new InternetAddress(user));

         message.addRecipient(Message.RecipientType.TO, new InternetAddress(em));

         message.setSubject("비밀번호 안내 메일입니다.");
         
         message.setText("비밀번호는("+pw+")입니다.");
         

         Transport.send(message);
         
      } catch (AddressException e) {
         e.printStackTrace();
      } catch (MessagingException e) {
         e.printStackTrace();
      }
      
      
   }

   
   
   
   public static void naverMailSendid(String pw,String em) {
		  
	      String host ="smtp.naver.com";
	      String user ="ehdrhr0304@naver.com";
	      String password = "789456123zz"; 
	      
	      Properties props=new Properties();
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port",465);
	      props.put("mail.smtp.auth","true");
	      props.put("mail.smtp.ssl.enable","true");
	      props.put("mail.smtp.ssl.trust",host);
	      
	      Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(user, password);
	            
	         }
	      });
	      
	      MimeMessage  message =new MimeMessage(session);
	      try {
	     
	         message.setFrom(new InternetAddress(user));

	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(em));

	         message.setSubject("아이디 안내 메일입니다.");
	         
	         message.setText("아이디는("+pw+")입니다.");
	         

	         Transport.send(message);
	         
	      } catch (AddressException e) {
	         e.printStackTrace();
	      } catch (MessagingException e) {
	         e.printStackTrace();
	      }
	      
	      
	   }

}






