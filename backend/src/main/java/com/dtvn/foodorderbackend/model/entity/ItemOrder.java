package com.dtvn.foodorderbackend.model.entity;

import com.dtvn.foodorderbackend.model.dto.response.ItemOrderDisplayResponse;
import com.dtvn.foodorderbackend.ulti.TimeUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "item_order")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemOrder extends BaseEntity implements RecordEntity {
    //same to cart id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;


    @JoinColumn(name = "dish_id", updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    Dish dish;

    @Column(name = "dish_id")
    Long dishId;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "approved")
    boolean approved;

    @Column(name = "deleted")
    boolean deleted;

    /**
     * Initial, approved set to false, when admin order this `ItemOrder` approved set to true
     */
    @Override
    public void enActive() {
        approved = true;
    }

    /**
     * Not use this func
     */

    @Override
    public void deActive() {
        approved = false;
    }

    /**
     * After ordered by admin, or deleted by admin, the delete field set to true;
     */
    @Override
    public void delete() {
        deleted = true;
    }

    public ItemOrderDisplayResponse toDisplayResponse() {
        return ItemOrderDisplayResponse.builder()
                .id(id)
                .createdTime(TimeUtil.formatToString(createdTime.toLocalDateTime(), "dd/MM/yyyy hh:mm"))
                .dishName(dish.name)
                .restaurantName(dish.getCategory().getRestaurant().name)
                .restaurantId(dish.getCategory().getRestaurant().deliveryId)
                .restaurantAddress(dish.getCategory().getRestaurant().address)
                .unitPrice(dish.price)
                .userFullName(createdBy.getFullName())
                .quantity(quantity)
                .build();
    }
}
