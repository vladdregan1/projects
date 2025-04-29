package controller;

import model.InformatiiAdminModel;
import model.InformatiiModel;
import view.DupaLogareAdminView;
import view.InformatiiAdminView;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class InformatiiAdminController {

    private InformatiiAdminView informatiiAdminView;
    private DupaLogareAdminView dupaLogareAdminView;
    private InformatiiAdminModel informatiiAdminModel;

    public InformatiiAdminController(InformatiiAdminView informatiiAdminView, DupaLogareAdminView dupaLogareAdminView, InformatiiAdminModel informatiiAdminModel) {
        this.informatiiAdminView = informatiiAdminView;
        this.dupaLogareAdminView = dupaLogareAdminView;
        this.informatiiAdminModel = informatiiAdminModel;
        this.informatiiAdminView.addBackButtonListener(new BackListener());
        this.informatiiAdminView.addModificareContListener(new ModificareContListener());
    }

    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dupaLogareAdminView.setVisible(true);
            informatiiAdminView.setVisible(false);
        }
    }

    class ModificareContListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String numeUtilizator = informatiiAdminView.getNumeUtilizator();
            String CNP = informatiiAdminView.getCNP();
            String nume = informatiiAdminView.getNumeInfo();
            String prenume = informatiiAdminView.getPrenumeInfo();
            String adresa = informatiiAdminView.getAdresa();
            String numarTelefon = informatiiAdminView.getNrTelefon();
            String email = informatiiAdminView.getEmail();
            String iban = informatiiAdminView.getIban();
            String nrContract = informatiiAdminView.getNrContract();

            informatiiAdminModel.setIdUtilizator(numeUtilizator);
            informatiiAdminModel.setCNP(CNP);
            informatiiAdminModel.setNume(nume);
            informatiiAdminModel.setPrenume(prenume);
            informatiiAdminModel.setAdresa(adresa);
            informatiiAdminModel.setNumarTelefon(numarTelefon);
            informatiiAdminModel.setEmail(email);
            informatiiAdminModel.setIban(iban);
            informatiiAdminModel.setNrContract(nrContract);

            boolean succesUpdate = informatiiAdminModel.modificareInformatii();

            if (succesUpdate) {
                informatiiAdminView.showMessage("Modificare realizata cu succes!");
            }
            else informatiiAdminView.showMessage("Modificarea nu a putut fi realizata.");

        }
    }

}
