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

public class Maladie_details {
    private int id;
    private int maladieId;
    private int symptomeId;
    private int etatDebut;
    private int etatFin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaladieId() {
        return maladieId;
    }

    public void setMaladieId(int maladieId) {
        this.maladieId = maladieId;
    }

    public int getSymptomeId() {
        return symptomeId;
    }

    public void setSymptomeId(int symptomeId) {
        this.symptomeId = symptomeId;
    }

    public int getEtatDebut() {
        return etatDebut;
    }

    public void setEtatDebut(int etatDebut) {
        this.etatDebut = etatDebut;
    }

    public int getEtatFin() {
        return etatFin;
    }

    public void setEtatFin(int etatFin) {
        this.etatFin = etatFin;
    }

    
    
    public Maladie_details() {
    }

    public Maladie_details(int id, int maladieId, int symptomeId, int etatDebut, int etatFin) {
        this.id = id;
        this.maladieId = maladieId;
        this.symptomeId = symptomeId;
        this.etatDebut = etatDebut;
        this.etatFin = etatFin;
    }

    // Ajoutez des méthodes getter et setter pour tous les champs

    public static List<Maladie_details> getAllMaladieDetails(Connection connection) throws SQLException {
        List<Maladie_details> maladieDetailsList = new ArrayList<>();
        String sql = "SELECT * FROM ma_symptome";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int maladieId = resultSet.getInt("maladie");
                int symptomeId = resultSet.getInt("symptome");
                int etatDebut = resultSet.getInt("etat_debut");
                int etatFin = resultSet.getInt("etat_fin");
                Maladie_details maladieDetails = new Maladie_details(id, maladieId, symptomeId, etatDebut, etatFin);
                maladieDetailsList.add(maladieDetails);
            }

        return maladieDetailsList;
    }
    
    public static List<Maladie_details> getAllMaladieDetails(Connection connection, int symp , int etat) throws SQLException {
        List<Maladie_details> maladieDetailsList = new ArrayList<>();
        String sql = "SELECT * FROM ma_symptome where symptome = "+symp+ " and etat_debut>="+etat+" and etat_fin<="+etat;
        Statement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int maladieId = resultSet.getInt("maladie");
                int symptomeId = resultSet.getInt("symptome");
                int etatDebut = resultSet.getInt("etat_debut");
                int etatFin = resultSet.getInt("etat_fin");
                Maladie_details maladieDetails = new Maladie_details(id, maladieId, symptomeId, etatDebut, etatFin);
                maladieDetailsList.add(maladieDetails);
            }

        return maladieDetailsList;
    }

    public static void addMaladieDetails(Connection connection, int maladieId, int symptomeId, int etatDebut, int etatFin) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO maladie_details (maladie, symptome, etat_debut, etat_fin) VALUES (?, ?, ?, ?)");

            statement.setInt(1, maladieId);
            statement.setInt(2, symptomeId);
            statement.setInt(3, etatDebut);
            statement.setInt(4, etatFin);
            statement.executeUpdate();
        
    }

    public static void deleteMaladieDetails(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM maladie_details WHERE id = ?");

            statement.setInt(1, id);
            statement.executeUpdate();
        
    }
}
