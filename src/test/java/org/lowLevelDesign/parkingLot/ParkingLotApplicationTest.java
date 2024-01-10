package org.lowLevelDesign.parkingLot;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lowLevelDesign.parkingLot.Controller.ParkingController;
import org.lowLevelDesign.parkingLot.Entities.Constants.VehicleType;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingTicket;

public class ParkingLotApplicationTest {
  private ParkingLotApplication parkingLotApplication;
  private ParkingController parkingController;

  @BeforeEach
  public void setUp() {
    // Initialize or mock the ParkingController before each test
    parkingLotApplication = ParkingLotApplication.application;
    parkingController = parkingLotApplication.parkingController;
  }

  @Test
  public void testParkH() {
    ParkingTicket parkingTicket =
        parkingController.parkVehicle(
            "parkingGarage1", "parkingFloor1", UUID.randomUUID().toString(), null, true);
    assertTrue(parkingTicket.getParkingSpot().isOccupied());

    parkingController.completeParking(parkingTicket.getVehicle().getLicenseNumber());
    assertFalse(parkingTicket.getParkingSpot().isOccupied());
  }

  @Test
  public void testParkCar() {
    ParkingTicket parkingTicket =
        parkingController.parkVehicle(
            "parkingGarage1",
            "parkingFloor1",
            UUID.randomUUID().toString(),
            VehicleType.CAR,
            false);
    assertTrue(parkingTicket.getParkingSpot().isOccupied());

    parkingController.completeParking(parkingTicket.getVehicle().getLicenseNumber());
    assertFalse(parkingTicket.getParkingSpot().isOccupied());
  }

  @Test
  public void testParkBike() {
    ParkingTicket parkingTicket =
        parkingController.parkVehicle(
            "parkingGarage1",
            "parkingFloor1",
            UUID.randomUUID().toString(),
            VehicleType.BIKE,
            false);
    assertTrue(parkingTicket.getParkingSpot().isOccupied());

    parkingController.completeParking(parkingTicket.getVehicle().getLicenseNumber());
    assertFalse(parkingTicket.getParkingSpot().isOccupied());
  }

  @Test
  public void testParkMiniVan() {
    ParkingTicket parkingTicket =
        parkingController.parkVehicle(
            "parkingGarage1",
            "parkingFloor1",
            UUID.randomUUID().toString(),
            VehicleType.MINI_VAN,
            false);
    assertTrue(parkingTicket.getParkingSpot().isOccupied());

    parkingController.completeParking(parkingTicket.getVehicle().getLicenseNumber());
    assertFalse(parkingTicket.getParkingSpot().isOccupied());
  }
}
