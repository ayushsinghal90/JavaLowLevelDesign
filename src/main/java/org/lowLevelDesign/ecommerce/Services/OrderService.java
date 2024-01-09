package org.lowLevelDesign.ecommerce.Services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lowLevelDesign.ecommerce.models.Order;
import org.lowLevelDesign.ecommerce.models.User;
import org.lowLevelDesign.ecommerce.models.cart.Item;
import org.lowLevelDesign.ecommerce.models.cart.ShoppingCart;
import org.lowLevelDesign.ecommerce.models.enums.OrderStatus;
import org.lowLevelDesign.ecommerce.models.enums.PaymentStatus;
import org.lowLevelDesign.ecommerce.repository.OrderRepository;

/**
 * Service class for handling orders.
 *
 * @author ayushsinghal90
 */
@AllArgsConstructor
public class OrderService {

  private static final Logger LOG = LogManager.getLogger(OrderService.class);

  // Dependencies
  private final OrderRepository orderRepository;
  private final UserService userService;
  private final ProductService productService;
  private final PaymentService paymentService;

  /**
   * Retrieves the order history for a given user.
   *
   * @param username The username of the user.
   * @return The list of orders for the user.
   * @throws Exception If an error occurs during retrieval.
   */
  public List<Order> getOrderHistory(final String username) throws Exception {
    User user = userService.getUser(username);
    return orderRepository.getUserOrders(username, null);
  }

  /**
   * Completes the payment for an order and updates product quantities.
   *
   * @param order The order to be completed.
   * @return The completed order.
   * @throws Exception If an error occurs during payment confirmation.
   */
  public Order completeOrderPayment(final Order order) throws Exception {
    HashMap<String, Item> items = order.getOrderItems();

    // Update product quantities
    for (Map.Entry<String, Item> item : items.entrySet()) {
      Item currItem = item.getValue();
      productService.updateProductQuantity(item.getKey(), currItem.getQuantity());
    }

    // Confirm payment
    paymentService.confirmPayment(order);

    // Update order status
    order.setOrderStatus(OrderStatus.IN_TRANSIT);

    return order;
  }

  /**
   * Creates a new order based on the items in the shopping cart.
   *
   * @param shoppingCart The shopping cart containing items to be ordered.
   * @return The newly created order.
   */
  public Order createOrder(final ShoppingCart shoppingCart) {
    HashMap<String, Item> items = shoppingCart.getItems();
    double totalCost = calculateTotalCost(items);

    Order order =
        new Order(
            shoppingCart.getUsername(),
            items,
            totalCost,
            OrderStatus.PENDING,
            PaymentStatus.PENDING,
            LocalDate.now());
    orderRepository.createOrUpdateOrder(order);
    return order;
  }

  /**
   * Calculates the total cost of items in the order.
   *
   * @param items The items in the order.
   * @return The total cost of the items.
   */
  private double calculateTotalCost(HashMap<String, Item> items) {
    double totalCost = 0.0;

    for (Item item : items.values()) {
      totalCost += item.getPrice();
    }
    return totalCost;
  }
}
