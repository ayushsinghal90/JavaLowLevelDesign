package org.lowLevelDesign.parkingLot.Entities.Abstracts;

import lombok.Getter;
import lombok.Setter;
import org.lowLevelDesign.parkingLot.Entities.Constants.VehicleType;

@Getter
@Setter
public abstract class Vehicle {
  String licenseNumber;
  VehicleType vehicleType;
  boolean isOwnerHandicapped;

  public Vehicle(String licenseNumber, VehicleType vehicleType) {
    this.vehicleType = vehicleType;
    this.licenseNumber = licenseNumber;
  }

  public Vehicle(String licenseNumber, VehicleType vehicleType, boolean isOwnerHandicapped) {
    this.vehicleType = vehicleType;
    this.licenseNumber = licenseNumber;
    this.isOwnerHandicapped = isOwnerHandicapped;
  }
}
