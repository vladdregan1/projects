package controller;

import model.AfisareActivitateModel;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.*;

public class AfisareActivitateController {

    private AfisareActivitateView afisareActivitateView;
    private DupaLogareViewStudent dupaLogareViewStudent;
    private AfisareActivitateModel afisareActivitateModel;
    private LogareView logareView;

    public AfisareActivitateController(AfisareActivitateView afisareActivitateView, DupaLogareViewStudent dupaLogareViewStudent, AfisareActivitateModel afisareActivitateModel, LogareView logareView) {
        this.afisareActivitateView = afisareActivitateView;
        this.dupaLogareViewStudent = dupaLogareViewStudent;
        this.afisareActivitateModel = afisareActivitateModel;
        this.logareView = logareView;

        this.afisareActivitateView.addBackButtonListener(new BackListener());
        this.afisareActivitateView.addDescarcaButtonListener(new DescarcaListener());

        loadActivitateList();
    }

    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            dupaLogareViewStudent.setVisible(true);
            afisareActivitateView.setVisible(false);
        }
    }

    class DescarcaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obține utilizatorul curent și lista de activități
            afisareActivitateModel.setNumeUtilizator(logareView.getNumeUtilizator());
            List<String[]> activitate = afisareActivitateModel.getActivitati();

            // Calea fișierului unde se va salva
            String filePath = "activitati.csv"; // Fișierul va fi salvat în directorul curent al aplicației

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
                JOptionPane.showMessageDialog(afisareActivitateView, "Fișierul activitati.csv a fost salvat cu succes!");
            } catch (Exception ex) {
                // Gestionare eroare și notificare utilizator
                JOptionPane.showMessageDialog(afisareActivitateView, "Eroare la salvarea fișierului: " + ex.getMessage());
            }
        }
    }


    public void loadActivitateList() {
        afisareActivitateModel.setNumeUtilizator(logareView.getNumeUtilizator());
        List<String[]> activitate = afisareActivitateModel.getActivitati();
        afisareActivitateView.setActivitateList(activitate);
    }


}
