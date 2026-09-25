package com.e_commerce.Project_E_Commerce_Spring.Controller;


import com.e_commerce.Project_E_Commerce_Spring.Dto.Order.Mapper.OrderMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Order.OrderRequestDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Order.OrderUpdateDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Order.Response.OrderResponseDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Payment.PaymentRequestDto;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Order_Item;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Order;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Order_Enum.Order_Status;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.aux_Order_Current_Position.CurrentPosition;
import com.e_commerce.Project_E_Commerce_Spring.service.Order.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private  final OrderMapper orderMapper;

    @PostMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('SCOPE_USER' , 'SCOPE_CLIENT_ADMIN')")
    public ResponseEntity<Void> order(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody OrderRequestDto orderRequestDto){
        UUID clientId = UUID.fromString(jwt.getSubject());
        orderService.order(clientId,orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/cancel/{orderId}")
    @PreAuthorize("isAuthenticated()")
    public  ResponseEntity<Void> cancelOrder(@PathVariable Long orderId){
        orderService.cancelarOrder(orderId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/cancel/admin/{orderId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public  ResponseEntity<Void> cancelOrderAdmin(@PathVariable Long orderId){
        orderService.cancelarOrderAdmin(orderId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{orderId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public  ResponseEntity<Void> deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{orderId}")
    @PreAuthorize("isAuthenticated()")
    private ResponseEntity<Void> updateOrder(@PathVariable Long orderId, @RequestBody OrderUpdateDto orderUpdateDto){
        orderService.update(orderId,orderUpdateDto);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/client/{clientId}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_CLIENT_ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> findByClientId(@PathVariable UUID clientId) {
        List<Order> orders = orderService.findByClientId(clientId);

        List<OrderResponseDto> response = orders.stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/status/{status}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> findByOrderStatus(@PathVariable Order_Status status) {
        List<Order> orders = orderService.findByOrderStatus(status);
        List<OrderResponseDto> response = orders.stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/date-between")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> findByOrderDateBetween(
        @RequestParam LocalDateTime min,
        @RequestParam  LocalDateTime max) {
        List<Order> orders = orderService.findByOrderDateBetween(min, max);
        List<OrderResponseDto> response = orders.stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/position/{currentPosition}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> findByOrderCurrentPosition(@PathVariable CurrentPosition currentPosition) {
         List<Order> orders = orderService.findByOrderCurrentPosition(currentPosition);
        List<OrderResponseDto> response = orders.stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/arrival-date")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> findByOrderArrivalDate(
        @RequestParam LocalDateTime arrivalDate) {
        List<Order> orders = orderService.findByOrderArrivalDate(arrivalDate);
        List<OrderResponseDto> response = orders.stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


    @PostMapping("/item")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<OrderResponseDto> findByOrderItem(@RequestBody Order_Item orderItem) {
        Order order = orderService.findByOrderItem(orderItem);
        OrderResponseDto reponse = orderMapper.toResponseDto(order);
        return ResponseEntity.ok(reponse);
    }
}
