package org.lowLevelDesign.parkingLot.Entities.Vehicles;

import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Constants.VehicleType;

public class Car extends Vehicle {
  public Car(String licenseNumber) {
    super(licenseNumber, VehicleType.CAR);
  }
}
