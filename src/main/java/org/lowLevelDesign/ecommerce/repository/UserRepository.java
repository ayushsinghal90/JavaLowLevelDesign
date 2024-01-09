package org.lowLevelDesign.ecommerce.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.lowLevelDesign.ecommerce.models.User;

/**
 * Repository class for managing users and their indices.
 *
 * @author ayushsinghal90
 */
public class UserRepository {

  // Index to store users based on their usernames.
  public static Map<String, User> usernameIndex = new HashMap<>();

  /**
   * Retrieves a user based on the username.
   *
   * @param username The username of the user to be retrieved.
   * @return The user corresponding to the username.
   * @throws Exception If the user does not exist for the given username.
   */
  public User getUser(final String username) throws Exception {
    return Optional.of(usernameIndex.get(username))
        .orElseThrow(
            () -> new Exception(String.format("User does not exist for username: %s", username)));
  }

  /**
   * Creates a new user. Generates a username using UUID if not provided.
   *
   * @param user The user to be created.
   * @return The created user.
   */
  public User createUser(User user) {
    if (user.getUsername() == null || user.getUsername().isEmpty()) {
      // Generate a unique username using UUID if not provided.
      user.setUsername(String.valueOf(UUID.randomUUID()));
    }

    // Update the usernameIndex with the user.
    usernameIndex.put(user.getUsername(), user);

    return user;
  }
}
