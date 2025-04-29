package controller;

import model.LogareModel;
import view.DupaLogareView;
import view.DupaLogareViewStudent;
import view.InformatiiView;
import view.LogareView;
import model.InformatiiModel;

import java.awt.*;
import java.awt.event.*;

public class DupaLogareController {
    private DupaLogareView dupaLogareView;
    private LogareView logareView;
    private InformatiiView informatiiView;
    private InformatiiModel informatiiModel;
    private LogareModel logareModel;


    public DupaLogareController(DupaLogareView dupaLogareView, LogareView logareView, InformatiiView informatiiView, InformatiiModel informatiiModel, LogareModel logareModel) {
        this.dupaLogareView = dupaLogareView;
        this.logareView = logareView;
        this.informatiiView = informatiiView;
        this.informatiiModel = informatiiModel;
        this.logareModel = logareModel;
        this.dupaLogareView.addDelogareButtonListener(new DelogareListener());
        this.dupaLogareView.addInfoButtonListener(new InfoListener());

    }

    class DelogareListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dupaLogareView.setVisible(false);
            logareView.setVisible(true);
        }
    }

    class InfoListener implements ActionListener {
        @Override

        public void actionPerformed(ActionEvent e) {
            InformatiiController informatiiController = new InformatiiController(informatiiView, dupaLogareView, informatiiModel);
            //System.out.println(logareView.getNumeUtilizator());
            InformatiiModel informatiiModel = new InformatiiModel(logareView.getNumeUtilizator());
            dupaLogareView.setVisible(false);
            informatiiView.setVisible(true);
        }
    }


}
