/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Haingo
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Medicament {
    private int id;
    private int symptomeId;
    private String nom;
    private double prix;
    private int ageId;

    public Medicament() {
    }

    public Medicament(int id, int symptomeId, String nom, double prix, int ageId) {
        this.id = id;
        this.symptomeId = symptomeId;
        this.nom = nom;
        this.prix = prix;
        this.ageId = ageId;
    }

    // Ajoutez des méthodes getter et setter pour tous les champs

    public static List<Medicament> getAllMedicaments(Connection connection) throws SQLException {
        List<Medicament> medicaments = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM medicament");
             ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int symptomeId = resultSet.getInt("symptome");
                String nom = resultSet.getString("nom");
                double prix = resultSet.getDouble("prix");
                int ageId = resultSet.getInt("age");
                Medicament medicament = new Medicament(id, symptomeId, nom, prix, ageId);
                medicaments.add(medicament);
            }

        return medicaments;
    }

    public static void addMedicament(Connection connection, int symptomeId, String nom, double prix, int ageId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO medicament (symptome, nom, prix, age) VALUES (?, ?, ?, ?)");

            statement.setInt(1, symptomeId);
            statement.setString(2, nom);
            statement.setDouble(3, prix);
            statement.setInt(4, ageId);
            statement.executeUpdate();
        
    }

    public static void deleteMedicament(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM medicament WHERE id = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
        
    }
}
