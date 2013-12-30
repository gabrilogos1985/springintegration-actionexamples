package paz.gps.integration.channel;

public class Seat {

	private String seatId;

	public String getSeatId() {
		return seatId;
	}

	public Seat(final String seatIdValue) {
		this.seatId = seatIdValue;
	}

	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + "]";
	}

}
