package model;
import java.sql.*;

public class UtilizatorModel {

    //utilizator
    private int id_rol;
    private String nume_utilizator;
    private String parola;

    //utilizator_info
    private int id_utilizator;
    private String CNP;
    private String nume;
    private String prenume;
    private String adresa;
    private String numar_telefon;
    private String email;
    private String iban;
    private String nr_contract;

    //profesor
    private int id_departament;
    private int nr_min_ore;
    private int nr_max_ore;

    //student
    private int an_studiu;
    private int nr_ore;

    ///UTILIZATOR
    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public void setNume_utilizator(String nume_utilizator) {
        this.nume_utilizator = nume_utilizator;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }


    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }

    ///UTILIZATOR_INFO
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

    public void setNumar_telefon(String numar_telefon) {
        this.numar_telefon = numar_telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setNr_contract(String nr_contract) {
        this.nr_contract = nr_contract;
    }
    ///PROFESOR/STUDENT
    public void setId_departament(int id_departament) {
        this.id_departament = id_departament;
    }

    public void setNr_min_ore(int nr_min_ore) {
        this.nr_min_ore = nr_min_ore;
    }

    public void setAn_studiu(int an_studiu) {
        this.an_studiu = an_studiu;
    }

    public void setNr_max_ore(int nr_max_ore) {
        this.nr_max_ore = nr_max_ore;
    }

    public void setNr_ore(int nr_ore) {
        this.nr_ore = nr_ore;
    }

    public boolean creareUtilizator()
    {

        String query = "INSERT INTO Utilizator (id_rol, nume_utilizator, parola) VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setInt(1, id_rol);
            preparedStatement.setString(2, nume_utilizator);
            preparedStatement.setString(3, parola);

            int rows = preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                 id_utilizator = generatedKeys.getInt(1);
            }


            if (rows > 0)
                return true;

            else
                return false;


        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

    }

    public boolean creareUtilizatorInfo(){
        String query = "INSERT INTO Utilizator_Info (id_utilizator, CNP, nume, prenume, adresa, numar_telefon, email, iban, nr_contract) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query))
        {
            preparedStatement.setInt(1, id_utilizator);
            preparedStatement.setString(2, CNP);
            preparedStatement.setString(3, nume);
            preparedStatement.setString(4, prenume);
            preparedStatement.setString(5, adresa);
            preparedStatement.setString(6, numar_telefon);
            preparedStatement.setString(7, email);
            preparedStatement.setString(8, iban);
            preparedStatement.setString(9, nr_contract);

            int rows = preparedStatement.executeUpdate();

            if (rows > 0)
                return true;

            else
                return false;

        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

    }

    public boolean creareProfesor()
    {
        String query = "INSERT INTO Profesor (id_departament, id_utilizator, nr_minim_ore, nr_maxim_ore) VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query))

        {
            preparedStatement.setInt(1, id_departament);
            preparedStatement.setInt(2, id_utilizator);
            preparedStatement.setInt(3, nr_min_ore);
            preparedStatement.setInt(4, nr_max_ore);

            int rows = preparedStatement.executeUpdate();

            if (rows > 0)
                return true;

            else
                return false;

        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean creareStudent()
    {
        String query = "INSERT INTO Student (id_utilizator, an_studiu, numar_ore) VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query))

        {
            preparedStatement.setInt(1, id_utilizator);
            preparedStatement.setInt(2, an_studiu);
            preparedStatement.setInt(3, nr_ore);

            int rows = preparedStatement.executeUpdate();

            if (rows > 0)
                return true;

            else
                return false;

        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
