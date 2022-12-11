package swing;

import exception.WordAlreadyExistsException;
import model.LexiNode;
import service.ReadWriteService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe créant l'interface graphique
 */
public class Window {

    private ReadWriteService readWriteService;

    private List<LexiNode> nodeList;

    private LexiNode currentNode;

    private JFrame windowFrame;

    private JButton addBtn;

    private JButton loadBtn;

    private JButton saveBtn;

    private JTextField wordField;

    private JTextArea descField;

    private JList<String> suggestedWordsList;

    private JList<String> allWordsList;

    private JFileChooser jFileChooser;

    /**
     * Constructeur créant un nouveau tableau et initialisant l'interface graphique
     * @param readWriteService
     */
    public Window(ReadWriteService readWriteService) {
        this.readWriteService = readWriteService;
        this.nodeList = new ArrayList<>();
        initGUI();
    }

    /**
     * Méthode créant les différents éléments de l'interface graphique
     */
    public void initGUI() {
        windowFrame = new JFrame("Dictio");

        addBtn = new JButton("ajouter/modifier");
        addBtn.setBounds(0, 230, 785, 30);
        addBtn.addActionListener(e -> onAddBtnClicked());

        loadBtn = new JButton("Charger");
        loadBtn.setBounds(295, 5, 100, 30);
        loadBtn.addActionListener(e -> onLoadBtnClicked());

        saveBtn = new JButton("Enregistrer");
        saveBtn.setBounds(405, 5, 100, 30);
        saveBtn.addActionListener(e -> onSaveBtnClicked());

        wordField = new JTextField();
        wordField.setBounds(0, 40, 300, 40);

        wordField.getDocument().addDocumentListener(new DocumentListener() {
            /**
             * méthodes mettant à jour les éléments de l'interface graphique
             * @param e
             */
            @Override
            public void insertUpdate(DocumentEvent e) {
                onWordFieldUpdate();
                updateSuggestedWordsField();
                updateDescriptionArea();
            }


            @Override
            public void removeUpdate(DocumentEvent e) {
                onWordFieldUpdate();
                updateSuggestedWordsField();
                updateDescriptionArea();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onWordFieldUpdate();
                updateSuggestedWordsField();
                updateDescriptionArea();
            }
        });

        descField = new JTextArea();
        descField.setBounds(305, 40, 300, 185);
        descField.setLineWrap(true);
        descField.setWrapStyleWord(true);

        suggestedWordsList = new JList<>();
        suggestedWordsList.setBounds(0, 85, 300, 140);

        allWordsList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(allWordsList);
        scrollPane.setBounds(610, 40, 160, 185);

        String userDirectory = new File("").getAbsolutePath();
        jFileChooser = new JFileChooser(userDirectory);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier text", "txt");
        jFileChooser.setFileFilter(filter);

        windowFrame.add(addBtn);
        windowFrame.add(loadBtn);
        windowFrame.add(saveBtn);
        windowFrame.add(wordField);
        windowFrame.add(descField);
        windowFrame.add(suggestedWordsList);
        windowFrame.add(scrollPane);

        windowFrame.setSize(800, 300);
        windowFrame.setLayout(null);
        windowFrame.setVisible(true);
    }

    /**
     * méthode mettant à jour le champ de texte
     */
    private void onWordFieldUpdate() {
        String currentWord = wordField.getText();

        if (!currentWord.equals("")){
            for (LexiNode node: nodeList) {
                if (node.getWord().charAt(0) == currentWord.charAt(0)){
                    currentNode = node.getNodeByWord(currentWord);
                    return;
                }
            }
        }

        currentNode = null;
    }

    /**
     * méthode ajoutant un mot et une définition à la liste des mots sans les ajoutant dans le fichier .txt
     */
    private void onAddBtnClicked() {
        String currentWord = wordField.getText();

        if (currentNode == null) {
            LexiNode nodeToAdd = new LexiNode(wordField.getText(), descField.getText());
            boolean entryAdded = false;
            for (LexiNode node: nodeList) {
                if (nodeToAdd.getWord().charAt(0) == node.getWord().charAt(0)){
                    try {
                        node.addNode(nodeToAdd);
                    } catch (WordAlreadyExistsException e) {
                        JOptionPane.showMessageDialog(windowFrame, e.getMessage());
                    }
                    entryAdded = true;
                    break;
                }
            }

            if (!entryAdded) {
                LexiNode initialNode = new LexiNode(nodeToAdd.getWord().substring(0, 1));
                try {
                    initialNode.addNode(nodeToAdd);
                } catch (WordAlreadyExistsException ignored) { }
                nodeList.add(initialNode);
            }

            updateAllWordsList();
            return;
        }

        if (currentWord.equals(currentNode.getWord())) {
            currentNode.setDefinition(descField.getText());
        }
    }

    /**
     * méthode permettant de charger un fichier .txt contenant un dictionnaire dans l'interface graphique
     */
    private void onLoadBtnClicked() {
        int option = jFileChooser.showOpenDialog(windowFrame);
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                nodeList = readWriteService.readFromFile(jFileChooser.getSelectedFile());
                updateAllWordsList();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(windowFrame, "Impossible de charger ce fichier!");
            }
        }
    }

    /**
     * méthode permettant d'enregistrer les mots et les définitions ajoutez dns le fichier .txt
     */
    private void onSaveBtnClicked() {
        int option = jFileChooser.showSaveDialog(windowFrame);
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                readWriteService.writeToFile(jFileChooser.getSelectedFile(), nodeList);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(windowFrame, "Impossible de sauvegarder ce fichier!");
            }
        }
    }

    /**
     * méthode mettant à jour la description du mots choisi
     */
    private void updateDescriptionArea() {
        if (currentNode != null && currentNode.getDefinition() != null) {
            descField.setText(currentNode.getDefinition());
        }else {
            descField.setText("");
        }
    }

    /**
     * méthode mettant à jour la liste des mots suggérez en fonction des lettres tapé dans le champ de texte
     */
    private void updateSuggestedWordsField() {
        if (currentNode != null) {
            suggestedWordsList.setListData(currentNode.getAllWords().toArray(new String[0]));
        }else {
            suggestedWordsList.setListData(new String[0]);
        }
    }

    /**
     * méthode mettant à jour la liste complète des mots avec les mots ajoutez
     */
    private void updateAllWordsList() {
        List<String> allWords = new ArrayList<>();
        for (LexiNode node: nodeList) {
            allWords.addAll(node.getAllWords());
        }
        allWordsList.setListData(allWords.toArray(String[]::new));
    }
}
