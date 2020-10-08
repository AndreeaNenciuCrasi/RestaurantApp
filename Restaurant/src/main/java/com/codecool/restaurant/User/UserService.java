package com.codecool.restaurant.User;

import com.codecool.restaurant.Security.JwtTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // Add encoder for password
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepository userRepository;

    private final JwtTokenServices jwtTokenServices;

    @Autowired
    public UserService(@Qualifier("userDB") UserRepository userRepository, JwtTokenServices jwtTokenServices){
        this.userRepository = userRepository;
        this.jwtTokenServices = jwtTokenServices;
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // Persisting User with encoded password
    public void addUser(UserApp userApp){
        userApp.setPassword(passwordEncoder.encode(userApp.getPassword()));
        userRepository.save(userApp);
    }

    public List<UserApp> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<UserApp> getUserById(Long id){
        return userRepository.findById(id);
    }

    public void updateUser(String firstName, String lastName, String emailAddress, String password,String deliveryAddress, String phoneNumber, String userName) {
        userRepository.updateUser(firstName, lastName, emailAddress, password, deliveryAddress, phoneNumber, userName);
    }


    public Optional<UserApp> getUserByUsername(String username){
        return userRepository.findByUserName(username);

    }

    public void deleteUserApp(String token){
        Authentication userName = jwtTokenServices.parseUserFromTokenInfo(token);
        Optional<UserApp> tempUser = userRepository.findByUserName(userName.getName());
        tempUser.ifPresent(userApp -> userRepository.deleteById(userApp.getId()));
    }

}
