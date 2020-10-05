package com.codecool.restaurant.ShoppingCart;

import com.codecool.restaurant.User.UserApp;

import javax.persistence.*;


@Entity
@Table(name = "SHOPPING_CART")
public class ShoppingCart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userApp_id")
    private UserApp userApp;

//    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<MealsToCart> mealsToCarts = new ArrayList<>();


    public ShoppingCart() {
    }


    public ShoppingCart(UserApp userApp) {
        this.userApp = userApp;
    }

    public Long getId() {
        return id;
    }

    public UserApp getUserApp() {
        return userApp;
    }

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
    }


    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", user=" + userApp +
                '}';
    }

}
