package com.codecool.restaurant.Meal;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="MEAL")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private Double price = 5.00;

//    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<MealsToCart> mealsToCarts = new ArrayList<>();

    public Meal() {
    }

    public Meal(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Long getId() {
        return id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return "{\"id\" : " + id + "," +
                "\"name\": \"" +name+ "\"," +
                "\"image\": \"" + image + "\"," +
                "\"price\": "+ price + "}";

    }
}
