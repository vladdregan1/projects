package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;

public class NoteView extends JFrame {

    private JButton backButton = new JButton("Inapoi");
    private JButton descarcaButton = new JButton("Descarca");
    private JList<String> noteList;
    private JList<String> noteFinaleList;
    private JLabel finalNoteLabel = new JLabel("NOTE FINALE", SwingConstants.CENTER);

    public NoteView() {
        setTitle("Note");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        noteList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(noteList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panou pentru "NOTE FINALE" și butonul "Inapoi"
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(finalNoteLabel, BorderLayout.NORTH);

       noteFinaleList = new JList<>();
       JScrollPane scrollPane2 = new JScrollPane(noteFinaleList);
       bottomPanel.add(scrollPane2, BorderLayout.CENTER);

        bottomPanel.add(backButton, BorderLayout.SOUTH);
        bottomPanel.add(descarcaButton, BorderLayout.EAST);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

        add(mainPanel);

    }

    public void setNoteList(List<String[]> noteDetails) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String[] details : noteDetails) {
            String displayText = details[0] + " - " + details[1] + " - " + details[2];
            listModel.addElement(displayText);
        }

        noteList.setModel(listModel);
    }

    public void setNoteFinaleList(List<String[]> noteDetails) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String[] details : noteDetails) {
            String displayText = details[0] + " - " + details[1];
            //System.out.println(displayText);
            listModel.addElement(displayText);
        }
        noteFinaleList.setModel(listModel);
    }


    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void addDescarcaButtonListener(ActionListener listener) {
        descarcaButton.addActionListener(listener);
    }
}
