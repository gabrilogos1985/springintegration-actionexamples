package paz.gps.integration.filter;

import paz.gps.integration.filter.messages.BankTransaction;

public class TransactionCancellationFilter {
	public TransactionCancellationFilter() {
		super();
	}

	public boolean accept(final BankTransaction cancellationRequest) {
		System.out.println("Filtrando: "
				+ cancellationRequest.getTransactionId());
		return false;
	}

	private String pattern = null;

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
