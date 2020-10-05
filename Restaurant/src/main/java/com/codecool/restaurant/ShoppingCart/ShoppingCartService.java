package com.codecool.restaurant.ShoppingCart;

import com.codecool.restaurant.User.UserApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(@Qualifier("cartDB") ShoppingCartRepository shoppingCartRepository){
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void addCart(ShoppingCart shoppingCart){
        shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart getCartByUser(UserApp userApp) {
        return shoppingCartRepository.findShoppingcartByUsername(userApp.getUserName());
    }

    public Optional<ShoppingCart> getCartById(Long id) {
        return shoppingCartRepository.findById(id);
    }

}