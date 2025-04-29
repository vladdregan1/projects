package controller;

import model.InformatiiAdminModel;
import view.DupaLogareAdminView;
import view.InformatiiAdminView;
import view.LogareView;

import java.awt.*;
import java.awt.event.*;


public class DupaLogareAdminController {

    private DupaLogareAdminView dupaLogareAdminView;
    private LogareView logareView;
    private InformatiiAdminView informatiiAdminView;

    public DupaLogareAdminController(DupaLogareAdminView dupaLogareAdminView, LogareView logareView, InformatiiAdminView informatiiAdminView) {
        this.dupaLogareAdminView = dupaLogareAdminView;
        this.logareView = logareView;
        this.informatiiAdminView = informatiiAdminView;
        this.dupaLogareAdminView.addDelogareButtonListener(new DelogareListener());
        this.dupaLogareAdminView.addInfoButtonListener(new InfoListener());
    }

    class DelogareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dupaLogareAdminView.setVisible(false);
            logareView.setVisible(true);
        }
    }

    class InfoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            InformatiiAdminController informatiiAdminController = new InformatiiAdminController(informatiiAdminView, dupaLogareAdminView, new InformatiiAdminModel());
            dupaLogareAdminView.setVisible(false);
            informatiiAdminView.setVisible(true);

        }
    }

}
