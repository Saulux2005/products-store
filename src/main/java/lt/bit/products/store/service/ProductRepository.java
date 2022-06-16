package lt.bit.products.store.service;

import lt.bit.products.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface ProductRepository extends JpaRepository<Product, Integer> {

      @Query(value = "DELETE FROM store_items WHERE product_id = ?1", nativeQuery = true)
//    @Query(value = "delete from ProductItem p where p.id = ?1")
    void deleteStoreItems(Integer productId);

}
