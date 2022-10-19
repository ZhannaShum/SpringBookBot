package shumi.SpringBookBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import shumi.SpringBookBot.service.ConsoleBot;

@SpringBootApplication
public class SpringBookBotApplication {

	public static void main(String[] args) {
		//ConsoleBot.onUpdateReceived();

		SpringApplication.run(SpringBookBotApplication.class, args);
	}

}
