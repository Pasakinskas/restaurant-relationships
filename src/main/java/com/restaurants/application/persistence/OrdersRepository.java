package com.restaurants.application.persistence;

import com.restaurants.application.entities.RestaurantOrder;
import com.restaurants.application.entities.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdersRepository extends JpaRepository<RestaurantOrder, Long> {

  @Query("SELECT a FROM RestaurantOrder a WHERE a.created_at > ?1 AND a.created_at < ?2 AND a.source = ?3")
  List<RestaurantOrder> getOrdersByDateAndSource(LocalDateTime fromDate, LocalDateTime toDate, Source source);

  @Query("SELECT r FROM RestaurantOrder r WHERE r.created_at > ?1 AND r.created_at < ?2")
  List<RestaurantOrder> getOrdersByDate(LocalDateTime fromDate, LocalDateTime toDate);
}
