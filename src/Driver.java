import service.ReadWriteService;

import java.io.IOException;

public class Driver {

    public static ReadWriteService readWriteService;


    public static void main(String[] args) {
        readWriteService = new ReadWriteService();
        try {
            readWriteService.writeToFile("foo.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
