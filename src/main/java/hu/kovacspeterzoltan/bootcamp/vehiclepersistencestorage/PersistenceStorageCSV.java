package hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.VehicleStorageInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PersistenceStorageCSV implements VehicleStorageInterface {
    private String fileName;
    private Map<String, VehicleEntity> allVehicle = new HashMap<String, VehicleEntity>();
    public PersistenceStorageCSV() {
        this.fileName = "./vehicleStorage.csv";
    }
    @Override
    public void saveVehicle(VehicleEntity vehicle) {

        String line = vehicleEntityToString(vehicle);

        try {
            Files.writeString(Path.of(fileName), line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public VehicleEntity getVehicle(String registrationNumber) {
        loadCSV();
        return allVehicle.get(registrationNumber);
    }
    private void loadCSV() {
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader("./vehicleStorage.csv"));
            while ((line = br.readLine()) != null) {
                VehicleEntity v = stringToVehicleEntity(line);
                allVehicle.put(v.registrationNumber, v);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private VehicleEntity stringToVehicleEntity(String line) {
        String[] vehicleStr = line.split(",");
        VehicleEntity v = new VehicleEntity();
        v.registrationNumber = (Objects.equals(vehicleStr[0], "") ? null : vehicleStr[0]);
        v.vehicleRegister = (Objects.equals(vehicleStr[1], "") ? null : vehicleStr[1]);
        v.vehicle = (Objects.equals(vehicleStr[2], "") ? null : vehicleStr[2]);
        v.make = (Objects.equals(vehicleStr[3], "") ? null : vehicleStr[3]);
        v.model = (Objects.equals(vehicleStr[4], "") ? null : vehicleStr[4]);
        v.numberOfSeats = Integer.parseInt(vehicleStr[5]);
        v.vehicleType = (Objects.equals(vehicleStr[6], "") ? null : vehicleStr[6]);
        return v;
    }
    private String vehicleEntityToString(VehicleEntity v) {
        return (v.registrationNumber == null ? "" : v.registrationNumber ) +
                "," + (v.vehicleRegister == null ? "" : v.vehicleRegister ) +
                "," + (v.vehicle == null ? "" : v.vehicle ) +
                "," + (v.make == null ? "" : v.make ) +
                "," + (v.model == null ? "" : v.model ) +
                "," + v.numberOfSeats +
                "," + (v.vehicleType == null ? "" : v.vehicleType );
    }
}
