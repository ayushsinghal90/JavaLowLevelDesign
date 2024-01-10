package org.lowLevelDesign.parkingLot.Entities.Parking.Spots;

import org.lowLevelDesign.parkingLot.Entities.Abstracts.ParkingSpot;
import org.lowLevelDesign.parkingLot.Entities.Constants.ParkingSpotType;

public class HandicapSpot extends ParkingSpot {
  public HandicapSpot() {
    super(ParkingSpotType.HANDICAPPED);
  }
}
