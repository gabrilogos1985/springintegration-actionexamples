package paz.gps.integration.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import paz.gps.integration.filter.messages.BankTransaction;
import paz.gps.integration.filter.messages.data.TransactionStatus;
import paz.gps.integration.filter.messages.defecto.BankRejectedTransaction;
import paz.gps.integration.filter.messages.defecto.CancellationTransaction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:appContext-MessagefilterUno-test.xml")
public class FilterMessagesCancellationTest {
	@Autowired
	@Qualifier("input")
	MessageChannel inputChannel;
	@Autowired
	@Qualifier("rejected")
	PollableChannel rejected;

	@Test
	public void getRejectedMessage() {

		BankTransaction transaction = retrieveRejectedMessage();
		assertEquals(BankRejectedTransaction.class, transaction.getClass());
		Integer expectedTransactionId = null;
		assertEquals(expectedTransactionId, transaction.getTransactionId());
		assertEquals(TransactionStatus.REJECTED, transaction.getStatus());
	}

	private BankTransaction retrieveRejectedMessage() {
		CancellationTransaction cancelTransaction = new CancellationTransaction();
		cancelTransaction.setTransactionId(1021);
		inputChannel
				.send(MessageBuilder.withPayload(cancelTransaction).build());
		return (BankTransaction) rejected.receive().getPayload();
	}

}
