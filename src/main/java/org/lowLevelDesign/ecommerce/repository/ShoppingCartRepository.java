package org.lowLevelDesign.ecommerce.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.lowLevelDesign.ecommerce.models.cart.ShoppingCart;

public class ShoppingCartRepository {
  public static Map<String, ShoppingCart> userCartMap = new HashMap<>();

  public ShoppingCart getCart(final String username) throws Exception {
    ShoppingCart shoppingCart = userCartMap.getOrDefault(username, null);
    if (username == null) {
      throw new Exception(String.format("Username does not exist: %s", username));
    }
    return shoppingCart;
  }

  public void registerUserCart(final String username) {
    userCartMap.put(
        username, new ShoppingCart(username, String.valueOf(UUID.randomUUID()), new HashMap<>()));
  }
}
