package com.backend.ecommerce.controller;

import com.backend.ecommerce.model.Product;
import com.backend.ecommerce.service.ProductService;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping(value = {"", "/"})
  public @NotNull Iterable<Product> getProducts() {
    return productService.getAllProducts();
  }

  @PostMapping
  public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    productService.save(product);
    String uri = ServletUriComponentsBuilder
        .fromCurrentServletMapping()
        .path("/products/{id}")
        .buildAndExpand(product.getId())
        .toString();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", uri);

    return new ResponseEntity<>(product, headers, HttpStatus.CREATED);
  }

}
