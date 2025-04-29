package controller;

import model.UtilizatorModel;
import view.UtilizatorView;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class UtilizatorController {

    private UtilizatorModel model;
    private UtilizatorView view;
    private Frame mainFrame;

    public UtilizatorController(UtilizatorModel model, UtilizatorView view, Frame mainFrame) {
        this.model = model;
        this.view = view;
        this.mainFrame = mainFrame;
        this.view.addCreareContListener(new CreareContListener());
        this.view.addBackButtonListener(new BackListener(mainFrame));
    }

    class CreareContListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ///UTILIZATOR
            String numeUtilizator = view.getNumeUtilizator();
            String parola = view.getParola();
            int rol = view.getRol();

            ///UTILIZATOR_INFO
            String nume = view.getNumeInfo();
            String prenume = view.getPrenumeInfo();
            String cnp = view.getCNP();
            String adresa = view.getAdresa();
            String email = view.getEmail();
            String numarTelefon = view.getNrTelefon();
            String iban  = view.getIban();
            String nrContract = view.getNrContract();

            ///PROFESOR/STUDENT
            if (rol == 2){
                String Departament = view.getIdDepartament();
                int nrMinOre = view.getNrMinOre();
                int nrMaxOre = view.getNrMaxOre();

                if (Departament.equals("Calculatoare"))
                    model.setId_departament(1);
                else if (Departament.equals("Automatica"))
                    model.setId_departament(2);

                model.setNr_min_ore(nrMinOre);
                model.setNr_max_ore(nrMaxOre);
            }
            else {
                int anStudiu = view.getAnStudiu();
                int numarOre = view.getNumarOre();
                model.setAn_studiu(anStudiu);
                model.setNr_ore(numarOre);
            }

            if (numeUtilizator.isEmpty() || parola.isEmpty() || nume.isEmpty() || prenume.isEmpty() || cnp.isEmpty() || adresa.isEmpty() || email.isEmpty() || numarTelefon.isEmpty() || iban.isEmpty() || nrContract.isEmpty()) {
                view.showMessage("Toate câmpurile sunt obligatorii!");
                return;
            }

            model.setNume_utilizator(numeUtilizator);
            model.setParola(parola);
            model.setId_rol(rol);

            model.setNume(nume);
            model.setPrenume(prenume);
            model.setCNP(cnp);
            model.setAdresa(adresa);
            model.setEmail(email);
            model.setNumar_telefon(numarTelefon);
            model.setIban(iban);
            model.setNr_contract(nrContract);



            boolean succesUtilizator = model.creareUtilizator();
            if (succesUtilizator) {
                boolean succesUtilizatorInfo = model.creareUtilizatorInfo();
                if (succesUtilizatorInfo) {

                    if (rol == 2){
                        boolean succesProfesor = model.creareProfesor();
                        if (succesProfesor) {
                            view.showMessage("Contul a fost creat cu succes!");
                        }

                        else {
                            view.showMessage("Eroare la crearea datelor pentru profesor!");
                        }
                    }
                    else {
                        boolean succesStudent = model.creareStudent();
                        if (succesStudent) {
                            view.showMessage("Contul a fost creat cu succes!");
                        }
                        else {
                            view.showMessage("Eroare la crearea datelor pentru student!");
                        }
                    }

                }
                else {
                    view.showMessage("Eroare la salvarea informatiilor!");
                }
            }
            else {
                view.showMessage("Eroare la crearea contului!");
            }
        }
    }

    class BackListener implements ActionListener {
        private Frame mainFrame;

        public BackListener(Frame mainFrame) {
            this.mainFrame = mainFrame;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            mainFrame.setVisible(true);
        }
    }
}
