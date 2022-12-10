package swing;

import model.Entree;
import model.LexiNode;
import service.ReadWriteService;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Window {

    private ReadWriteService readWriteService;

    private List<LexiNode> nodeList;

    private JFrame windowFrame;

    private JButton addBtn;

    private JButton loadBtn;

    private JButton saveBtn;

    private JTextField wordField;

    private JTextArea descField;

    private JList<String> suggestedWordsList;

    private JList<String> allWordsList;

    private JFileChooser jFileChooser;

    public Window(ReadWriteService readWriteService) {
        this.readWriteService = readWriteService;
        this.nodeList = new ArrayList<>();
        initGUI();
    }

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

        descField = new JTextArea();
        descField.setBounds(305, 40, 300, 185);

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

    private void onAddBtnClicked() {
    }

    private void onLoadBtnClicked() {
        System.out.println("Load button clicked");

        int option = jFileChooser.showOpenDialog(windowFrame);
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                nodeList = readWriteService.readFromFile(jFileChooser.getSelectedFile());
                updateAllWordsList();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(windowFrame, "Impossible de charger ce fichier!");
            }
        }
    }

    private void onSaveBtnClicked() {
    }

    private void updateAllWordsList() {
        List<String> allWords = new ArrayList<>();
        for (LexiNode node: nodeList) {
            allWords.addAll(node.getAllWords());
        }
        allWordsList.setListData(allWords.toArray(String[]::new));
    }
}
