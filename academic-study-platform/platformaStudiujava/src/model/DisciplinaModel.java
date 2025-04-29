package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaModel {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/platformaStudiu";
        String user = "root";
        String password = "suntsoferpecamion";
        return DriverManager.getConnection(url, user, password);
    }

    public List<String[]> getDisciplinaDetails() {
        List<String[]> disciplinaDetails = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nume_disciplina, descriere FROM Disciplina")) {

            while (rs.next()) {
                String[] details = new String[2];
                details[0] = rs.getString("nume_disciplina");
                details[1] = rs.getString("descriere");
                disciplinaDetails.add(details);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disciplinaDetails;
    }


    public void assignStudentToDisciplina(String disciplinaName, int studentId) {
        String queryIdDisciplina = "SELECT id_disciplina FROM Disciplina WHERE nume_disciplina = ?";
        String querySelect = "SELECT id_profesor, nr_studenti_asignati FROM Profesor_Disciplina WHERE id_disciplina = ?";
        String queryUpdateProfesor = "UPDATE Profesor_Disciplina SET nr_studenti_asignati = nr_studenti_asignati + 1 WHERE id_profesor = ?";
        String queryInsertStudentDisciplina = "INSERT INTO Student_Disciplina (id_disciplina, id_student) VALUES (?, ?)";

        try (Connection connection = getConnection()) {

            PreparedStatement stmtIdDisciplina = connection.prepareStatement(queryIdDisciplina);
            stmtIdDisciplina.setString(1, disciplinaName);
            ResultSet rsIdDisciplina = stmtIdDisciplina.executeQuery();

            int idDisciplina = -1;
            if (rsIdDisciplina.next()) {
                 idDisciplina = rsIdDisciplina.getInt("id_disciplina");
                //System.out.println(idDisciplina);

            }


            int professorId = -1;
            int minStudentCount = Integer.MAX_VALUE;

            PreparedStatement stmtSelect = connection.prepareStatement(querySelect);
            stmtSelect.setInt(1, idDisciplina);
            ResultSet rs = stmtSelect.executeQuery();

            while (rs.next()) {
                int currentProfessorId = rs.getInt("id_profesor");
                int currentStudentCount = rs.getInt("nr_studenti_asignati");

                if (currentStudentCount < minStudentCount) {
                    minStudentCount = currentStudentCount;
                    professorId = currentProfessorId;
                }
            }

            if (professorId != -1) {
                PreparedStatement stmtUpdateProfesor = connection.prepareStatement(queryUpdateProfesor);
                stmtUpdateProfesor.setInt(1, professorId);
                stmtUpdateProfesor.executeUpdate();

                //System.out.println(idDisciplina + " aaaa " + studentId);
                PreparedStatement stmtInsertStudentDisciplina = connection.prepareStatement(queryInsertStudentDisciplina);
                stmtInsertStudentDisciplina.setInt(1, idDisciplina);
                stmtInsertStudentDisciplina.setInt(2, studentId);
                stmtInsertStudentDisciplina.executeUpdate();
            } else {
                System.out.println("Nu s-a găsit un profesor valid pentru disciplina selectată.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLoggedStudentId(String username) {
        String query = "SELECT id_utilizator FROM Utilizator WHERE nume_utilizator = ?";
        String query2 = "SELECT id_student FROM Student WHERE id_utilizator = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            int idUtilizator = -1;
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                //return rs.getInt("id_utilizator");
                idUtilizator = rs.getInt("id_utilizator");
            }

            PreparedStatement stmt2 = connection.prepareStatement(query2);
            stmt2.setInt(1, idUtilizator);
            ResultSet rs2 = stmt2.executeQuery();
            if (rs2.next()) {
                return rs2.getInt("id_student");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new IllegalStateException("Nu s-a găsit studentul logat.");
    }




}
