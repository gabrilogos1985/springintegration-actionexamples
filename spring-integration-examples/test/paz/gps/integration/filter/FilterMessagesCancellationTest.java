package paz.gps.integration.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.annotation.Payload;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import paz.gps.integration.filter.messages.BankTransaction;
import paz.gps.integration.filter.messages.data.TransactionStatus;
import paz.gps.integration.filter.messages.defecto.CancellationTransaction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:appContext-MessagefilterUno-test.xml")
public class FilterMessagesCancellationTest {
	public class OutputMessage implements Runnable {

		private int counter;
		private Object payload;

		public OutputMessage(Object payload, int counterP) {
			this.payload = payload;
			this.counter = counterP;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()
					+ " -> recibiendo: " + payload + " - " + +counter++);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public class InputMessage implements Runnable {

		private int counter;

		public InputMessage(int counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			CancellationTransaction cancelTransaction = new CancellationTransaction();
			cancelTransaction.setTransactionId(1);
			inputChannel.send(MessageBuilder.withPayload(cancelTransaction)
					.build());
			System.out.println(Thread.currentThread().getName()
					+ " -> Sending: " + counter);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Autowired
	@Qualifier("input")
	MessageChannel inputChannel;
	@Autowired
	@Qualifier("rejected")
	PollableChannel rejected;
	@Autowired
	@Qualifier("confirmed")
	PollableChannel confirmedChannel;

	@Test
	public void getRejectedOddIdMessage() {
		Integer expectedTransactionId = 1021;
		CancellationTransaction cancelTransaction = new CancellationTransaction();
		cancelTransaction.setTransactionId(expectedTransactionId);
		inputChannel
				.send(MessageBuilder.withPayload(cancelTransaction).build());

		BankTransaction rejectedTransaction = retrieveRejectedMessage(0);
		BankTransaction confirmedTransaction = retrieveConfirmedMessage(100);

		assertEquals(CancellationTransaction.class,
				rejectedTransaction.getClass());
		assertEquals(expectedTransactionId,
				rejectedTransaction.getTransactionId());
		assertEquals(null, rejectedTransaction.getStatus());
		assertNull(confirmedTransaction);
	}

	@Test
	public void getConfirmedEvenIdMessage() {
		Integer expectedTransactionId = 1022;
		CancellationTransaction cancelTransaction = new CancellationTransaction();
		cancelTransaction.setTransactionId(expectedTransactionId);
		inputChannel
				.send(MessageBuilder.withPayload(cancelTransaction).build());

		BankTransaction rejectedTransaction = retrieveRejectedMessage(100);
		BankTransaction confirmedTransaction = retrieveConfirmedMessage(0);

		assertEquals(CancellationTransaction.class,
				confirmedTransaction.getClass());
		assertEquals(expectedTransactionId,
				confirmedTransaction.getTransactionId());
		assertEquals(TransactionStatus.CONFIRMED,
				confirmedTransaction.getStatus());
		assertNull(rejectedTransaction);
	}

	@Test
	public void testFillRejectedChannelQueue() throws InterruptedException {
		int maxMessages = 100000;
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
		for (int counter = 0; counter < maxMessages; counter++) {
			Runnable command = new InputMessage(counter);
			newFixedThreadPool.execute(command);
		}
		newFixedThreadPool.awaitTermination(2, TimeUnit.MINUTES);
		//newFixedThreadPool.shutdown();
		Message<?> receive = null;
		int counter = 0;
		//newFixedThreadPool = Executors.newFixedThreadPool(10);
		while ((receive = this.rejected.receive(1000)) != null) {
			Runnable command = new OutputMessage(receive.getPayload(),
					counter++);
			newFixedThreadPool.execute(command);
		}
		newFixedThreadPool.awaitTermination(1, TimeUnit.MINUTES);
		newFixedThreadPool.shutdown();
	}

	private BankTransaction retrieveConfirmedMessage(long milis) {
		Message<?> receive = confirmedChannel.receive(milis);
		BankTransaction confirmPayload = receive == null ? null
				: (BankTransaction) receive.getPayload();
		return confirmPayload;
	}

	private BankTransaction retrieveRejectedMessage(long milis) {
		Message<?> receive = rejected.receive(milis);
		BankTransaction confirmedPayload = receive == null ? null
				: (BankTransaction) receive.getPayload();
		return confirmedPayload;
	}

}
