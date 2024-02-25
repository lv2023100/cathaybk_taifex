package tw.com.cathaybk.digital.taifex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CathaybkTaifexApplication {

	public static void main(String[] args) {
		SpringApplication.run(CathaybkTaifexApplication.class, args);
	}
}
