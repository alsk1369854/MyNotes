package text;



import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
  public static void main(String[] args) throws MessagingException {
    Properties props = System.getProperties();
    System.out.println(props);
    props.put("mail.smtp.host", "smtp.nknu.edu.tw");
    props.put("mail.transport.protocol", "smtp");
    javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
    InternetAddress from = new InternetAddress("xxx@yyy.com");//不做身份驗證
    //InternetAddress to =  new InternetAddress("your mail address");//送信給自己
    InternetAddress to =  new InternetAddress("alsk1369854@gmail.com");//送信給自己
    Message msg = new MimeMessage(session);
    msg.setFrom(from);
    msg.setRecipient(Message.RecipientType.TO, to);
    msg.setSubject("高師大");
    msg.setText("我很好, hello");
    Transport.send(msg);
    System.out.println("mail sent");
  }
}
