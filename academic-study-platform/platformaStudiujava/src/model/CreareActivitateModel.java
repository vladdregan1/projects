package model;
import java.sql.*;

public class CreareActivitateModel {

    private int idDisciplina;
    private String numeDisciplina;
    private String numeUtilizator;
    private int idProfesor;
    private String tipActivitate;
    private String dataInceput;
    private String dataSfarsit;
    private String frecventa;
    private int procent;

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public void setNumeDisciplina(String numeDisciplina) {
        this.numeDisciplina = numeDisciplina;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public void setTipActivitate(String tipActivitate) {
        this.tipActivitate = tipActivitate;
    }

    public void setDataInceput(String dataInceput) {
        this.dataInceput = dataInceput;
    }

    public void setDataSfarsit(String dataSfarsit) {
        this.dataSfarsit = dataSfarsit;
    }

    public void setFrecventa(String frecventa) {
        this.frecventa = frecventa;
    }

    public void setProcent(int procent) {
        this.procent = procent;
    }

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    public boolean CreareActivitate()
    {
        String queryIdDisciplina = "SELECT id_disciplina FROM Disciplina WHERE nume_disciplina = ?";
        String queryIdUtilizator = "SELECT id_utilizator FROM Utilizator WHERE nume_utilizator = ?";
        String queryIdProfesor = "SELECT id_profesor FROM Profesor WHERE id_utilizator = ?";
        String queryInsert = "INSERT INTO Activitate (id_disciplina, id_profesor, tip_activitate, data_inceput, data_sfarsit, frecventa, procent) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection())
        {
            ///AFLARE ID_DISCIPLINA
            //numeDisciplina = "Analiza Matematica";
            PreparedStatement preparedStatement = con.prepareStatement(queryIdDisciplina);
            preparedStatement.setString(1, numeDisciplina);
            ResultSet resultSet = preparedStatement.executeQuery();
            idDisciplina = -1;
            if (resultSet.next()) {
                idDisciplina = resultSet.getInt("id_disciplina");
               // System.out.println(idDisciplina);
            }

            if (idDisciplina == -1) {
                //System.out.println("Nu s-a gasit disciplina.");
                return false;
            }

            ///AFLARE ID_UTILIZATOR
            //numeUtilizator = "DorianP";
            PreparedStatement preparedStatement2 = con.prepareStatement(queryIdUtilizator);
            preparedStatement2.setString(1, numeUtilizator);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            int idUtilizator = -1;
            if (resultSet2.next()) {
                idUtilizator = resultSet2.getInt("id_utilizator");
                System.out.println(idUtilizator);
            }

            if (idUtilizator == -1) {
                //System.out.println("Nu s-a gasit utilizator.");
                return false;
            }

            ///AFLARE ID PROFESOR
            PreparedStatement preparedStatement3 = con.prepareStatement(queryIdProfesor);
            preparedStatement3.setInt(1, idUtilizator);
            ResultSet resultSet3 = preparedStatement3.executeQuery();
            idProfesor = -1;
            if (resultSet3.next()) {
                idProfesor = resultSet3.getInt("id_profesor");
                System.out.println(idProfesor);
            }

            if (idProfesor == -1) {
                System.out.println("Nu s-a gasit profesor.");
                return false;
            }

            ///INSERARE
            PreparedStatement preparedStatement4 = con.prepareStatement(queryInsert);
            preparedStatement4.setInt(1, idDisciplina);
            preparedStatement4.setInt(2, idProfesor);
            preparedStatement4.setString(3, tipActivitate);
            preparedStatement4.setString(4, dataInceput);
            preparedStatement4.setString(5, dataSfarsit);
            preparedStatement4.setString(6, frecventa);
            preparedStatement4.setInt(7, procent);

            int rows = preparedStatement4.executeUpdate();

            if (rows > 0)
                return true;
            else return false;

        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
