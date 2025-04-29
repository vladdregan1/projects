package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupModel {

    private String numeGrup;
    private int idGrup;

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }

    public List<String[]> getGrupDetails() {
        List<String[]> grupDetails = new ArrayList<>();

        String query = "SELECT id_grup, nume_grup FROM Grup_Studiu";

        try(Connection con = getConnection())
        {

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                String[] grup = new String[2];
                grup[0] = resultSet.getString("id_grup");
                grup[1] = resultSet.getString("nume_grup");
                grupDetails.add(grup);
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        /*for(String[] grup : grupDetails){
            System.out.println(grup[0] + " " + grup[1]);
        } */

        return grupDetails;

    }

    /*public static void main(String[] args) {
        GrupModel model = new GrupModel();
        model.getGrupDetails();
    } */
}
