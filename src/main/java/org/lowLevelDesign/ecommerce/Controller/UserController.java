package org.lowLevelDesign.ecommerce.Controller;

import org.lowLevelDesign.ecommerce.Services.UserService;
import org.lowLevelDesign.ecommerce.models.User;

public class UserController {
  UserService userService;

  public UserController(final UserService userService) {
    this.userService = userService;
  }

  public User createUser(User user) throws Exception {
    return userService.createUser(user);
  }

  public User getUser(String username) throws Exception {
    return userService.getUser(username);
  }
}
