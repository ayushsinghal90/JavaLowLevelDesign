package org.lowLevelDesign.parkingLot.Services;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Constants.VehicleType;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingTicket;

/**
 * Service class for managing parking operations.
 *
 * @author ayushsinghal90
 */
@AllArgsConstructor
public class ParkingService {

  // Logger for logging service-related events
  private static final Logger LOG = LogManager.getLogger(ParkingService.class);

  // Services for handling tickets and vehicles
  private final TicketService ticketService;
  private final VehicleService vehicleService;

  /**
   * Parks a vehicle in the specified garage and floor.
   *
   * @param garageName The name of the parking garage.
   * @param floorName The name of the parking floor.
   * @param licenseNumber The license number of the vehicle.
   * @param vehicleType The type of the vehicle.
   * @param isOwnerHandicapped Flag indicating whether the vehicle owner is handicapped.
   * @return The parking ticket associated with the parked vehicle.
   */
  public ParkingTicket parkVehicle(
      String garageName,
      String floorName,
      String licenseNumber,
      VehicleType vehicleType,
      boolean isOwnerHandicapped) {
    // Get or create the vehicle based on the provided details
    Vehicle vehicle =
        this.vehicleService.getOrCreateVehicle(licenseNumber, vehicleType, isOwnerHandicapped);

    // Get or create a parking ticket for the vehicle in the specified garage and floor
    return ticketService.getOrCreateParkingTicket(garageName, floorName, vehicle);
  }

  /**
   * Completes the parking process for a vehicle with the specified license number.
   *
   * @param licenseNumber The license number of the parked vehicle.
   */
  public void completeParking(String licenseNumber) {
    ticketService.completeParkingTicket(licenseNumber);
  }
}
