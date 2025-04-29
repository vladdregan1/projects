package model;
import java.sql.*;

public class LogareModel {

    private String numeUtilizator;
    private String parola;
    private int id_rol;

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getNumeUtilizator() {
        return numeUtilizator;
    }

    public int getId_rol() {
        return id_rol;
    }


    public boolean Logare()
    {
        String query = "SELECT parola, id_rol FROM Utilizator WHERE nume_utilizator = ?";
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query))
        {
            preparedStatement.setString(1, numeUtilizator);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String parolaBD = resultSet.getString("parola");
                id_rol = resultSet.getInt("id_rol");
                if (parola.equals(parolaBD)) {
                    return true;
                }
                else {
                    return false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


}
