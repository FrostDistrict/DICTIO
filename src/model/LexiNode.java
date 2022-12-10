package model;

import exception.WordAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;


public class LexiNode {

    private final char definingLetter;

    private String word;

    private String definition;

    private List<LexiNode> childNodes;

    public LexiNode(String word) {
        this.childNodes = new ArrayList<>();
        this.word = word;

        if (word.length() == 1) {
            this.definingLetter = word.charAt(0);
        }else {
            this.definingLetter = word.charAt(word.length() - 1);
        }
    }

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

        if (nextChar == node.getDefiningLetter()){
            childNodes.add(node);
        }else {
            childNodes.add(new LexiNode(word + nextChar));
            addNode(node);
        }
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

    public char getDefiningLetter() {
        return definingLetter;
    }

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public List<LexiNode> getChildNodes() {
        return childNodes;
    }

    public String toString() {
        return word + " & " + definition;
    }

}
