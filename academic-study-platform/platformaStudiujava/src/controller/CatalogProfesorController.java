package controller;

import model.CatalogProfesorModel;
import view.CatalogProfesorView;
import view.DupaLogareProfesorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CatalogProfesorController {
    private CatalogProfesorView catalogProfesorView;
    private DupaLogareProfesorView dupaLogareProfesorView;
    private CatalogProfesorModel catalogProfesorModel;

    public CatalogProfesorController(CatalogProfesorView catalogProfesorView, DupaLogareProfesorView dupaLogareProfesorView, CatalogProfesorModel catalogProfesorModel) {
        this.catalogProfesorView = catalogProfesorView;
        this.dupaLogareProfesorView = dupaLogareProfesorView;
        this.catalogProfesorModel = catalogProfesorModel;

        this.catalogProfesorView.addBackButtonListener(new BackListener());
        this.catalogProfesorView.addSubmitButtonListener(new SubmitListener());
        this.catalogProfesorView.addRefreshButtonListener(new RefreshListener());

        populareDisciplina();
        populareStudenti();
        populareActivitati();
    }

    public void populareDisciplina(){
        String[] discipline = catalogProfesorModel.getDisciplina();
        catalogProfesorView.setDiscipline(discipline);
    }

    public void populareStudenti(){
        String disciplinaSelectata = catalogProfesorView.getSelectedDisciplina();
        catalogProfesorModel.setIdDisciplina(disciplinaSelectata);
        String[] numeStudenti = catalogProfesorModel.getNumeStudenti();
        catalogProfesorView.setStudenti(numeStudenti);
    }

    public void populareActivitati(){
        String disciplinaSelectata = catalogProfesorView.getSelectedDisciplina();
        //System.out.println(disciplinaSelectata);
        catalogProfesorModel.setIdDisciplina(disciplinaSelectata);
        String[] activitatiNume = catalogProfesorModel.getActivitati();
        //for (int i = 0; i < activitatiNume.length; i++)
            //System.out.println(activitatiNume[i]);
        catalogProfesorView.setTipuriActivitate(activitatiNume);

    }

    public void selectareIdDisciplina(){
        String Disciplina = catalogProfesorView.getSelectedDisciplina();
        catalogProfesorModel.setIdDisciplina(Disciplina);
    }

    public void selectareIdStudent(){
        String Student = catalogProfesorView.getSelectedStudent();
        catalogProfesorModel.setIdStudent(Student);
    }

    public void selectareIdActivitate(){
        String Activitate = catalogProfesorView.getSelectedTipActivitate();
        catalogProfesorModel.setIdActivitate(Activitate);
    }

    public void selectareData()
    {
        String data = catalogProfesorView.getData();
        catalogProfesorModel.setData(data);
    }

    public void selectareNota(){
        float nota = catalogProfesorView.getNota();
        catalogProfesorModel.setNota(nota);
    }

    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dupaLogareProfesorView.setVisible(true);
            catalogProfesorView.setVisible(false);
        }
    }

    class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            selectareIdDisciplina();
            selectareIdStudent();
            selectareIdActivitate();
            selectareData();
            selectareNota();
            boolean succesInserare = catalogProfesorModel.insertNota();
            if (succesInserare) {
                JOptionPane.showMessageDialog(catalogProfesorView, "Nota a fost inserata cu succes!");
            } else {
                JOptionPane.showMessageDialog(catalogProfesorView, "Nota nu a fost inserata!");
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //populareDisciplina();
            populareStudenti();
            populareActivitati();
        }
    }
}
