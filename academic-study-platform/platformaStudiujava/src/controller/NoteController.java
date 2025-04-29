package controller;

import model.NoteModel;
import view.DupaLogareViewStudent;
import view.LogareView;
import view.NoteView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.*;

public class NoteController {

    private NoteView noteView;
    private DupaLogareViewStudent dupaLogareViewStudent;
    private NoteModel noteModel;
    private LogareView logareView;

    public NoteController(NoteView noteView, DupaLogareViewStudent dupaLogareViewStudent, NoteModel noteModel, LogareView logareView) {
        this.noteView = noteView;
        this.dupaLogareViewStudent = dupaLogareViewStudent;
        this.noteModel = noteModel;
        this.logareView = logareView;
        this.noteView.addBackButtonListener(new BackListener());
        this.noteView.addDescarcaButtonListener(new DescarcaListener());

        loadNoteList();
        loadNoteFinaleList();
    }

    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dupaLogareViewStudent.setVisible(true);
            noteView.setVisible(false);
        }
    }

    public void loadNoteList() {
        noteModel.setNumeUtilizator(logareView.getNumeUtilizator());
        List<String[]> note = noteModel.getNote();
        noteView.setNoteList(note);
    }

    public void loadNoteFinaleList() {
        noteModel.setNumeUtilizator(logareView.getNumeUtilizator());
        List<String[]> note = noteModel.getNoteFinale();
        noteView.setNoteFinaleList(note);
    }

    class DescarcaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            noteModel.setNumeUtilizator(logareView.getNumeUtilizator());
            List<String[]> note = noteModel.getNote();

            // Locație și nume de fișier predefinite
            String filePath = "note.csv"; // Fișierul va fi creat în directorul curent al aplicației

            try {
                // Scrierea în fișier
                try (FileWriter fileWriter = new FileWriter(filePath);
                     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                    // Scrie antetele coloanelor
                    bufferedWriter.write("Materie,Activitate,Nota\n");

                    // Scrie fiecare notă în fișier
                    for (String[] nota : note) {
                        String line = String.join(",", nota); // Concatenează cu separatorul ","
                        bufferedWriter.write(line);
                        bufferedWriter.newLine();
                    }
                }

                // Notificare de succes
                JOptionPane.showMessageDialog(noteView, "Fișier salvat cu succes în " + filePath);
            } catch (Exception ex) {
                // Gestionare eroare
                JOptionPane.showMessageDialog(noteView, "Eroare la salvarea fișierului: " + ex.getMessage());
            }
        }
    }



}
