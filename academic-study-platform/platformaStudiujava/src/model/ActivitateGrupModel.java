package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivitateGrupModel {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }

    public List<String[]> getActivitateGrupDetails( int idGrup) {
        List<String[]> activitateGrupDetails = new ArrayList<>();

        String query = "SELECT id_activitate_grup, nume_activitate_grup, data_creare, data_inceput, data_sfarsit, nr_min_participanti, data_validarii, anulat FROM Activitate_Grup WHERE id_grup = ?";

        try(Connection con = getConnection())
        {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idGrup);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                String[] activitateGrup = new String[8];
                activitateGrup[0] = resultSet.getString("id_activitate_grup");
                activitateGrup[1] = resultSet.getString("nume_activitate_grup");
                activitateGrup[2] = resultSet.getString("data_creare");
                activitateGrup[3] = resultSet.getString("data_inceput");
                activitateGrup[4] = resultSet.getString("data_sfarsit");
                activitateGrup[5] = resultSet.getString("nr_min_participanti");
                activitateGrup[6] = resultSet.getString("data_validarii");
                activitateGrup[7] = resultSet.getString("anulat");
                activitateGrupDetails.add(activitateGrup);

            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
       /* for(String[] activitateGrup : activitateGrupDetails){
            System.out.println(activitateGrup[0] + " " + activitateGrup[1] + " " + activitateGrup[2] + " " + activitateGrup[3] + " " + activitateGrup[4] + " " + activitateGrup[5] + " " + activitateGrup[6] + " " + activitateGrup[7]);
        } */
        return activitateGrupDetails;
    }

    public boolean inscriere(int idActivitateGrup, String numeUtilizator)
    {
        String queryIdUtilizator = "SELECT id_utilizator FROM Utilizator WHERE nume_utilizator = ?";
        String queryIdStudent = "SELECT id_student FROM Student WHERE id_utilizator = ?";
        String queryInscriere = "INSERT INTO Activitate_Grup_Student (id_activitate_grup, id_student, creator) VALUES (?, ?, ?)";

        try(Connection con = getConnection())
        {
            ///ID UTILIZATOR
            PreparedStatement preparedStatement = con.prepareStatement(queryIdUtilizator);
            preparedStatement.setString(1, numeUtilizator);
            ResultSet resultSet = preparedStatement.executeQuery();
            int idUtilizator = -1;
            if(resultSet.next())
            {
                idUtilizator = resultSet.getInt("id_utilizator");
                //System.out.println(idUtilizator);
            }

            ///ID STUDENT
            PreparedStatement preparedStatement2 = con.prepareStatement(queryIdStudent);
            preparedStatement2.setInt(1, idUtilizator);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            int idStudent = -1;
            if(resultSet2.next())
            {
                idStudent = resultSet2.getInt("id_student");
                //System.out.println(idStudent);
            }

            ///INSERARE
            int creator = 0;
            PreparedStatement preparedStatement3 = con.prepareStatement(queryInscriere);
            preparedStatement3.setInt(1, idActivitateGrup);
            preparedStatement3.setInt(2, idStudent);
            preparedStatement3.setInt(3, creator);
            int rows = preparedStatement3.executeUpdate();
            if(rows > 0)
                return true;
            else return false;



        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

    /*public static void main(String[] args) {
        ActivitateGrupModel model = new ActivitateGrupModel();
        model.inscriere(1,"Tudor12");
    } */
}
