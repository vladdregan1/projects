package controller;

import model.DisciplinaModel;
import view.DisciplinaView;
import view.DupaLogareViewStudent;
import view.LogareView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisciplinaController {

    private DisciplinaView disciplinaView;
    private DupaLogareViewStudent dupaLogareViewStudent;
    private DisciplinaModel disciplinaModel;
    private LogareView logareView;

    public DisciplinaController(DisciplinaView disciplinaView, DupaLogareViewStudent dupaLogareViewStudent, DisciplinaModel disciplinaModel, LogareView logareView) {
        this.disciplinaView = disciplinaView;
        this.dupaLogareViewStudent = dupaLogareViewStudent;
        this.disciplinaModel = disciplinaModel;
        this.logareView = logareView;

        this.disciplinaView.addBackButtonListener(new BackListener());
        this.disciplinaView.addAssignButtonListener(new AssignListener());

        loadDisciplinaList();
    }

    class AssignListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedDisciplina = disciplinaView.getSelectedDisciplina();
            //System.out.println(selectedDisciplina);
            int studentId = disciplinaModel.getLoggedStudentId(logareView.getNumeUtilizator());
            //System.out.println(studentId);

            if (selectedDisciplina != null) {
                disciplinaModel.assignStudentToDisciplina(selectedDisciplina, studentId);
                JOptionPane.showMessageDialog(disciplinaView, "Disciplina asignată cu succes!");
            } else {
                JOptionPane.showMessageDialog(disciplinaView, "Selectați o disciplină înainte de a asigna.");
            }
        }
    }

    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dupaLogareViewStudent.setVisible(true);
            disciplinaView.setVisible(false);
        }
    }


    public void loadDisciplinaList() {
        List<String[]> disciplinaDetails = disciplinaModel.getDisciplinaDetails();
        disciplinaView.setDisciplinaList(disciplinaDetails);
    }

}
