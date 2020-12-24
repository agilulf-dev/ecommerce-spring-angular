package com.backend.ecommerce.repository;

import com.backend.ecommerce.model.OrderProduct;
import com.backend.ecommerce.model.OrderProductPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {

}
