package hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.VehicleStorageInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersistenceStorage implements VehicleStorageInterface {
    private Path fileName = Path.of("./vehicleStorage.csv");
    @Override
    public void saveVehicle(VehicleEntity vehicle) {

        String line = vehicleEntityToString(vehicle);

        try {
            Files.writeString(fileName, line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public VehicleEntity getVehicle(String registrationNumber) {
        loadCSV();
        List<VehicleEntity> allVehicle = new ArrayList<VehicleEntity>();

        return null;
    }

    private void loadCSV() {
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader("./vehicleStorage.csv"));
            while ((line = br.readLine()) != null) {
                String[] vehicleStr = line.split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
