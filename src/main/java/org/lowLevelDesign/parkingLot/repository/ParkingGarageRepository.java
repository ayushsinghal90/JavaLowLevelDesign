package org.lowLevelDesign.parkingLot.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingFloor;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingGarage;

public class ParkingGarageRepository {
  private static final Map<String, ParkingGarage> parkingGarageMap = new HashMap<>();

  public ParkingFloor addParkingFloor(String garageName, String floorName) {
    ParkingFloor parkingFloor = createNewParkingFloor(floorName);
    getParkingGarage(garageName).getParkingFloors().add(parkingFloor);
    return parkingFloor;
  }

  public ParkingFloor getParkingFloor(String garageName, String floorName) {
    return getParkingGarage(garageName)
        .getParkingFloors()
        .stream()
        .filter(parkingFloor -> parkingFloor.getName().equals(floorName))
        .findFirst()
        .orElse(null);
  }

  public ParkingGarage getParkingGarage(String garageName) {
    return parkingGarageMap.get(garageName);
  }

  public ParkingGarage addParkingGarage(String garageName) {
    ParkingGarage parkingGarage = createNewParkingGarage(garageName);
    parkingGarageMap.put(garageName, parkingGarage);
    return parkingGarage;
  }

  private ParkingGarage createNewParkingGarage(String garageName) {
    return new ParkingGarage(garageName, new ArrayList<>());
  }

  public ParkingFloor createNewParkingFloor(String floorName) {
    return new ParkingFloor(floorName, new ArrayList<>());
  }
}
