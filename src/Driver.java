import service.ReadWriteService;
import swing.Window;

/**
 * Classe contenant le main qui permet de run l'application graphique
 */
public class Driver {
    /**
     * main instanciant un nouveau driver
     * @param args
     */
    public static void main(String[] args) {
        Driver driver = new Driver();
    }

    private Window ui;

    /**
     * méthode instanciant une nouvelle fenêtre graphique
     */
    public Driver() {
        ui = new Window(new ReadWriteService());
    }
}
