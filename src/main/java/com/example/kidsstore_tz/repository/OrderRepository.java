package com.example.kidsstore_tz.repository;

import com.example.kidsstore_tz.domain.Order;
import com.example.kidsstore_tz.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByUser(User user);

}
