package lt.bit.products.store.controller;

import java.util.List;
import lt.bit.products.store.model.Product;
import lt.bit.products.store.model.ProductItems;
import lt.bit.products.store.model.ProductRequest;
import lt.bit.products.store.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ProductController.ROOT_MAPPING)
class ProductController {

    public static final String ROOT_MAPPING = "products";
    public static final String ID_MAPPING = "/{id}";
    private ProductService service;

    ProductController(ProductService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Product createProduct(@RequestBody ProductRequest productRequest) {
        return service.saveProduct(Product.from(productRequest));
    }

    @PutMapping("/{id}")
    ResponseEntity<Product> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable Integer id) {
        Product product = service.findProduct(id);
        if (product == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.saveProduct(Product.from(productRequest, id)));
    }

    @GetMapping
    List<Product> fetchProducts() {
        return service.findProducts();
    }


    @GetMapping(ID_MAPPING)
    ResponseEntity<Product> fetchProduct(@PathVariable Integer id) {
        Product product = service.findProduct(id);
        if (product == null) {
           return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping(ID_MAPPING + "/items")
    ResponseEntity<ProductItems> fetchProductItems(@PathVariable("id") Integer productId) {
        ProductItems items = service.getProductItems(productId);
        return items == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(items);
    }

    @DeleteMapping(ID_MAPPING)
    ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer productId) {
        Product product = service.findProduct(productId);
        if (product == null) {
            return  ResponseEntity.notFound().build();
        }
        service.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }



}
