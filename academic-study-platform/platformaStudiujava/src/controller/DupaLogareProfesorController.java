package controller;


import model.*;
import view.*;

import java.awt.*;
import java.awt.event.*;


public class DupaLogareProfesorController extends DupaLogareController {
    private CreareActivitateView creareActivitateView;
    private DupaLogareProfesorView dupaLogareProfesorView;
    private LogareView logareView;
    private AfisareActivitateProfesorView afisareActivitateProfesorView;
    private CatalogProfesorView catalogProfesorView;
    private CalendarProfesorView calendarProfesorView;



    public DupaLogareProfesorController(DupaLogareProfesorView dupaLogareProfesorView, LogareView logareView, InformatiiView informatiiView, InformatiiModel informatiiModel, LogareModel logareModel, CreareActivitateView creareActivitateView, AfisareActivitateProfesorView afisareActivitateProfesorView, CatalogProfesorView catalogProfesorView, CalendarProfesorView calendarProfesorView) {
        super(dupaLogareProfesorView, logareView, informatiiView, informatiiModel, logareModel);

       this.creareActivitateView = creareActivitateView;
       this.dupaLogareProfesorView = dupaLogareProfesorView;
       this.logareView = logareView;
       this.afisareActivitateProfesorView = afisareActivitateProfesorView;
       this.catalogProfesorView = catalogProfesorView;
       this.calendarProfesorView = calendarProfesorView;
       this.dupaLogareProfesorView.addActivitateButtonListener(new ActivitateButtonListener());
       this.dupaLogareProfesorView.addShowActivitateButtonListener(new ShowActivitateButtonListener());
       this.dupaLogareProfesorView.addCatalogProfesorButtonListener(new CatalogProfesorListener());
       this.dupaLogareProfesorView.addCalendarProfesorButtonListener(new CalendarProfesorListener());

    }

    class ActivitateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CreareActivitateController creareActivitateController = new CreareActivitateController(creareActivitateView, dupaLogareProfesorView, new CreareActivitateModel(), logareView);
            creareActivitateView.setVisible(true);
            dupaLogareProfesorView.setVisible(false);
        }
    }

    class ShowActivitateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AfisareActivitateProfesorController afisareActivitateProfesorController = new AfisareActivitateProfesorController(afisareActivitateProfesorView, dupaLogareProfesorView, new AfisareActivitateProfesorModel(), logareView);
            afisareActivitateProfesorView.setVisible(true);
            dupaLogareProfesorView.setVisible(false);
        }
    }

    class CatalogProfesorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CatalogProfesorController catalogProfesorController = new CatalogProfesorController(catalogProfesorView, dupaLogareProfesorView, new CatalogProfesorModel());
            catalogProfesorView.setVisible(true);
            dupaLogareProfesorView.setVisible(false);
        }
    }

    class CalendarProfesorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CalendarProfesorController calendarProfesorController = new CalendarProfesorController(calendarProfesorView, dupaLogareProfesorView, new CalendarProfesorModel(), logareView);
            calendarProfesorView.setVisible(true);
            dupaLogareProfesorView.setVisible(false);
        }
    }
}
