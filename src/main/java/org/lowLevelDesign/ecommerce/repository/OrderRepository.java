package org.lowLevelDesign.ecommerce.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.lowLevelDesign.ecommerce.models.Order;
import org.lowLevelDesign.ecommerce.models.enums.OrderStatus;

/**
 * Repository class for managing orders and their indices.
 *
 * @author ayushsinghal90
 */
public class OrderRepository {

  // Index to store orders based on their IDs.
  public static Map<String, Order> orderIdIndex = new HashMap<>();

  // Index to store order IDs based on the username of users.
  public static Map<String, Set<String>> userOrderIndex = new HashMap<>();

  /**
   * Retrieves an order based on its product ID.
   *
   * @param productId The product ID associated with the order.
   * @return The order corresponding to the product ID.
   * @throws Exception If the order does not exist for the given product ID.
   */
  public Order getOrder(final String productId) throws Exception {
    return Optional.of(orderIdIndex.get(productId))
        .orElseThrow(
            () -> new Exception(String.format("Product does not exist for: %s", productId)));
  }

  /**
   * Retrieves a list of user orders based on the username and optional order status.
   *
   * @param username The username of the user.
   * @param orderStatus The status of the orders to filter (can be null for all orders).
   * @return The list of user orders matching the criteria.
   */
  public List<Order> getUserOrders(final String username, final OrderStatus orderStatus) {
    List<Order> userOrders = new ArrayList<>();
    Set<String> userOrderIds = userOrderIndex.getOrDefault(username, new HashSet<>());
    for (String orderId : userOrderIds) {
      Order order = orderIdIndex.getOrDefault(orderId, null);
      if (order != null && (orderStatus == null || order.getOrderStatus() == orderStatus)) {
        userOrders.add(order);
      }
    }
    return userOrders;
  }

  /**
   * Creates or updates an order. Generates an ID if the order does not have one.
   *
   * @param order The order to be created or updated.
   * @return The created or updated order.
   */
  public Order createOrUpdateOrder(final Order order) {
    if (order.getId() == null) {
      // Generate an ID for the order if it doesn't have one.
      order.setId(String.valueOf(UUID.randomUUID()));
    }

    // Update the orderIdIndex with the order.
    orderIdIndex.put(order.getId(), order);

    // Update the userOrderIndex with the order ID for the user.
    Set<String> userOrders = userOrderIndex.getOrDefault(order.getUsername(), new HashSet<>());
    userOrders.add(order.getId());
    userOrderIndex.put(order.getUsername(), userOrders);

    return order;
  }
}
