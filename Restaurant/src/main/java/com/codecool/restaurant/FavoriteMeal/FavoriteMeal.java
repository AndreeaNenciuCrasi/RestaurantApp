package com.codecool.restaurant.FavoriteMeal;

import com.codecool.restaurant.User.UserApp;

import javax.persistence.*;

@Entity
@Table(name="FAVORITE_MEAL")
public class FavoriteMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idMeal;

    @OneToOne
    @JoinColumn(name="userApp_id", referencedColumnName = "id")
    private UserApp userApp;

    private String name;
    private String image;

    public FavoriteMeal(UserApp userApp, String name, String image, String idMeal) {
        this.userApp = userApp;
        this.name = name;
        this.image = image;
        this.idMeal = idMeal;
    }

    public FavoriteMeal() {
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public UserApp getUserApp() {
        return userApp;
    }

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
