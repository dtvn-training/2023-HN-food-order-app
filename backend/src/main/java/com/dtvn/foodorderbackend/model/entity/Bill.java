package com.dtvn.foodorderbackend.model.entity;


import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "bill")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bill extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToOne(targetEntity = ItemOrder.class)
    @JoinColumn(name = "order_id", updatable = false, insertable = false)
    ItemOrder order;

    @Column(name = "order_id")
    Long orderId;

    @Column(name = "price")
    int price;
    @Column(name = "discount")
    int discount;
    @Column(name = "ship_fee")
    int shipFee;

    @Column(name = "final_cost")
    int finalCost;

}
