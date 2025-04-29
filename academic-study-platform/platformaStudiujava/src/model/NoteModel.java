package model;
import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteModel {

    private String numeUtilizator;

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    private String numeDisciplina;
    private String numeActivitate;
    private float nota;
    private float notaFinala;

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }

    public List<String[]> getNote(){
        List<String[]> note = new ArrayList<>();

        //numeUtilizator = "VladD";
        String queryIdUtilizator = "SELECT id_utilizator FROM Utilizator WHERE nume_utilizator = ?";
        String queryIdStudent = "SELECT id_student FROM Student WHERE id_utilizator = ?";
        String queryIdActivitate = "SELECT id_activitate FROM Catalog WHERE id_student = ?";
        String queryIdDisciplina = "SELECT id_disciplina FROM Activitate WHERE id_activitate = ?";
        String queryNumeDisciplina = "SELECT nume_disciplina FROM Disciplina WHERE id_disciplina = ?";
        String queryNumeActivitate = "SELECT tip_activitate FROM Activitate WHERE id_activitate = ?";
        String queryNota = "SELECT nota FROM Catalog WHERE id_activitate = ? AND id_student = ?";

        try(Connection con = getConnection())
        {
            ///ID UTILIZATOR
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

            ///ID ACTIVITATE
            PreparedStatement preparedStatement3 = con.prepareStatement(queryIdActivitate);
            preparedStatement3.setInt(1, idStudent);
            ResultSet resultSet3 = preparedStatement3.executeQuery();
            int idActivitate = -1;
            while (resultSet3.next()) {
                 idActivitate = resultSet3.getInt("id_activitate");
                 //System.out.println(idActivitate);

                ///ID DISCIPLINA
                    PreparedStatement preparedStatement4 = con.prepareStatement(queryIdDisciplina);
                    preparedStatement4.setInt(1, idActivitate);
                    ResultSet resultSet4 = preparedStatement4.executeQuery();
                    int idDisciplina = -1;
                    if (resultSet4.next()) {
                        idDisciplina = resultSet4.getInt("id_disciplina");
                        //System.out.println(idDisciplina);
                    }

                ///NUME DISCIPLINA
                PreparedStatement preparedStatement5 = con.prepareStatement(queryNumeDisciplina);
                preparedStatement5.setInt(1, idDisciplina);
                ResultSet resultSet5 = preparedStatement5.executeQuery();
                if (resultSet5.next()) {
                    numeDisciplina = resultSet5.getString("nume_disciplina");
                }
                //System.out.println(numeDisciplina);

                ///NUME ACTIVITATE
                PreparedStatement preparedStatement6 = con.prepareStatement(queryNumeActivitate);
                preparedStatement6.setInt(1, idActivitate);
                ResultSet resultSet6 = preparedStatement6.executeQuery();
                if (resultSet6.next()) {
                    numeActivitate = resultSet6.getString("tip_activitate");
                }
                //System.out.println(numeActivitate);

                ///NOTA
                PreparedStatement preparedStatement7 = con.prepareStatement(queryNota);
                preparedStatement7.setInt(1, idActivitate);
                preparedStatement7.setInt(2, idStudent);
                ResultSet resultSet7 = preparedStatement7.executeQuery();
                if (resultSet7.next()) {
                    nota = resultSet7.getFloat("nota");
                }
                //System.out.println(numeDisciplina + " " + numeActivitate + " " + nota);

                note.add(new String[]{
                        numeDisciplina,
                        numeActivitate,
                        Float.toString(nota)
                });

            }
            /*for (String[] noteActivitate : note) {
                System.out.println(String.join(" ", noteActivitate));
            } */



        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return note;
    }

    /*static public void main(String[] args) {
        NoteModel model = new NoteModel();
        model.getNote();
    } */

    public List<String[]> getNoteFinale(){
        List<String[]> note = new ArrayList<>();

        //numeUtilizator = "VladD";

        String queryIdUtilizator = "SELECT id_utilizator FROM Utilizator WHERE nume_utilizator = ?";
        String queryIdStudent = "SELECT id_student FROM Student WHERE id_utilizator = ?";
        String queryIdDisciplina = "SELECT id_disciplina FROM Student_Disciplina WHERE id_student = ?";
        String queryNumeDisciplina = "SELECT nume_disciplina FROM Disciplina WHERE id_disciplina = ?";
        String queryIdActivitate = "SELECT id_activitate FROM Activitate WHERE id_disciplina = ?";
        String queryProcent = "SELECT procent FROM Activitate WHERE id_activitate = ?";
        String queryNota = "SELECT nota FROM Catalog WHERE id_activitate = ? AND id_student = ?";

        try(Connection con = getConnection())
        {

            ///ID UTILIZATOR
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
            if(resultSet2.next()){
                idStudent = resultSet2.getInt("id_student");
            }
            //System.out.println(idStudent);

            ///ID DISCIPLINA
            PreparedStatement preparedStatement3 = con.prepareStatement(queryIdDisciplina);
            preparedStatement3.setInt(1, idStudent);
            ResultSet resultSet3 = preparedStatement3.executeQuery();
            int idDisciplina = -1;
            while (resultSet3.next()) {
                idDisciplina = resultSet3.getInt("id_disciplina");
                //System.out.println(idDisciplina);
                PreparedStatement preparedStatement4 = con.prepareStatement(queryNumeDisciplina);
                preparedStatement4.setInt(1, idDisciplina);
                ResultSet resultSet4 = preparedStatement4.executeQuery();
                String numeDisciplina = "";
                if (resultSet4.next()) {
                    numeDisciplina = resultSet4.getString("nume_disciplina");
                }
                //System.out.println(numeDisciplina);

                float notaFinala = 0;

                ///ID ACTIVITATE
                PreparedStatement preparedStatement5 = con.prepareStatement(queryIdActivitate);
                preparedStatement5.setInt(1, idDisciplina);
                ResultSet resultSet5 = preparedStatement5.executeQuery();
                int idActivitate = -1;
                while (resultSet5.next()) {
                    idActivitate = resultSet5.getInt("id_activitate");
                    //System.out.println(idActivitate);

                    ///PROCENT
                    PreparedStatement preparedStatement6 = con.prepareStatement(queryProcent);
                    preparedStatement6.setInt(1, idActivitate);
                    ResultSet resultSet6 = preparedStatement6.executeQuery();
                    int procent = 0;
                    if (resultSet6.next()) {
                        procent = resultSet6.getInt("procent");
                    }
                    //System.out.println(procent);

                    ///NOTA ACTIVITATE
                    PreparedStatement preparedStatement7 = con.prepareStatement(queryNota);
                    preparedStatement7.setInt(1, idActivitate);
                    preparedStatement7.setInt(2, idStudent);
                    ResultSet resultSet7 = preparedStatement7.executeQuery();
                    float nota = 0;
                    if (resultSet7.next()) {
                        nota = resultSet7.getFloat("nota");
                    }
                    //System.out.println(numeDisciplina + " " + nota + " " + procent);
                    notaFinala += nota * procent / 100;
                    //System.out.println(notaFinala);

                }
                //System.out.println(numeDisciplina + " " + notaFinala);
                note.add(new String[]{
                        numeDisciplina,
                        Float.toString(notaFinala)
                });

            }

           /* for (String[] noteActivitate : note) {
                System.out.println(String.join(" ", noteActivitate));
            } */


        }catch(SQLException e)
        {
            e.printStackTrace();
        }

        return note;
    }

    /*static public void main(String[] args) {
        NoteModel model = new NoteModel();
        model.getNoteFinale();
    } */

}
