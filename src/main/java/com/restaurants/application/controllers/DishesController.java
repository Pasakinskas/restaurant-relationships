package com.restaurants.application.controllers;

import com.restaurants.application.entities.Dish;
import com.restaurants.application.persistence.DishesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DishesController {

  private DishesRepository repository;

  @Autowired
  public DishesController(DishesRepository repository) {
    this.repository = repository;
  }

  @PostMapping("/v1/dishes")
  public Dish createNewDish(@RequestBody Dish dish) {
    return repository.save(dish);
  }

  @GetMapping("/v1/dishes")
  public List<Dish> getAllDishes(@RequestBody Dish dish) {
    return repository.findAll();
  }
}
