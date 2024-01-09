package org.lowLevelDesign.ecommerce.utils;

import java.util.regex.Pattern;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Utility class for common validation tasks.
 *
 * @author ayushsinghal90
 */
public class Validators {

  /**
   * Validates an email address using Apache Commons EmailValidator.
   *
   * @param email The email address to be validated.
   * @return True if the email is valid; otherwise, false.
   */
  public static boolean validateEmail(String email) {
    // Use Apache Commons EmailValidator for email validation.
    return EmailValidator.getInstance().isValid(email);
  }

  /**
   * Validates a phone number against a simple pattern. Assumes a valid phone number has exactly 10
   * digits.
   *
   * @param phoneNo The phone number to be validated.
   * @return True if the phone number is valid; otherwise, false.
   */
  public static boolean validatePhoneNo(String phoneNo) {
    // Use a regular expression pattern for phone number validation.
    Pattern phonePattern = Pattern.compile("^\\d{10}$");
    return phonePattern.matcher(phoneNo).matches();
  }
}
