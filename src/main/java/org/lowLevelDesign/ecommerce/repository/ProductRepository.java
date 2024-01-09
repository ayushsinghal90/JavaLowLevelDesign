package org.lowLevelDesign.ecommerce.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.lowLevelDesign.ecommerce.exception.ProductNotFoundException;
import org.lowLevelDesign.ecommerce.models.Product;

/**
 * Repository class for managing products and their indices.
 *
 * @author ayushsinghal90
 */
public class ProductRepository {

  // Index to store products based on their IDs.
  public static Map<String, Product> productIdIndex =
      new HashMap<>() {
        {
          // Initial products added to the productIdIndex for demonstration purposes.
          put("ProductId1", new Product("ProductId1", "laptop", 100, 100000.0, "Best laptop"));
          put("ProductId2", new Product("ProductId2", "mobile", 1000, 10000.0, "Best mobile"));
          put("ProductId3", new Product("ProductId3", "ipod", 800, 20000.0, "Best ipod"));
        }
      };

  /**
   * Retrieves a product based on its ID.
   *
   * @param productId The ID of the product to be retrieved.
   * @return The product corresponding to the product ID.
   * @throws ProductNotFoundException If the product does not exist for the given ID.
   */
  public Product getProduct(final String productId) throws ProductNotFoundException {
    return Optional.of(productIdIndex.getOrDefault(productId, null))
        .orElseThrow(
            () ->
                new ProductNotFoundException(
                    String.format("Product does not exist for: %s", productId)));
  }

  /**
   * Creates or updates a product. Generates an ID if the product does not have one.
   *
   * @param product The product to be created or updated.
   * @return The created or updated product.
   */
  public Product createOrUpdateProduct(Product product) {
    if (product.getId() == null) {
      // Generate an ID for the product if it doesn't have one.
      product.setId(String.valueOf(UUID.randomUUID()));
    }

    // Update the productIdIndex with the product.
    productIdIndex.put(product.getId(), product);

    return product;
  }
}
