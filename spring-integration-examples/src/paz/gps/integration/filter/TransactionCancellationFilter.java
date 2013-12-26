package paz.gps.integration.filter;

import paz.gps.integration.filter.messages.BankTransaction;

public class TransactionCancellationFilter {
	private String pattern = null;

	public TransactionCancellationFilter() {
		super();
	}

	public boolean accept(final BankTransaction cancellationRequest) {
		Integer transactionId = cancellationRequest.getTransactionId();
		System.out.println("Filtrando: " + transactionId);
		return transactionId % 2 == 0;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
