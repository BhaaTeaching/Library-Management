package application;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("unit-test")
@SpringBootConfiguration
@SpringBootTest
public abstract class ApplicationTestBase {
}
