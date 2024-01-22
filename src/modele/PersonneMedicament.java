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

public class PersonneMedicament {
    int symptome;
    double quantite;
    double prix;
    int personne;
    int medicaments;
    String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPersonne() {
        return personne;
    }

    public void setPersonne(int personne) {
        this.personne = personne;
    }

    public int getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(int medicaments) {
        this.medicaments = medicaments;
    }

    public int getSymptome() {
        return symptome;
    }

    public void setSymptome(int symptome) {
        this.symptome = symptome;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public PersonneMedicament(int symptome, double quantite, double prix, int personne, String nom) {
        this.symptome = symptome;
        this.quantite = quantite;
        this.prix = prix;
        this.personne = personne;
        this.nom = nom;
    }

    
    public PersonneMedicament(int symptome, double quantite, double prix) {
        this.symptome = symptome;
        this.quantite = quantite;
        this.prix = prix;
    }

    public PersonneMedicament(int symptome, double quantite, double prix, int personne, int medicaments) {
        this.symptome = symptome;
        this.quantite = quantite;
        this.prix = prix;
        this.personne = personne;
        this.medicaments = medicaments;
    }
    
    public static void insert(Connection c, int s, int q, double prix,int presonne, int medoc) throws SQLException{
        String sql = "insert into personnemedicaments values (?,?,?,?,?)";
        PreparedStatement st = c.prepareStatement(sql);
        st.setInt(1, presonne);
        st.setInt(2,s);
        st.setInt(3, medoc);
        st.setInt(4, q);
        st.setDouble(5, prix);
        st.executeUpdate();
    }
    
    public static List<PersonneMedicament> getProposition(Connection c , int personne) throws SQLException{
        List<PersonneMedicament> liste = new ArrayList<>();
        String sql = "select symptome,min(prix) from pm where personne = "+personne+" GROUP by symptome";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int symtome = rs.getInt("symptome");
            double prix = rs.getDouble("min");
            PersonneMedicament p = new PersonneMedicament(symtome,0.0, prix, 5, 1);
            liste.add(p);
        }
        return liste;
    }
    
    public static List<PersonneMedicament> getTout(Connection c , int personne, int symptome, double prixs) throws SQLException{
        List<PersonneMedicament> liste = new ArrayList<>();
        String sql = "select distinct * from pm where personne = "+personne+" and symptome = "+symptome+" and prix = "+prixs+" order by symptome asc";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int personnes = rs.getInt("personne");
            int symtome = rs.getInt("symptome");
            String medocs = rs.getString("medicament");
            int quantite = rs.getInt("quantite");
            double prix = rs.getDouble("prix");
            PersonneMedicament p = new PersonneMedicament(symtome,(double)quantite, prix, personnes, medocs);
            liste.add(p);
        }
        return liste;
    }
}
