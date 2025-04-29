package controller;

import model.CreareGrupModel;
import view.CreareGrupView;
import view.DupaLogareViewStudent;

import java.awt.*;
import java.awt.event.*;

public class CreareGrupController {

    private CreareGrupView creareGrupView;
    private DupaLogareViewStudent dupaLogareViewStudent;
    private CreareGrupModel creareGrupModel;

    public CreareGrupController(CreareGrupView creareGrupView, DupaLogareViewStudent dupaLogareViewStudent, CreareGrupModel creareGrupModel) {
        this.creareGrupView = creareGrupView;
        this.dupaLogareViewStudent = dupaLogareViewStudent;
        this.creareGrupModel = creareGrupModel;
        this.creareGrupView.addBackButtonListener(new BackListener());
        this.creareGrupView.addCreareGrupListener(new CreareGrupListener());
    }

    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dupaLogareViewStudent.setVisible(true);
            creareGrupView.setVisible(false);
        }
    }

    class CreareGrupListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String numeDisciplina = creareGrupView.getDisciplina();
            String numeGrup = creareGrupView.getNumeGrup();

            boolean reusit = creareGrupModel.creare(numeDisciplina, numeGrup);
            if(reusit) {
                creareGrupView.showMessage("Grupul a fost creat cu succes!");
            }
            else {
                creareGrupView.showMessage("Grupul nu a putut fi creat!");
            }
        }
    }
}
