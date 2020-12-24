package com.adolfs.ecommerce.repository;

import com.adolfs.ecommerce.model.OrderProduct;
import com.adolfs.ecommerce.model.OrderProductPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {

}
