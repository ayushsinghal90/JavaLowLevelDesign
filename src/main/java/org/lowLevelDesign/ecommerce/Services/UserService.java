package org.lowLevelDesign.ecommerce.Services;

import static org.lowLevelDesign.ecommerce.utils.Validators.validateEmail;
import static org.lowLevelDesign.ecommerce.utils.Validators.validatePhoneNo;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lowLevelDesign.ecommerce.models.User;
import org.lowLevelDesign.ecommerce.repository.UserRepository;

/**
 * Service class for managing user-related operations.
 *
 * @author ayushsinghal90
 */
@AllArgsConstructor
public class UserService {
  private static final Logger LOG = LogManager.getLogger(UserService.class);

  private final UserRepository userRepository;
  private final ShoppingService shoppingService;

  /**
   * Creates a new user, validates user details, and initializes a shopping cart for the user.
   *
   * @param user The user to be created.
   * @return The created user.
   * @throws Exception If user details are not valid or there is an issue creating the user.
   */
  public User createUser(User user) throws Exception {
    if (!validateEmail(user.getEmail()) || !validatePhoneNo(user.getPhoneNumber())) {
      throw new Exception("User details are not valid");
    }

    userRepository.createUser(user);
    shoppingService.createShoppingCart(user.getUsername());

    LOG.info("User created successfully: " + user.getUsername());
    return user;
  }

  /**
   * Retrieves user information based on the username.
   *
   * @param username The username of the user.
   * @return The user information.
   * @throws Exception If there is an issue retrieving user information.
   */
  public User getUser(String username) throws Exception {
    return userRepository.getUser(username);
  }
}
