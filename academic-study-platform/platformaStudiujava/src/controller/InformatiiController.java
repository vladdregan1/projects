package controller;

import java.awt.*;
import java.awt.event.*;

import view.InformatiiView;
import view.DupaLogareView;
import model.InformatiiModel;

public class InformatiiController {

    private InformatiiView informatiiView;
    private DupaLogareView dupaLogareView;
    private InformatiiModel informatiiModel;

    public InformatiiController(InformatiiView informatiiView, DupaLogareView dupaLogareView, InformatiiModel informatiiModel) {
        this.informatiiView = informatiiView;
        this.dupaLogareView = dupaLogareView;
        this.informatiiModel = informatiiModel;
        this.informatiiView.addBackButtonListener(new BackListener());
        populateInformatii();
    }

    private void populateInformatii() {
        if (informatiiModel.getID() && informatiiModel.SelectInfo()) {
            informatiiView.setInformatii(
                    informatiiModel.getNume(),
                    informatiiModel.getPrenume(),
                    informatiiModel.getCNP(),
                    informatiiModel.getAdresa(),
                    informatiiModel.getNumarTelefon(),
                    informatiiModel.getEmail(),
                    informatiiModel.getIban(),
                    informatiiModel.getNrContract()
            );
        } else {
            System.out.println("Nu s-au putut obține informațiile utilizatorului.");
        }
    }

    class BackListener implements ActionListener {
        @Override

        public void actionPerformed(ActionEvent e) {
            dupaLogareView.setVisible(true);
            informatiiView.setVisible(false);
        }
    }
}
