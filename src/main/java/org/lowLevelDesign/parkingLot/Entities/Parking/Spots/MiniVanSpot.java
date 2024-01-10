package org.lowLevelDesign.parkingLot.Entities.Parking.Spots;

import org.lowLevelDesign.parkingLot.Entities.Abstracts.ParkingSpot;
import org.lowLevelDesign.parkingLot.Entities.Constants.ParkingSpotType;

public class MiniVanSpot extends ParkingSpot {
  public MiniVanSpot() {
    super(ParkingSpotType.MINI_VAN);
  }
}
