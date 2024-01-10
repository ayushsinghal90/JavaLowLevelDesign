package org.lowLevelDesign.parkingLot.Entities.Parking;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParkingGarage {
  @Getter String name;
  List<ParkingFloor> parkingFloors;
}
