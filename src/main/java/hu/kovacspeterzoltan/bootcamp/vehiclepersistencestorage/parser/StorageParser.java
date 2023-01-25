package hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage.parser;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;

import java.util.*;

public class StorageParser {
    public List<String[]> vehiclesToCsv(Map<String, VehicleEntity> vehicles) {
        List<String[]> csv = new ArrayList<>();
        for (Map.Entry<String, VehicleEntity> entry: vehicles.entrySet()) {
            csv.add(vehicleEntityToString(entry.getValue()));
        }
        return csv;
    }
    public Map<String, VehicleEntity> csvToVehicles(List<String[]> csv) {
        Map<String, VehicleEntity> vehicles = new HashMap<>();
        csv.forEach(line -> {
            VehicleEntity vehicle = csvLineToVehicleEntity(line);
            vehicles.put(vehicle.registrationNumber.toUpperCase(), vehicle);
        });
        return vehicles;
    }
    public VehicleEntity csvLineToVehicleEntity(String[] vehicleColumn) {
        VehicleEntity v = new VehicleEntity();
        v.registrationNumber = parseString(vehicleColumn[0]);
        v.vehicleRegister = parseString(vehicleColumn[1]);
        v.make = parseString(vehicleColumn[2]);
        v.model = parseString(vehicleColumn[3]);
        v.numberOfSeats = Integer.parseInt(vehicleColumn[4]);
        v.vehicleType = parseString(vehicleColumn[5]);
        return v;
    }
    private String parseString(String s) {
        return (Objects.equals(s.trim(), "") ? null : s);
    }
    public String[] vehicleEntityToString(VehicleEntity v) {
        return new String[]{
            variableToString(v.registrationNumber),
            variableToString(v.vehicleRegister),
            variableToString(v.make),
            variableToString(v.model),
            Integer.toString(v.numberOfSeats),
            variableToString(v.vehicleType)
        };
    }
    private String variableToString(String v) {
        return (v == null ? "" : v.trim());
    }
}
