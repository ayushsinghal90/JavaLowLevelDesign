package org.lowLevelDesign.ecommerce.Services;

import static org.lowLevelDesign.ecommerce.utils.Validators.validateEmail;
import static org.lowLevelDesign.ecommerce.utils.Validators.validatePhoneNo;

import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import org.lowLevelDesign.ecommerce.models.User;
import org.lowLevelDesign.ecommerce.repository.UserRepository;

@AllArgsConstructor
public class UserService {
  static final Logger LOG = Logger.getLogger(UserService.class.getName());
  final UserRepository userRepository;
  final ShoppingService shoppingService;

  public User createUser(User user) throws Exception {
    if (!validateEmail(user.getEmail()) || !validatePhoneNo(user.getPhoneNumber())) {
      throw new Exception("User details are not valid");
    }
    userRepository.createUser(user);
    shoppingService.createShoppingCart(user.getUsername());
    return user;
  }

  public User getUser(String username) throws Exception {
    return userRepository.getUser(username);
  }
}
