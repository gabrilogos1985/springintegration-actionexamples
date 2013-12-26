package paz.gps.integration.filter;

import paz.gps.integration.filter.messages.BankTransaction;
import paz.gps.integration.filter.messages.data.TransactionStatus;
import paz.gps.integration.filter.messages.defecto.CancellationTransaction;

public class TransactionCancellationService {

	public BankTransaction cancel(final BankTransaction cancellationRequest) {
		((CancellationTransaction) cancellationRequest)
				.setStatus(TransactionStatus.CONFIRMED);
		System.out.println("Cancelacion confirmada para: "
				+ cancellationRequest.getTransactionId());
		return cancellationRequest;
	}
}
