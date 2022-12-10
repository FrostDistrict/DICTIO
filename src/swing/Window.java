package swing;

import service.ReadWriteService;

import javax.swing.*;

public class Window {

    private ReadWriteService readWriteService;

    private JFrame windowFrame;

    private JButton addBtn;

    private JButton loadBtn;

    private JButton saveBtn;

    private JTextField wordField;

    private JTextArea descField;

    private JList<String> suggestedWordsList;

    private JList<String> allWordsList;

    public Window(ReadWriteService readWriteService) {
        this.readWriteService = readWriteService;
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
        allWordsList.setBounds(610, 40, 160, 185);

        windowFrame.add(addBtn);
        windowFrame.add(loadBtn);
        windowFrame.add(saveBtn);
        windowFrame.add(wordField);
        windowFrame.add(descField);
        windowFrame.add(suggestedWordsList);
        windowFrame.add(allWordsList);

        windowFrame.setSize(800, 300);
        windowFrame.setLayout(null);
        windowFrame.setVisible(true);
    }

    private void onAddBtnClicked() {
    }

    private void onLoadBtnClicked() {
    }

    private void onSaveBtnClicked() {
    }
}
