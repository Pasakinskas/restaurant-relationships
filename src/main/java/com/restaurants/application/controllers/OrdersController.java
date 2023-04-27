package com.restaurants.application.controllers;

import com.restaurants.application.dto.OrderDetails;
import com.restaurants.application.entities.Dish;
import com.restaurants.application.entities.Period;
import com.restaurants.application.entities.RestaurantOrder;
import com.restaurants.application.entities.Source;
import com.restaurants.application.persistence.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OrdersController {

  private OrdersRepository repository;

  @Autowired
  public OrdersController(OrdersRepository repository) {
    this.repository = repository;
  }

  @PostMapping("/v1/orders")
  public OrderDetails createOrder(@RequestBody RestaurantOrder order) {
    RestaurantOrder restaurantOrder = repository.save(order);

    return new OrderDetails(
        restaurantOrder.getId(),
        restaurantOrder.getSource(),
        restaurantOrder.getCreated_at(),
        restaurantOrder.getOrderDishes().stream().map(Dish::getId).toList()
    );
  }

  @GetMapping("/v1/orders")
  public List<RestaurantOrder> getAllOrders() {
    return repository.findAll();
  }

  @GetMapping("/v1/orders/count")
  public List<RestaurantOrder> getOrdersByPeriod(
    @RequestParam("period") Period period,
    @RequestParam("fromDate") LocalDate fromDate,
    @RequestParam(value = "source", required = false) Source source
  ) {
    LocalDate toDate = switch (period) {
      case WEEKLY -> fromDate.plusDays(7);
      case MONTHLY -> fromDate.plusMonths(1);
    };

    if (source == null) {
      return repository.getOrdersByDate(fromDate.atStartOfDay(), toDate.atStartOfDay());
    }

    return repository.getOrdersByDateAndSource(fromDate.atStartOfDay(), toDate.atStartOfDay(), source);
  }
}
