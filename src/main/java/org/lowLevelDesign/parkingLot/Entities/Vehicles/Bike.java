package org.lowLevelDesign.parkingLot.Entities.Vehicles;

import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Constants.VehicleType;

public class Bike extends Vehicle {
  public Bike(String licenseNumber) {
    super(licenseNumber, VehicleType.BIKE);
  }
}
