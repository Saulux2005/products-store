package lt.bit.products.store.controller;

import java.util.List;
import lt.bit.products.store.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
class ProductController {

  @GetMapping
  List<Product> fetchProducts() {
    return ProductStore.getProducts();
  }
}
