package controller;

import model.CalendarStudentModel;
import view.CalendarStudentView;
import view.DupaLogareViewStudent;
import view.LogareView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalendarStudentController {

    private CalendarStudentView calendarStudentView;
    private DupaLogareViewStudent dupaLogareViewStudent;
    private CalendarStudentModel calendarStudentModel;
    private LogareView logareView;

    public CalendarStudentController(CalendarStudentView calendarStudentView, DupaLogareViewStudent dupaLogareViewStudent, CalendarStudentModel calendarStudentModel, LogareView logareView) {
        this.calendarStudentView = calendarStudentView;
        this.dupaLogareViewStudent = dupaLogareViewStudent;
        this.calendarStudentModel = calendarStudentModel;
        this.logareView = logareView;

        this.calendarStudentView.addBackButtonListener(new BackListener());
        this.calendarStudentView.addInscriereButtonListener(new InscriereListener());

        loadCalendarStudentList();

    }

    class InscriereListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int CalendarId = calendarStudentView.getSelectedIdCalendar();
            int StudentId = calendarStudentModel.getStudentId(logareView.getNumeUtilizator());


            // Obține data curentă
            LocalDateTime now = LocalDateTime.now();

            // Formatează data în formatul dorit
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dataInscriere = now.format(formatter);


            //System.out.println("Calendar ID: " + CalendarId + ", Student ID: " + StudentId + ", Data Înscriere: " + dataInscriere);

            if (calendarStudentModel.verificareLocuriInserare(CalendarId) == true){

                boolean succesInscriere = calendarStudentModel.inserareStudent(CalendarId, StudentId, dataInscriere);
                if (succesInscriere)
                {
                    JOptionPane.showMessageDialog(calendarStudentView, "Inscriere cu succes!");
                }
                else JOptionPane.showMessageDialog(calendarStudentView, "Inscrierea a esuat!");

            }

            else JOptionPane.showMessageDialog(calendarStudentView, "Locuri indisponibile pentru inscriere!");

        }
    }


    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dupaLogareViewStudent.setVisible(true);
            calendarStudentView.setVisible(false);
        }
    }

    public void loadCalendarStudentList() {
        List<String[]> CalendarDetails = calendarStudentModel.getCalendarDetails();
        calendarStudentView.setCalendarList(CalendarDetails);
    }
}
