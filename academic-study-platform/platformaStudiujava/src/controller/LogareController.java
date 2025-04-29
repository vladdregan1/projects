package controller;

import model.*;
import view.*;

import java.awt.*;
import java.awt.event.*;




public class LogareController {

    private LogareModel model;
    private LogareView view;
    private Frame mainFrame;

    public LogareController(LogareView view, LogareModel model ,Frame mainFrame) {
        this.view = view;
        this.model = model;
        this.mainFrame = mainFrame;
        this.view.addLogareButtonListener(new LogareListener());
        this.view.addBackButtonListener(new BackListener(mainFrame));
    }

    class LogareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String numeUtilizator = view.getNumeUtilizator();
            String parola = view.getParola();

            model.setNumeUtilizator(numeUtilizator);
            model.setParola(parola);

            String superAdminUser = "SuperAdmin";
            String superAdminPass = "putere";

            //String[] adminUser = {"admin1", "admin2"};
            //String[] adminPass = {"platforma123", "platforma10"};

            String[] adminUser = AdminData.getAdminUser();
            String[] adminPass = AdminData.getAdminPass();

            int n = 0;
            while (adminUser[n] != null) n++;


           /* for (int i = 2; i < adminUser.length; i++)
            {
                adminUser[i] = null;
                adminPass[i] = null;
            } */

          /*  adminUser[2] = "admin3";
            adminPass[2] = "PASSWORD";
            n++; */



            int okAdmin = 0;

            boolean succesLogare = model.Logare();

            for (int i = 0; i < n; i++)
            {
                if (adminUser[i].equals(numeUtilizator) && adminPass[i].equals(parola)) {
                    okAdmin = 1;
                    succesLogare = true;
                }
            }

            int okSuperAdmin = 0;
            if (numeUtilizator.equals(superAdminUser) && parola.equals(superAdminPass))
            {
                okSuperAdmin = 1;
                succesLogare = true;
            }


            if (succesLogare) {

                //view.showMessage("Logare cu succes!");

                if (okSuperAdmin == 1){
                    DupaLogareSuperAdminView dupaLogareSuperAdminView = new DupaLogareSuperAdminView();
                    DupaLogareSuperAdminController dupaLogareSuperAdminController = new DupaLogareSuperAdminController(dupaLogareSuperAdminView, view, new AdaugaAdminView(), adminUser, adminPass);
                    dupaLogareSuperAdminView.setVisible(true);
                    view.setVisible(false);

                }

                else if (okAdmin == 1){
                    DupaLogareAdminView dupaLogareAdminView = new DupaLogareAdminView();
                    DupaLogareAdminController dupaLogareAdminController = new DupaLogareAdminController(dupaLogareAdminView, view, new InformatiiAdminView());
                    dupaLogareAdminView.setVisible(true);
                    view.setVisible(false);

                }
                else {

                    int id_rol = model.getId_rol();
                    if (id_rol == 2) {
                        DupaLogareProfesorView dupaLogareProfesorView = new DupaLogareProfesorView();
                        dupaLogareProfesorView.setVisible(true);
                        DupaLogareProfesorController dupaLogareProfesorController = new DupaLogareProfesorController(dupaLogareProfesorView, view, new InformatiiView(), new InformatiiModel(model.getNumeUtilizator()), model, new CreareActivitateView(), new AfisareActivitateProfesorView(), new CatalogProfesorView(), new CalendarProfesorView());
                    } else {
                        DupaLogareViewStudent dupaLogareViewStudent = new DupaLogareViewStudent();
                        dupaLogareViewStudent.setVisible(true);
                        DupaLogareStudentController dupaLogareStudentController = new DupaLogareStudentController(dupaLogareViewStudent, view, new InformatiiView(), new InformatiiModel(model.getNumeUtilizator()), model, new DisciplinaView(), new DisciplinaModel(), new AfisareActivitateView(), new NoteView(), new CalendarStudentView(), new GrupView(), new CreareGrupView());
                    }

                    view.setVisible(false);
                }

            }
            else view.showMessage("Eroare la logare!");

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

    /* public static void main(String[] args) {
        String[] adminUser = {"admin1", "admin2"};
        System.out.println(adminUser[0] + " " + adminUser[1]);
    } */

}
