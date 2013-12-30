package paz.gps.integration.channel.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import paz.gps.integration.channel.BookingPricesBusinessStub;
import paz.gps.integration.channel.ChargedBooking;
import paz.gps.integration.channel.Seat;
import paz.gps.integration.channel.SeatConfirmation;

public class SeatAvailabilityService {

	@Autowired
	private BookingPricesBusinessStub pricesBusiness;
	public SeatConfirmation confirmSeat(ChargedBooking chargedBooking) {
		Seat seat = chargedBooking.getPrice()
				.compareTo(BigDecimal.valueOf(100)) > 0 ? new Seat(pricesBusiness.getSeatId()) : null;
		return new SeatConfirmation(chargedBooking, seat);
	}
}
