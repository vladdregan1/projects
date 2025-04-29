package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AfisareActivitateModel {

    private String numeUtilizator;

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }

    public List<String[]> getActivitati(){
        List<String[]> activitati = new ArrayList<>();

        String queryIdUtilizator = "SELECT id_utilizator FROM Utilizator WHERE nume_utilizator = ?";
        String queryIdStudent = "SELECT id_student FROM Student WHERE id_utilizator = ?";
        String queryIdDisciplina = "SELECT id_disciplina FROM Student_Disciplina WHERE id_student = ?";
        String queryNumeDisciplina = "SELECT nume_disciplina FROM Disciplina WHERE id_disciplina = ?";
        String queryActivitate = "SELECT id_profesor, tip_activitate, data_inceput, data_sfarsit, frecventa, procent FROM Activitate WHERE id_disciplina = ?";
        String queryIdUtilizatorProfesor = "SELECT id_utilizator FROM Profesor WHERE id_profesor = ?";
        String queryNumeProfesor = "SELECT nume, prenume FROM Utilizator_Info WHERE id_utilizator = ?";


        try (Connection con = getConnection())
        {

            ///ID UTILIZATOR
            //numeUtilizator = "VladD";
            PreparedStatement preparedStatement = con.prepareStatement(queryIdUtilizator);
            preparedStatement.setString(1, numeUtilizator);
            ResultSet resultSet = preparedStatement.executeQuery();
            int idUtilizator = -1;
            if (resultSet.next()) {
                idUtilizator = resultSet.getInt("id_utilizator");
            }
            //System.out.println(idUtilizator);

            ///ID STUDENT
            PreparedStatement preparedStatement2 = con.prepareStatement(queryIdStudent);
            preparedStatement2.setInt(1, idUtilizator);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            int idStudent = -1;
            if (resultSet2.next()) {
                idStudent = resultSet2.getInt("id_student");
            }
            //System.out.println(idStudent);

            ///ID DISCIPLINE
            PreparedStatement preparedStatement3 = con.prepareStatement(queryIdDisciplina);
            preparedStatement3.setInt(1, idStudent);
            ResultSet resultSet3 = preparedStatement3.executeQuery();

            while (resultSet3.next()) {
                int idDisciplina = resultSet3.getInt("id_disciplina");

                ///NUME DISCIPLINA
                PreparedStatement preparedStatement4 = con.prepareStatement(queryNumeDisciplina);
                preparedStatement4.setInt(1, idDisciplina);
                ResultSet resultSet4 = preparedStatement4.executeQuery();
                String numeDisciplina = "";
                if (resultSet4.next()) {
                    numeDisciplina = resultSet4.getString("nume_disciplina");
                    //System.out.println(numeDisciplina);
                }


                PreparedStatement preparedStatement5 = con.prepareStatement(queryActivitate);
                preparedStatement5.setInt(1, idDisciplina);
                ResultSet resultSet5 = preparedStatement5.executeQuery();
                int idProfesor = -1;
                int idUtilizatorProfesor = -1;
                String numeProfesor = "";
                String prenumeProfesor = "";
                String tipActivitate = "";
                String dataInceput = "";
                String dataSfarsit = "";
                String frecventa = "";
                int procent = -1;
                while (resultSet5.next()) {
                    idProfesor = resultSet5.getInt("id_profesor");
                    tipActivitate = resultSet5.getString("tip_activitate");
                    dataInceput = resultSet5.getString("data_inceput");
                    dataSfarsit = resultSet5.getString("data_sfarsit");
                    frecventa = resultSet5.getString("frecventa");
                    procent = resultSet5.getInt("procent");

                    ///ID UTILIZATOR PROFESOR
                    PreparedStatement preparedStatement6 = con.prepareStatement(queryIdUtilizatorProfesor);
                    preparedStatement6.setInt(1, idProfesor);
                    ResultSet resultSet6 = preparedStatement6.executeQuery();
                    if (resultSet6.next()) {
                        idUtilizatorProfesor = resultSet6.getInt("id_utilizator");
                    }

                    ///NUME PROFESOR
                    PreparedStatement preparedStatement7 = con.prepareStatement(queryNumeProfesor);
                    preparedStatement7.setInt(1, idUtilizatorProfesor);
                    ResultSet resultSet7 = preparedStatement7.executeQuery();
                    if (resultSet7.next()) {
                        numeProfesor = resultSet7.getString("nume");
                        prenumeProfesor = resultSet7.getString("prenume");
                    }

                    activitati.add(new String[]{
                            numeDisciplina,
                            numeProfesor,
                            prenumeProfesor,
                            tipActivitate,
                            dataInceput,
                            dataSfarsit,
                            frecventa,
                            "procent nota finala: " + procent
                    });



                    //System.out.println(numeDisciplina + " " + numeProfesor + " " + prenumeProfesor + " " + tipActivitate + " " + dataInceput + " " + dataSfarsit + " " + frecventa + " " + procent);
                }


            }
            //for (String[] activitate : activitati) {
             //   System.out.println(String.join(" ", activitate));
            //}



        }catch (SQLException e)
        {
            e.printStackTrace();

        }

        return activitati;
    }

   /* public static void main(String[] args) {
        AfisareActivitateModel model = new AfisareActivitateModel();
        model.getActivitati();
    } */
}
