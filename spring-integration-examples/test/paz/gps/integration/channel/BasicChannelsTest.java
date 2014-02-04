package paz.gps.integration.channel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import paz.gps.integration.channel.service.EmailConfirmationService;
import paz.gps.integration.channel.service.MailNotification;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appContext-basicChannels-test.xml")
public class BasicChannelsTest {


	@Autowired
	@Qualifier("bookingRequestChannel")
	 MessageChannel bookingRequestChannel;
	
	@Autowired
	BookingPricesBusinessStub pricesBusiness;
	
	@Autowired
	EmailConfirmationService mailNotificationService;
	@Test
	public void passingMessageToServiceAndNotification() {
		Integer expectedReservation = 1820;
		String expectedRecipient = "gg@matrix.com";
		String seatNumber = "12";
		Long priceFlying = 221L;
		pricesBusiness.setPrice(BigDecimal.valueOf(priceFlying.longValue()));
		pricesBusiness.setSeatId(seatNumber);
		Message<?> message = MessageBuilder.withPayload(
				new BookingRequestConfirmation(expectedReservation,
						expectedRecipient)).build();
		bookingRequestChannel.send(message, 1000);

		MailNotification notification = this.mailNotificationService
				.getNotification();
		assertTrue(notification.isSent());
		Email confirmation = notification.getConfirmation();
		assertEquals(expectedReservation, confirmation.idReservation());
		assertEquals(expectedRecipient, confirmation.getRecipient());
		assertTrue(confirmation.getBookingInformation().contains(
				priceFlying.toString()));
		assertTrue(confirmation.getBookingInformation().contains(
				seatNumber.toString()));
	}
}
