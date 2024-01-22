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

public class Personne {
    int id;
    String nom;
    Date date;
    Date consultation;
    double budget;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getConsultation() {
        return consultation;
    }

    public void setConsultation(Date consultation) {
        this.consultation = consultation;
    }
    
    

    public Personne() {
    }

    public Personne(int id, String nom, Date da, Date consult) {
        this.id = id;
        this.nom = nom;
        this.setDate(da);
        this.setConsultation(consult);
    }
    
    public Personne(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    public Personne(String nom, Date da, Date consult) {
        this.setNom(nom);
        this.setDate(da);
        this.setConsultation(consult);
    }

    // Ajoutez des méthodes getter et setter pour tous les champs

    public static Personne getAllPersonnes(Connection connection, int ids) throws SQLException {
        Personne personnes = new Personne();
        String sql = "select * from personne where id ="+ids;
        //System.out.println(sql);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                Date da = resultSet.getDate("date_consultation");
                Date nai = resultSet.getDate("naissance");
                personnes = new Personne(id, nom, da, nai);
            }
        return personnes;
    }
    
    public static List<Personne> getAllPersonnes(Connection connection) throws SQLException {
        List<Personne> personnes = new ArrayList<>();
        String sql = "SELECT * FROM personne";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                Personne personne = new Personne(id, nom);
                personnes.add(personne);
            }
        return personnes;
    }

    public static void addPersonne(Connection connection, String nom, Date nais, Date cons, double budget) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO personne (nom,naissance,date_consultation,budget) VALUES (?,?,?,?)");
        statement.setString(1, nom);
        statement.setDate(2, nais);
        statement.setDate(3, cons);
        statement.setDouble(4,budget);
        statement.executeUpdate();
        
    }

    public static void deletePersonne(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM personne WHERE id = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
