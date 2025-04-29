package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreareActivitateGrupModel {


    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }

    public boolean creare(int idGrup, String numeActivitateGrup, String dataCreare, String dataInceput, String dataSfarsit, int nrMinParticipanti, String dataValidarii, int anulat)
    {
        String queryInsert = "INSERT INTO Activitate_Grup (id_grup, nume_activitate_grup, data_creare, data_inceput, data_sfarsit, nr_min_participanti, data_validarii, anulat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection con = getConnection())
        {
            PreparedStatement preparedStatement = con.prepareStatement(queryInsert);
            preparedStatement.setInt(1, idGrup);
            preparedStatement.setString(2, numeActivitateGrup);
            preparedStatement.setString(3, dataCreare);
            preparedStatement.setString(4, dataInceput);
            preparedStatement.setString(5, dataSfarsit);
            preparedStatement.setInt(6, nrMinParticipanti);
            preparedStatement.setString(7, dataValidarii);
            preparedStatement.setInt(8, anulat);
            int rows = preparedStatement.executeUpdate();

            if(rows > 0)
                return true;
            else return false;


        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

}
