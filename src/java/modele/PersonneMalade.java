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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PersonneMalade {
    private int id;
    private int personneId;
    private int maladieId;

    public PersonneMalade() {
    }

    public PersonneMalade(int personneId, int maladieId) {
        this.personneId = personneId;
        this.maladieId = maladieId;
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

    public int getMaladieId() {
        return maladieId;
    }

    public void setMaladieId(int maladieId) {
        this.maladieId = maladieId;
    }

    public static List<PersonneMalade> getRepet(Connection c, int pers) throws SQLException{
        List<PersonneMalade> liste = new ArrayList<PersonneMalade>();
        String sql = "SELECT maladie, COUNT(*) AS repetitions FROM getRepet WHERE personne = "+pers+" GROUP BY maladie order by repetitions desc";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int i = rs.getInt("maladie");
            int m = rs.getInt("repetitions");
            PersonneMalade p = new PersonneMalade(i,m);
            liste.add(p);
        }
        return liste;
    }
}

