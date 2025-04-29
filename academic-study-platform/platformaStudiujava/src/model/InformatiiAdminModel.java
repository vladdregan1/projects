package model;
import java.sql.*;

public class InformatiiAdminModel {

    int idUtilizator;
    String CNP;
    String nume;
    String prenume;
    String adresa;
    String numarTelefon;
    String email;
    String iban;
    String nrContract;

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }

    public void setIdUtilizator(String numeUtilizator)
    {
        String query = "SELECT id_utilizator FROM Utilizator WHERE nume_utilizator = ?";

        try(Connection con = getConnection())
        {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, numeUtilizator);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                idUtilizator = resultSet.getInt("id_utilizator");
                //System.out.println(idUtilizator);
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean modificareInformatii(){

        String query = "UPDATE Utilizator_Info SET CNP = ?, nume = ?, prenume = ?, adresa = ?, numar_telefon = ?, email = ?, iban = ?, nr_contract = ? WHERE id_utilizator = ?";

        try(Connection con = getConnection())
        {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, CNP);
            preparedStatement.setString(2, nume);
            preparedStatement.setString(3, prenume);
            preparedStatement.setString(4, adresa);
            preparedStatement.setString(5, numarTelefon);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, iban);
            preparedStatement.setString(8, nrContract);
            preparedStatement.setInt(9, idUtilizator);
            preparedStatement.executeUpdate();
            int rows = preparedStatement.executeUpdate();

            if (rows > 0)
                return true;

            else
                return false;

        }catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setNrContract(String nrContract) {
        this.nrContract = nrContract;
    }

    /*    public static void main(String[] args) {
        InformatiiAdminModel model = new InformatiiAdminModel();
        model.getIdUtilizator("VladD");
    } */

}
