package org.lowLevelDesign.parkingLot.Factories;

import org.lowLevelDesign.parkingLot.Entities.Abstracts.ParkingSpot;
import org.lowLevelDesign.parkingLot.Entities.Constants.ParkingSpotType;
import org.lowLevelDesign.parkingLot.Entities.Parking.Spots.BikeSpot;
import org.lowLevelDesign.parkingLot.Entities.Parking.Spots.CarSpot;
import org.lowLevelDesign.parkingLot.Entities.Parking.Spots.ElectricCarSpot;
import org.lowLevelDesign.parkingLot.Entities.Parking.Spots.HandicapSpot;
import org.lowLevelDesign.parkingLot.Entities.Parking.Spots.MiniVanSpot;

public final class ParkingSpotFactory {

  private ParkingSpotFactory() {}

  public static ParkingSpot createParkingSpot(ParkingSpotType parkingSpotType) {
    switch (parkingSpotType) {
      case HANDICAPPED:
        return new HandicapSpot();
      case CAR:
        return new CarSpot();
      case BIKE:
        return new BikeSpot();
      case MINI_VAN:
        return new MiniVanSpot();
      case ELECTRIC_CAR:
        return new ElectricCarSpot();
      default:
        throw new IllegalArgumentException("Invalid parking spot type: " + parkingSpotType);
    }
  }
}
