package com.e_commerce.Project_E_Commerce_Spring.Repository.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Order_Item;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order_ItemRepository extends JpaRepository<Order_Item,Long> {

    List<Order_Item> findByOrderId(Long orderId);

    List<Order_Item> findByProductId(Long productId);

    @Query("SELECT oi FROM Order_Item oi JOIN FETCH oi.product WHERE oi.order.id = :orderId")
    List<Order_Item> findByOrderIdWithProduct(Long orderId);
}
