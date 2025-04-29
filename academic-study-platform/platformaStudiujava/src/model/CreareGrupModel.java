package model;

import java.sql.*;


public class CreareGrupModel {


    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }

    public boolean creare(String numeDisciplina, String numeGrup)
    {
        String queryIdDisciplina = "SELECT id_disciplina FROM Disciplina WHERE nume_disciplina = ?";
        String queryInsert = "INSERT INTO Grup_Studiu (id_disciplina, nume_grup) VALUES (?, ?)";

        try(Connection con = getConnection())
        {
            PreparedStatement preparedStatement = con.prepareStatement(queryIdDisciplina);
            preparedStatement.setString(1, numeDisciplina);
            ResultSet resultSet = preparedStatement.executeQuery();
            int idDisciplina = -1;
            if(resultSet.next())
            {
                idDisciplina = resultSet.getInt("id_disciplina");
                //System.out.println(idDisciplina);
            }
            PreparedStatement preparedStatement2 = con.prepareStatement(queryInsert);
            preparedStatement2.setInt(1, idDisciplina);
            preparedStatement2.setString(2, numeGrup);
            //System.out.println(idDisciplina + " " + numeGrup);
            int rows = preparedStatement2.executeUpdate();
            if(rows > 0)
                return true;
            else return false;


        }catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /*public static void main(String[] args) {
        CreareGrupModel model = new CreareGrupModel();
        model.creare("Programarea Calculatoarelor","Matematica");
    } */

}
