import hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage.PersistenceStorageCSV;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.VehicleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SaveFileTest {
    @Test
    void testSaveFile() {
        VehicleEntity ve = getVehicleEntity();

        PersistenceStorageCSV storage = new PersistenceStorageCSV();
        storage.saveVehicle(ve);
    }
    @Test
    void testGetNullIfNotExists() {
        VehicleEntity ve = getVehicleEntity();
        PersistenceStorageCSV storage = new PersistenceStorageCSV();
        storage.saveVehicle(ve);
        Assertions.assertNull(storage.getVehicle("aaa"));
    }
    @Test
    void testGetVehicleIfExists() {
        VehicleEntity ve = getVehicleEntity();
        PersistenceStorageCSV storage = new PersistenceStorageCSV();
        storage.saveVehicle(ve);
        Assertions.assertEquals(ve.registrationNumber, storage.getVehicle(ve.registrationNumber).registrationNumber);
    }

    private static VehicleEntity getVehicleEntity() {
        VehicleEntity ve = new VehicleEntity();
        ve.registrationNumber = "regNum";
        ve.vehicleRegister = "vehicleRegister";
        ve.vehicle = "vehicle";
        ve.make = "make";
        ve.model = "model";
        ve.numberOfSeats = 5;
        ve.vehicleType = "vehicleType";
        return ve;
    }
}
