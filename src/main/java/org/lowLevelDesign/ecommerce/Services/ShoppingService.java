package org.lowLevelDesign.ecommerce.Services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lowLevelDesign.ecommerce.models.Product;
import org.lowLevelDesign.ecommerce.models.cart.Item;
import org.lowLevelDesign.ecommerce.models.cart.ShoppingCart;
import org.lowLevelDesign.ecommerce.repository.ShoppingCartRepository;

/**
 * Service class for shopping-related operations.
 *
 * @author ayushsinghal90
 */
@AllArgsConstructor
public class ShoppingService {
  private static final Logger LOG = LogManager.getLogger(ShoppingService.class);

  private final ShoppingCartRepository shoppingCartRepository;
  private final ProductService productService;

  /**
   * Creates a shopping cart for the specified user.
   *
   * @param username The username of the user.
   */
  public void createShoppingCart(String username) {
    shoppingCartRepository.registerUserCart(username);
  }

  /**
   * Retrieves the shopping cart for the specified user.
   *
   * @param username The username of the user.
   * @return The shopping cart associated with the user.
   * @throws Exception If there is an error retrieving the shopping cart.
   */
  public ShoppingCart getCart(final String username) throws Exception {
    return shoppingCartRepository.getCart(username);
  }

  /**
   * Adds items to the user's shopping cart.
   *
   * @param username The username of the user.
   * @param productId The ID of the product to be added.
   * @param quantity The quantity of the product to be added.
   * @return The updated shopping cart.
   * @throws Exception If there is an error adding items to the cart.
   */
  public ShoppingCart addToCart(String username, String productId, int quantity) throws Exception {
    ShoppingCart shoppingCart = getCart(username);
    Product product = productService.getProduct(productId);

    // Update the shopping cart with the new item.
    updateCartItem(shoppingCart, product, quantity);

    return shoppingCart;
  }

  /**
   * Validates the items in the shopping cart to ensure product availability.
   *
   * @param shoppingCart The shopping cart to validate.
   * @return True if all items are valid; otherwise, false.
   * @throws Exception If there is an error validating the items.
   */
  public boolean validateCartItems(final ShoppingCart shoppingCart) throws Exception {
    HashMap<String, Item> cartItems = shoppingCart.getItems();

    for (Map.Entry<String, Item> entry : cartItems.entrySet()) {
      // Validate each item in the shopping cart.
      validateCartItem(entry.getKey(), entry.getValue());
    }
    return true;
  }

  // Private methods for internal logic

  private void updateCartItem(ShoppingCart shoppingCart, Product product, int quantity) {
    HashMap<String, Item> cartItems = shoppingCart.getItems();
    Item itemToUpdate = cartItems.getOrDefault(product.getId(), null);
    Item newItem = createItem(product, quantity);

    if (itemToUpdate == null) {
      // If the item is not in the cart, add it.
      cartItems.put(product.getId(), newItem);
    } else {
      // If the item is already in the cart, update its quantity and price.
      itemToUpdate.setQuantity(itemToUpdate.getQuantity() + newItem.getQuantity());
      itemToUpdate.setPrice(itemToUpdate.getPrice() + newItem.getPrice());
    }
  }

  private void validateCartItem(String productId, Item currentItem) throws Exception {
    Product product = productService.getProduct(productId);
    if (product.getCount() < currentItem.getQuantity()) {
      throw new Exception("Some items are not available currently");
    }
  }

  private Item createItem(final Product product, int quantity) {
    // Creates a new item for the shopping cart.
    return new Item(
        String.valueOf(UUID.randomUUID()),
        quantity,
        product.getPrice() * quantity,
        product.getId());
  }
}
