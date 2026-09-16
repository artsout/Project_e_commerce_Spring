package com.e_commerce.Project_E_Commerce_Spring.service;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationDto;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationMapper.NotificationMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationUpdateRequest;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Notification;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.NotificationType;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.Notification_Class;
import com.e_commerce.Project_E_Commerce_Spring.Repository.user_module.NotificationRepository;
import com.e_commerce.Project_E_Commerce_Spring.service.Exceptions.NotificationDeleteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private  final NotificationMapper notificationMapper;
    private final Follow_StoreService followStoreService;
    private final ClientService clientService;
    private  final StoreService storeService;
    private final ProductService productService;

    public void sendNotificationToFollowers(UUID storeId, NotificationDto Dto){
        if(storeId == null || Dto==null){
            throw  new IllegalArgumentException("Parameter cant be null");
        }
        Store store =storeService.findById(storeId);
        Product product = Dto.getProductId() !=null ? productService.findById(Dto.getProductId()) : null;


       followStoreService.getFollowsByStoreId(storeId).stream().forEach(
               follow_store ->{
                   Notification notification = new Notification();
                   notification.setNotificationName(Dto.getNotificationName());
                   notification.setNotificationDescription(Dto.getNotificationDescription());
                   notification.setNotificationAlreadyRead(false);
                   notification.setNotificationClass(Dto.getNotificationClass());
                   notification.setNotificationDate(Dto.getNotificationDate());
                   notification.setNotificationType(Dto.getType());
                   notification.setClient(follow_store.getClient());
                   notification.setStore(store);
                   notification.setProduct(product);
                   notificationRepository.save(notification);
               }
        );
    }
    public void updateNotification(Long notificationId, NotificationUpdateRequest notificationUpdateRequest){
        if(notificationId == null || notificationUpdateRequest ==null){
            throw  new IllegalArgumentException("Parameter cant be null");
        }
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(()-> new NoSuchElementException("No notifications found"));
        if (notificationUpdateRequest.getNotificationName() != null) {
            notification.setNotificationName(notificationUpdateRequest.getNotificationName());
        }
        if (notificationUpdateRequest.getNotificationDescription() != null) {
            notification.setNotificationDescription(notificationUpdateRequest.getNotificationDescription());
        }
        if (notificationUpdateRequest.getNotificationType() != null) {
            notification.setNotificationType(notificationUpdateRequest.getNotificationType());
        }
        if (notificationUpdateRequest.getNotificationAlreadyRead() != null) {
            notification.setNotificationAlreadyRead(notificationUpdateRequest.getNotificationAlreadyRead());
        }
        if (notificationUpdateRequest.getNotificationClass() != null) {
            notification.setNotificationClass(notificationUpdateRequest.getNotificationClass());
        }

        notificationRepository.save(notification);
    }

    public Notification deleteNotification(Long notificationId){
        if( notificationId ==null ){
            throw  new IllegalArgumentException("Parameter cant be null");
        }

        Notification notifications = notificationRepository.findById(notificationId).orElseThrow(() -> new NoSuchElementException("Notification do not exist"));
         if(!notifications.getNotificationAlreadyRead()){
             throw  new NotificationDeleteException("You cant Delete a notification that you dint read");
         }
        return  notifications;
    }

    public List<Notification> getNotificationsCustomized(UUID clientId, NotificationType notificationType , Boolean notificationAlreadyRead , Notification_Class notificationClass){
        if(clientId == null ){
            throw  new IllegalArgumentException("Parameter cant be null");
        }
        List<Notification> notifications =  notificationRepository.findAllNotificationsByClientAndType(clientId, notificationType,notificationAlreadyRead,notificationClass);
        if(notifications.isEmpty()){
            throw new NoSuchElementException("Notifications not found");
        }
        return notifications;
    }

    public   List<Notification> findByClientId(UUID clientId){
        if(clientId == null ){
            throw  new IllegalArgumentException("Parameter cant be null");
        }
        List<Notification> notification = notificationRepository.findByClientId(clientId);
        if(notification.isEmpty()){
            throw  new NoSuchElementException("No notifications found");

        }
        return notification;
    }

    public  List<Notification> findByNotificationDateBetween(LocalDateTime inicio ,LocalDateTime fim ){
        if(inicio == null || fim ==null ){
            throw  new IllegalArgumentException("Parameter cant be null");
        }
        if(inicio.isAfter(fim) || fim.isBefore(inicio)){
            throw  new IllegalArgumentException("Parameter are impossible");
        }
        List<Notification> result = notificationRepository.findByNotificationDateBetween(inicio, fim);
        if (result.isEmpty()){
            throw  new NoSuchElementException("No notifications found");

        }

        return result;
    }


    public List<Notification> findByNotificationNameContaining(String name){
        if(name == null ){
            throw  new IllegalArgumentException("Parameter cant be null");
        }
       List<Notification>notifications = notificationRepository.findByNotificationNameContainingIgnoreCase(name);
        if (notifications.isEmpty()){
            throw  new NoSuchElementException("No notifications found");

        }

        return notifications;
    }

    public List<Notification> findByStoreId(UUID storeId) {
        if(storeId == null ){
            throw  new IllegalArgumentException("Parameter cant be null");
        }



        List<Notification> notifications= notificationRepository.findByStoreId(storeId);
        if (notifications.isEmpty()){
            throw  new NoSuchElementException("No notifications found");

        }


        return  notifications;
    }
}
