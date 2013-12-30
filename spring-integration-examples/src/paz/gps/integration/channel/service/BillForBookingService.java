package paz.gps.integration.channel.service;

import paz.gps.integration.channel.BookingPricesBusinessStub;
import paz.gps.integration.channel.BookingRequestConfirmation;
import paz.gps.integration.channel.ChargedBooking;

public class BillForBookingService {
	private BookingPricesBusinessStub bookingPricesBusiness;
	 public ChargedBooking pay(BookingRequestConfirmation booking) {
	        ChargedBooking chargedBooking = new ChargedBooking(booking);
	        chargedBooking.setBookingPricesBusiness(bookingPricesBusiness);
			return chargedBooking;
	    }
	public void setBookingPricesBusiness(BookingPricesBusinessStub bookingPricesBusiness) {
		this.bookingPricesBusiness = bookingPricesBusiness;
	}
}
