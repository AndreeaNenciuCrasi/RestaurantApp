package com.codecool.restaurant.User;


import com.codecool.restaurant.Security.JwtTokenServices;
import com.codecool.restaurant.ShoppingCart.ShoppingCart;
import com.codecool.restaurant.ShoppingCart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


@RequestMapping("/yellowrestaurant/api/v1/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    private final UserService userService;
    private final ShoppingCartService shoppingCartService;


    @Autowired
    public UserController(UserService userService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    public void addUser(@Valid @RequestBody UserApp userApp){
        System.out.println(userApp);
        userService.addUser(userApp);
        shoppingCartService.addCart(new ShoppingCart(userApp));
    }


    @GetMapping
    public List<UserApp> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(path="{id}")
    public UserApp getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id)
                .orElse(null);
    }

    @DeleteMapping()
    public void deletePersonByUsername(@CookieValue("token") String token){
        userService.deleteUserApp(token);
    }

    @GetMapping(path="view/{username}")
    public UserApp getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username)
                .orElse(null);
    }

    @PostMapping(path = "{username}/edit")
    public void updateUserProfile(@PathVariable("username") String username, @RequestBody UserApp updatedUserApp) {
        String firstName = updatedUserApp.getFirstName();
        String lastName = updatedUserApp.getLastName();
        String emailAddress = updatedUserApp.getEmailAddress();
        String password = updatedUserApp.getPassword();
        String deliveryAddress = updatedUserApp.getDeliveryAddress();
        String phoneNumber = updatedUserApp.getPhoneNumber();

        userService.updateUser(firstName, lastName, emailAddress, password, deliveryAddress, phoneNumber, username);

    }

}

