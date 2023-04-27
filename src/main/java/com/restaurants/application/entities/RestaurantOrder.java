package com.restaurants.application.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class RestaurantOrder {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Source source;

  @CreatedDate
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  @Column
  private LocalDateTime created_at;

  @ManyToMany
  @JoinTable(
    name = "order_dishes",
    joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id")
  )
  @ToString.Exclude
  List<Dish> orderDishes;
}
