package hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.storage.VehicleRegisterStorageInterface;

import java.io.*;
import java.util.*;

public class PersistenceStorageCSV implements VehicleRegisterStorageInterface {
    private String fileName;
    private Map<String, VehicleEntity> allVehicle;
    public PersistenceStorageCSV() {
        this.fileName = "./vehicleStorage.csv";
        createFileIfNotExists();
    }

    private void createFileIfNotExists() {
        try {
            File f = new File(fileName);
            if (!f.exists() || f.isDirectory()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveVehicle(VehicleEntity vehicle) {
        loadCSV();
        if (allVehicle.get(vehicle.registrationNumber) != null) {
            allVehicle.replace(vehicle.registrationNumber, vehicle);
        } else {
            allVehicle.put(vehicle.registrationNumber, vehicle);
        }
        saveCSV();
    }
    private void saveCSV() {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(fileName, false);
            bw = new BufferedWriter(fw);
            for (Map.Entry<String, VehicleEntity> entry: allVehicle.entrySet()) {
                bw.write(vehicleEntityToString(entry.getValue()));
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fw != null) {
                try {
                    assert bw != null;
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public VehicleEntity findVehicle(String registrationNumber) {
        loadCSV();
        return allVehicle.get(registrationNumber.toUpperCase());
    }
    private void loadCSV() {
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            allVehicle = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null) {
                VehicleEntity v = stringToVehicleEntity(line);
                allVehicle.put(v.registrationNumber.toUpperCase(), v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    assert br != null;
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private VehicleEntity stringToVehicleEntity(String line) {
        String[] vehicleStr = line.split(",");
        VehicleEntity v = new VehicleEntity();
        v.registrationNumber = (Objects.equals(vehicleStr[0], "") ? null : vehicleStr[0]);
        v.vehicleRegister = (Objects.equals(vehicleStr[1], "") ? null : vehicleStr[1]);
        v.make = (Objects.equals(vehicleStr[2], "") ? null : vehicleStr[2]);
        v.model = (Objects.equals(vehicleStr[3], "") ? null : vehicleStr[3]);
        v.numberOfSeats = Integer.parseInt(vehicleStr[4]);
        v.vehicleType = (Objects.equals(vehicleStr[5], "") ? null : vehicleStr[5]);
        return v;
    }
    private String vehicleEntityToString(VehicleEntity v) {
        return (v.registrationNumber == null ? "" : v.registrationNumber ) +
                "," + (v.vehicleRegister == null ? "" : v.vehicleRegister ) +
                "," + (v.make == null ? "" : v.make ) +
                "," + (v.model == null ? "" : v.model ) +
                "," + v.numberOfSeats +
                "," + (v.vehicleType == null ? "" : v.vehicleType );
    }
}
