package paz.gps.integration.channel;

import java.math.BigDecimal;

public class BookingPricesBusinessStub {

	private BigDecimal price;
	private String seatId;

	public BigDecimal getPrice(BookingRequestConfirmation booking) {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSeatId() {
		// TODO Auto-generated method stub
		return this.seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

}
