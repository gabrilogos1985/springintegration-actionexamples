package paz.gps.integration.channel;

public class Email {

	private String recipient;
	private Integer idReservation;
	private String bookingInformation;

	public Integer idReservation() {
		return this.idReservation;
	}

	public String getRecipient() {
		return this.recipient;
	}

	public String getBookingInformation() {
		return this.bookingInformation;
	}

	@Override
	public String toString() {
		return "Email [idReservation()=" + idReservation()
				+ ", getRecipient()=" + getRecipient()
				+ ", getBookingInformation()=" + getBookingInformation() + "]";
	}

	public Integer getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(Integer idReservation) {
		this.idReservation = idReservation;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public void setBookingInformation(String bookingInformation) {
		this.bookingInformation = bookingInformation;
	}

}
