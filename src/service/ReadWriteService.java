package service;

import exception.WordAlreadyExistsException;
import model.Entree;
import model.LexiNode;

import java.io.*;
import java.util.ArrayList;
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

    public List<LexiNode> readFromFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<LexiNode> lexiNodes = new ArrayList<>();

        for(String line : bufferedReader.lines().toList()) {
            String[] entry = line.split("&");
            entry[0] = entry[0].replace("\uFEFF", "");

            LexiNode nodeToAdd = new LexiNode(entry[0].replace(" ", ""), entry[1]);
            boolean entryAdded = false;

            for (LexiNode currentNode: lexiNodes) {
                if (nodeToAdd.getWord().charAt(0) == currentNode.getWord().charAt(0)){
                    try {
                        currentNode.addNode(nodeToAdd);
                    } catch (WordAlreadyExistsException ignored) { }
                    entryAdded = true;
                    break;
                }
            }

            if (!entryAdded) {
                LexiNode initialNode = new LexiNode(nodeToAdd.getWord().substring(0, 1));
                try {
                    initialNode.addNode(nodeToAdd);
                } catch (WordAlreadyExistsException ignored) { }
                lexiNodes.add(initialNode);
            }
        }

        bufferedReader.close();
        fileReader.close();

        return lexiNodes;
    }

}
