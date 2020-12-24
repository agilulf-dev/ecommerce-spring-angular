package com.backend.ecommerce.service.internal;

import com.backend.ecommerce.exception.ResourceNotFoundException;
import com.backend.ecommerce.model.Product;
import com.backend.ecommerce.repository.ProductRepository;
import com.backend.ecommerce.service.ProductService;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public @NotNull Iterable<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Product getProduct(@Min(value = 1L, message = "Invalid product ID.") long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
  }

  @Override
  public Product save(Product product) {
    return productRepository.save(product);
  }
}
