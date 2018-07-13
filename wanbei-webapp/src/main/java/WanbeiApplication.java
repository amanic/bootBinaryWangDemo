import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.amateur.wanbei.webapp","com.amateur.wanbei.service"})
public class WanbeiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WanbeiApplication.class, args);
	}
}
