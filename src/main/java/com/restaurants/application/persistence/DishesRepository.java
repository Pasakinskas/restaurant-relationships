package com.restaurants.application.persistence;

import com.restaurants.application.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishesRepository extends JpaRepository<Dish, Long> {}
