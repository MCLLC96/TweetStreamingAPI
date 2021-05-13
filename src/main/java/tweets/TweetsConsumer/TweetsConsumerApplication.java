package tweets.TweetsConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tweets.TweetsConsumer.service.TweetService;

@SpringBootApplication
public class TweetsConsumerApplication implements CommandLineRunner {
	
	@Autowired
	private TweetService tweetService;

	public static void main(String[] args) {
		SpringApplication.run(TweetsConsumerApplication.class, args);
	}

	@Bean
	public CommandLineRunner streamingAPI() {

		return args -> {

			tweetService.streamingAPI();
		};
	}

	@Override
	public void run(String... args) throws Exception {
	};
}
