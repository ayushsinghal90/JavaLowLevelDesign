package org.lowLevelDesign.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Address {
  String id;
  String street;
  String city;
  String state;
  String zipCode;
  String country;
}
