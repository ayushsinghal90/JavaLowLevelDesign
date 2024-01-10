package org.lowLevelDesign.parkingLot.Entities.Parking.Spots;

import org.lowLevelDesign.parkingLot.Entities.Abstracts.ParkingSpot;
import org.lowLevelDesign.parkingLot.Entities.Constants.ParkingSpotType;

public class CarSpot extends ParkingSpot {
  public CarSpot() {
    super(ParkingSpotType.CAR);
  }
}
