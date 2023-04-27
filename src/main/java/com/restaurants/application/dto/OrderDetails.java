package com.restaurants.application.dto;

import com.restaurants.application.entities.Source;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
  private Long id;
  private Source source;
  private LocalDateTime created_at;
  List<Long> dishes;
}
