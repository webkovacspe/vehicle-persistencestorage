package hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage;

import hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage.csv.CSVManager;
import hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage.parser.StorageParser;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.storage.VehicleRegisterStoragePlugInInterface;

import java.util.List;
import java.util.Map;

public class VehicleRegisterPersistenceStorageCSVPlugIn implements VehicleRegisterStoragePlugInInterface {
    private Map<String, VehicleEntity> vehicles;
    private final StorageParser parser;
    private final CSVManager csvManager;

    public VehicleRegisterPersistenceStorageCSVPlugIn() {
        parser = new StorageParser();
        csvManager = new CSVManager("./vehicleStorage.csv");
        csvManager.createFileIfNotExists();
    }
    @Override
    public VehicleEntity findVehiclePlugIn(String registrationNumber) {
        loadCSV();
        return vehicles.get(registrationNumber.toUpperCase());
    }
    private void loadCSV() {
        List<String[]> csv = csvManager.load();
        vehicles = parser.csvToVehicles(csv);
    }
}