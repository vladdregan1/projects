package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CatalogProfesorView extends JFrame {

    private JButton backButton = new JButton( "Inapoi");
    private JButton submitButton = new JButton("Submit");
    private JButton refreshButton = new JButton("Refresh");
    private JComboBox<String> disciplinaComboBox;
    private JComboBox<String> studentComboBox;
    private JComboBox<String> tipActivitateComboBox;
    private JTextField data = new JTextField(20);
    private JTextField nota = new JTextField(20);
    private String[] discipline = new String[0];
    private String[] numeStudenti = new String[0];
    private String[] tipuriActivitate = new String[0];

    public CatalogProfesorView() {
        setTitle("Catalog");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        disciplinaComboBox = new JComboBox<>(discipline);
        studentComboBox = new JComboBox<>(numeStudenti);
        tipActivitateComboBox = new JComboBox<>(tipuriActivitate);

        JPanel mainPanel = new JPanel(new GridLayout(10, 2, 5, 5));

        mainPanel.add(new JLabel("Selectați disciplina:"));
        mainPanel.add(disciplinaComboBox);
        mainPanel.add(new JLabel("Selectati studentul:"));
        mainPanel.add(studentComboBox);
        mainPanel.add(new JLabel("Selectati activitatea:"));
        mainPanel.add(tipActivitateComboBox);
        mainPanel.add(new JLabel("Data YYYY-MM-DD:"));
        mainPanel.add(data);
        mainPanel.add(new JLabel("Nota:"));
        mainPanel.add(nota);
        mainPanel.add(submitButton);
        mainPanel.add(refreshButton);
        mainPanel.add(backButton);
        setLocationRelativeTo(null);

        add(mainPanel);
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public String getSelectedDisciplina() {
        return (String) disciplinaComboBox.getSelectedItem();
    }

    public String getSelectedStudent() {
        return (String) studentComboBox.getSelectedItem();
    }

    public String getSelectedTipActivitate() {
        return (String) tipActivitateComboBox.getSelectedItem();
    }

    public String getData() {
        return data.getText();
    }

   public float getNota() {
        return Float.parseFloat(nota.getText());
   }

    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void setDiscipline(String[] discipline) {
        this.discipline = discipline;
        disciplinaComboBox.setModel(new DefaultComboBoxModel<>(discipline));
    }

    public void setStudenti(String[] numeStudenti) {
        this.numeStudenti = numeStudenti;
        studentComboBox.setModel(new DefaultComboBoxModel<>(numeStudenti));
    }

    public void setTipuriActivitate(String[] tipuriActivitate) {
        this.tipuriActivitate = tipuriActivitate;
        //for(int i = 0; i < tipuriActivitate.length; i++)
            //System.out.println(tipuriActivitate[i]);
        tipActivitateComboBox.setModel(new DefaultComboBoxModel<>(tipuriActivitate));
    }

    public void addRefreshButtonListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

}
