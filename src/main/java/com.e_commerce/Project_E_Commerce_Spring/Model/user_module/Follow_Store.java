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
    @JoinColumn(name = "fk_follow_store_id_client")
    private Client id_client;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_follow_store_id_store")
    private Store id_store;


    public Follow_Store(Long id, LocalDateTime followStoreDate, Client id_client, Store id_store) {
        this.id = id;
        this.followStoreDate = followStoreDate;
        this.id_client = id_client;
        this.id_store = id_store;
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

    public Client getId_client() {
        return id_client;
    }

    public void setId_client(Client id_client) {
        this.id_client = id_client;
    }

    public Store getId_store() {
        return id_store;
    }

    public void setId_store(Store id_store) {
        this.id_store = id_store;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Follow_Store that = (Follow_Store) o;
        return id == that.id && Objects.equals(followStoreDate, that.followStoreDate) && Objects.equals(id_client, that.id_client) && Objects.equals(id_store, that.id_store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, followStoreDate, id_client, id_store);
    }

    @Override
    public String toString() {
        return "Follow_Store{" +
                "id=" + id +
                ", followStoreDate=" + followStoreDate +
                ", id_client=" + id_client +
                ", id_store=" + id_store +
                '}';
    }
}
