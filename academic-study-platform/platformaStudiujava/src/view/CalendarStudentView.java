package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;

public class CalendarStudentView extends JFrame {

    private JButton backButton = new JButton("Inapoi");
    private JButton inscriereButton = new JButton("Inscriere");
    private JList<String> calendarList;

    public CalendarStudentView() {
        setTitle("Calendar");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        calendarList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(calendarList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(inscriereButton, BorderLayout.NORTH);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        add(mainPanel);

    }

    public void setCalendarList(List<String[]> calendarDetails) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String[] details : calendarDetails) {
            String displayText = details[0] + " - " + details[1] + " - " + details[2] + " - " + details[3] + " - " + details[4] + " - " + details[5] + " - " + details[6] + " - " + details[7];
            listModel.addElement(displayText);
        }
        calendarList.setModel(listModel);

    }

    public int getSelectedIdCalendar() {
        String selectedValue = calendarList.getSelectedValue();
        if (selectedValue != null) {
            return Integer.parseInt(selectedValue.split(" - ")[0]);
        }
        return -1;
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void addInscriereButtonListener(ActionListener listener) {
        inscriereButton.addActionListener(listener);
    }
}
