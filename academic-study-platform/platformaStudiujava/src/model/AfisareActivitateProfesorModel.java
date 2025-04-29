package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AfisareActivitateProfesorModel {

    private String numeUtilizator;

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }

    public List<String[]> getActivitati() {
        List<String[]> activitati = new ArrayList<>();

        String queryIdUtilizator = "SELECT id_utilizator FROM Utilizator WHERE nume_utilizator = ?";
        String queryIdProfesor = "SELECT id_profesor FROM Profesor WHERE id_utilizator = ?";
        String queryActivitate = "SELECT id_disciplina, tip_activitate, data_inceput, data_sfarsit, frecventa, procent FROM Activitate WHERE id_profesor = ?";
        String queryNumeDisciplina = "SELECT nume_disciplina FROM Disciplina WHERE id_disciplina = ?";

        try (Connection con = getConnection())
        {
            ///ID UTILIZATOR
            //numeUtilizator = "DorianP";
            PreparedStatement preparedStatement = con.prepareStatement(queryIdUtilizator);
            preparedStatement.setString(1, numeUtilizator);
            ResultSet resultSet = preparedStatement.executeQuery();
            int idUtilizator = -1;
            if (resultSet.next()) {
                idUtilizator = resultSet.getInt("id_utilizator");
            }
            //System.out.println(idUtilizator);

            ///ID PROFESOR
            PreparedStatement preparedStatement2 = con.prepareStatement(queryIdProfesor);
            preparedStatement2.setInt(1, idUtilizator);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            int idProfesor = -1;
            if (resultSet2.next()) {
                idProfesor = resultSet2.getInt("id_profesor");
            }
            //System.out.println(idProfesor);

            ///PRIMIRE DATE
            String numeDisciplina = "";
            int idDisciplina = -1;
            String tipActivitate = "";
            String dataInceput = "";
            String dataSfarsit = "";
            String frecventa = "";
            int procent = -1;
            PreparedStatement preparedStatement3 = con.prepareStatement(queryActivitate);
            preparedStatement3.setInt(1, idProfesor);
            ResultSet resultSet3 = preparedStatement3.executeQuery();
            while (resultSet3.next()) {
                idDisciplina = resultSet3.getInt("id_disciplina");
                tipActivitate = resultSet3.getString("tip_activitate");
                dataInceput = resultSet3.getString("data_inceput");
                dataSfarsit = resultSet3.getString("data_sfarsit");
                frecventa = resultSet3.getString("frecventa");
                procent = resultSet3.getInt("procent");

                PreparedStatement preparedStatement4 = con.prepareStatement(queryNumeDisciplina);
                preparedStatement4.setInt(1, idDisciplina);
                ResultSet resultSet4 = preparedStatement4.executeQuery();
                if (resultSet4.next()) {
                    numeDisciplina = resultSet4.getString("nume_disciplina");
                }

                activitati.add(new String[]{
                        numeDisciplina,
                        tipActivitate,
                        dataInceput,
                        dataSfarsit,
                        frecventa,
                        "procent nota finala: " + procent
                });

               // System.out.println(numeDisciplina + " " + tipActivitate + " " + dataInceput + " " + dataSfarsit + " " + frecventa + " " + procent);
            }



        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return activitati;
    }

  /*  public static void main(String[] args) {
        AfisareActivitateProfesorModel model = new AfisareActivitateProfesorModel();
        model.getActivitati();
    } */

}
