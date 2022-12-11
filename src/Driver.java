import service.ReadWriteService;
import swing.Window;

public class Driver {

    public static void main(String[] args) {
        Driver driver = new Driver();
    }

    private Window ui;

    public Driver() {
        ui = new Window(new ReadWriteService());
    }
}
