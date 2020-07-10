package br.com.diegojlima.moneyprinterproducer;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class MoneyprinterProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyprinterProducerApplication.class, args);
	}

}

@EnableBinding(Source.class)
@EnableScheduling
@AllArgsConstructor
class Spammer {
	private final Source source;
	private final SubscriberGenerator generator;

	@Scheduled(fixedRate = 1000)
	private void spam() {
		Money money = generator.printMoney();
		System.out.println(money);
		source.output().send(MessageBuilder.withPayload(money).build());
	}
}

@Component
class SubscriberGenerator {
	private final String[] type = "Coin, Note".split(", ");
	private final String[] currency = "CHF, EUR, USD, JPY, GBP".split(", ");
	private final String[] value = "1, 2, 5, 10, 20, 50, 100, 200, 500, 1000".split(", ");
	private final String[] quality = "poor, fair, good, premium, flawless, perfect".split(", ");
	private final Random rnd = new Random();
	private int i = 0, j = 0, k=0, l=0;

	Money printMoney() {
		i = rnd.nextInt(2);
		j = rnd.nextInt(5);
		k = rnd.nextInt(10);
		l = rnd.nextInt(6);

		return new Money(UUID.randomUUID().toString(), type[i], currency[j], value[k], quality[l]);
	}
}

@Data
@AllArgsConstructor
class Money {
	private final String id, type, currency, value, quality;
}
