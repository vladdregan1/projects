package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import view.DupaLogareView;

public class DupaLogareProfesorView extends DupaLogareView {
    private JButton activitateButton = new JButton("Creare Activitate");
    private JButton showActivitateButton = new JButton("Afisare Activitati");
    private JButton catalogProfesorButton = new JButton("Catalog");
    private JButton calendarProfesorButton = new JButton("Calendar");

    public DupaLogareProfesorView() {
        super();
        mainPanel.add(activitateButton);
        mainPanel.add(showActivitateButton);
        mainPanel.add(catalogProfesorButton);
        mainPanel.add(calendarProfesorButton);
    }

    public void addActivitateButtonListener(ActionListener listener) {
        activitateButton.addActionListener(listener);
    }

    public void addShowActivitateButtonListener(ActionListener listener) {
        showActivitateButton.addActionListener(listener);
    }

    public void addCatalogProfesorButtonListener(ActionListener listener) {
        catalogProfesorButton.addActionListener(listener);
    }

    public void addCalendarProfesorButtonListener(ActionListener listener) {
        calendarProfesorButton.addActionListener(listener);
    }
}
