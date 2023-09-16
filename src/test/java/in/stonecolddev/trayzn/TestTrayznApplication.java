package in.stonecolddev.trayzn;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("it-test")
@Tag("it-test")
@TestConfiguration(proxyBeanMethods = false)
public class TestTrayznApplication {

	public static void main(String[] args) {
		SpringApplication.from(TrayznApplication::main).with(TestTrayznApplication.class).run(args);
	}

}