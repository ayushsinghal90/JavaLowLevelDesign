package org.lowLevelDesign.ecommerce.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.lowLevelDesign.ecommerce.models.Order;
import org.lowLevelDesign.ecommerce.models.enums.OrderStatus;

public class OrderRepository {
  public static Map<String, Order> orderIdIndex = new HashMap<>();
  public static Map<String, Set<String>> userOrderIndex = new HashMap<>();

  public Order getOrder(final String productId) throws Exception {
    Order order = orderIdIndex.getOrDefault(productId, null);
    if (order == null) {
      throw new Exception(String.format("Product does not exist for: %s", productId));
    }
    return order;
  }

  public List<Order> getUserOrders(final String username, final OrderStatus orderStatus) {
    List<Order> userOrders = new ArrayList<>();
    Set<String> userOrderIds = userOrderIndex.getOrDefault(username, new HashSet<>());
    for (String orderId : userOrderIds) {
      Order order = orderIdIndex.getOrDefault(orderId, null);
      if (order != null) {
        if (orderStatus == null || order.getOrderStatus() == orderStatus) {
          userOrders.add(order);
        }
      }
    }
    return userOrders;
  }

  public Order createOrUpdateOrder(final Order order) {
    if (order.getId() == null) {
      order.setId(String.valueOf(UUID.randomUUID()));
    }
    orderIdIndex.put(order.getId(), order);
    Set<String> userOrders = userOrderIndex.getOrDefault(order.getUsername(), new HashSet<>());
    userOrders.add(order.getId());
    userOrderIndex.put(order.getUsername(), userOrders);
    return order;
  }
}
