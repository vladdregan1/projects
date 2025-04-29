package model;
import java.sql.*;

import view.LogareView;

public class InformatiiModel {

    private int id;
    private String numeUtilizator;
    private String nume;
    private String prenume;
    private String CNP;
    private String adresa;
    private String numarTelefon;
    private String email;
    private String iban;
    private String nrContract;


    public InformatiiModel(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }


    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }


    public boolean getID(){
        //this.numeUtilizator = "VladD";

        String query = "SELECT id_utilizator FROM Utilizator WHERE nume_utilizator = ?";

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query))
        {
            //System.out.println(numeUtilizator);

            preparedStatement.setString(1, numeUtilizator);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                this.id = resultSet.getInt("id_utilizator");
                return true;
            }
            else {
                System.out.println("ID-ul nu a fost gasit");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean SelectInfo()
    {
        String query = "SELECT nume, prenume, CNP, adresa, numar_telefon, email, iban, nr_contract FROM Utilizator_Info WHERE id_utilizator = ?";

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query))
        {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                this.nume = resultSet.getString("nume");
                this.prenume = resultSet.getString("prenume");
                this.CNP = resultSet.getString("CNP");
                this.adresa = resultSet.getString("adresa");
                this.numarTelefon = resultSet.getString("numar_telefon");
                this.email = resultSet.getString("email");
                this.iban = resultSet.getString("iban");
                this.nrContract = resultSet.getString("nr_contract");
                return true;
            } else {
                return false;
            }


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getCNP() {
        return CNP;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public String getEmail() {
        return email;
    }

    public String getIban() {
        return iban;
    }

    public String getNrContract() {
        return nrContract;
    }
}
