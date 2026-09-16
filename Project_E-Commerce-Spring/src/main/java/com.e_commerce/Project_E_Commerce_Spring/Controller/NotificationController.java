package com.e_commerce.Project_E_Commerce_Spring.Controller;


import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationDto;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationMapper.NotificationMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationUpdateRequest;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Notification;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.NotificationType;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.Notification_Class;
import com.e_commerce.Project_E_Commerce_Spring.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//Vai mandar qualquer alteraçao de produtos no banco e criaçao desses produtos

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    private final NotificationMapper notificationMapper;


    @PostMapping("{storeId}/send")
    public ResponseEntity<Void> createNotification(
            @PathVariable UUID storeId ,
            @Valid  @RequestBody NotificationDto notificationDto
    ){
                                                        //So seguidores recebem notification
      notificationService.sendNotificationToFollowers(storeId,notificationDto);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/delete/{notificationId}")
    public ResponseEntity<NotificationDto> deleteNotification(@PathVariable Long notificationId ){
                                            //so vai dar para deletar se ela foi lida
        Notification notification =  notificationService.deleteNotification(notificationId);
        NotificationDto result = notificationMapper.toDto(notification);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{notificationId}")
    public ResponseEntity<Void> updateNotification(@PathVariable Long notificationId, @RequestBody NotificationUpdateRequest notificationUpdateRequest){
       notificationService.updateNotification(notificationId,notificationUpdateRequest);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


    @GetMapping("/{clientId}")
    public ResponseEntity<List<NotificationDto>> findByClientId(@PathVariable UUID clientId){
        List<Notification> notifications =  notificationService.findByClientId(clientId);
        List<NotificationDto> result = notifications.stream()
                .map(notificationMapper::toDto)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{storeId}/store")
    public ResponseEntity<List<NotificationDto>> getAllNotificationsOfStore(@PathVariable UUID storeId){
        List<Notification> notifications =  notificationService.findByStoreId(storeId);
        List<NotificationDto> result = notifications.stream()
                .map(notificationMapper::toDto)
                .toList();

        return ResponseEntity.ok(result);
    }
    @GetMapping("/{clientId}/client")
    public ResponseEntity<List<NotificationDto>> getAllNotificationsOfClient(@PathVariable UUID clientId,
                                                                            @RequestParam(required = false ) NotificationType notificationType, @RequestParam(required = false, defaultValue = "false") Boolean notificationAlreadyRead,@RequestParam(required = false) Notification_Class notificationClass){
        List<Notification> notifications = notificationService.getNotificationsCustomized(clientId, notificationType, notificationAlreadyRead , notificationClass);

        List<NotificationDto> result = notifications.stream()
                .map(notificationMapper::toDto)
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/date")
    public ResponseEntity<List<NotificationDto>> getNotificationByDate(LocalDateTime start , LocalDateTime end){
       List<Notification> notifications = notificationService.findByNotificationDateBetween(start,end);
       List<NotificationDto> result = notifications
               .stream()
               .map(notificationMapper::toDto)
               .collect(Collectors.toList());

       return ResponseEntity.ok(result);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<NotificationDto>> getNotificationByName(@PathVariable String name){
        List<Notification> notifications = notificationService.findByNotificationNameContaining(name);
        List<NotificationDto> result = notifications
                .stream()
                .map(notificationMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


}
