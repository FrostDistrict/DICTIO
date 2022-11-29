import model.Entree;
import service.ReadWriteService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Driver {

    public static ReadWriteService readWriteService;

    public static List<Entree> entreeList;


    public static void main(String[] args) {
        readWriteService = new ReadWriteService();
        entreeList = new ArrayList<>();

        Entree e1 = new Entree("Abeille", "Une bibitte que fait du miel.");
        Entree e2 = new Entree("Pomme", "Genre de fruit qui pousse dans un arbre.");
        Entree e3 = new Entree("Carrot", "Legume qui pousse dans terre");
        Entree e4 = new Entree("Abeille2", "testestestestest");
        Entree e5 = new Entree("Allo", "Salutation.");

        entreeList.addAll(Arrays.asList(e1, e2, e3, e4, e5));

        try {
            readWriteService.writeToFile("foo.txt", entreeList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Entree> entreesLecture;
        try {
            entreesLecture = readWriteService.readFromFile("DictioExemple.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Entree entree: entreesLecture) {
            System.out.println(entree.toString());
        }
    }
}
