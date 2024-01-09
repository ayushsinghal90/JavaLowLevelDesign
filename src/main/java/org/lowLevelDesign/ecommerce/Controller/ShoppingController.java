package org.lowLevelDesign.ecommerce.Controller;

import java.util.List;
import org.lowLevelDesign.ecommerce.Services.OrderService;
import org.lowLevelDesign.ecommerce.Services.ProductService;
import org.lowLevelDesign.ecommerce.Services.ShoppingService;
import org.lowLevelDesign.ecommerce.models.Order;
import org.lowLevelDesign.ecommerce.models.Product;
import org.lowLevelDesign.ecommerce.models.cart.ShoppingCart;

/**
 * Controller class for handling shopping-related operations.
 *
 * @author ayushsinghal90
 */
public class ShoppingController {

  private final ShoppingService shoppingService;
  private final OrderService orderService;
  private final ProductService productService;

  /**
   * Constructor for initializing the controller with necessary services.
   *
   * @param shoppingService The shopping service.
   * @param orderService The order service.
   * @param productService The product service.
   */
  public ShoppingController(
      ShoppingService shoppingService, OrderService orderService, ProductService productService) {
    this.shoppingService = shoppingService;
    this.orderService = orderService;
    this.productService = productService;
  }

  /**
   * Retrieves the shopping cart for a user.
   *
   * @param username The username of the user.
   * @return The user's shopping cart.
   * @throws Exception If there's an issue retrieving the shopping cart.
   */
  public ShoppingCart getCart(final String username) throws Exception {
    return shoppingService.getCart(username);
  }

  /**
   * Adds a product to the user's shopping cart.
   *
   * @param username The username of the user.
   * @param productId The ID of the product to be added.
   * @param quantity The quantity of the product to be added.
   * @return The updated shopping cart.
   * @throws Exception If there's an issue adding the product to the cart.
   */
  public ShoppingCart addToCart(String username, String productId, int quantity) throws Exception {
    return shoppingService.addToCart(username, productId, quantity);
  }

  /**
   * Retrieves the order history for a user.
   *
   * @param username The username of the user.
   * @return The order history for the user.
   * @throws Exception If there's an issue retrieving the order history.
   */
  public List<Order> getOrderHistory(final String username) throws Exception {
    return orderService.getOrderHistory(username);
  }

  /**
   * Retrieves product information based on the product ID.
   *
   * @param productId The ID of the product.
   * @return The product information.
   * @throws Exception If there's an issue retrieving the product.
   */
  public Product getProduct(final String productId) throws Exception {
    return productService.getProduct(productId);
  }

  /**
   * Handles the checkout process for a user's shopping cart.
   *
   * @param username The username of the user.
   * @return The created order after successful checkout.
   * @throws Exception If there's an issue during the checkout process.
   */
  public Order checkout(final String username) throws Exception {
    ShoppingCart shoppingCart = getCart(username);
    if (shoppingService.validateCartItems(shoppingCart)) {
      Order order = orderService.createOrder(shoppingCart);
      orderService.completeOrderPayment(order);
      return order;
    }
    throw new Exception("Exception while creating order");
  }
}
