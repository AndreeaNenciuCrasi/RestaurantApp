package com.codecool.restaurant.ShoppingCart;

import com.codecool.restaurant.User.UserApp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "USER_ORDER")
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private Double totalPrice;

    private Timestamp date;

    @ManyToOne
    @JoinColumn(name="userApp_id")
    private UserApp userApp;

    public UserOrder() {
    }

    public UserOrder(String status, Double totalPrice) {
        this.status = status;
        this.totalPrice = totalPrice;
        this.date = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
