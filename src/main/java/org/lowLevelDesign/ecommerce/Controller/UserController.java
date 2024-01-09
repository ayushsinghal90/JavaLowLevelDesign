package org.lowLevelDesign.ecommerce.Controller;

import org.lowLevelDesign.ecommerce.Services.UserService;
import org.lowLevelDesign.ecommerce.models.User;

/**
 * Controller class for handling user-related operations.
 *
 * @author ayushsinghal90
 */
public class UserController {

  private final UserService userService;

  /**
   * Constructor for initializing the controller with the user service.
   *
   * @param userService The user service.
   */
  public UserController(final UserService userService) {
    this.userService = userService;
  }

  /**
   * Creates a new user using the provided user service.
   *
   * @param user The user to be created.
   * @return The created user.
   * @throws Exception If there's an issue creating the user.
   */
  public User createUser(User user) throws Exception {
    return userService.createUser(user);
  }

  /**
   * Retrieves user information based on the username using the provided user service.
   *
   * @param username The username of the user to be retrieved.
   * @return The user information.
   * @throws Exception If there's an issue retrieving the user.
   */
  public User getUser(String username) throws Exception {
    return userService.getUser(username);
  }
}
