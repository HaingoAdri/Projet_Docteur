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

public class Liaison {
    private int id;
    private int personneId;
    private int symptomeId;
    private int etat;

    public Liaison() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonneId() {
        return personneId;
    }

    public void setPersonneId(int personneId) {
        this.personneId = personneId;
    }

    public int getSymptomeId() {
        return symptomeId;
    }

    public void setSymptomeId(int symptomeId) {
        this.symptomeId = symptomeId;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    

    public Liaison(int id, int personneId, int symptomeId, int etat) {
        this.id = id;
        this.personneId = personneId;
        this.symptomeId = symptomeId;
        this.etat = etat;
    }

    // Ajoutez des méthodes getter et setter pour tous les champs

    public static List<Liaison> getAllLiaisons(Connection connection) throws SQLException {
        List<Liaison> liaisons = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM pe_symptome");
             ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int personneId = resultSet.getInt("personne");
                int symptomeId = resultSet.getInt("symptome");
                int etat = resultSet.getInt("etat");
                Liaison liaison = new Liaison(id, personneId, symptomeId, etat);
                liaisons.add(liaison);
            }
        
        return liaisons;
    }
    
    public static List<Liaison> getAllLiaisons(Connection connection, int personne) throws SQLException {
        List<Liaison> liaisons = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM pe_symptome where personne = "+personne);
             ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int personneId = resultSet.getInt("personne");
                int symptomeId = resultSet.getInt("symptome");
                int etat = resultSet.getInt("etat");
                Liaison liaison = new Liaison(id, personneId, symptomeId, etat);
                liaisons.add(liaison);
            }
        
        return liaisons;
    }

    public static void addLiaison(Connection connection, int personneId, int symptomeId, int etat) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO pe_Symptome (personne, symptome,etat) VALUES (?, ?, ?)");
            statement.setInt(1, personneId);
            statement.setInt(2, symptomeId);
            statement.setInt(3, etat);
            statement.executeUpdate();
        
    }

    public static void deleteLiaison(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM liaison WHERE id = ?");

            statement.setInt(1, id);
            statement.executeUpdate();
        
    }
}
