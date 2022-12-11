package model;

import exception.WordAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Représente un noeud dans l'arbre lexicographique.
 * Responsable d'appeler les fonctions récursive de ses enfants.
 *
 */
public class LexiNode {

    private final char definingLetter;

    private String word;

    private String definition;

    private List<LexiNode> childNodes;

    /**
     * Constructeur de classe qui prend seulement un mot comme attribut.
     * @param word mot avec lequel la class est instancié. Ne doit pas être null
     */
    public LexiNode(String word) {
        this.childNodes = new ArrayList<>();
        this.word = word;

        if (word.length() == 1) {
            this.definingLetter = word.charAt(0);
        }else {
            this.definingLetter = word.charAt(word.length() - 1);
        }
    }

    /**
     * Constructeur de classe qui prend un mot et une définition.
     * @param word mot avec lequel la class est instancié.
     * @param definition definition du mot
     */
    public LexiNode(String word, String definition) {
        this.childNodes = new ArrayList<>();
        this.word = word;
        this.definition = definition;

        if (word.length() == 1) {
            this.definingLetter = word.charAt(0);
        }else {
            this.definingLetter = word.charAt(word.length() - 1);
        }
    }

    /**
     * Méthode permettant d'ajouter un node aux childNode
     * @param node
     * @throws WordAlreadyExistsException
     */
    public void addNode(LexiNode node) throws WordAlreadyExistsException {
        if (word.equalsIgnoreCase(node.getWord())){
            throw new WordAlreadyExistsException(word);
        }

        char nextChar = node.getWord().charAt(word.length());
        for (LexiNode childNode: childNodes) {
            if (childNode.getDefiningLetter() == nextChar) {
                childNode.addNode(node);
                return;
            }
        }

        if (node.getWord().length() == word.length() + 1){
            childNodes.add(node);
        }else {
            childNodes.add(new LexiNode(word + nextChar));
            addNode(node);
        }
    }

    public List<LexiNode> getAllNodes() {
        List<LexiNode> nodes = new ArrayList<>();

        if (definition != null) {
            nodes.add(this);
        }

        for (LexiNode node: childNodes) {
            nodes.addAll(node.getAllNodes());
        }

        return nodes;
    }

    public List<String> getAllWords() {
        List<String> allWords = new ArrayList<>();

        if (definition != null) {
            allWords.add(word);
        }

        for (LexiNode node: childNodes) {
            allWords.addAll(node.getAllWords());
        }
        return allWords;
    }

    public LexiNode getNodeByWord(String word) {
        if (this.word.equals(word)) {
            return this;
        }

        char nextChar = word.charAt(this.word.length());
        for (LexiNode childNode: childNodes) {
            if (childNode.getDefiningLetter() == nextChar) {
                return childNode.getNodeByWord(word);
            }
        }

        return null;
    }

    public char getDefiningLetter() {
        return definingLetter;
    }

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public List<LexiNode> getChildNodes() {
        return childNodes;
    }

    public String toString() {
        return word + " & " + definition;
    }

}
