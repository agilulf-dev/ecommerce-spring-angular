package com.adolfs.ecommerce.controller;

import com.adolfs.ecommerce.model.Product;
import com.adolfs.ecommerce.service.ProductService;
import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
