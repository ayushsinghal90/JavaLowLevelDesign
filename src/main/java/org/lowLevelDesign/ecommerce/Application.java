package org.lowLevelDesign.ecommerce;

import org.lowLevelDesign.ecommerce.Controller.ShoppingController;
import org.lowLevelDesign.ecommerce.Controller.UserController;
import org.lowLevelDesign.ecommerce.Services.OrderService;
import org.lowLevelDesign.ecommerce.Services.PaymentService;
import org.lowLevelDesign.ecommerce.Services.ProductService;
import org.lowLevelDesign.ecommerce.Services.ShoppingService;
import org.lowLevelDesign.ecommerce.Services.UserService;
import org.lowLevelDesign.ecommerce.repository.OrderRepository;
import org.lowLevelDesign.ecommerce.repository.ProductRepository;
import org.lowLevelDesign.ecommerce.repository.ShoppingCartRepository;
import org.lowLevelDesign.ecommerce.repository.UserRepository;

/** The main application class responsible for initializing controllers and services. */
public final class Application {

  // Singleton instance
  public static final Application application = new Application();
  // Controllers
  public UserController userController;
  public ShoppingController shoppingController;

  /** Private constructor to enforce singleton pattern and initialize components. */
  private Application() {
    // Initialize repositories
    UserRepository userRepository = new UserRepository();
    OrderRepository orderRepository = new OrderRepository();
    ProductRepository productRepository = new ProductRepository();
    ShoppingCartRepository shoppingCartRepository = new ShoppingCartRepository();

    // Initialize services
    PaymentService paymentService = new PaymentService();
    ProductService productService = new ProductService(productRepository);
    ShoppingService shoppingService = new ShoppingService(shoppingCartRepository, productService);
    UserService userService = new UserService(userRepository, shoppingService);
    OrderService orderService =
        new OrderService(orderRepository, userService, productService, paymentService);

    // Initialize controllers
    this.userController = new UserController(userService);
    this.shoppingController = new ShoppingController(shoppingService, orderService, productService);
  }
}
