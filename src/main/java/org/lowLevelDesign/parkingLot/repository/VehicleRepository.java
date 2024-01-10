package org.lowLevelDesign.parkingLot.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Constants.VehicleType;
import org.lowLevelDesign.parkingLot.Factories.VehicleFactory;

/**
 * Repository class for managing vehicles and associated mappings.
 *
 * @author ayushsinghal90
 */
public class VehicleRepository {
  // Map to store vehicles by license number
  private static final Map<String, Vehicle> vehicleLicenseMap = new HashMap<>();

  /**
   * Retrieves an existing vehicle with the given license number or creates a new one if not found.
   *
   * @param licenseNumber The license number of the vehicle.
   * @param vehicleType The type of the vehicle.
   * @param isOwnerHandicapped A boolean indicating whether the owner is handicapped.
   * @return The existing or newly created vehicle.
   */
  public Vehicle getOrCreateVehicle(
      String licenseNumber, VehicleType vehicleType, boolean isOwnerHandicapped) {
    return Optional.ofNullable(getVehicle(licenseNumber))
        .orElseGet(
            () -> {
              Vehicle vehicle =
                  VehicleFactory.createVehicle(vehicleType, licenseNumber, isOwnerHandicapped);
              vehicleLicenseMap.put(licenseNumber, vehicle);
              return vehicle;
            });
  }

  /**
   * Retrieves a vehicle with the given license number.
   *
   * @param licenseNumber The license number of the vehicle.
   * @return The vehicle with the specified license number, or null if not found.
   */
  public Vehicle getVehicle(String licenseNumber) {
    return vehicleLicenseMap.get(licenseNumber);
  }
}
