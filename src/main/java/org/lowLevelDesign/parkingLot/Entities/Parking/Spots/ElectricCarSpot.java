package org.lowLevelDesign.parkingLot.Entities.Parking.Spots;

import org.lowLevelDesign.parkingLot.Entities.Abstracts.ParkingSpot;
import org.lowLevelDesign.parkingLot.Entities.Constants.ParkingSpotType;

public class ElectricCarSpot extends ParkingSpot {
  public ElectricCarSpot() {
    super(ParkingSpotType.ELECTRIC_CAR);
  }
}
