package br.com.diegojlima.moneyprinterconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;

@SpringBootApplication
public class MoneyprinterConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyprinterConsumerApplication.class, args);
	}

}

@EnableBinding(Sink.class)
@MessageEndpoint
class Catcher {

	@StreamListener(Sink.INPUT)
	void logMessage(Money money) {
		System.out.println(money);
	}

}

@Data
@AllArgsConstructor
class Money {
	private final String id, type, currency, value, quality;
}
