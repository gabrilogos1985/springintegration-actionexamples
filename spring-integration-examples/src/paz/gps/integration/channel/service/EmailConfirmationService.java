package paz.gps.integration.channel.service;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;

import paz.gps.integration.channel.BookingRequestConfirmation;
import paz.gps.integration.channel.ChargedBooking;
import paz.gps.integration.channel.Email;
import paz.gps.integration.channel.Seat;
import paz.gps.integration.channel.SeatConfirmation;

public class EmailConfirmationService {

	private MailNotification mailNotification;

	public void handleMessage(Message<?> message) throws MessagingException {
		SeatConfirmation payload = (SeatConfirmation) message.getPayload();
		System.out.println(payload);
		this.mailNotification = new MailNotification();
		Email email = new Email();
		ChargedBooking chargedBooking = payload.getChargedBooking();
		BookingRequestConfirmation booking = chargedBooking.getBooking();
		email.setIdReservation(booking.getIdReservation());
		email.setRecipient(booking.getRecipient());
		Seat seat = payload.getSeat();
		String bookingInformation = "Asiento: "
				+ (seat == null ? " No avaliable" : seat.getSeatId()) + " \n "
				+ chargedBooking.getPrice();
		email.setBookingInformation(bookingInformation);
		this.mailNotification.setEmail(email);
		this.mailNotification.setSent(seat != null);
	}

	public void sendEmail(Email email) {

	}

	public MailNotification getNotification() {
		return this.mailNotification;
	}

}
