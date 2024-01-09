package org.lowLevelDesign.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
  String username;
  String name;
  String email;
  String phoneNumber;
  String password;
  Address shippingAddress;
}
