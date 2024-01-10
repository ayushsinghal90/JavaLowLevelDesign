package org.lowLevelDesign.parkingLot.Entities.Parking;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.ParkingSpot;

@Getter
@AllArgsConstructor
public class ParkingFloor {
  private final String name;
  private final List<ParkingSpot> parkingSpots;
}
