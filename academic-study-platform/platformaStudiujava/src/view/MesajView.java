package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MesajView extends JFrame {

    private JTextArea mesajArea; // Zonă pentru afișarea mesajelor
    private JTextField mesajField; // Câmp pentru introducerea unui mesaj nou
    private JButton trimiteButton; // Buton pentru trimiterea mesajelor
    private JButton inapoiButton; // Buton pentru întoarcerea la ecranul anterior

    public MesajView() {
        setTitle("Mesagerie");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Zonă de afișare a mesajelor
        mesajArea = new JTextArea();
        mesajArea.setEditable(false); // Mesajele afișate nu pot fi editate de utilizator
        mesajArea.setLineWrap(true); // Mesajele lungi vor fi împărțite pe mai multe linii
        mesajArea.setWrapStyleWord(true); // Cuvintele nu vor fi tăiate
        JScrollPane scrollPane = new JScrollPane(mesajArea);

        // Panou pentru introducerea mesajelor
        JPanel inputPanel = new JPanel(new BorderLayout());
        mesajField = new JTextField();
        trimiteButton = new JButton("Trimite");
        inapoiButton = new JButton("Înapoi");

        inputPanel.add(mesajField, BorderLayout.CENTER);
        inputPanel.add(trimiteButton, BorderLayout.EAST);

        // Panou suplimentar pentru butonul "Înapoi"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(inapoiButton);

        // Adăugarea componentelor în fereastră
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER); // Zonă principală cu mesajele
        add(inputPanel, BorderLayout.SOUTH); // Zona de introducere a mesajelor
        add(buttonPanel, BorderLayout.NORTH); // Butonul de "Înapoi"

        //setVisible(true);
    }

    // Metodă pentru afișarea unui mesaj nou
    public void addMesaj(String mesaj) {
        mesajArea.append(mesaj + "\n");
    }

    // Metodă pentru golirea câmpului de introducere
    public void clearMesajField() {
        mesajField.setText("");
    }

    // Metodă pentru a obține textul din câmpul de introducere
    public String getMesajFieldText() {
        return mesajField.getText();
    }

    // Adăugare listener pentru butonul "Trimite"
    public void addTrimiteButtonListener(ActionListener listener) {
        trimiteButton.addActionListener(listener);
    }

    // Adăugare listener pentru butonul "Înapoi"
    public void addInapoiButtonListener(ActionListener listener) {
        inapoiButton.addActionListener(listener);
    }
}
