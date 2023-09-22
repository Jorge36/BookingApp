package com.restaurant.BookingApplication.Repository;

import com.restaurant.BookingApplication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE user SET logged_in = :logged_in WHERE id = :id", nativeQuery = true)
    void updateLoggedIn(@Param("logged_in") Boolean loggedIn, @Param("id") Integer id);

}
