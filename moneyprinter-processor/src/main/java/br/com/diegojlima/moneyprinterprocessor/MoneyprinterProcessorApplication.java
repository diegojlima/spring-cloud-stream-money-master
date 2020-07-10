package br.com.diegojlima.moneyprinterprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.messaging.handler.annotation.SendTo;

@SpringBootApplication
public class MoneyprinterProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyprinterProcessorApplication.class, args);
	}

}

@EnableBinding(Processor.class)
@MessageEndpoint
class Transformer {

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	Money transform(Money money) {

		//Quality Assurance Processor. This polishs all coins and notes to perfect quality.
		Money newSub = new Money(money.getId(),
				money.getType(),
				money.getCurrency(),
				money.getValue(),
				"perfect");

		System.out.println(newSub);

		return newSub;
	}
}

@Data
@AllArgsConstructor
class Money {
	private final String id, type, currency, value, quality;
}