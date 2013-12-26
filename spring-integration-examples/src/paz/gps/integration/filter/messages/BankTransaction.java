package paz.gps.integration.filter.messages;

import paz.gps.integration.filter.messages.data.TransactionStatus;

public interface BankTransaction {

	Integer getTransactionId();

	TransactionStatus getStatus();

}
