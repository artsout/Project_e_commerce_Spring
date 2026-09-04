package com.e_commerce.Project_E_Commerce_Spring.Model.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product_Rating;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "client",indexes = {
        @Index(name = "idx_clent_creation_date",columnList = "client_creation_date"),
        @Index(name = "idx_client_address",columnList = "client_address")


})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(min = 5, max = 20)
    @Column(nullable = false)
    private String clientName;


    @NotBlank
    @Email(message = "Please,insert a valid email")
    @Column(nullable = false,unique = true)
    private String emailClient;


    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime clientCreationDate;

    @NotBlank
    @Size(min = 8,max = 15,message = "Please,insert a valid password")
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Pattern(regexp ="\\d{13}")
    @Column(updatable = false,unique = true,length = 13)
    private String number;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer clientPedidoCount;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer clientNotificacaoCount;


    @Embedded
    private Address clientAddress;

    @OneToMany(mappedBy = "id_client",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Notification> notifications = new HashSet<>();

    @OneToMany(mappedBy = "id_product",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Notification> productNotifications =new HashSet<>();

    @OneToMany(mappedBy = "id_store",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Notification> storeNotification=new HashSet<>();


    @OneToMany(mappedBy = "id_client",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Product_Rating> clientProductsRating =new HashSet<>();

    @OneToMany(mappedBy = "id_client")
    private Set<Follow_Store> clientFollow=new HashSet<>();

    public Client(UUID id, String clientName, String emailClient, LocalDateTime clientCreationDate, String password, String number, Integer clientPedidoCount, Integer clientNotificacaoCount, Address clientAddress, Set<Notification> notifications, Set<Notification> productNotifications, Set<Notification> storeNotification, Set<Product_Rating> clientProductsRating, Set<Follow_Store> clientFollow) {
        this.id = id;
        this.clientName = clientName;
        this.emailClient = emailClient;
        this.clientCreationDate = clientCreationDate;
        this.password = password;
        this.number = number;
        this.clientPedidoCount = clientPedidoCount;
        this.clientNotificacaoCount = clientNotificacaoCount;
        this.clientAddress = clientAddress;
        this.notifications = notifications;
        this.productNotifications = productNotifications;
        this.storeNotification = storeNotification;
        this.clientProductsRating = clientProductsRating;
        this.clientFollow = clientFollow;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public LocalDateTime getClientCreationDate() {
        return clientCreationDate;
    }

    public void setClientCreationDate(LocalDateTime clientCreationDate) {
        this.clientCreationDate = clientCreationDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getClientPedidoCount() {
        return clientPedidoCount;
    }

    public void setClientPedidoCount(Integer clientPedidoCount) {
        this.clientPedidoCount = clientPedidoCount;
    }

    public Integer getClientNotificacaoCount() {
        return clientNotificacaoCount;
    }

    public void setClientNotificacaoCount(Integer clientNotificacaoCount) {
        this.clientNotificacaoCount = clientNotificacaoCount;
    }

    public Address getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(Address clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Set<Notification> getProductNotifications() {
        return productNotifications;
    }

    public void setProductNotifications(Set<Notification> productNotifications) {
        this.productNotifications = productNotifications;
    }

    public Set<Notification> getStoreNotification() {
        return storeNotification;
    }

    public void setStoreNotification(Set<Notification> storeNotification) {
        this.storeNotification = storeNotification;
    }

    public Set<Product_Rating> getClientProductsRating() {
        return clientProductsRating;
    }

    public void setClientProductsRating(Set<Product_Rating> clientProductsRating) {
        this.clientProductsRating = clientProductsRating;
    }

    public Set<Follow_Store> getClientFollow() {
        return clientFollow;
    }

    public void setClientFollow(Set<Follow_Store> clientFollow) {
        this.clientFollow = clientFollow;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(clientName, client.clientName) && Objects.equals(emailClient, client.emailClient) && Objects.equals(clientCreationDate, client.clientCreationDate) && Objects.equals(password, client.password) && Objects.equals(number, client.number) && Objects.equals(clientPedidoCount, client.clientPedidoCount) && Objects.equals(clientNotificacaoCount, client.clientNotificacaoCount) && Objects.equals(clientAddress, client.clientAddress) && Objects.equals(notifications, client.notifications) && Objects.equals(productNotifications, client.productNotifications) && Objects.equals(storeNotification, client.storeNotification) && Objects.equals(clientProductsRating, client.clientProductsRating) && Objects.equals(clientFollow, client.clientFollow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientName, emailClient, clientCreationDate, password, number, clientPedidoCount, clientNotificacaoCount, clientAddress, notifications, productNotifications, storeNotification, clientProductsRating, clientFollow);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", emailClient='" + emailClient + '\'' +
                ", clientCreationDate=" + clientCreationDate +
                ", password='" + password + '\'' +
                ", number='" + number + '\'' +
                ", clientPedidoCount=" + clientPedidoCount +
                ", clientNotificacaoCount=" + clientNotificacaoCount +
                ", clientAddress=" + clientAddress +
                ", notifications=" + notifications +
                ", productNotifications=" + productNotifications +
                ", storeNotification=" + storeNotification +
                ", clientProductsRating=" + clientProductsRating +
                ", clientFollow=" + clientFollow +
                '}';
    }

    public  void addNotification(Notification notification){
        notifications.add(notification);
    }
    public  void removeNotification(Notification notification){
        notifications.remove(notification);
    }
    public  void addProductNotification(Notification notification){
        productNotifications.add(notification);
    }
    public  void removeProductNotification(Notification notification){
        productNotifications.remove(notification);
    }
    public  void addStoreNotification(Notification notification){
        storeNotification.add(notification);
    }
    public  void removeStoreNotification(Notification notification){
        storeNotification.remove(notification);
    }
    public  void addClientProductsRating(Product_Rating product_rating){
        clientProductsRating.add(product_rating);
    }
    public  void removeClientProductsRating(Product_Rating product_rating){clientProductsRating.remove(product_rating);}
    public void addClientFollow(Follow_Store follow_store){clientFollow.add(follow_store);}
    public  void removeClientFollow(Follow_Store follow_store){clientFollow.remove(follow_store);}
}
