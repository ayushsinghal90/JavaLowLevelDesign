package org.lowLevelDesign.ecommerce.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.lowLevelDesign.ecommerce.exception.ProductNotFoundException;
import org.lowLevelDesign.ecommerce.models.Product;

public class ProductRepository {
  public static Map<String, Product> productIdIndex =
      new HashMap<>() {
        {
          put("ProductId1", new Product("ProductId1", "laptop", 100, 100000.0, "Best laptop"));
          put("ProductId2", new Product("ProductId2", "mobile", 1000, 10000.0, "Best mobile"));
          put("ProductId3", new Product("ProductId3", "ipod", 800, 20000.0, "Best ipod"));
        }
      };

  public Product getProduct(final String productId) throws ProductNotFoundException {
    Product product = productIdIndex.getOrDefault(productId, null);
    if (product == null) {
      throw new ProductNotFoundException(
          String.format("Product does not exist for: %s", productId));
    }
    return product;
  }

  public Product createOrUpdateProduct(Product product) {
    if (product.getId() == null) {
      product.setId(String.valueOf(UUID.randomUUID()));
    }
    productIdIndex.put(product.getId(), product);
    return product;
  }
}
