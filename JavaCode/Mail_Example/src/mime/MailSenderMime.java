package mime;



import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSenderMime {
  public static void main(String[] args)
      throws MessagingException {
    java.util.Properties props = System.getProperties();
    System.out.println(props);
    props.put("mail.smtp.host", "smtp.nknu.edu.tw");
    props.put("mail.transport.protocol", "smtp");
    javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
    InternetAddress from = new InternetAddress("xxx@yyy.com");//不做身份驗證
    InternetAddress to =  new InternetAddress("alsk1369854@gmail.com");//送信給自己
    Message msg = new MimeMessage(session);
    msg.setFrom(from);
    msg.setRecipient(Message.RecipientType.TO, to);
    msg.setSubject("高師大軟體系");//set Subject 
    //msg.setText("A test mail.");//Set Contents
       
    //======== multipart: adding text ==============
    MimeBodyPart messageBodyPart = new MimeBodyPart();
    Multipart multipart = new MimeMultipart();
    MimeBodyPart text = new MimeBodyPart();
    String textBody = "您好! 我們是高師大軟體系的師生，跟您問候!";
    text.setContent(textBody,"text/plain; charset=UTF-8");
    multipart.addBodyPart(text);
  
    //======== multipart: adding first attached file ==============
    messageBodyPart = new MimeBodyPart();
    String path = "D:/";
    String filename = "F16.png";
    DataSource source = new FileDataSource(path + filename);
    messageBodyPart.setDataHandler(new DataHandler(source));
    messageBodyPart.setFileName(filename);
    multipart.addBodyPart(messageBodyPart);
    
   //======== multipart: adding second attached file ==============
    MimeBodyPart messageBodyPart2;
    messageBodyPart2 = new MimeBodyPart();
    path = "D:/";
    filename = "attach.txt";
    source = new FileDataSource(path + filename);
    messageBodyPart2.setDataHandler(new DataHandler(source));
    messageBodyPart2.setFileName(filename);
    multipart.addBodyPart(messageBodyPart2);
   
    //set up message
    msg.setContent(multipart);
    Transport.send(msg);
    
    System.out.println("mail sent");  
  }
}
