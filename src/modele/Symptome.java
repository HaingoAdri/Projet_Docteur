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

public class Symptome {
    private int id;
    private String nom;

    public Symptome() {
    }

    public Symptome(int id, String nom) {
        this.setId(id);
        this.setNom(nom);
    }

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

    public static List<Symptome> getAllSymptomes(Connection c) throws SQLException {
        List<Symptome> symptomes = new ArrayList<>();
        
        PreparedStatement statement = c.prepareStatement("SELECT * FROM symptome");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            Symptome symptome = new Symptome(id, nom);
            symptomes.add(symptome);
        }
        return symptomes;
    }

    public static void addSymptome(Connection c ,String nom) throws SQLException {
        PreparedStatement statement = c.prepareStatement("INSERT INTO symptome (nom) VALUES (?)");
        statement.setString(1, nom);
        statement.executeUpdate();
    }

    public static void deleteSymptome(Connection c, int id) throws SQLException {
        PreparedStatement statement = c.prepareStatement("DELETE FROM symptome WHERE id = ?");
        statement.setInt(1, id);
        statement.executeUpdate();        
    }
}

