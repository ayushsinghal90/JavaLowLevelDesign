package org.lowLevelDesign.parkingLot.repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.ParkingSpot;
import org.lowLevelDesign.parkingLot.Entities.Abstracts.Vehicle;
import org.lowLevelDesign.parkingLot.Entities.Constants.ParkingTicketStatus;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingTicket;

public class ParkingTicketRepository {
  private static final Map<String, ParkingTicket> parkingTicketMap = new HashMap<>();
  private static final Map<String, String> vehicleTicketMap = new HashMap<>();
  private static final Map<String, String> parkingSpotTicketMap = new HashMap<>();

  public void completeParkingTicket(String ticketNumber) {
    Optional.of(parkingTicketMap.get(ticketNumber))
        .ifPresentOrElse(this::parkingComplete, RuntimeException::new);
  }

  public ParkingTicket assignParkingTicket(ParkingSpot parkingSpot, Vehicle vehicle) {
    ParkingTicket parkingTicket = createNewTicket(parkingSpot, vehicle);
    parkingTicketMap.put(parkingTicket.getTicketNumber(), parkingTicket);
    vehicleTicketMap.put(vehicle.getLicenseNumber(), parkingTicket.getTicketNumber());
    parkingSpotTicketMap.put(parkingSpot.getNumber(), parkingTicket.getTicketNumber());

    parkingSpot.setOccupied(true);
    return parkingTicket;
  }

  public ParkingTicket getParkingTicket(String ticketNumber) {
    return parkingTicketMap.get(ticketNumber);
  }

  public ParkingTicket getTicketByLicenseNumber(String licenseNumber) {
    return Optional.ofNullable(vehicleTicketMap.get(licenseNumber))
        .map(parkingTicketMap::get)
        .orElse(null);
  }

  public ParkingTicket getTicketByParkingSpot(String spotNumber) {
    return Optional.ofNullable(parkingSpotTicketMap.get(spotNumber))
        .map(parkingTicketMap::get)
        .orElse(null);
  }

  private ParkingTicket createNewTicket(ParkingSpot parkingSpot, Vehicle vehicle) {
    return ParkingTicket.builder()
        .parkingSpot(parkingSpot)
        .parkingTicketStatus(ParkingTicketStatus.ACTIVE)
        .vehicle(vehicle)
        .ticketNumber(UUID.randomUUID().toString())
        .issuedAt(Instant.now().getEpochSecond())
        .build();
  }

  private double getCharges(ParkingTicket parkingTicket) {
    long timeSpentInHours = (Instant.now().getEpochSecond() - parkingTicket.getIssuedAt()) / 3600;
    return timeSpentInHours * 10;
  }

  private void parkingComplete(ParkingTicket parkingTicket) {
    parkingTicket.setCharges(getCharges(parkingTicket));
    parkingTicket.setVacatedAt(Instant.now().getEpochSecond());
    parkingTicket.setParkingTicketStatus(ParkingTicketStatus.PAID);

    vehicleTicketMap.remove(parkingTicket.getVehicle().getLicenseNumber());
    parkingSpotTicketMap.remove(parkingTicket.getParkingSpot().getNumber());
  }
}
