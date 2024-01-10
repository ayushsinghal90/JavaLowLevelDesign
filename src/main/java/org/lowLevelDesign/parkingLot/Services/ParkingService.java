package org.lowLevelDesign.parkingLot.Services;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Constants.VehicleType;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingTicket;

@AllArgsConstructor
public class ParkingService {
  static final Logger LOG = LogManager.getLogger(ParkingService.class);
  private final TicketService ticketService;
  private final VehicleService vehicleService;

  public ParkingTicket parkVehicle(
      String garageName,
      String floorName,
      String licenseNumber,
      VehicleType vehicleType,
      boolean isOwnerHandicapped) {
    Vehicle vehicle =
        this.vehicleService.getOrCreateVehicle(licenseNumber, vehicleType, isOwnerHandicapped);
    return ticketService.getOrCreateParkingTicket(garageName, floorName, vehicle);
  }

  public void completeParking(String licenseNumber) {
    ticketService.completeParkingTicket(licenseNumber);
  }
}
