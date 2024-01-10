package org.lowLevelDesign.parkingLot.Entities.Vehicles;

import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Constants.VehicleType;

public class ElectricCar extends Vehicle {
  public ElectricCar(String licenseNumber) {
    super(licenseNumber, VehicleType.ELECTRIC_CAR);
  }
}
