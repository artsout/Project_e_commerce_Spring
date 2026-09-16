package com.e_commerce.Project_E_Commerce_Spring.Model.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "follow_store",indexes = {
        @Index(name = "idx_follow_store_date",columnList = "follow_store_date"),
        @Index(name = "idx_follow_store_id_client",columnList = "fk_follow_store_id_client"),
        @Index(name = "idx_followed_store_id_store",columnList = "fk_followed_store_id_store"),
})
@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
