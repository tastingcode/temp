package tobyspring.hellospring.payment;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.*;

class PaymentServiceTest {

	@Test
	void prepare() throws IOException {
		testAmount(valueOf(500), valueOf(5_000));
		testAmount(valueOf(1_000), valueOf(10_000));

		/* 원화환산금액의 유효시간 계산 */
//		assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
	}

	private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
		PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));

		Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

		assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
		assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
	}

}