package lt.bit.products.store.service;

import java.util.List;
import lt.bit.products.store.model.Product;
import lt.bit.products.store.model.ProductItems;
import lt.bit.products.store.model.ProductRequest;
import org.flywaydb.core.api.ErrorCode;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repository;
    private final ProductItemsRepository productItemsRepository;

    public ProductService(ProductRepository repository, ProductItemsRepository productItemsRepository) {
        this.repository = repository;
        this.productItemsRepository = productItemsRepository;
    }

    public List<Product> findProducts() {
        return repository.findAll();
    }

    public long countProducts() {
        return repository.count();
    }

    public Product findProduct(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteProduct(Integer id) {
//        repository.deleteStoreItems(id);
        productItemsRepository.deleteAllByProductId(id);
//        productItemsRepository.deleteById(id); veikia maniskis - virsuje destytojo
        repository.deleteById(id);
    }

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public ProductItems getProductItems(Integer productId) {
        return productItemsRepository.findById(productId).orElse(null);
    }

}
