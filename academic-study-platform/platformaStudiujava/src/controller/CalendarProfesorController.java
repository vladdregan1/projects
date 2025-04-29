package controller;

import model.CalendarProfesorModel;
import view.CalendarProfesorView;
import view.DupaLogareProfesorView;
import view.LogareView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CalendarProfesorController {

    private CalendarProfesorView calendarProfesorView;
    private DupaLogareProfesorView dupaLogareProfesorView;
    private CalendarProfesorModel calendarProfesorModel;
    private LogareView logareView;

    public CalendarProfesorController(CalendarProfesorView calendarProfesorView, DupaLogareProfesorView dupaLogareProfesorView, CalendarProfesorModel calendarProfesorModel, LogareView logareView) {
        this.calendarProfesorView = calendarProfesorView;
        this.dupaLogareProfesorView = dupaLogareProfesorView;
        this.calendarProfesorModel = calendarProfesorModel;
        this.logareView = logareView;

        this.calendarProfesorView.addBackButtonListener(new BackListener());
        this.calendarProfesorView.addSubmitButtonListener(new SubmitListener());
        this.calendarProfesorView.addRefreshButtonListener(new RefreshListener());

        populareDisciplina();
        populareActivitate();
    }

    public void populareDisciplina() {
        String[] disciplina = calendarProfesorModel.getDisciplina();
        calendarProfesorView.setDiscipline( disciplina);
    }

    public void populareActivitate(){
        String disciplinaSelectata = calendarProfesorView.getSelectedDisciplina();
        calendarProfesorModel.setIdDisciplina(disciplinaSelectata);

        String[] activitate = calendarProfesorModel.getActivitate();
        calendarProfesorView.setActivitati(activitate);
    }

    public void selectareIdDisciplina(){
        String Disciplina = calendarProfesorView.getSelectedDisciplina();
        calendarProfesorModel.setIdDisciplina(Disciplina);
    }

    public void selectareIdActivitate(){
        String Activitate = calendarProfesorView.getSelectedActivitate();
        calendarProfesorModel.setIdActivitate(Activitate);
    }


    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dupaLogareProfesorView.setVisible(true);
            calendarProfesorView.setVisible(false);
        }
    }

    class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String numeUtilizator = logareView.getNumeUtilizator();
            calendarProfesorModel.setIdProfesor(numeUtilizator);

            String dataInceput = calendarProfesorView.getDataInceput();
            calendarProfesorModel.setDataInceput(dataInceput);

            String dataSfarsit = calendarProfesorView.getDataFinal();
            calendarProfesorModel.setDataSfarsit(dataSfarsit);

            int nrMaxmimParticipanti = calendarProfesorView.getNrMaximParticipanti();
            calendarProfesorModel.setNrMaximParticipanti(nrMaxmimParticipanti);

            String descriere = calendarProfesorView.getDescriere();
            calendarProfesorModel.setDescriere(descriere);


            selectareIdDisciplina();
            selectareIdActivitate();

            boolean succesInserare = calendarProfesorModel.insertCalendar();
            if (succesInserare) {
                JOptionPane.showMessageDialog(calendarProfesorView, "Evenimentul a fost creat cu succes!");
            }
            else{
                JOptionPane.showMessageDialog(calendarProfesorView, "Evenimentul nu a fost creat!");
            }

        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //populareDisciplina();
            populareActivitate();
        }
    }

}
