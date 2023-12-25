package com.dtvn.foodorderbackend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "restaurant")
/*
  id: delivery id for restaurant
 */
public class Restaurant {
    @Id
    @Column(name = "id")
    long id;
    @Column(name = "name")
    String name;
    @Column(name = "description")
    String description;
    @Column(name = "address")
    String address;
    @Column(name = "rating")
    double rating;
    @Column(name = "uri")
    String uri;
    @Column(name = "image")
    String image;

//    String category; // json
}
