package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembriiModel {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }

    public List<String[]> getMembrii(int idActivitateGrup) {
        List<String[]> membrii = new ArrayList<>();
        String queryIdStudent = "SELECT id_student FROM Activitate_Grup_Student WHERE id_activitate_grup = ?";
        String queryIdUtilizator = "SELECT id_utilizator FROM Student WHERE id_student = ?";
        String queryNumePrenume = "SELECT nume, prenume FROM Utilizator_Info WHERE id_utilizator = ?";

        try(Connection con = getConnection())
        {
            ///ID STUDENT
            PreparedStatement preparedStatement1 = con.prepareStatement(queryIdStudent);
            preparedStatement1.setInt(1, idActivitateGrup);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            int idStudent = -1;
            while(resultSet1.next())
            {
                idStudent = resultSet1.getInt("id_student");

                ///ID UTILIZATOR
                PreparedStatement preparedStatement = con.prepareStatement(queryIdUtilizator);
                preparedStatement.setInt(1, idStudent);
                ResultSet resultSet = preparedStatement.executeQuery();
                int idUtilizator = -1;
                if (resultSet.next())
                {
                    idUtilizator = resultSet.getInt("id_utilizator");
                    //System.out.println(idUtilizator);
                }

                ///NUME PRENUME
                PreparedStatement preparedStatement2 = con.prepareStatement(queryNumePrenume);
                preparedStatement2.setInt(1, idUtilizator);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                String nume = "";
                String prenume = "";
                if(resultSet2.next())
                {
                    String[] numePrenume = new String[2];
                    numePrenume[0] = resultSet2.getString("nume");
                    numePrenume[1] = resultSet2.getString("prenume");
                    //System.out.println(nume + " " + prenume);
                    membrii.add(numePrenume);
                }
            }



            //for (String[] membri : membrii)
                //System.out.println(membri[0] + " " + membri[1]);


        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return membrii;

    }

   /* public static void main(String[] args) {
        MembriiModel model = new MembriiModel();
        model.getMembrii(1);
    } */
}
