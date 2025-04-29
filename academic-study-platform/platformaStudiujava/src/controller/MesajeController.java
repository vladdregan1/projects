package controller;

import model.MesajModel;
import view.GrupView;
import view.LogareView;
import view.MesajView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MesajeController {

    private MesajView mesajView;
    private GrupView grupView;
    private MesajModel mesajModel;
    private LogareView logareView;

    public MesajeController(MesajView mesajView, GrupView grupView, MesajModel mesajModel, LogareView logareView) {
        this.mesajView = mesajView;
        this.grupView = grupView;
        this.mesajModel = mesajModel;
        this.logareView = logareView;
        this.mesajView.addInapoiButtonListener(new InapoiListener());
        this.mesajView.addTrimiteButtonListener(new TrimiteListener());
    }


    class InapoiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            grupView.setVisible(true);
            mesajView.setVisible(false);
        }
    }

    class TrimiteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String mesaj = mesajView.getMesajFieldText();
            if (!mesaj.isEmpty()) {
                int idGrup = grupView.getSelectedIdGrup();
                String numeUtilizator = logareView.getNumeUtilizator();
                mesajModel.setMesaj(idGrup, numeUtilizator, mesaj);
                mesajView.addMesaj(mesaj);
                mesajView.clearMesajField();
            }
        }
    }
}
