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

public class Maladie {
    private int id;
    private String nom;

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
    
    
    public Maladie() {
    }

    public Maladie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // Ajoutez des méthodes getter et setter pour tous les champs

    public static Maladie getMaladies(Connection connection, int i) throws SQLException {
        Maladie maladie = new Maladie();
        String sql = "SELECT * FROM maladie where id = '"+i+"'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
             

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                maladie = new Maladie(id, nom);
                
            }

        return maladie;
    }


}

