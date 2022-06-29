package lt.bit.products.store.jobs;

import lt.bit.products.store.model.Product;
import lt.bit.products.store.service.ProductItemsRepository;
import lt.bit.products.store.service.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
public class Production {

    private final static Logger LOG = LoggerFactory.getLogger(Production.class);

    @Value("${jobs.production.number_of_new_products}")
    private int numberOfNewProducts;
    private final ProductRepository productRepository;
    private final ProductItemsRepository productItemsRepository;
    private String[] ipAddressNumbers;

    Production(ProductRepository productRepository,
               ProductItemsRepository productItemsRepository) {
        this.productRepository = productRepository;
        this.productItemsRepository = productItemsRepository;
        try {
            String ipAddress = InetAddress.getLocalHost().getHostAddress();
            ipAddressNumbers = ipAddress.split("\\.");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRateString = "10000")
    void addNewProducts() {
        LOG.info("Production job started");
        LOG.info("Adding products...");

        LOG.info("IP: " + Arrays.toString(ipAddressNumbers));

        for (int i = 0; i < numberOfNewProducts; i++) {
            Product generatedProduct = createProduct(i + 1);
            productRepository.save(generatedProduct);
            LOG.info("Generated quantity for i={} -> {}", i, generateQuantity(i));
            LOG.info("i=" + i + " -> " + generatedProduct + " - SAVED!");
        }
        LOG.info("Generated price: {}", generatePrice());
    }

    private Product createProduct(int i) {
        Product product = new Product();
        product.setName(generateName(i));
        product.setDescription(generateDescription());
        product.setCreated(LocalDate.now());
        return product;
    }

    private String generateName(int i) {
//        Mano varjantas
//            LocalDate date = LocalDate.now();
//            String text = date.format(DateTimeFormatter.ofPattern("E"));
//            String name = "";
//            name = "" + text + "-" + (i+1);
//            return name;

//            Desttytojo varjantas
//            String dayOfWeek = DateTimeFormatter.ofPattern("E" , Locale.ENGLISH).format(LocalDateTime.now());

//            Mano pataisytas destytojo varjantas
        String dayOfWeek = LocalDate.now().format(DateTimeFormatter.ofPattern("E"));

        return String.format("%s-%d", dayOfWeek, i);
    }

    private String generateDescription() {
//        Mano varjantas
//            return "Desc " + LocalDate.now();
//            Destytojo varjantas
        return String.format("Desc %s", LocalDate.now());
    }

    private BigDecimal generatePrice() {

        LocalDateTime now = LocalDateTime.now();
        // 1
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("m.s");
        return new BigDecimal(now.format(formatter));
//       2
//        return new BigDecimal(LocalDateTime.now().format(DateTimeFormatter.ofPattern("m.s")));

//        3
        //            LocalDateTime now = LocalDateTime.now();
//            int min = (now.getMinute());
//            int sec = (now.getSecond());
//        return new BigDecimal(String.format("%d.%d", min, sec));
    }

    private int generateQuantity(int index) {
        int ipNumberIndex = index >= ipAddressNumbers.length ? index - ipAddressNumbers.length : index;
        return Integer.parseInt(ipAddressNumbers[ipNumberIndex]);
    }

}

