package org.lowLevelDesign.ecommerce.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.lowLevelDesign.ecommerce.models.User;

public class UserRepository {
  public static Map<String, User> usernameIndex = new HashMap<>();

  public User getUser(final String username) throws Exception {
    User user = usernameIndex.getOrDefault(username, null);
    if (user == null) {
      throw new Exception(String.format("User does not exist for: %s", username));
    }
    return user;
  }

  public User createUser(User user) {
    user.setUsername(String.valueOf(UUID.randomUUID()));
    usernameIndex.put(user.getUsername(), user);
    return user;
  }
}
