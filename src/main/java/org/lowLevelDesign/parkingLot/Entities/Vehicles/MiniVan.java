package org.lowLevelDesign.parkingLot.Entities.Vehicles;

import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Constants.VehicleType;

public class MiniVan extends Vehicle {
  public MiniVan(String licenseNumber) {
    super(licenseNumber, VehicleType.MINI_VAN);
  }
}
