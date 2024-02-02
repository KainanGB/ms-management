package ms.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class MsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsManagementApplication.class, args);
	}

}
