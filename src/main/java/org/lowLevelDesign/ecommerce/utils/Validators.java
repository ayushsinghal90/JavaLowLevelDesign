package org.lowLevelDesign.ecommerce.utils;

import java.util.regex.Pattern;
import org.apache.commons.validator.routines.EmailValidator;

public class Validators {
  public static boolean validateEmail(String email) {
    return EmailValidator.getInstance().isValid(email);
  }

  public static boolean validatePhoneNo(String phoneNo) {
    Pattern p = Pattern.compile("^\\d{10}$");
    return p.matcher(phoneNo).matches();
  }
}
