package controller;

import model.CreareActivitateGrupModel;
import view.CreareActivitateGrupView;
import view.GrupView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class CreareActivitateGrupController {

    private CreareActivitateGrupView creareActivitateGrupView;
    private CreareActivitateGrupModel creareActivitateGrupModel;
    private GrupView grupView;

    public CreareActivitateGrupController(CreareActivitateGrupView creareActivitateGrupView, GrupView grupView, CreareActivitateGrupModel creareActivitateGrupModel) {
        this.creareActivitateGrupView = creareActivitateGrupView;
        this.grupView = grupView;
        this.creareActivitateGrupModel = creareActivitateGrupModel;
        this.creareActivitateGrupView.addBackButtonListener(new BackListener());
        this.creareActivitateGrupView.addSubmitButtonListener(new SubmitListener());
    }

    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            grupView.setVisible(true);
            creareActivitateGrupView.setVisible(false);
        }
    }

    class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idGrup = grupView.getSelectedIdGrup();
            String numeActivitateGrup = creareActivitateGrupView.getNume();
            String dataCreare = creareActivitateGrupView.getDataCreare();
            String dataInceput = creareActivitateGrupView.getDataInceput();
            String dataFinal = creareActivitateGrupView.getDataFinal();
            int nrMinParticipanti = creareActivitateGrupView.getNrMinParticipanti();
            String dataValidarii = creareActivitateGrupView.getDataValidarii();
            int anulat = 0;

            boolean succes = creareActivitateGrupModel.creare(idGrup, numeActivitateGrup, dataCreare, dataInceput, dataFinal, nrMinParticipanti, dataValidarii, anulat);
            if (succes) {
                JOptionPane.showMessageDialog(null, "Activitatea a fost creata cu succes!");
            } else {
                JOptionPane.showMessageDialog(null, "Activitatea nu a fost creata!");
            }

        }
    }
}
