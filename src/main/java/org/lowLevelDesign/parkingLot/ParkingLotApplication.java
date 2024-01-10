package org.lowLevelDesign.parkingLot;

import org.lowLevelDesign.parkingLot.Controller.GarageManagerController;
import org.lowLevelDesign.parkingLot.Controller.ParkingController;
import org.lowLevelDesign.parkingLot.Entities.Parking.ParkingGarage;
import org.lowLevelDesign.parkingLot.Services.ParkingCatalogService;
import org.lowLevelDesign.parkingLot.Services.ParkingService;
import org.lowLevelDesign.parkingLot.Services.TicketService;
import org.lowLevelDesign.parkingLot.Services.VehicleService;
import org.lowLevelDesign.parkingLot.repository.ParkingFloorRepository;
import org.lowLevelDesign.parkingLot.repository.ParkingGarageRepository;
import org.lowLevelDesign.parkingLot.repository.ParkingTicketRepository;
import org.lowLevelDesign.parkingLot.repository.VehicleRepository;

public class ParkingLotApplication {
  public static final ParkingLotApplication application = new ParkingLotApplication();
  public final GarageManagerController garageManagerController;
  public final ParkingController parkingController;

  private ParkingLotApplication() {
    ParkingFloorRepository parkingFloorRepository = new ParkingFloorRepository();
    ParkingGarageRepository parkingGarageRepository = new ParkingGarageRepository();
    ParkingTicketRepository parkingTicketRepository = new ParkingTicketRepository();
    VehicleRepository vehicleRepository = new VehicleRepository();

    ParkingCatalogService parkingCatalogService =
        new ParkingCatalogService(parkingGarageRepository, parkingFloorRepository);
    VehicleService vehicleService = new VehicleService(vehicleRepository);
    TicketService ticketService =
        new TicketService(vehicleRepository, parkingTicketRepository, parkingCatalogService);
    ParkingService parkingService = new ParkingService(ticketService, vehicleService);

    garageManagerController = new GarageManagerController(parkingCatalogService);
    parkingController = new ParkingController(parkingService);
    setup();
  }

  private void setup() {
    garageManagerController.createParkingGarage();
    ParkingGarage parkingGarage = garageManagerController.createParkingGarage();
    garageManagerController.addParkingFloorAndParkingSpot(parkingGarage.getName());
  }
}
