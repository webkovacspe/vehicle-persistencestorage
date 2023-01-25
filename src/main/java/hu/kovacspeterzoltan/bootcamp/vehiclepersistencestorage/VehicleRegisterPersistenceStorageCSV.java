package hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage;

import hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage.csv.CSVManager;
import hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage.parser.StorageParser;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.storage.VehicleRegisterStorageInterface;

import java.util.*;

public class VehicleRegisterPersistenceStorageCSV implements VehicleRegisterStorageInterface {
    private Map<String, VehicleEntity> vehicles;
    private final StorageParser parser;
    private final CSVManager csvManager;

    public VehicleRegisterPersistenceStorageCSV() {
        parser = new StorageParser();
        csvManager = new CSVManager("./vehicleStorage.csv");
        csvManager.createFileIfNotExists();
    }

    @Override
    public void saveVehicle(VehicleEntity vehicle) {
        mergeNewVehicle(vehicle);
        saveCSV();
    }
    private void mergeNewVehicle(VehicleEntity vehicle) {
        loadCSV();
        if (vehicles.get(vehicle.registrationNumber) != null) {
            vehicles.replace(vehicle.registrationNumber, vehicle);
        } else {
            vehicles.put(vehicle.registrationNumber, vehicle);
        }
    }
    private void saveCSV() {
        List<String[]> csv = parser.vehiclesToCsv(vehicles);
        csvManager.save(csv);
    }
    @Override
    public VehicleEntity findVehicle(String registrationNumber) {
        loadCSV();
        return vehicles.get(registrationNumber.toUpperCase());
    }
    private void loadCSV() {
        List<String[]> csv = csvManager.load();
        vehicles = parser.csvToVehicles(csv);
    }
}