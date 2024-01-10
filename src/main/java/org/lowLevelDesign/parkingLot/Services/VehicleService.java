package org.lowLevelDesign.parkingLot.Services;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Constants.VehicleType;
import org.lowLevelDesign.parkingLot.repository.VehicleRepository;

@AllArgsConstructor
public class VehicleService {
  static final Logger LOG = LogManager.getLogger(VehicleService.class);

  private final VehicleRepository vehicleRepository;

  public Vehicle getOrCreateVehicle(
      String licenseNumber, VehicleType vehicleType, boolean isOwnerHandicapped) {
    return vehicleRepository.getOrCreateVehicle(licenseNumber, vehicleType, isOwnerHandicapped);
  }

  public Vehicle getVehicle(String licenseNumber) {
    return vehicleRepository.getVehicle(licenseNumber);
  }
}
