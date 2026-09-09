package com.e_commerce.Project_E_Commerce_Spring.Model.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "follow_store",indexes = {
        @Index(name = "idx_follow_store_date",columnList = "follow_store_date"),
        @Index(name = "idx_follow_store_id_client",columnList = "fk_follow_store_id_client"),
        @Index(name = "idx_followed_store_id_store",columnList = "fk_followed_store_id_store"),
})
public class Follow_Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime followStoreDate;


    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;


    public Follow_Store(Long id, LocalDateTime followStoreDate, Client client, Store store) {
        this.id = id;
        this.followStoreDate = followStoreDate;
        this.client = client;
        this.store = store;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFollowStoreDate() {
        return followStoreDate;
    }

    public void setFollowStoreDate(LocalDateTime followStoreDate) {
        this.followStoreDate = followStoreDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Follow_Store that = (Follow_Store) o;
        return id == that.id && Objects.equals(followStoreDate, that.followStoreDate) && Objects.equals(client, that.client) && Objects.equals(store, that.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, followStoreDate, client, store);
    }

    @Override
    public String toString() {
        return "Follow_Store{" +
                "id=" + id +
                ", followStoreDate=" + followStoreDate +
                ", id_client=" + client +
                ", id_store=" + store +
                '}';
    }
}
