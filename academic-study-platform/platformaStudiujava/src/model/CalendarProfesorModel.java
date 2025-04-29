package model;

import java.net.ConnectException;
import java.sql.*;

public class CalendarProfesorModel {

    private int idDisciplina;
    private int idActivitate;
    private int idProfesor;
    private String dataInceput;
    private String dataSfarsit;
    private int nrMaximParticipanti;
    private String descriere;

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }

    public String[] getDisciplina(){
        String[] disciplina = null;

        String query = "SELECT nume_disciplina FROM Disciplina";
        String query2 = "SELECT COUNT(*) AS numar_discipline FROM Disciplina";

        try (Connection con = getConnection())
        {
            PreparedStatement preparedStatement2 = con.prepareStatement(query2);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            int nrDisciplina = 0;
            if (resultSet2.next()) {
                nrDisciplina = resultSet2.getInt("numar_discipline");
            }
            disciplina = new String[nrDisciplina];

            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                disciplina[i] = resultSet.getString("nume_disciplina");
                //System.out.println(disciplina[i]);
                i++;
            }


        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return disciplina;
    }

    public void setIdDisciplina(String numeDisciplina)
    {
        idDisciplina = -1;
        //System.out.println(numeDisciplina);
        String query = "SELECT id_disciplina FROM Disciplina WHERE nume_disciplina = ?";

        try(Connection con = getConnection())
        {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, numeDisciplina);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idDisciplina = resultSet.getInt("id_disciplina");
            }
            //System.out.println(idDisciplina);

        }catch(SQLException e)
        {
            e.printStackTrace();
        }


    }

    public void setIdProfesor(String numeUtilizator){
        int idUtilizator = -1;
        idProfesor = -1;
        String query = "SELECT id_utilizator FROM Utilizator WHERE nume_utilizator = ?";
        String query2 = "SELECT id_profesor FROM Profesor WHERE id_utilizator = ?";

        try(Connection con = getConnection())
        {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, numeUtilizator);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idUtilizator = resultSet.getInt("id_utilizator");
                //System.out.println(idUtilizator);
            }

            PreparedStatement preparedStatement2 = con.prepareStatement(query2);
            preparedStatement2.setInt(1, idUtilizator);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            if (resultSet2.next()) {
                idProfesor = resultSet2.getInt("id_profesor");
                //System.out.println(idProfesor);
            }

        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public String[] getActivitate(){
        String[] activitate = null;

        //idDisciplina = 1;
        String queryNrActivitati = "SELECT COUNT(*) AS numar_activitati FROM Activitate WHERE id_disciplina = ?";
        String query = "SELECT tip_activitate FROM Activitate WHERE id_disciplina = ?";

        try(Connection con = getConnection())
        {
            PreparedStatement preparedStatement = con.prepareStatement(queryNrActivitati);
            preparedStatement.setInt(1, idDisciplina);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int nrActivitati = resultSet.getInt("numar_activitati");
                activitate = new String[nrActivitati];
                //System.out.println(nrActivitati);
            }

            PreparedStatement preparedStatement2 = con.prepareStatement(query);
            preparedStatement2.setInt(1, idDisciplina);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            int i = 0;
            while (resultSet2.next()) {
                activitate[i] = resultSet2.getString("tip_activitate");
                //System.out.println(activitate[i]);
                i++;
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return activitate;
    }

    public void setIdActivitate(String activitate){
        idActivitate = -1;
       // idDisciplina = 1;
        String query = "SELECT id_activitate FROM Activitate WHERE tip_activitate = ? AND id_disciplina = ?";

        try(Connection con = getConnection())
        {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, activitate);
            preparedStatement.setInt(2, idDisciplina);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                 idActivitate = resultSet.getInt("id_activitate");
                //System.out.println(idActivitate);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setDataInceput(String dataInceput) {
        this.dataInceput = dataInceput;
        //System.out.println(this.dataInceput);
    }

    public void setDataSfarsit(String dataSfarsit) {
        this.dataSfarsit = dataSfarsit;
        //System.out.println(this.dataSfarsit);
    }

    public void setNrMaximParticipanti(int nrMaximParticipanti) {
        this.nrMaximParticipanti = nrMaximParticipanti;
        //System.out.println(this.nrMaximParticipanti);
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
        //System.out.println(this.descriere);
    }

    public boolean insertCalendar()
    {
        String query = "INSERT INTO Calendar (id_activitate, id_profesor, data_inceput, data_sfarsit, nr_maxim_participanti, descriere) VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection con = getConnection())
        {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idActivitate);
            preparedStatement.setInt(2, idProfesor);
            preparedStatement.setString(3, dataInceput);
            preparedStatement.setString(4, dataSfarsit);
            preparedStatement.setInt(5, nrMaximParticipanti);
            preparedStatement.setString(6, descriere);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                return true;
            }
            else return false;

        }catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

  /*  public static void main(String[] args) {
        CalendarProfesorModel model = new CalendarProfesorModel();
        model.setIdProfesor("VasileP");
    } */

}
