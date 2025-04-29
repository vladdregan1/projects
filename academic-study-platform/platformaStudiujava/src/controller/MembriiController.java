package controller;

import model.MembriiModel;
import view.ActivitateGrupView;
import view.MembriiView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MembriiController {

    private MembriiView membriiView;
    private ActivitateGrupView activitateGrupView;
    private MembriiModel membriiModel;

    public MembriiController(MembriiView membriiView, ActivitateGrupView activitateGrupView, MembriiModel membriiModel) {
        this.membriiView = membriiView;
        this.activitateGrupView = activitateGrupView;
        this.membriiModel = membriiModel;

        this.membriiView.addBackButtonListener(new BackListener());
        loadGrupList();
    }

    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            activitateGrupView.setVisible(true);
            membriiView.setVisible(false);
        }
    }

    public void loadGrupList() {
        List<String[]> membriiList = membriiModel.getMembrii(activitateGrupView.getSelectedIdActivitate());
        membriiView.setMemList(membriiList);
    }

}
