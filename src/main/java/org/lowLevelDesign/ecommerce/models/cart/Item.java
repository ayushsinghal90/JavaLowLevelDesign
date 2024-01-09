package org.lowLevelDesign.ecommerce.models.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Item {
  String id;
  int quantity;
  double price;
  String productId;
}
