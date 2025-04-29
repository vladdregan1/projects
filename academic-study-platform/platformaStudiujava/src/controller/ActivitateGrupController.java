package controller;

import model.ActivitateGrupModel;
import model.MembriiModel;
import view.ActivitateGrupView;
import view.GrupView;
import view.LogareView;
import view.MembriiView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ActivitateGrupController {

    private ActivitateGrupView activitateGrupView;
    private GrupView grupView;
    private ActivitateGrupModel activitateGrupModel;
    private LogareView logareView;
    private MembriiView membriiView;

    public ActivitateGrupController(ActivitateGrupView activitateGrupView, GrupView grupView, ActivitateGrupModel activitateGrupModel, LogareView logareView, MembriiView membriiView) {
        this.activitateGrupView = activitateGrupView;
        this.grupView = grupView;
        this.activitateGrupModel = activitateGrupModel;
        this.logareView = logareView;
        this.membriiView = membriiView;

        this.activitateGrupView.addBackButtonListener(new BackListener());
        this.activitateGrupView.addInscriereButtonListener(new InscriereListener());
        this.activitateGrupView.addMembriiListener(new MembriiListener());
        loadActivitateList();

    }

    public void loadActivitateList() {
        List<String[]> activitati = activitateGrupModel.getActivitateGrupDetails(grupView.getSelectedIdGrup());
        activitateGrupView.setActivitateList(activitati);
    }

    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            grupView.setVisible(true);
            activitateGrupView.setVisible(false);
        }
    }

    class InscriereListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
         String numeUtilizator = logareView.getNumeUtilizator();
         int idActivitateGrup = activitateGrupView.getSelectedIdActivitate();
         boolean succes = activitateGrupModel.inscriere(idActivitateGrup, numeUtilizator);
         if(succes) {
             JOptionPane.showMessageDialog(null, "Ai fost inscris cu succes");
         }
         else {
             JOptionPane.showMessageDialog(null, "Nu ai putut fi inscris");
         }

        }
    }

    class MembriiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MembriiController controller = new MembriiController(membriiView, activitateGrupView, new MembriiModel());
            membriiView.setVisible(true);
            activitateGrupView.setVisible(false);
        }
    }
}
