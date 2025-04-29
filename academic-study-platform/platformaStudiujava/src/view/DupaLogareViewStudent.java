package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import view.DupaLogareView;

public class DupaLogareViewStudent extends DupaLogareView {
    private JButton disciplinaButton = new JButton("Disciplina");
    private JButton showActivitateButton = new JButton("Afisare Activitati");
    private JButton showNoteButton = new JButton("Afisare Note");
    private JButton calendarStudentButton = new JButton("Calendar");
    private JButton grupButton = new JButton("Grup");
    private JButton creareGrupButton = new JButton("Creare Grup");

    public DupaLogareViewStudent() {
        super();
        mainPanel.add(disciplinaButton);
        mainPanel.add(showActivitateButton);
        mainPanel.add(showNoteButton);
        mainPanel.add(calendarStudentButton);
        mainPanel.add(grupButton);
        mainPanel.add(creareGrupButton);
    }

    public void addDisciplinaButtonListener(ActionListener listener) {
        disciplinaButton.addActionListener(listener);
    }

    public void addShowActivitateButtonListener(ActionListener listener) {
        showActivitateButton.addActionListener(listener);
    }

    public void addShowNoteButtonListener(ActionListener listener) {
        showNoteButton.addActionListener(listener);
    }

    public void addCalendarStudentButtonListener(ActionListener listener) {
        calendarStudentButton.addActionListener(listener);
    }

    public void addGrupButtonListener(ActionListener listener) {
        grupButton.addActionListener(listener);
    }

    public void addCreareGrupButtonListener(ActionListener listener) {
        creareGrupButton.addActionListener(listener);
    }


}
