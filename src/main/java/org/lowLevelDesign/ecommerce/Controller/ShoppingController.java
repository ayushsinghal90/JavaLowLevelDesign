package org.lowLevelDesign.ecommerce.Controller;

import java.util.List;
import org.lowLevelDesign.ecommerce.Services.OrderService;
import org.lowLevelDesign.ecommerce.Services.ProductService;
import org.lowLevelDesign.ecommerce.Services.ShoppingService;
import org.lowLevelDesign.ecommerce.models.Order;
import org.lowLevelDesign.ecommerce.models.Product;
import org.lowLevelDesign.ecommerce.models.cart.ShoppingCart;

public class ShoppingController {
  final ShoppingService shoppingService;
  final OrderService orderService;
  final ProductService productService;

  public ShoppingController(
      ShoppingService shoppingService, OrderService orderService, ProductService productService) {
    this.shoppingService = shoppingService;
    this.orderService = orderService;
    this.productService = productService;
  }

  public ShoppingCart getCart(final String username) throws Exception {
    return shoppingService.getCart(username);
  }

  /**
   * If present quantity will be added to existing. Ex: Item with 10 quantity already present in
   * card will get updated to 20 if a request for same product with 10 quantity is asked. Same will
   * happen with price.
   *
   * @param username
   * @param productId
   * @param quantity
   * @return
   * @throws Exception
   */
  public ShoppingCart addToCart(String username, String productId, int quantity) throws Exception {
    return shoppingService.addToCart(username, productId, quantity);
  }

  public List<Order> getOrderHistory(final String username) throws Exception {
    return orderService.getOrderHistory(username);
  }

  public Product getProduct(final String productId) throws Exception {
    return productService.getProduct(productId);
  }

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
