package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalendarProfesorView extends JFrame {

    private JButton backButton = new JButton( "Inapoi");
    private JButton submitButton = new JButton("Submit");
    private JButton refreshButton = new JButton("Refresh");
    private JComboBox<String> disciplinaComboBox;
    private JComboBox<String> activitateComboBox;
    private JTextField dataInceput = new JTextField(20);
    private JTextField dataFinal = new JTextField(20);
    private JTextField nrMaximParticipanti = new JTextField(20);
    private JTextField descriere = new JTextField(100);
    private String[] discipline = new String[0];
    private String[] activitati = new String[0];


    public CalendarProfesorView() {
        setTitle("Calendar");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        disciplinaComboBox = new JComboBox<>(discipline);
        activitateComboBox = new JComboBox<>(activitati);

        JPanel mainPanel = new JPanel(new GridLayout(10, 2, 5, 5));

        mainPanel.add(new JLabel("Selectați disciplina:"));
        mainPanel.add(disciplinaComboBox);
        mainPanel.add(new JLabel("Selectati activitatea:"));
        mainPanel.add(activitateComboBox);
        mainPanel.add(new JLabel("Data inceput (yyyy-MM-dd HH:mm:ss):"));
        mainPanel.add(dataInceput);
        mainPanel.add(new JLabel("Data sfarsit (yyyy-MM-dd HH:mm:ss):"));
        mainPanel.add(dataFinal);
        mainPanel.add(new JLabel("Numar maxim participanti:"));
        mainPanel.add(nrMaximParticipanti);
        mainPanel.add(new JLabel("Descriere:"));
        mainPanel.add(descriere);

        mainPanel.add(submitButton);
        mainPanel.add(refreshButton);
        mainPanel.add(backButton);
        setLocationRelativeTo(null);

        add(mainPanel);
    }

    public String getSelectedDisciplina() {
        return (String) disciplinaComboBox.getSelectedItem();
    }

    public String getSelectedActivitate() {
        return (String) activitateComboBox.getSelectedItem();
    }

    public String getDataInceput() {
        return dataInceput.getText();
    }

    public String getDataFinal() {
        return dataFinal.getText();
    }

   public int getNrMaximParticipanti() {
        return Integer.parseInt(nrMaximParticipanti.getText());
   }

   public String getDescriere() {
        return descriere.getText();
   }

    public void setDiscipline(String[] discipline) {
        this.discipline = discipline;
        disciplinaComboBox.setModel(new DefaultComboBoxModel<>(discipline));
    }

    public void setActivitati(String[] activitati) {
        this.activitati = activitati;
        activitateComboBox.setModel(new DefaultComboBoxModel<>(activitati));
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addRefreshButtonListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

}
