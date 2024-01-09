package org.lowLevelDesign.ecommerce.models.cart;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ShoppingCart {
  String username;
  String id;
  HashMap<String, Item> items;
}
