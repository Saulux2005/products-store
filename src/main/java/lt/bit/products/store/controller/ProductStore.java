package lt.bit.products.store.controller;

import java.time.LocalDate;
import java.util.List;
import lt.bit.products.store.model.Product;

public class ProductStore {

  private static final List<Product> PRODUCTS = List.of(
      new Product(1, "p1", "d1", LocalDate.now()),
      new Product(2, "p2", "d2", LocalDate.now().minusDays(2)),
      new Product(3, "p3", "d3", LocalDate.now().minusMonths(1).minusDays(1))
  );

  static List<Product> getProducts() {
    return PRODUCTS;
  }

}
