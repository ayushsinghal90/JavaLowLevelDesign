package org.lowLevelDesign.parkingLot.Controller;

import lombok.AllArgsConstructor;
import org.lowLevelDesign.parkingLot.Entities.Constants.VehicleType;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingTicket;
import org.lowLevelDesign.parkingLot.Services.ParkingService;

@AllArgsConstructor
public class ParkingController {
  private final ParkingService parkingService;

  public ParkingTicket parkVehicle(
      String garageName,
      String floorName,
      String licenseNumber,
      VehicleType vehicleType,
      boolean isOwnerHandicapped) {
    return parkingService.parkVehicle(
        garageName, floorName, licenseNumber, vehicleType, isOwnerHandicapped);
  }

  public void completeParking(String licenseNumber) {
    parkingService.completeParking(licenseNumber);
  }
}
