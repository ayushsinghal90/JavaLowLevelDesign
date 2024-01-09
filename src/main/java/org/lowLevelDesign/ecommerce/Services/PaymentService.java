package org.lowLevelDesign.ecommerce.Services;

import java.util.logging.Logger;
import org.lowLevelDesign.ecommerce.models.Order;
import org.lowLevelDesign.ecommerce.models.enums.PaymentStatus;

/**
 * Service class for handling payment-related operations.
 *
 * @author ayushsinghal90
 */
public class PaymentService {

    // Logger for logging payment-related events.
    private static final Logger LOG = Logger.getLogger(PaymentService.class.getName());

    /**
     * Confirms the payment for the given order by updating its payment status.
     *
     * @param order The order for which payment confirmation is requested.
     * @return True if the payment is confirmed successfully; otherwise, false.
     */
    public boolean confirmPayment(final Order order) {
        // Set the payment status of the order to complete, indicating successful payment.
        order.setPaymentStatus(PaymentStatus.COMPLETE);

        // Log the payment confirmation event.
        LOG.info("Payment confirmed for Order ID: " + order.getId());

        // Return true to indicate successful payment confirmation.
        return true;
    }
}
