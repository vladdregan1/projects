package controller;

import view.AdaugaAdminView;
import view.DupaLogareSuperAdminView;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class AdaugaAdminController {

    private AdaugaAdminView adaugaAdminView;
    private DupaLogareSuperAdminView dupaLogareSuperAdminView;
    private String[] adminUser;
    private String[] adminPass;

    public AdaugaAdminController(AdaugaAdminView adaugaAdminView, DupaLogareSuperAdminView dupaLogareSuperAdminView, String[] adminUser, String[] adminPass) {
        this.adaugaAdminView = adaugaAdminView;
        this.dupaLogareSuperAdminView = dupaLogareSuperAdminView;
        this.adminUser = adminUser;
        this.adminPass = adminPass;
        this.adaugaAdminView.addCreareAdminListener(new CreareListener());
        this.adaugaAdminView.addInapoiButtonListener(new InapoiListener());
        this.adaugaAdminView.addStergereAdminListener(new StergereListener());
    }

    class InapoiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            adaugaAdminView.setVisible(false);
            dupaLogareSuperAdminView.setVisible(true);
        }
    }

    class CreareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userName = adaugaAdminView.getUsername();
            String userPass = adaugaAdminView.getPassword();

            String[] adminUser = AdminData.getAdminUser();
            String[] adminPass = AdminData.getAdminPass();

            int n = 0;
            while (adminUser[n] != null) n++;


            if (n < adminUser.length) {
                adminUser[n] = userName;
                adminPass[n] = userPass;
                System.out.println(Arrays.toString(adminUser));
                System.out.println(Arrays.toString(adminPass));
                adaugaAdminView.showMessage("Creare admin cu succes!");
            } else {
                adaugaAdminView.showMessage("Nu mai există spațiu pentru noi administratori.");
            }
        }
    }

    class StergereListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userName = adaugaAdminView.getUsername();
            String userPass = adaugaAdminView.getPassword();

            String[] adminUser = AdminData.getAdminUser();
            String[] adminPass = AdminData.getAdminPass();

            int n = adminUser.length;
            boolean found = false;

            // Găsirea și ștergerea utilizatorului
            for (int i = 0; i < n; i++) {
                if (adminUser[i] != null && adminUser[i].equals(userName) && adminPass[i].equals(userPass)) {
                    found = true;
                    // Deplasarea elementelor spre stânga
                    for (int j = i; j < n - 1; j++) {
                        adminUser[j] = adminUser[j + 1];
                        adminPass[j] = adminPass[j + 1];
                    }
                    // Eliminarea ultimei referințe
                    adminUser[n - 1] = null;
                    adminPass[n - 1] = null;
                    break; // Oprește iterarea după ștergere
                }
            }

            System.out.println(Arrays.toString(adminUser));
            System.out.println(Arrays.toString(adminPass));

            // Mesaj de confirmare sau eroare
            if (found) {
                adaugaAdminView.showMessage("Ștergere admin cu succes!");
            } else {
                adaugaAdminView.showMessage("Adminul specificat nu a fost găsit.");
            }
        }
    }




}
