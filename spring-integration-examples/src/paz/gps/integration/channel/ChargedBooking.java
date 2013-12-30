package paz.gps.integration.channel;

import java.math.BigDecimal;

public class ChargedBooking {
	private BookingRequestConfirmation booking;
	private BigDecimal price;


	public ChargedBooking(final BookingRequestConfirmation bookingValue) {
		this.booking = bookingValue;
	}

	public void setBookingPricesBusiness(BookingPricesBusinessStub bookingPricesBusiness) {
		price = bookingPricesBusiness.getPrice(this.booking);
	}

	public BookingRequestConfirmation getBooking() {
		return booking;
	}

	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "ChargedBooking [booking=" + booking + ", price=" + price + "]";
	}
	
	
}
