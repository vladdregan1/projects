package model;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CalendarStudentModel {

    private int idCalendar;
    private int idActivitate;
    private int idProfesor;
    private String dataInceput;
    private String dataSfarsit;
    private int nrMaximParticipanti;
    private String descriere;
    //private String numeUtilizator;

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }

   /* public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    } */


    public List<String[]> getCalendarDetails() {
        List<String[]> calendarDetails = new ArrayList<>();
        String query1 = "SELECT * FROM Calendar";
        String query2 = "SELECT id_disciplina FROM Activitate WHERE id_activitate = ?";
        String query3 = "SELECT nume_disciplina FROM Disciplina WHERE id_disciplina = ?";
        String query4 = "SELECT tip_activitate FROM Activitate WHERE id_activitate = ?";
        String query5 = "SELECT id_utilizator FROM Profesor WHERE id_profesor = ?";
        String query6 = "SELECT nume, prenume FROM Utilizator_Info WHERE id_utilizator = ?";

        int idDisciplina = -1;
        String numeDisciplina = "";
        String tipActivitate = "";
        int idUtilizator = -1;
        String numeProfesor = "";

        try (Connection con = getConnection())
        {
            PreparedStatement preparedStatement = con.prepareStatement(query1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idCalendar = resultSet.getInt("id_calendar");
                idActivitate = resultSet.getInt("id_activitate");
                idProfesor = resultSet.getInt("id_profesor");
                dataInceput = resultSet.getString("data_inceput");
                dataSfarsit = resultSet.getString("data_sfarsit");
                nrMaximParticipanti = resultSet.getInt("nr_maxim_participanti");
                descriere = resultSet.getString("descriere");
                //System.out.println(idCalendar + " " + idActivitate + " " + idProfesor + " " + dataInceput + " " + dataSfarsit + " " + nrMaximParticipanti + " " + descriere);

                ///ID DISCIPLINA
                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                preparedStatement2.setInt(1, idActivitate);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                if (resultSet2.next()) {
                    idDisciplina = resultSet2.getInt("id_disciplina");
                    //System.out.println(idDisciplina);
                }

                ///NUME DISCIPLINA
                PreparedStatement preparedStatement3 = con.prepareStatement(query3);
                preparedStatement3.setInt(1, idDisciplina);
                ResultSet resultSet3 = preparedStatement3.executeQuery();
                if (resultSet3.next()) {
                    numeDisciplina = resultSet3.getString("nume_disciplina");
                    //System.out.println(numeDisciplina);
                }

                ///TIP ACTIVITATE
                PreparedStatement preparedStatement4 = con.prepareStatement(query4);
                preparedStatement4.setInt(1, idActivitate);
                ResultSet resultSet4 = preparedStatement4.executeQuery();
                if (resultSet4.next()) {
                    tipActivitate = resultSet4.getString("tip_activitate");
                    //System.out.println(numeDisciplina + tipActivitate);
                }

                ///ID_Utilizator_profesor
                PreparedStatement preparedStatement5 = con.prepareStatement(query5);
                preparedStatement5.setInt(1, idProfesor);
                ResultSet resultSet5 = preparedStatement5.executeQuery();
                if (resultSet5.next()) {
                    idUtilizator = resultSet5.getInt("id_utilizator");
                }

                ///NUME PROFESOR
                PreparedStatement preparedStatement6 = con.prepareStatement(query6);
                preparedStatement6.setInt(1, idUtilizator);
                ResultSet resultSet6 = preparedStatement6.executeQuery();
                if (resultSet6.next()) {
                    numeProfesor = resultSet6.getString("nume") + " " + resultSet6.getString("prenume");
                }
                //System.out.println(numeProfesor);
                //System.out.println(idCalendar + " " + numeDisciplina + " " + tipActivitate + " " + numeProfesor + " " + dataInceput + " " + dataSfarsit + " " + nrMaximParticipanti + " " + descriere);
                calendarDetails.add(new String[]{
                        String.valueOf(idCalendar),
                        numeDisciplina,
                        tipActivitate,
                        numeProfesor,
                        dataInceput,
                        dataSfarsit,
                        String.valueOf(nrMaximParticipanti),
                        descriere
                });

            }

          /*  for (String[] calendar : calendarDetails) {
                System.out.println(String.join(" ", calendar));
            } */




        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return calendarDetails;
    }

    public int getStudentId(String numeUtilizator){
        int idStudent = -1;
        int idUtilizator = -1;

        String query1 = "SELECT id_utilizator FROM Utilizator WHERE nume_utilizator = ?";
        String query2 = "SELECT id_student FROM Student WHERE id_utilizator = ?";

        try(Connection con = getConnection())
        {
            PreparedStatement preparedStatement = con.prepareStatement(query1);
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
                idStudent = resultSet2.getInt("id_student");
                //System.out.println(idStudent);
            }



        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return idStudent;

    }

    public boolean verificareLocuriInserare(int idCalendar){

        int nrMaximStudenti = -1;
        int studentiParticipanti = -1;

        String query = "SELECT nr_maxim_participanti FROM Calendar WHERE id_calendar = ?";
        String query2 = "SELECT COUNT(*) AS numar_studenti FROM Student_Calendar WHERE id_calendar = ?";

        try(Connection con = getConnection())
        {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idCalendar);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nrMaximStudenti = resultSet.getInt("nr_maxim_participanti");
                //System.out.println(nrMaximStudenti);
            }

            PreparedStatement preparedStatement2 = con.prepareStatement(query2);
            preparedStatement2.setInt(1, idCalendar);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            if (resultSet2.next()) {
                studentiParticipanti = resultSet2.getInt("numar_studenti");
                //System.out.println(studentiParticipanti);
            }

            if (studentiParticipanti >= nrMaximStudenti || nrMaximStudenti == -1 || studentiParticipanti == -1)
                return false;


            return true;


        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

    }

    public boolean inserareStudent(int idCalendar, int idStudent, String dataCurenta){

        String query = "INSERT INTO Student_Calendar (id_calendar, id_student, data_inscriere) VALUES (?, ?, ?)";

        try(Connection con = getConnection())
        {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idCalendar);
            preparedStatement.setInt(2, idStudent);
            preparedStatement.setString(3, dataCurenta);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0)
                return true;
            else return false;

        }catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }

    }

    /*static public void main(String[] args) {
        CalendarStudentModel model = new CalendarStudentModel();
        model.verificareLocuriInserare(6);
    } */

}
