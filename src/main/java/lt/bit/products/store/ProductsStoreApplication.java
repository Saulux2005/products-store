package lt.bit.products.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProductsStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsStoreApplication.class, args);
	}

}
