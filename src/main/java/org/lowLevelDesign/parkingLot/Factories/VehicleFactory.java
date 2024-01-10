package org.lowLevelDesign.parkingLot.Factories;

import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Constants.VehicleType;
import org.lowLevelDesign.parkingLot.Entities.Vehicles.Bike;
import org.lowLevelDesign.parkingLot.Entities.Vehicles.Car;
import org.lowLevelDesign.parkingLot.Entities.Vehicles.ElectricCar;
import org.lowLevelDesign.parkingLot.Entities.Vehicles.Handicap;
import org.lowLevelDesign.parkingLot.Entities.Vehicles.MiniVan;

public final class VehicleFactory {

  private VehicleFactory() {}

  public static Vehicle createVehicle(
      VehicleType vehicleType, String licenseNumber, boolean isOwnerHandicapped) {
    if (isOwnerHandicapped) {
      return new Handicap(licenseNumber);
    }

    switch (vehicleType) {
      case CAR:
        return new Car(licenseNumber);
      case BIKE:
        return new Bike(licenseNumber);
      case MINI_VAN:
        return new MiniVan(licenseNumber);
      case ELECTRIC_CAR:
        return new ElectricCar(licenseNumber);
      default:
        throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
    }
  }
}
