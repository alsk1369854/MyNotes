package mime;


import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class MailReaderMime {
  Session session ;
 
  public MailReaderMime(){
    Properties props = System.getProperties();
    props.setProperty("mail.pop3.disabletop", "true");
    session = Session.getDefaultInstance(props);
  }
 
 
  public Message[] read(String host, String user, String pw){
    Message[] messages = null;
    try {
      Store store = session.getStore("pop3");
      store.connect(host, user, pw);
      Folder inbox = store.getFolder("INBOX");
      inbox.open(Folder.READ_ONLY);
      messages = inbox.getMessages( );
    } catch (NoSuchProviderException e) {
      e.printStackTrace();
     
    } catch (MessagingException e) {
      e.printStackTrace();
     
    }
    return messages;
  }
}
 
