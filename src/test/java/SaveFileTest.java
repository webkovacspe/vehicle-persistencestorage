import hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage.PersistenceStorage;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.VehicleEntity;
import org.junit.jupiter.api.Test;

public class SaveFileTest {
    @Test
    void testSaveFile() {
        VehicleEntity ve = new VehicleEntity();
        ve.registrationNumber = "regNum";
        ve.vehicleRegister = "vehicleRegister";
        ve.vehicle = "vehicle";
        ve.make = "make";
        ve.model = "model";
        ve.numberOfSeats = 5;
        ve.vehicleType = "vehicleType";

        PersistenceStorage storage = new PersistenceStorage();
        storage.saveVehicle(ve);
    }
}
