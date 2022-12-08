package swing;

import service.ReadWriteService;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.event.*;
import model.Entree;

public class Window {
    public static ReadWriteService lire;
    public static List<Entree> entreeList;
    public static List<String> motList;
    public static List<String> testList;
    public static void main(String[] args) {
        lire = new ReadWriteService();
        entreeList = new ArrayList<>();
        motList = new ArrayList<String>();

        Entree e1 = new Entree("Abeille", "Une bibitte que fait du miel.");
        Entree e2 = new Entree("Pomme", "Genre de fruit qui pousse dans un arbre.");
        Entree e3 = new Entree("Carrot", "Legume qui pousse dans terre");
        Entree e4 = new Entree("Abeille2", "testestestestest");
        Entree e5 = new Entree("Allo", "Salutation.");

        entreeList.addAll(Arrays.asList(e1, e2, e3, e4, e5));

        for (Entree entree : entreeList) {
            motList.add(entree.getMot());
        }



        JFrame f = new JFrame();//creating instance of JFrame
        f.setTitle("Dictio");

        JButton b1 = new JButton("ajouter/modifier");//creating instance of JButton
        b1.setBounds(0, 230, 785, 30);//x axis, y axis, width, height
        f.add(b1);//adding button in JFrame


        JButton b2 = new JButton("Charger");
        b2.setBounds(295, 5, 100, 30);
        f.add(b2);

        JButton b3 = new JButton("Enregistrer");
        b3.setBounds(405, 5, 100, 30);
        f.add(b3);

        JTextField t1 = new JTextField("");
        t1.setBounds(0, 40, 300, 40);
        f.add(t1);

        JList l1 = new JList();
        l1.setBounds(0, 85, 300, 140);
        f.add(l1);

        JTextArea ta2 = new JTextArea("");
        ta2.setBounds(305, 40, 300, 185);
        f.add(ta2);

        JPanel p1 = new JPanel();
        JList l2 = new JList();
        l2.setBounds(610, 40, 160, 185);
        List<String> list = List.of();
        l2.setListData(list.toArray(motList.toArray()));
        f.add(l2);

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ta2.setText("test");
            }
        });

        b1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                String mot = t1.getText();
                List<String> testList = new ArrayList<String>(Collections.singleton(mot));
                System.out.println(testList);
            }
        });



        try {
            lire.writeToFile("foo.txt", entreeList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Entree> entreesLecture;
        try {
            entreesLecture = lire.readFromFile("foo.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Entree entree : entreesLecture) {
            System.out.println(entree.toString());
        }


        f.setSize(800, 300);
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }

}
