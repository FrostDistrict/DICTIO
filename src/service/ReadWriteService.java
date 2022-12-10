package service;

import model.Entree;

import java.io.*;
import java.util.List;

public class ReadWriteService {

    public void writeToFile(String filePath, List<Entree> entreeList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));

        for(Entree entree: entreeList) {
            printWriter.write(entree.toString());
            printWriter.println();
        }

        printWriter.close();
        fileWriter.close();
    }

    public List<Entree> readFromFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<Entree> entrees = bufferedReader.lines().map(line -> {
            String[] entree = line.split("&");
            return new Entree(entree[0], entree[1]);
        }).toList();

        bufferedReader.close();
        fileReader.close();

        return entrees;
    }

}
