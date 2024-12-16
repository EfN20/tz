package com.example.kidsstore_tz.repository;

import com.example.kidsstore_tz.domain.Cart;
import com.example.kidsstore_tz.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> getCartByUser(User user);

}
