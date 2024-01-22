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

public class Age {
    private int id;
    private String nom;
    private int ageDebut;
    private int ageFin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAgeDebut() {
        return ageDebut;
    }

    public void setAgeDebut(int ageDebut) {
        this.ageDebut = ageDebut;
    }

    public int getAgeFin() {
        return ageFin;
    }

    public void setAgeFin(int ageFin) {
        this.ageFin = ageFin;
    }

    
    
    public Age() {
    }

    public Age(int id, String nom, int ageDebut, int ageFin) {
        this.id = id;
        this.nom = nom;
        this.ageDebut = ageDebut;
        this.ageFin = ageFin;
    }

    // Ajoutez des méthodes getter et setter pour tous les champs

    public static List<Age> getAllAges(Connection connection) throws SQLException {
        List<Age> ages = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM age");
             ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                int ageDebut = resultSet.getInt("age_debut");
                int ageFin = resultSet.getInt("age_fin");
                Age age = new Age(id, nom, ageDebut, ageFin);
                ages.add(age);
            }
        return ages;
    }

    public static void addAge(Connection connection, String nom, int ageDebut, int ageFin) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO age (nom, age_debut, age_fin) VALUES (?, ?, ?)");

            statement.setString(1, nom);
            statement.setInt(2, ageDebut);
            statement.setInt(3, ageFin);
            statement.executeUpdate();
    }

    public static void deleteAge(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM age WHERE id = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
    
    }
}
