package service;

import exception.WordAlreadyExistsException;
import model.LexiNode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * permet de lire et d'écrire dans le fichier .txt
 */
public class ReadWriteService {

    /**
     * Méthode permettant d'écrire dans le fichier .txt
     * @param file nom du fichier
     * @param nodeList liste de node de la classe LexiNode
     * @throws IOException exception si les paramètre sont null
     */
    public void writeToFile(File file, List<LexiNode> nodeList) throws IOException {
        if (!file.getName().contains(".txt")){
            file = new File(file.getParent(), file.getName() + ".txt");
        }

        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));

        List<LexiNode> allNodes = new ArrayList<>();
        for (LexiNode node: nodeList) {
            allNodes.addAll(node.getAllNodes());
        }

        for(LexiNode node: allNodes) {
            printWriter.write(node.toString());
            printWriter.println();
        }

        printWriter.close();
        fileWriter.close();
    }

    /**
     * Méthode pour lire le fichier .txt
     * @param file nom du fichier à lire
     * @return une liste de node comprenant les mots et définitions contenu dans le fichier
     * @throws Exception exception si les paramètre sont null
     */
    public List<LexiNode> readFromFile(File file) throws Exception {
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
