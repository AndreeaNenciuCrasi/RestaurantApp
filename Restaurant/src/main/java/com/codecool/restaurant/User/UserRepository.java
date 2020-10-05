package com.codecool.restaurant.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository("userDB")
public interface UserRepository extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByUserName(String userName);

    @Modifying(clearAutomatically = true)
    @Query(value = "update UserApp u set u.firstName=:firstName, " +
            "u.lastName=:lastName , " +
            "u.emailAddress=:emailAddress, " +
            "u.password=:password, " +
            "u.deliveryAddress = :deliveryAddress, " +
            "u.phoneNumber= :phoneNumber where u.userName = :userName")
    @Transactional
    void updateUser(@Param("firstName") String firstName,
                    @Param("lastName") String lastName,
                    @Param("emailAddress") String emailAddress,
                    @Param("password") String password ,
                    @Param("deliveryAddress") String deliveryAddress,
                    @Param("phoneNumber") String phoneNumber,
                    @Param("userName") String userName);

}
