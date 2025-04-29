package controller;

import model.AfisareActivitateProfesorModel;
import view.AfisareActivitateProfesorView;
import view.DupaLogareProfesorView;
import view.LogareView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.*;

public class AfisareActivitateProfesorController {

    private AfisareActivitateProfesorView afisareActivitateProfesorView;
    private DupaLogareProfesorView dupaLogareProfesorView;
    private AfisareActivitateProfesorModel afisareActivitateProfesorModel;
    private LogareView logareView;

    public AfisareActivitateProfesorController(AfisareActivitateProfesorView afisareActivitateProfesorView, DupaLogareProfesorView dupaLogareProfesorView, AfisareActivitateProfesorModel afisareActivitateProfesorModel, LogareView logareView) {
        this.afisareActivitateProfesorView = afisareActivitateProfesorView;
        this.dupaLogareProfesorView = dupaLogareProfesorView;
        this.afisareActivitateProfesorModel = afisareActivitateProfesorModel;
        this.logareView = logareView;

        this.afisareActivitateProfesorView.addBackButtonListener(new BackListener());
        this.afisareActivitateProfesorView.addDescarcaButtonListener( new DescarcaListener());

        loadActivitateList();
    }

    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dupaLogareProfesorView.setVisible(true);
            afisareActivitateProfesorView.setVisible(false);
        }
    }

    class DescarcaListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            afisareActivitateProfesorModel.setNumeUtilizator(logareView.getNumeUtilizator());
            List<String[]> activitate = afisareActivitateProfesorModel.getActivitati();


            String filePath = "activitatiProfesor.csv"; // Fișierul va fi salvat în directorul curent al aplicației

            try {
                // Scrierea datelor în fișier
                try (FileWriter fileWriter = new FileWriter(filePath);
                     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                    // Scrie antetele coloanelor
                    bufferedWriter.write("\n");

                    // Scrie fiecare activitate în fișier
                    for (String[] act : activitate) {
                        String line = String.join(",", act); // Concatenează valorile cu separatorul ","
                        bufferedWriter.write(line);
                        bufferedWriter.newLine();
                    }
                }

                // Notificare utilizator despre succesul operațiunii
                JOptionPane.showMessageDialog(afisareActivitateProfesorView, "Fișierul activitati.csv a fost salvat cu succes!");
            } catch (Exception ex) {
                // Gestionare eroare și notificare utilizator
                JOptionPane.showMessageDialog(afisareActivitateProfesorView, "Eroare la salvarea fișierului: " + ex.getMessage());
            }

        }
    }

    public void loadActivitateList() {
        afisareActivitateProfesorModel.setNumeUtilizator(logareView.getNumeUtilizator());
        List<String[]> activitate = afisareActivitateProfesorModel.getActivitati();
        afisareActivitateProfesorView.setActivitateList(activitate);
    }
}
