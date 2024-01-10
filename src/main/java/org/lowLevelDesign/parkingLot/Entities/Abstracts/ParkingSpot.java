package org.lowLevelDesign.parkingLot.Entities.Abstracts;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.lowLevelDesign.parkingLot.Entities.Constants.ParkingSpotType;

@Getter
@Setter
public abstract class ParkingSpot {
  String number;
  ParkingSpotType parkingSpotType;
  boolean occupied;

  public ParkingSpot(ParkingSpotType parkingSpotType) {
    this.parkingSpotType = parkingSpotType;
    this.number = UUID.randomUUID().toString();
  }
}
