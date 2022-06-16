package lt.bit.products.store.controller;

import lt.bit.products.store.model.Product;
import lt.bit.products.store.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProductController.ROOT_MAPPING)
class ProductController {

    public static final String ROOT_MAPPING = "products";
    private ProductService service;

    ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    List<Product> fetchProducts() {
        return service.findProducts();
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> fetchProduct(@PathVariable Integer id) {
        Product product = service.findProduct(id);
        if (product == null) {
           return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer productId) {
        Product product = service.findProduct(productId);
        if (product == null) {
            return  ResponseEntity.notFound().build();
        }
        service.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
