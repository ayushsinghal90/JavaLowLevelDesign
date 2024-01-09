package org.lowLevelDesign.ecommerce;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lowLevelDesign.ecommerce.models.Address;
import org.lowLevelDesign.ecommerce.models.Order;
import org.lowLevelDesign.ecommerce.models.Product;
import org.lowLevelDesign.ecommerce.models.User;
import org.lowLevelDesign.ecommerce.models.cart.ShoppingCart;

public class EcommerceTest {
  private EcommerceApplication ecommerceApplication;

  @BeforeEach
  void setUp() {
    ecommerceApplication = EcommerceApplication.ECOMMERCE_APPLICATION;
  }

  @Test
  void testCreateAndRetrieveUser() throws Exception {
    User user = createTestUser();
    User createdUser = ecommerceApplication.userController.createUser(user);
    User retrievedUser = ecommerceApplication.userController.getUser(createdUser.getUsername());
    assertEquals(createdUser.getUsername(), retrievedUser.getUsername());
    assertNotNull(retrievedUser);
  }

  @Test
  void testRetrieveProduct() throws Exception {
    Product product = ecommerceApplication.shoppingController.getProduct("ProductId1");
    assertNotNull(product);
    assertEquals("ProductId1", product.getId());
  }

  @Test
  void testRetrieveShoppingCart() throws Exception {
    User user = createTestUser();
    ecommerceApplication.userController.createUser(user);
    ShoppingCart shoppingCart = ecommerceApplication.shoppingController.getCart(user.getUsername());
    assertNotNull(shoppingCart);
  }

  @Test
  void testAddToCartAndCheckout() throws Exception {
    User user = createTestUser();
    ecommerceApplication.userController.createUser(user);

    assertDoesNotThrow(
        () ->
            ecommerceApplication.shoppingController.addToCart(
                user.getUsername(), "ProductId1", 10));

    Order order =
        assertDoesNotThrow(
            () -> ecommerceApplication.shoppingController.checkout(user.getUsername()));

    assertNotNull(order);
    assertNotNull(order.getId());
    assertEquals(1000000.0, order.getTotal());
  }

  @Test
  void testRetrieveOrderHistory() throws Exception {
    User user = createTestUser();
    ecommerceApplication.userController.createUser(user);

    ecommerceApplication.shoppingController.addToCart(user.getUsername(), "ProductId1", 10);
    ecommerceApplication.shoppingController.checkout(user.getUsername());

    List<Order> orders =
        ecommerceApplication.shoppingController.getOrderHistory(user.getUsername());
    assertNotNull(orders);
    assertFalse(orders.isEmpty());
  }

  private User createTestUser() {
    User user = new User();
    user.setName("ush");
    user.setEmail("ayus@test.com");
    user.setPassword("test");
    user.setPhoneNumber("8816789195");
    user.setShippingAddress(
        new Address(
            String.valueOf(UUID.randomUUID()), "test", "Asgard", "Marvel", "1007007", "India"));
    return user;
  }
}
