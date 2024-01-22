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
    private int id;
    private int personneId;
    private int medicamentId;

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

    public int getMedicamentId() {
        return medicamentId;
    }

    public void setMedicamentId(int medicamentId) {
        this.medicamentId = medicamentId;
    }

    
    
    public PersonneMedicament() {
    }

    public PersonneMedicament(int id, int personneId, int medicamentId) {
        this.id = id;
        this.personneId = personneId;
        this.medicamentId = medicamentId;
    }

    // Ajoutez des méthodes getter et setter pour tous les champs

    public static List<PersonneMedicament> getAllPersonnesMedicaments(Connection connection) throws SQLException {
        List<PersonneMedicament> personnesMedicaments = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM personne_medicament");
             ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int personneId = resultSet.getInt("personne");
                int medicamentId = resultSet.getInt("medicament");
                PersonneMedicament personneMedicament = new PersonneMedicament(id, personneId, medicamentId);
                personnesMedicaments.add(personneMedicament);
            }
        

        return personnesMedicaments;
    }

    public static void addPersonneMedicament(Connection connection, int personneId, int medicamentId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO personne_medicament (personne, medicament) VALUES (?, ?)");

            statement.setInt(1, personneId);
            statement.setInt(2, medicamentId);
            statement.executeUpdate();
        
    }

    public static void deletePersonneMedicament(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM personne_medicament WHERE id = ?");

            statement.setInt(1, id);
            statement.executeUpdate();
        
    }
}
