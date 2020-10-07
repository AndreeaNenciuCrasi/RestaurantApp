package com.codecool.restaurant.User;


import com.codecool.restaurant.Security.JwtTokenServices;
import com.codecool.restaurant.ShoppingCart.ShoppingCart;
import com.codecool.restaurant.ShoppingCart.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "User Queries", value = "UserQueries", description = "Controller for User Queries")
public class UserController {

    private final UserService userService;
    private final ShoppingCartService shoppingCartService;


    @Autowired
    public UserController(UserService userService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    @ApiOperation(value = "Add a new user")
    public void addUser(@Valid @RequestBody UserApp userApp){
        System.out.println(userApp);
        userService.addUser(userApp);
        shoppingCartService.addCart(new ShoppingCart(userApp));
    }


    @GetMapping
    @ApiOperation(value = "Find all users registered in the DB")
    public List<UserApp> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(path="{id}")
    @ApiOperation(value = "Find a user by id")
    public UserApp getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id)
                .orElse(null);
    }

    @DeleteMapping()
    @ApiOperation(value = "Delete a user from DB by ID")
    public void deletePersonByUsername(@CookieValue("token") String token){
        userService.deleteUserApp(token);
    }

    @GetMapping(path="view/{username}")
    @ApiOperation(value = "Find a user by username")
    public UserApp getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username)
                .orElse(null);
    }

    @PostMapping(path = "{username}/edit")
    @ApiOperation(value = "Update user details")
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

