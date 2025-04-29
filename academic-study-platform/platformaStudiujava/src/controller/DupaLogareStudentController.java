package controller;

import model.*;
import view.*;

import java.awt.*;
import java.awt.event.*;

public class DupaLogareStudentController extends DupaLogareController {

    private DisciplinaView disciplinaView;
    private DupaLogareViewStudent dupaLogareViewStudent;
    private DisciplinaModel disciplinaModel;
    private LogareView logareView;
    private AfisareActivitateView afisareActivitateView;
    private NoteView noteView;
    private CalendarStudentView calendarStudentView;
    private GrupView grupView;
    private CreareGrupView creareGrupView;
    //private CalendarStudentModel calendarStudentModel;

    public DupaLogareStudentController(DupaLogareViewStudent dupaLogareViewStudent, LogareView logareView, InformatiiView informatiiView, InformatiiModel informatiiModel, LogareModel logareModel, DisciplinaView disciplinaView, DisciplinaModel disciplinaModel, AfisareActivitateView afisareActivitateView, NoteView noteView, CalendarStudentView calendarStudentView, GrupView grupView, CreareGrupView creareGrupView) {
        super(dupaLogareViewStudent, logareView, informatiiView, informatiiModel, logareModel);

        this.dupaLogareViewStudent = dupaLogareViewStudent;
        this.disciplinaView = disciplinaView;
        this.disciplinaModel = disciplinaModel;
        this.logareView = logareView;
        this.afisareActivitateView = afisareActivitateView;
        this.noteView = noteView;
        this.calendarStudentView = calendarStudentView;
        this.grupView = grupView;
        this.creareGrupView = creareGrupView;
       // this.calendarStudentModel = calendarStudentModel;
        this.dupaLogareViewStudent.addDisciplinaButtonListener(new DisciplinaButtonListener());
        this.dupaLogareViewStudent.addShowActivitateButtonListener(new ShowActivitateListener());
        this.dupaLogareViewStudent.addShowNoteButtonListener(new NoteListener());
        this.dupaLogareViewStudent.addCalendarStudentButtonListener(new CalendarStudentListener());
        this.dupaLogareViewStudent.addGrupButtonListener(new GrupListener());
        this.dupaLogareViewStudent.addCreareGrupButtonListener(new CreareGrupListener());
        loadDisciplinaList();
        //loadCalendarStudentList();

    }

    private void loadDisciplinaList() {
        disciplinaModel = new DisciplinaModel();
        disciplinaView.setDisciplinaList(disciplinaModel.getDisciplinaDetails());
    }

   /* private void loadCalendarStudentList() {
        calendarStudentModel = new CalendarStudentModel();
        calendarStudentView.setCalendarList(calendarStudentModel.getCalendarDetails());
    } */



    class DisciplinaButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DisciplinaController disciplinaController = new DisciplinaController(disciplinaView, dupaLogareViewStudent, disciplinaModel, logareView);
            disciplinaView.setVisible(true);
            dupaLogareViewStudent.setVisible(false);
        }
    }

    class ShowActivitateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AfisareActivitateController afisareActivitateController = new AfisareActivitateController(afisareActivitateView, dupaLogareViewStudent, new AfisareActivitateModel(), logareView);
            afisareActivitateView.setVisible(true);
            dupaLogareViewStudent.setVisible(false);
        }
    }

    class NoteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            NoteController noteController = new NoteController(noteView, dupaLogareViewStudent, new NoteModel(), logareView);
            noteView.setVisible(true);
            dupaLogareViewStudent.setVisible(false);
        }
    }

    class CalendarStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CalendarStudentController calendarStudentController = new CalendarStudentController(calendarStudentView, dupaLogareViewStudent, new CalendarStudentModel(), logareView);
            calendarStudentView.setVisible(true);
            dupaLogareViewStudent.setVisible(false);
        }
    }

    class GrupListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GrupController grupController = new GrupController(grupView, dupaLogareViewStudent, new GrupModel(), new CreareActivitateGrupView(), new ActivitateGrupView(), logareView, new MesajView());
            grupView.setVisible(true);
            dupaLogareViewStudent.setVisible(false);
        }
    }

    class CreareGrupListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CreareGrupController creareGrupController = new CreareGrupController(creareGrupView, dupaLogareViewStudent, new CreareGrupModel());
            creareGrupView.setVisible(true);
            dupaLogareViewStudent.setVisible(false);
        }
    }
}
