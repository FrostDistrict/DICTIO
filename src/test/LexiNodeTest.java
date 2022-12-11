package test;

import exception.WordAlreadyExistsException;
import model.LexiNode;

import java.util.List;

public class LexiNodeTest {

    public static void main(String[] args) {
        System.out.println(testAddNode() ? "AddNode successful" : "AddNode failed");
        System.out.println(testAddNode2() ? "AddNode successful" : "AddNode failed");
        System.out.println(testAddNode3() ? "AddNode successful" : "AddNode failed");

        System.out.println(testGetAllNodes() ? "GetAllNodes successful" : "GetAllNodes failed");
        System.out.println(testGetAllWords() ? "GetAllWords successful" : "GetAllWords failed");
        System.out.println(testGetNodeByWord() ? "GetNodeByWord successful" : "GetNodeByWord failed");

    }

    public static boolean testAddNode() {
        LexiNode node1 = new LexiNode("Pomm");

        try {
            node1.addNode(new LexiNode("Pomme","Fruit du pommier." ));
        } catch (Exception e) {
            return false;
        }

        return node1.getChildNodes().size() == 1;
    }

    public static boolean testAddNode2() {
        LexiNode node1 = new LexiNode("Pom");

        try {
            node1.addNode(new LexiNode("Pomme","Fruit du pommier." ));
        } catch (Exception e) {
            return false;
        }

        return node1.getChildNodes().get(0).getWord().equals("Pomm");
    }

    public static boolean testAddNode3() {
        LexiNode node1 = new LexiNode("Pomme");

        try {
            node1.addNode(new LexiNode("Pomme","Fruit du pommier." ));
        } catch (Exception e) {
            return true;
        }

        return false;
    }

    public static boolean testGetAllNodes() {
        LexiNode node1 = new LexiNode("GAULLI");

        try {
            node1.addNode(new LexiNode("GAULLIEN", "Partisan du général de Gaulle à l'époque de la Résistance"));
            node1.addNode(new LexiNode("GAULLISME", "Partisan du général de Gaulle à l'époque de la Résistance"));
            node1.addNode(new LexiNode("GAULLISTE", "Partisan du général de Gaulle à l'époque de la Résistance"));
        } catch (WordAlreadyExistsException e) {
            return false;
        }

        List<LexiNode> nodes = node1.getAllNodes();
        return nodes.size() == 3;
    }

    public static boolean testGetAllWords() {
        LexiNode node1 = new LexiNode("GAULL");

        try {
            node1.addNode(new LexiNode("GAULLIEN", "Partisan du général de Gaulle à l'époque de la Résistance"));
            node1.addNode(new LexiNode("GAULLISME", "Partisan du général de Gaulle à l'époque de la Résistance"));
            node1.addNode(new LexiNode("GAULLISTE", "Partisan du général de Gaulle à l'époque de la Résistance"));
        } catch (WordAlreadyExistsException e) {
            return false;
        }

        List<String> words = node1.getAllWords();
        return words.size() == 3;
    }

    public static boolean testGetNodeByWord() {
        LexiNode node1 = new LexiNode("GAUL");

        try {
            node1.addNode(new LexiNode("GAULLIEN", "Partisan du général de Gaulle à l'époque de la Résistance"));
            node1.addNode(new LexiNode("GAULLISME", "Partisan du général de Gaulle à l'époque de la Résistance"));
            node1.addNode(new LexiNode("GAULLISTE", "Partisan du général de Gaulle à l'époque de la Résistance"));
        } catch (WordAlreadyExistsException e) {
            return false;
        }

        LexiNode node = node1.getNodeByWord("GAULLISTE");
        return node.getWord().equals("GAULLISTE");
    }
}
