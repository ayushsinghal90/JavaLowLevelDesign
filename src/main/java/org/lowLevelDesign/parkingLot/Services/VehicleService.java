package org.lowLevelDesign.parkingLot.Services;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Constants.VehicleType;
import org.lowLevelDesign.parkingLot.repository.VehicleRepository;

/**
 * Service class for managing vehicles.
 *
 * @author ayushsinghal90
 */
@AllArgsConstructor
public class VehicleService {

  // Logger for logging service-related events
  private static final Logger LOG = LogManager.getLogger(VehicleService.class);

  // Repository for handling vehicles
  private final VehicleRepository vehicleRepository;

  /**
   * Gets or creates a vehicle based on the provided details.
   *
   * @param licenseNumber The license number of the vehicle.
   * @param vehicleType The type of the vehicle.
   * @param isOwnerHandicapped Flag indicating whether the vehicle owner is handicapped.
   * @return The vehicle associated with the provided details.
   */
  public Vehicle getOrCreateVehicle(
      String licenseNumber, VehicleType vehicleType, boolean isOwnerHandicapped) {
    return vehicleRepository.getOrCreateVehicle(licenseNumber, vehicleType, isOwnerHandicapped);
  }

  /**
   * Gets a vehicle based on the provided license number.
   *
   * @param licenseNumber The license number of the vehicle to retrieve.
   * @return The vehicle associated with the provided license number.
   */
  public Vehicle getVehicle(String licenseNumber) {
    return vehicleRepository.getVehicle(licenseNumber);
  }
}
