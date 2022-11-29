package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReadWriteService {

    public void writeToFile(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));

        printWriter.write("somthing somthi");
        printWriter.close();
    }


}
