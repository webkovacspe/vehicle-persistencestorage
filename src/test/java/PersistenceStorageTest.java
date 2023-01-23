import hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage.PersistenceStorageCSV;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

class PersistenceStorageTest {
    private PersistenceStorageCSV storage;
    @BeforeEach
    void setUp() {
        storage = new PersistenceStorageCSV();
        storage.saveVehicle(getValidVehicleEntity());
    }
    @Test
    void testFileExists() {
        File f = new File("./vehicleStorage.csv");
        Assertions.assertTrue(f.exists() && !f.isDirectory());
    }
    @Test
    void testGetNullIfNotExists() {
        Assertions.assertNull(storage.findVehicle("aaa"));
    }
    @Test
    void testGetVehicleIfExists() {
        VehicleEntity ve = getValidVehicleEntity();
        Assertions.assertEquals(ve.registrationNumber, storage.findVehicle(ve.registrationNumber).registrationNumber);
    }
    @Test
    void testSaveSomeVehicleAndAllExists() {
        saveMoreVehicle();
        VehicleEntity o1 = getVehicleEntityO1();
        Assertions.assertEquals(o1.registrationNumber, storage.findVehicle(o1.registrationNumber).registrationNumber);
        VehicleEntity l3e = getVehicleEntityL3e();
        Assertions.assertEquals(l3e.registrationNumber, storage.findVehicle(l3e.registrationNumber).registrationNumber);
    }
    @Test
    void testChangeVehicleRegister() {
        saveMoreVehicle();
        VehicleEntity ve = getValidVehicleEntity();
        ve.vehicleRegister = "Barack";
        storage.saveVehicle(ve);
        Assertions.assertEquals(ve.vehicleRegister, storage.findVehicle(ve.registrationNumber).vehicleRegister);
    }
    private void saveMoreVehicle() {
        VehicleEntity o1 = getVehicleEntityO1();
        storage.saveVehicle(o1);
        VehicleEntity l3e = getVehicleEntityL3e();
        storage.saveVehicle(l3e);
        VehicleEntity n1 = getVehicleEntityN1();
        storage.saveVehicle(n1);
    }

    private static VehicleEntity getValidVehicleEntity() {
        VehicleEntity ve = new VehicleEntity();
        ve.registrationNumber = "AA:BB-123";
        ve.vehicleRegister = "Alma";
        ve.make = "Opel";
        ve.model = "Astra";
        ve.numberOfSeats = 5;
        ve.vehicleType = "m1";
        return ve;
    }
    private static VehicleEntity getVehicleEntityO1() {
        VehicleEntity ve = new VehicleEntity();
        ve.registrationNumber = "BB:CC-456";
        ve.vehicleRegister = "Körte";
        ve.make = "Ford";
        ve.model = "Focus";
        ve.numberOfSeats = 5;
        ve.vehicleType = "o1";
        return ve;
    }
    private static VehicleEntity getVehicleEntityL3e() {
        VehicleEntity ve = new VehicleEntity();
        ve.registrationNumber = "CC:DD-789";
        ve.vehicleRegister = "Kiss János";
        ve.make = "Toyota";
        ve.model = "Corolla";
        ve.numberOfSeats = 5;
        ve.vehicleType = "L3e";
        return ve;
    }
    private static VehicleEntity getVehicleEntityN1() {
        VehicleEntity ve = new VehicleEntity();
        ve.registrationNumber = "dd:ee-321";
        ve.vehicleRegister = "Kiss János";
        ve.make = "Ferrari";
        ve.model = "California";
        ve.numberOfSeats = 2;
        ve.vehicleType = "n1";
        return ve;
    }
}