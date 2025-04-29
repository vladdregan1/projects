package controller;

import view.AdaugaAdminView;
import view.DupaLogareSuperAdminView;
import view.LogareView;

import java.awt.*;
import java.awt.event.*;

public class DupaLogareSuperAdminController {

    private DupaLogareSuperAdminView dupaLogareSuperAdminView;
    private AdaugaAdminView adaugaAdminView;
    private LogareView logareView;
    private String[] adminUser;
    private String[] adminPass;

    public DupaLogareSuperAdminController(DupaLogareSuperAdminView dupaLogareSuperAdminView, LogareView logareView, AdaugaAdminView adaugaAdminView, String[] adminUser, String[] adminPass) {
        this.dupaLogareSuperAdminView = dupaLogareSuperAdminView;
        this.logareView = logareView;
        this.adaugaAdminView = adaugaAdminView;
        this.adminUser = adminUser;
        this.adminPass = adminPass;
        this.dupaLogareSuperAdminView.addDelogareButtonListener(new DelogareListener());
        this.dupaLogareSuperAdminView.addAdaugaAdminListener(new AdaugaAdminListener());
    }

    class DelogareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dupaLogareSuperAdminView.setVisible(false);
            logareView.setVisible(true);
        }
    }

    class AdaugaAdminListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdaugaAdminController adaugaAdminController = new AdaugaAdminController(adaugaAdminView, dupaLogareSuperAdminView, adminUser, adminPass);
            adaugaAdminView.setVisible(true);
            dupaLogareSuperAdminView.setVisible(false);
        }
    }
}
