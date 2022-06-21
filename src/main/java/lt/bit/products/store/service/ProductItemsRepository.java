package lt.bit.products.store.service;

import lt.bit.products.store.model.ProductItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemsRepository extends JpaRepository<ProductItems, Integer> {
    void deleteAllByProductId(Integer productId);
}
