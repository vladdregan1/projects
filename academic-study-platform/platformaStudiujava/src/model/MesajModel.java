package model;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MesajModel {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }

    public void setMesaj(int idGrup, String numeUtilizator, String mesaj)
    {

        String queridUtilizator = "SELECT id_utilizator FROM Utilizator WHERE nume_utilizator = ?";
        String queryidStudent = "SELECT id_student FROM Student WHERE id_utilizator = ?";
        String queryInsert = "INSERT INTO Mesaj (id_grup, id_student, data_creare, mesaj) VALUES (?, ?, ?, ?)";

        try(Connection con = getConnection())
        {
            ///ID UTILIZATOR
            PreparedStatement preparedStatement = con.prepareStatement(queridUtilizator);
            preparedStatement.setString(1, numeUtilizator);
            ResultSet resultSet = preparedStatement.executeQuery();
            int idUtilizator = -1;
            if(resultSet.next())
            {
                idUtilizator = resultSet.getInt("id_utilizator");
               // System.out.println(idUtilizator);
            }
            ///ID STUDENT
            PreparedStatement preparedStatement2 = con.prepareStatement(queryidStudent);
            preparedStatement2.setInt(1, idUtilizator);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            int idStudent = -1;
            if (resultSet2.next())
            {
                idStudent = resultSet2.getInt("id_student");
                //System.out.println(idStudent);
            }

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String sqlDateTime = now.format(formatter);

            ///INSERARE
            PreparedStatement preparedStatement3 = con.prepareStatement(queryInsert);
            preparedStatement3.setInt(1, idGrup);
            preparedStatement3.setInt(2, idStudent);
            preparedStatement3.setString(3, sqlDateTime);
            preparedStatement3.setString(4, mesaj);
            preparedStatement3.executeUpdate();


        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

   /* public static void main(String[] args) {
        MesajModel model = new MesajModel();
        model.setMesaj(1,"VladD");
    } */

}
