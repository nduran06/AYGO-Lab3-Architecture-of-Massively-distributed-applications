package eci.aygo.eciuber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class EciUberApplication {

	public static void main(String[] args) {
		SpringApplication.run(EciUberApplication.class, args);
	}

}
