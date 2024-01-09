package org.lowLevelDesign.ecommerce.models;

import java.time.LocalDate;
import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.lowLevelDesign.ecommerce.models.cart.Item;
import org.lowLevelDesign.ecommerce.models.enums.OrderStatus;
import org.lowLevelDesign.ecommerce.models.enums.PaymentStatus;

@Setter
@Getter
@AllArgsConstructor
public class Order {
  String id;
  String username;
  HashMap<String, Item> orderItems;
  double total;
  OrderStatus orderStatus;
  PaymentStatus paymentStatus;
  LocalDate orderDate;

  public Order(
      String username,
      HashMap<String, Item> orderItems,
      double total,
      OrderStatus orderStatus,
      PaymentStatus paymentStatus,
      LocalDate orderDate) {
    this.username = username;
    this.orderItems = orderItems;
    this.total = total;
    this.orderStatus = orderStatus;
    this.paymentStatus = paymentStatus;
    this.orderDate = orderDate;
  }
}
