package org.lowLevelDesign.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Product {
  String id;
  String name;
  int count;
  double price;
  String description;
}
