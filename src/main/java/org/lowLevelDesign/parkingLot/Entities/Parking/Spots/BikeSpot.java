package org.lowLevelDesign.parkingLot.Entities.Parking.Spots;

import org.lowLevelDesign.parkingLot.Entities.Abstracts.ParkingSpot;
import org.lowLevelDesign.parkingLot.Entities.Constants.ParkingSpotType;

public class BikeSpot extends ParkingSpot {
  public BikeSpot() {
    super(ParkingSpotType.BIKE);
  }
}
