package com.codecool.restaurant;

import com.codecool.restaurant.ShoppingCart.ShoppingCart;
import com.codecool.restaurant.ShoppingCart.ShoppingCartService;
import com.codecool.restaurant.User.UserApp;
import com.codecool.restaurant.User.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ShoppingCartService shoppingCartService;

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public DataInitializer(UserRepository userRepository, ShoppingCartService shoppingCartService) {
        this.userRepository = userRepository;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void run(String... args) {

        UserApp user = new UserApp("Andreea", "Grosu", "admin", "andreea.grosu87@gmail.com", "", "", passwordEncoder.encode("admin"));

        userRepository.save(user);
        shoppingCartService.addCart(new ShoppingCart(user));

    }
}
