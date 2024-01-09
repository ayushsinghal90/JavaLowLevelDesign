package org.lowLevelDesign.ecommerce.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.lowLevelDesign.ecommerce.models.cart.ShoppingCart;

/**
 * Repository class for managing shopping carts associated with users.
 *
 * @author ayushsinghal90
 */
public class ShoppingCartRepository {

  // Map to store shopping carts based on usernames.
  public static Map<String, ShoppingCart> userCartMap = new HashMap<>();

  /**
   * Retrieves a user's shopping cart based on the username.
   *
   * @param username The username of the user.
   * @return The shopping cart associated with the username.
   * @throws Exception If the username is null or the shopping cart does not exist.
   */
  public ShoppingCart getCart(final String username) throws Exception {
    if (username == null) {
      throw new Exception("Username is null. Cannot retrieve shopping cart.");
    }

    return Optional.of(userCartMap.get(username))
        .orElseThrow(
            () ->
                new Exception(
                    String.format("Shopping cart does not exist for username: %s", username)));
  }

  /**
   * Registers a new shopping cart for a user.
   *
   * @param username The username of the user.
   */
  public void registerUserCart(final String username) {
    // Generate a unique cart ID using UUID.
    String cartId = String.valueOf(UUID.randomUUID());

    // Create a new shopping cart and associate it with the username.
    ShoppingCart newShoppingCart = new ShoppingCart(username, cartId, new HashMap<>());
    userCartMap.put(username, newShoppingCart);
  }
}
