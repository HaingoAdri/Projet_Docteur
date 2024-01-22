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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSymptomeId() {
        return symptomeId;
    }

    public void setSymptomeId(int symptomeId) {
        this.symptomeId = symptomeId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getAgeId() {
        return ageId;
    }

    public void setAgeId(int ageId) {
        this.ageId = ageId;
    }
    
    

    public Medicament(int id, int symptomeId, String nom, double prix, int ageId) {
        this.id = id;
        this.symptomeId = symptomeId;
        this.nom = nom;
        this.prix = prix;
        this.ageId = ageId;
    }

    // Ajoutez des méthodes getter et setter pour tous les champs

    public static List<Medicament> getAllMedicaments(Connection connection, int symptome) throws SQLException {
        List<Medicament> medicaments = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM view_medicament where symptome = "+symptome);
             ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int symptomeId = resultSet.getInt("symptome");
                String nom = resultSet.getString("nom");
                double prix = resultSet.getDouble("prix");
                int ageId = resultSet.getInt("effacite");
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
