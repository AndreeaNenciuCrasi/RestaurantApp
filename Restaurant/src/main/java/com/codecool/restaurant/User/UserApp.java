package com.codecool.restaurant.User;


import com.codecool.restaurant.order.UserOrder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "USER")
public class UserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(unique=true)
    @NotBlank(message = "UserName is mandatory")
    private String userName;

    @Column(unique = true)
    @NotBlank(message = "E-mail is mandatory" )
    private String emailAddress;
    private String deliveryAddress;
    private String phoneNumber;
    private String password;

    // roles of the user (ADMIN, USER,..)
//    @Enumerated(value = EnumType.STRING)
//    private List<String> roles = new ArrayList<>();
    private String userRole= "ROLE_USER";

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<ShoppingCart> shoppingCartList = new ArrayList<>();

    @OneToMany(mappedBy = "userApp", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserOrder> userOrders = new ArrayList<>();

    public UserApp() {
    }

    public UserApp(String firstName, String lastName, String userName, String emailAddress, String deliveryAddress, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.deliveryAddress = deliveryAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
//        this.roles = Arrays.asList("ROLE_USER");
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    //    public List<String> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<String> roles) {
//        this.roles = roles;
//    }

//    public TypeUserRole getUserRole() {
//        return userRole;
//    }
//
//    public void setUserRole(TypeUserRole userRole) {
//        this.userRole = userRole;
//    }

//    public List<ShoppingCart> getShoppingCart() {
//        return shoppingCartList;
//    }
//
//    public void setShoppingCart(ShoppingCart shoppingCart) {
//        this.shoppingCartList = shoppingCartList;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", userOrders=" + userOrders +
                '}';
    }
}
