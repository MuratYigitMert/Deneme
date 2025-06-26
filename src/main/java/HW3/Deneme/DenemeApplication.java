package HW3.Deneme;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableEncryptableProperties
@SpringBootApplication
public class DenemeApplication {

	public static void main(String[] args) {

		SpringApplication.run(DenemeApplication.class, args);
	}

}
