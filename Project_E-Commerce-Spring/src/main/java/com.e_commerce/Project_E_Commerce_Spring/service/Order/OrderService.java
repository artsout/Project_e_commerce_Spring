package com.e_commerce.Project_E_Commerce_Spring.service.Order;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Order.OrderRequestDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Order.OrderUpdateDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Order_Item.Order_ItemDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Order_Item.Order_ItemMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Payment.Mapper.PaymentMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Payment.PaymentRequestDto;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.EnumPayment.PaymentLevel;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Order_Item;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Payment;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Order;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Order_Enum.Order_Status;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.aux_Order_Current_Position.CurrentPosition;
import com.e_commerce.Project_E_Commerce_Spring.Repository.user_module.OrderRepository;
import com.e_commerce.Project_E_Commerce_Spring.service.ClientService;
import com.e_commerce.Project_E_Commerce_Spring.service.Exceptions.ResourceNotFoundException;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private  final OrderRepository orderRepository;
    private  final ClientService clientService;
    private final Order_ItemMapper orderItemMapper;
    private final PaymentMapper paymentMapper;

    public void  order(UUID clientId, OrderRequestDto orderRequestDto){

        Client client = clientService.findById(clientId);
        Set<Order_ItemDto> ordersItemsDto = orderRequestDto.getOrderItemOrders();

        Set<Order_Item> orderItems =ordersItemsDto.stream()
                .map(orderItemMapper::toEntity)
                .collect(Collectors.toSet());

        Order order = new Order();

        order.setClient(client);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderCurrentPosition(orderRequestDto.getOrderCurrentPosition());

        orderItems.forEach(item -> item.setOrder(order));
        order.setOrderItemOrders(orderItems);

        Set<PaymentRequestDto> paymentRequestDto = orderRequestDto.getPayments();

        Set<Payment> payments =paymentRequestDto.stream()
                .map(paymentMapper::toEntity)
                .map(payment -> initializeOrderPayment(order,payment))
                .collect(Collectors.toSet());


        order.setPayments(payments);

        order.setOrderArrivalDate(orderRequestDto.getOrderArrivalDate());//metodo de calcular chegada
        order.setOrderExchangePeriod(calculateExchangePeriod(order));//calculo do tempo de troca


        client.setClientPedidoCount(client.getClientPedidoCount()+1);
        orderRepository.save(order);
    }

    //user
    public void cancelarOrder(Long orderId){
        if (orderId==null){
            throw  new IllegalArgumentException("Parameter cant be null");
        }
        Order order = findById(orderId);
        Order_Status statusAtual = order.getOrderStatus();
        if (statusAtual == Order_Status.SENT || statusAtual == Order_Status.DELIVERED || statusAtual== Order_Status.DELIVERED) {
            throw new IllegalStateException("The order cna be canceled ,cause is already going to your home");
        }
        order.setOrderStatus(Order_Status.CANCELED);

        Set<Payment> payments =order.getPayments();

       // refunded(payments); //metodo para devolver o dinheiro e settar o payment level para refunded
    }

    //admin
    public void cancelarOrderAdmin(Long orderId){
        if (orderId==null){
            throw  new IllegalArgumentException("Parameter cant be null");
        }
        Order order = findById(orderId);
        Order_Status statusAtual = order.getOrderStatus();

        order.setOrderStatus(Order_Status.CANCELED);

        Set<Payment> payments =order.getPayments();

        // refunded(payments); //metodo para devolver o dinheiro e settar o payment level para refunded
    }


    //Admin
    public void deleteOrder(Long orderId){
        if (orderId==null){
            throw  new IllegalArgumentException("Parameter cant be null");
        }
        Order order = findById(orderId);

        orderRepository.delete(order);
    }

    //Admin
    public Order update(Long orderId, OrderUpdateDto orderUpdateDto){
        if (orderId==null || orderUpdateDto ==null){
            throw  new IllegalArgumentException("Parameter cant be null");
        }
        Order order = findById(orderId);
        if(orderUpdateDto.getOrderExchangePeriod() != null){
            order.setOrderExchangePeriod(orderUpdateDto.getOrderExchangePeriod());
        }
        if(orderUpdateDto.getOrderArrivalDate() != null){
            order.setOrderArrivalDate(orderUpdateDto.getOrderArrivalDate());
        }
        if(orderUpdateDto.getOrderCurrentPosition() != null){
            order.setOrderCurrentPosition(orderUpdateDto.getOrderCurrentPosition());
        }

        orderRepository.save(order);
        return order;
    }

    public Order findById(Long orderId){
        if (orderId==null){
            throw new IllegalArgumentException("Parameter cant be null");
        }
       return orderRepository.findById(orderId).orElseThrow(()-> new NoSuchElementException("Cannot found that order"));
    }

    public List<Order> findByOrderStatus(Order_Status order_status){
        if (order_status==null){
            throw new IllegalArgumentException("Parameter cant be null");
        }
        List<Order> orders =  orderRepository.findByOrderStatus(order_status);
        if (orders.isEmpty()){
            throw  new ResourceNotFoundException("No orders found");
        }
        return  orders;
    }

    public List<Order> findByClientId(UUID clientId){
        if (clientId==null){
            throw new IllegalArgumentException("Parameter cant be null");
        }

        List<Order> orders =  orderRepository.findByClientId(clientId);
        if (orders.isEmpty()){
            throw  new ResourceNotFoundException("No orders found");
        }
        return  orders;
    }
    public List<Order> findByOrderDateBetween(LocalDateTime min, LocalDateTime max){
        if (min==null || max ==null){
            throw new IllegalArgumentException("Parameter cant be null");
        }
        if (min.isAfter(max)){
            throw new IllegalArgumentException("Parameter dates are invalid");
        }
        List<Order> orders =  orderRepository.findByOrderDateBetween(min,max);
        if (orders.isEmpty()){
            throw  new ResourceNotFoundException("No orders found");
        }
        return  orders;
    }
    public List<Order> findByOrderCurrentPosition(CurrentPosition currentPosition){
        if (currentPosition==null){
            throw new IllegalArgumentException("Parameter cant be null");
        }
        List<Order> orders =  orderRepository.findByOrderCurrentPosition(currentPosition);
        if (orders.isEmpty()){
            throw  new ResourceNotFoundException("No orders found");
        }
        return  orders;
    }
    public List<Order> findByOrderArrivalDate(LocalDateTime arrivalDate){
        if (arrivalDate==null){
            throw new IllegalArgumentException("Parameter cant be null");
        }
        List<Order> orders =  orderRepository.findByOrderArrivalDate(arrivalDate);
        if (orders.isEmpty()){
            throw  new ResourceNotFoundException("No orders found");
        }
        return  orders;
    }
    public Order findByOrderItem(Order_Item orderItem){
        if (orderItem==null){
            throw new IllegalArgumentException("Parameter cant be null");
        }
        return orderRepository.findByOrderItem(orderItem);
    }

    public void processOrderStatus(Order order, Payment payment) {
        if (payment == null || payment.getPaymentLevel() == null) {
            throw new IllegalArgumentException("Parameter cat be null");
        }
        switch (payment.getPaymentLevel()) {
            case APPROVED -> order.setOrderStatus(Order_Status.PAYMENT_CONFIRMED);

            case REJECTED -> order.setOrderStatus(Order_Status.CANCELED);

            case PENDING, PROCESSING -> order.setOrderStatus(Order_Status.WAITING_PAYMENT);


            default -> throw new IllegalStateException("PAymentLevel is not mapped in orderStatus " + payment.getPaymentLevel());
        }
    }

   public Payment  initializeOrderPayment(Order order, Payment payment){

       if (payment == null || payment.getPaymentType() == null) {
           throw new IllegalArgumentException("Parameter cant be null");
       }

       payment.setOrder(order);

       switch (payment.getPaymentType()) {
           case BOLETO, CASH -> {
               payment.setPaymentLevel(PaymentLevel.PENDING);
               order.setOrderStatus(Order_Status.WAITING_PAYMENT);
               return payment;
           }

           case PIX, CREDIT_CARD, DEBIT_CARD -> {
               // Tentam a aprovaçao da api externa
               payment.setPaymentLevel(PaymentLevel.PROCESSING);
               order.setOrderStatus(Order_Status.WAITING_PAYMENT);

               // Aqui e integraçao com uma api de pagamento:
               //q ira retornar algo e mudar o payment
               //ai chamamos o metodo para alterar os sattus
               processOrderStatus(order, payment);
               return payment;
           }

       }
       return null;
   }

   public LocalDateTime calculateExchangePeriod(Order order){
        return  order.getOrderArrivalDate().plusMonths(1);
   }
}
