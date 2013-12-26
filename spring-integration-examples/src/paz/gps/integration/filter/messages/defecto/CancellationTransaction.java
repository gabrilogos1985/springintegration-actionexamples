package paz.gps.integration.filter.messages.defecto;

import paz.gps.integration.filter.messages.BankTransaction;
import paz.gps.integration.filter.messages.data.TransactionStatus;

public class CancellationTransaction implements BankTransaction {

	private TransactionStatus status;
	private Integer transactionId;

	@Override
	public Integer getTransactionId() {
		// TODO Auto-generated method stub
		return this.transactionId;
	}

	@Override
	public TransactionStatus getStatus() {
		return this.status;
	}

	public void setStatus(final TransactionStatus statusToSet) {
		this.status = statusToSet;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

}
