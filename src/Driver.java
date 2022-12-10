import model.Entree;
import service.ReadWriteService;
import swing.Window;

import java.util.List;

public class Driver {

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.run();
    }

    private final ReadWriteService readWriteService;

    private Window ui;

    public List<Entree> entreeList;

    public Driver() {
        readWriteService = new ReadWriteService();
        ui = new Window(readWriteService);
    }

    private void run() {

    }
}
