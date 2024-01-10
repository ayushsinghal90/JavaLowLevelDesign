package org.lowLevelDesign.parkingLot.Controller;

import lombok.AllArgsConstructor;
import org.lowLevelDesign.parkingLot.Entities.Constants.VehicleType;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingTicket;
import org.lowLevelDesign.parkingLot.Services.ParkingService;

/**
 * Controller class for parking operations.
 *
 * @author ayushsinghal90
 */
@AllArgsConstructor
public class ParkingController {

  // Service for handling parking operations
  private final ParkingService parkingService;

  /**
   * Parks a vehicle in the specified garage and floor.
   *
   * @param garageName The name of the garage.
   * @param floorName The name of the floor in the garage.
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
    return parkingService.parkVehicle(
        garageName, floorName, licenseNumber, vehicleType, isOwnerHandicapped);
  }

  /**
   * Completes the parking process for a vehicle with the specified license number.
   *
   * @param licenseNumber The license number of the vehicle for which parking is completed.
   */
  public void completeParking(String licenseNumber) {
    parkingService.completeParking(licenseNumber);
  }
}
