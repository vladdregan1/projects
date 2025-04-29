package controller;

import model.ActivitateGrupModel;
import model.CreareActivitateGrupModel;
import model.GrupModel;
import model.MesajModel;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class GrupController {

    private GrupView grupView;
    private DupaLogareViewStudent dupaLogareViewStudent;
    private GrupModel grupModel;
    private CreareActivitateGrupView creareActivitateGrupView;
    private ActivitateGrupView activitateGrupView;
    private LogareView logareView;
    private MesajView mesajView;

    public GrupController(GrupView grupView, DupaLogareViewStudent dupaLogareViewStudent, GrupModel grupModel, CreareActivitateGrupView creareActivitateGrupView, ActivitateGrupView activitateGrupView, LogareView logareView, MesajView mesajView) {
        this.grupView = grupView;
        this.dupaLogareViewStudent = dupaLogareViewStudent;
        this.grupModel = grupModel;
        this.creareActivitateGrupView = new CreareActivitateGrupView();
        this.activitateGrupView = activitateGrupView;
        this.logareView = logareView;
        this.mesajView = mesajView;
        this.grupView.addBackButtonListener(new BackListener());
        this.grupView.addCreareActivitateGrupButtonListener(new CreareActivitateListener());
        this.grupView.addInscriereGrupButtonListener(new InscriereListener());
        this.grupView.addMesajeListener(new MesajListener());
        loadGrupList();
    }

    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dupaLogareViewStudent.setVisible(true);
            grupView.setVisible(false);
        }
    }

    class CreareActivitateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CreareActivitateGrupController controller = new CreareActivitateGrupController(creareActivitateGrupView, grupView, new CreareActivitateGrupModel());
            creareActivitateGrupView.setVisible(true);
            grupView.setVisible(false);
        }
    }

    class InscriereListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivitateGrupController controller = new ActivitateGrupController(activitateGrupView, grupView, new ActivitateGrupModel(), logareView, new MembriiView());
            activitateGrupView.setVisible(true);
            grupView.setVisible(false);
        }
    }

    class MesajListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MesajeController mesajeController = new MesajeController(mesajView, grupView, new MesajModel(), logareView);
            mesajView.setVisible(true);
            grupView.setVisible(false);

        }
    }

    public void loadGrupList() {
        List<String[]> grupuri = grupModel.getGrupDetails();
        grupView.setGrupList(grupuri);
    }

}
