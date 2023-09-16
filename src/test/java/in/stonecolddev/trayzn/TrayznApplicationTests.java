package in.stonecolddev.trayzn;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("it-test")
@Tag("it-test")
@SpringBootTest
class TrayznApplicationTests {

	@Test
	void contextLoads() {
	}

}