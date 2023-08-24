package in.stonecolddev.trayzn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestTrayznApplication {

	public static void main(String[] args) {
		SpringApplication.from(TrayznApplication::main).with(TestTrayznApplication.class).run(args);
	}

}