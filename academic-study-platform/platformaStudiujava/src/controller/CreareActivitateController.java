package controller;


import model.CreareActivitateModel;
import model.UtilizatorModel;
import view.CreareActivitateView;
import view.DupaLogareProfesorView;
import view.LogareView;
import view.UtilizatorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CreareActivitateController {
    private CreareActivitateView creareActivitateView;
    private DupaLogareProfesorView dupaLogareProfesorView;
    private CreareActivitateModel creareActivitateModel;
    private LogareView logareView;

    public CreareActivitateController(CreareActivitateView creareActivitateView, DupaLogareProfesorView dupaLogareProfesorView, CreareActivitateModel creareActivitateModel, LogareView logareView) {
        this.creareActivitateView = creareActivitateView;
        this.dupaLogareProfesorView = dupaLogareProfesorView;
        this.creareActivitateModel = creareActivitateModel;
        this.logareView = logareView;
        this.creareActivitateView.addBackButtonListener(new BackListener());
        this.creareActivitateView.addSubmitButtonListener(new SubmitListener());
    }

    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dupaLogareProfesorView.setVisible(true);
            creareActivitateView.setVisible(false);
        }
    }

    class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String numeUtilizator = logareView.getNumeUtilizator();
            String numeDisciplina = creareActivitateView.getDisciplina();
            String tipActivitate = creareActivitateView.getTipActivitate();
            String dataInceput = creareActivitateView.getDataInceput();
            String dataSfarsit = creareActivitateView.getDataSfarsit();
            String frecventa = creareActivitateView.getFrecventa();
            int procent = creareActivitateView.getProcent();

            creareActivitateModel.setNumeDisciplina(numeDisciplina);
            creareActivitateModel.setNumeUtilizator(numeUtilizator);
            creareActivitateModel.setTipActivitate(tipActivitate);
            creareActivitateModel.setDataInceput(dataInceput);
            creareActivitateModel.setDataSfarsit(dataSfarsit);
            creareActivitateModel.setFrecventa(frecventa);
            creareActivitateModel.setProcent(procent);

            boolean succesActivitate = creareActivitateModel.CreareActivitate();
            if (succesActivitate) {
                JOptionPane.showMessageDialog(creareActivitateView, "Activitatea a fost creata cu succes!");
            } else {
                JOptionPane.showMessageDialog(creareActivitateView, "Activitatea nu a fost creata!");
            }

        }
    }





}
