package com.e_commerce.Project_E_Commerce_Spring.Repository.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Order;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Order_Enum.Order_Status;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.aux_Order_Current_Position.CurrentPosition;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByOrderStatus(Order_Status order_status);

    List<Order> findByClientId(UUID userId);

    List<Order> findByOrderDate(LocalDateTime orderDate);

    List<Order> findByOrderCurrentPosition(CurrentPosition currentPosition);


}
