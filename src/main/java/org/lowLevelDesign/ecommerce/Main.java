package org.lowLevelDesign.ecommerce;

import java.util.List;
import java.util.UUID;
import org.apache.commons.collections.CollectionUtils;
import org.lowLevelDesign.ecommerce.models.Address;
import org.lowLevelDesign.ecommerce.models.Order;
import org.lowLevelDesign.ecommerce.models.Product;
import org.lowLevelDesign.ecommerce.models.User;
import org.lowLevelDesign.ecommerce.models.cart.ShoppingCart;

public class Main {
  public static void main(String[] args) throws Exception {
    Application application = Application.application;

    User user = new User();
    user.setName("ayush");
    user.setEmail("ayus@test.com");
    user.setPassword("test");
    user.setPhoneNumber("9916789195");
    user.setShippingAddress(
        new Address(String.valueOf(UUID.randomUUID()), "test", "paonta", "H.P", "173025", "India"));

    User createdUser = application.userController.createUser(user);
    User retriveUser = application.userController.getUser(createdUser.getUsername());

    if (createdUser.getUsername().equals(retriveUser.getUsername())) {
      System.out.println("User Created " + createdUser.getName());
    }

    Product product = application.shoppingController.getProduct("ProductId1");

    if (product != null) {
      System.out.println("Product present " + product.getName() + "Quantity " + product.getCount());
    }

    ShoppingCart shoppingCart = application.shoppingController.getCart(createdUser.getUsername());
    if (shoppingCart != null) {
      System.out.println("Shopping cart present  " + shoppingCart.getId());
    }

    application.shoppingController.addToCart(createdUser.getUsername(), product.getId(), 10);

    Order order = application.shoppingController.checkout(createdUser.getUsername());
    if (order != null) {
      System.out.println(
          "Order created order id " + order.getId() + " order total " + order.getTotal());
      System.out.println("Update product quantity " + product.getCount());
    }

    List<Order> orders = application.shoppingController.getOrderHistory(createdUser.getUsername());

    if (CollectionUtils.isNotEmpty(orders)) {
      System.out.println("Order history present, order size " + orders.size());

      for (Order cur : orders) {
        System.out.println("Order id " + cur.getId());
      }
    }
  }
}
