package paz.gps.integration.channel.service;

import paz.gps.integration.channel.Email;

public class MailNotification {

	private Email email;
	private boolean sent;

	public boolean isSent() {
		return this.sent;
	}

	public Email getConfirmation() {
		return this.email;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

}
