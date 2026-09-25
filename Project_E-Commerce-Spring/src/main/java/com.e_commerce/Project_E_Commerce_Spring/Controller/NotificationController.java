package com.e_commerce.Project_E_Commerce_Spring.Controller;


import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationDto;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationMapper.NotificationMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationUpdateRequest;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationUserDtoResponse;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.StoreDto;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Notification;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.NotificationType;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.Notification_Class;
import com.e_commerce.Project_E_Commerce_Spring.service.NotificationService;
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

//Vai mandar qualquer alteraçao de produtos no banco e criaçao desses produtos

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    private final NotificationMapper notificationMapper;


    @PostMapping("/send")
    @PreAuthorize("hasAnyAuthority('SCOPE_OWNER', 'SCOPE_STORE_ADMIN')")
    public ResponseEntity<Void> createNotification(
            @AuthenticationPrincipal Jwt jwt,
            @Valid  @RequestBody NotificationDto notificationDto,
            @RequestParam(required = false) Long productId
    ){
      UUID storeId = UUID.fromString(jwt.getSubject());
      notificationService.sendNotificationToFollowers(storeId,notificationDto,productId);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/delete/{notificationId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long notificationId){

        notificationService.deleteNotification(notificationId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping("/delete/{notificationId}/me")
    @PreAuthorize("hasAuthority('SCOPE_OWNER')")
    public ResponseEntity<Void> deleteNotification(@AuthenticationPrincipal Jwt jwt , @PathVariable Long notificationId){

        UUID storeId = UUID.fromString(jwt.getSubject());
         notificationService.deleteNotification(storeId,notificationId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{notificationId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<NotificationDto> updateNotification(@PathVariable Long notificationId, @RequestBody NotificationUpdateRequest notificationUpdateRequest){
       notificationService.updateNotification(notificationId,notificationUpdateRequest);

        Notification notification = notificationService.findById(notificationId);
        NotificationDto notificationDto = notificationMapper.toDto(notification);
       return  ResponseEntity.ok(notificationDto);
    }


    @GetMapping("/client/{clientId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<NotificationUserDtoResponse>> findByClientId(@PathVariable UUID clientId){
        List<Notification> notifications =  notificationService.findByClientId(clientId);
        List<NotificationUserDtoResponse> result = notifications.stream()
                .map(notificationMapper::toUserDto)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/store/{storeId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<NotificationUserDtoResponse>> getAllNotificationsOfStore(@PathVariable UUID storeId){
        List<Notification> notifications =  notificationService.findByStoreId(storeId);
        List<NotificationUserDtoResponse> result = notifications.stream()
                .map(notificationMapper::toUserDto)
                .toList();

        return ResponseEntity.ok(result);
    }
    @GetMapping("/client-notifications/{clientId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<NotificationDto>> getAllNotificationsOfClient(@PathVariable UUID clientId,
                                                                            @RequestParam(required = false ) NotificationType notificationType, @RequestParam(required = false, defaultValue = "false") Boolean notificationAlreadyRead,@RequestParam(required = false) Notification_Class notificationClass){
        List<Notification> notifications = notificationService.getNotificationsCustomized(clientId, notificationType, notificationAlreadyRead , notificationClass);

        List<NotificationDto> result = notifications.stream()
                .map(notificationMapper::toDto)
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/client-notifications/me")
    @PreAuthorize("hasAnyAuthority('SCOPE_USER' , 'SCOPE_CLIENT_ADMIN')")
    public ResponseEntity<List<NotificationDto>> getAllNotificationsOfClient(@AuthenticationPrincipal Jwt jwt,
                                                                             @RequestParam(required = false ) NotificationType notificationType, @RequestParam(required = false, defaultValue = "false") Boolean notificationAlreadyRead,@RequestParam(required = false) Notification_Class notificationClass){

        UUID clientId = UUID.fromString(jwt.getSubject());
        List<Notification> notifications = notificationService.getNotificationsCustomized(clientId, notificationType, notificationAlreadyRead , notificationClass);

        List<NotificationDto> result = notifications.stream()
                .map(notificationMapper::toDto)
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/date")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<NotificationUserDtoResponse>> getNotificationByDate(@RequestParam LocalDateTime start ,@RequestParam LocalDateTime end){
       List<Notification> notifications = notificationService.findByNotificationDateBetween(start,end);
       List<NotificationUserDtoResponse> result = notifications
               .stream()
               .map(notificationMapper::toUserDto)
               .collect(Collectors.toList());

       return ResponseEntity.ok(result);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<NotificationUserDtoResponse>> getNotificationByName(@PathVariable String name){
        List<Notification> notifications = notificationService.findByNotificationNameContaining(name);
        List<NotificationUserDtoResponse> result = notifications
                .stream()
                .map(notificationMapper::toUserDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


}
