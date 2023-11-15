import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class WhatsappSender {
  // Find your Account Sid and Token at twilio.com/console
  public static final String ACCOUNT_SID = "ACeb7951dff0bf559ec68ee18d4dcb4de7";
  public static final String AUTH_TOKEN = "16b7e5cee0e5eb2a8a365a9d3fe6799a ";

  public static void main(String[] args) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    
    String messageString = SeatavailabilityOnFridays.trainSeatAvailabilityCheck();
    
    Message message = Message.creator(
      new com.twilio.type.PhoneNumber("whatsapp:+918847748744"),
      new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
      messageString )
      .create();

    System.out.println(message.getSid());
  }
}