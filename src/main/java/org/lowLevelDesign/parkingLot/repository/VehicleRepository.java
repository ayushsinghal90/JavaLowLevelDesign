package org.lowLevelDesign.parkingLot.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Constants.VehicleType;
import org.lowLevelDesign.parkingLot.Factories.VehicleFactory;

public class VehicleRepository {
  private static final Map<String, Vehicle> vehicleLicenseMap = new HashMap<>();

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

  public Vehicle getVehicle(String licenseNumber) {
    return vehicleLicenseMap.get(licenseNumber);
  }
}
