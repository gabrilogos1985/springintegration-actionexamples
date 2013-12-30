package paz.gps.integration.channel;

public class BookingRequestConfirmation {

	private Integer idReservation;
	private String recipient;

	public BookingRequestConfirmation(Integer expectedReservation,
			String expectedRecipient) {
		this.idReservation=expectedReservation;
		this.recipient=expectedRecipient;
	}

	public Integer getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(Integer idReservation) {
		this.idReservation = idReservation;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	@Override
	public String toString() {
		return "BookingRequestConfirmation [idReservation=" + idReservation
				+ ", recipient=" + recipient + "]";
	}

}
