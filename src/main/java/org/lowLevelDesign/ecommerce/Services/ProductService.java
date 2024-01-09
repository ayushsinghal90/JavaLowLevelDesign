package org.lowLevelDesign.ecommerce.Services;

import lombok.AllArgsConstructor;
import org.lowLevelDesign.ecommerce.exception.InsufficientQuantityException;
import org.lowLevelDesign.ecommerce.exception.ProductNotFoundException;
import org.lowLevelDesign.ecommerce.models.Product;
import org.lowLevelDesign.ecommerce.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Service class for managing products. */
@AllArgsConstructor
public class ProductService {
  private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);
  private final ProductRepository productRepository;

  /**
   * Get product by ID.
   *
   * @param productId The ID of the product to retrieve.
   * @return The product with the specified ID.
   * @throws ProductNotFoundException if the product with the given ID is not found.
   */
  public Product getProduct(final String productId) throws ProductNotFoundException {
    try {
      return productRepository.getProduct(productId);
    } catch (ProductNotFoundException e) {
      LOG.error("Error fetching product with ID {}: {}", productId, e.getMessage());
      throw e;
    }
  }

  /**
   * Update product quantity based on consumed quantity.
   *
   * @param productId The ID of the product to update.
   * @param consumedQuantity The quantity to consume.
   * @return true if the update is successful, false otherwise.
   * @throws InsufficientQuantityException if the consumed quantity is greater than available
   *     quantity.
   * @throws ProductNotFoundException if the product with the given ID is not found.
   */
  public boolean updateProductQuantity(final String productId, final int consumedQuantity)
      throws InsufficientQuantityException, ProductNotFoundException {
    try {
      Product product = getProduct(productId);

      if (product.getCount() < consumedQuantity) {
        throw new InsufficientQuantityException(
            "Required Product quantity is not available for product " + product.getName());
      }

      product.setCount(product.getCount() - consumedQuantity);
      return true;
    } catch (ProductNotFoundException e) {
      LOG.error("Error updating product quantity for ID {}: {}", productId, e.getMessage());
      throw e;
    }
  }
}
