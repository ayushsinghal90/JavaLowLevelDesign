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

/**
 * Main application class for the Parking Lot system.
 *
 * @author ayushsinghal90
 */
public class ParkingLotApplication {

  // Singleton instance of the application
  public static final ParkingLotApplication application = new ParkingLotApplication();

  // Controllers for managing parking garage and parking operations
  public final GarageManagerController garageManagerController;
  public final ParkingController parkingController;

  /** Private constructor to enforce singleton pattern and initialize controllers and services. */
  private ParkingLotApplication() {
    // Initialize repositories
    ParkingFloorRepository parkingFloorRepository = new ParkingFloorRepository();
    ParkingGarageRepository parkingGarageRepository = new ParkingGarageRepository();
    ParkingTicketRepository parkingTicketRepository = new ParkingTicketRepository();
    VehicleRepository vehicleRepository = new VehicleRepository();

    // Initialize services
    ParkingCatalogService parkingCatalogService =
        new ParkingCatalogService(parkingGarageRepository, parkingFloorRepository);
    VehicleService vehicleService = new VehicleService(vehicleRepository);
    TicketService ticketService =
        new TicketService(vehicleRepository, parkingTicketRepository, parkingCatalogService);
    ParkingService parkingService = new ParkingService(ticketService, vehicleService);

    // Initialize controllers
    garageManagerController = new GarageManagerController(parkingCatalogService);
    parkingController = new ParkingController(parkingService);

    // Setup initial parking garage and floor
    setup();
  }

  /**
   * Performs the initial setup by creating a parking garage and adding a parking floor with spots.
   */
  private void setup() {
    garageManagerController.createParkingGarage();
    ParkingGarage parkingGarage = garageManagerController.createParkingGarage();
    garageManagerController.addParkingFloorAndParkingSpot(parkingGarage.getName());
  }
}
