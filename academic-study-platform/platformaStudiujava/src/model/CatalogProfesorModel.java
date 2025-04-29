package model;

import java.net.ConnectException;
import java.sql.*;

public class CatalogProfesorModel {

    private int idDisciplina;
    private String[] numeStudenti;
    private int[] idStudenti;
    private int idStudent;
    private String[] activitati;
    private int idActivitate;
    private String data;
    private float nota;

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

    public void setIdStudent(String numeStudent)
    {
        for (int i = 0; i < numeStudenti.length; i++) {
            if (numeStudenti[i].equals(numeStudent)) {
                idStudent = idStudenti[i];
                //System.out.println(idStudent);
                break;
            }
        }
    }

    public void setIdActivitate(String activitate)
    {
        idActivitate = -1;
        //idDisciplina = 1;
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setData(String data) {
        this.data = data;
        //System.out.println(this.data);
    }

    public void setNota(float nota) {
        this.nota = nota;
        //System.out.println(this.nota);
    }

    public String[] getNumeStudenti(){
         numeStudenti = null;
         idStudenti = null;
        int[] idUtilizatori = null;

        //idDisciplina = 2;

        String queryNrStudenti = "SELECT COUNT(*) AS numar_studenti FROM Student_Disciplina WHERE id_disciplina = ?";
        String queryIdStudenti = "SELECT id_student FROM Student_Disciplina WHERE id_disciplina = ?";
        String queryIdUtilizatori = "SELECT id_utilizator FROM Student WHERE id_student = ?";
        String queryNumeUtilizatori = "SELECT nume, prenume FROM Utilizator_Info WHERE id_utilizator = ?";

        try(Connection con = getConnection())
        {
            ///nr studenti
            PreparedStatement preparedStatement = con.prepareStatement(queryNrStudenti);
            preparedStatement.setInt(1, idDisciplina);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int nrStudenti = resultSet.getInt("numar_studenti");
                numeStudenti = new String[nrStudenti];
                idStudenti = new int[nrStudenti];
                idUtilizatori = new int[nrStudenti];
                //System.out.println(nrStudenti);
            }

            ///id studenti
            PreparedStatement preparedStatement2 = con.prepareStatement(queryIdStudenti);
            preparedStatement2.setInt(1, idDisciplina);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            int i = 0;
            while (resultSet2.next()) {
                idStudenti[i] = resultSet2.getInt("id_student");
                i++;
            }
            for (int j = 0; j < idStudenti.length; j++) {
                //System.out.println(idStudenti[j]);
            }

            ///id utilizator
            for (int j = 0; j < idStudenti.length; j++) {
                PreparedStatement preparedStatement3 = con.prepareStatement(queryIdUtilizatori);
                preparedStatement3.setInt(1, idStudenti[j]);
                ResultSet resultSet3 = preparedStatement3.executeQuery();
                if (resultSet3.next()) {
                    idUtilizatori[j] = resultSet3.getInt("id_utilizator");
                }
                //System.out.println(idUtilizatori[j]);
            }

            ///nume utilizator
            for (int j = 0; j < idUtilizatori.length; j++) {
                PreparedStatement preparedStatement4 = con.prepareStatement(queryNumeUtilizatori);
                preparedStatement4.setInt(1, idUtilizatori[j]);
                ResultSet resultSet4 = preparedStatement4.executeQuery();
                if (resultSet4.next()) {
                    String numeUtilizator = resultSet4.getString("nume") + " " + resultSet4.getString("prenume");
                    numeStudenti[j] = numeUtilizator;
                }
               // System.out.println(numeStudenti[j]);

            }
            //System.out.println(numeStudenti[1] + " " + idStudenti[1]);



        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return numeStudenti;
    }

    public String[] getActivitati(){
        activitati = null;
        int nrActivitati = -1;
        //idDisciplina = 1;
        //System.out.println(idDisciplina);

        String queryNrActivitati = "SELECT COUNT(*) AS numar_activitati FROM Activitate WHERE id_disciplina = ?";
        String query = "SELECT tip_activitate FROM Activitate WHERE id_disciplina = ?";


        try (Connection con = getConnection())
        {
            PreparedStatement preparedStatement2 = con.prepareStatement(queryNrActivitati);
            preparedStatement2.setInt(1, idDisciplina);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            if (resultSet2.next()) {
                nrActivitati = resultSet2.getInt("numar_activitati");
                //System.out.println(nrActivitati);
            }


            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idDisciplina);
            ResultSet resultSet = preparedStatement.executeQuery();
            int i = 0;
            activitati = new String[nrActivitati];
            while (resultSet.next()) {
                activitati[i] = resultSet.getString("tip_activitate");
                i++;
               // System.out.println(activitati[i]);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return activitati;

    }

    public boolean insertNota()
    {
        String query = "INSERT INTO Catalog (id_activitate, id_student, data, nota) VALUES (?, ?, ?, ?)";

        try(Connection con = getConnection())
        {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idActivitate);
            preparedStatement.setInt(2, idStudent);
            preparedStatement.setString(3, data);
            preparedStatement.setFloat(4, nota);
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

   /* static public void main(String[] args) {
        CatalogProfesorModel model = new CatalogProfesorModel();
        model.setIdActivitate("Laborator");
    }*/





}
