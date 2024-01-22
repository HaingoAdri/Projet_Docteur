/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import modele.Age;
import modele.Connexion;
import modele.Liaison;
import modele.Maladie;
import modele.Maladie_details;
import modele.Medicament;
import modele.Personne;
import modele.PersonneMalade;
import modele.PersonneMedicament;

/**
 *
 * @author Haingo Adrienne
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Connexion c = new Connexion();
        System.out.println("\n------------- Personne -------------- ");
        Date d = Date.valueOf("2024-01-12");
        Personne olona = Personne.getAllPersonnes(c.connectToPostgres(), 7);
        System.out.println("nom :"+olona.getNom());
        
        List<PersonneMalade> liste = PersonneMalade.getRepet(c.connectToPostgres(), olona.getId());
        Maladie m = Maladie.getMaladies(c.connectToPostgres(), liste.get(0).getPersonneId());
        System.out.println("La maladie probable est "+m.getNom() +" n° :"+ m.getId());
        
        //maka maladie
        System.out.println("\n------------- Maladie de la personne -------------- ");
        for(PersonneMalade p : liste){
            Maladie h = Maladie.getMaladies(c.connectToPostgres(), p.getId());
            System.out.println("Maladie : "+h.getNom()+" et repetition : "+p.getMaladieId());
        }
        
        //maka fanafody
        System.out.println("\n------------- Liste des medicaments par symptome -------------- ");
        List<Liaison> personne = Liaison.getAllLiaisons(c.connectToPostgres(), olona.getId());
        List<Double> listePrix = new ArrayList<>();
        List<PersonneMedicament> personnemedicaments = new ArrayList<>();
        for(Liaison n : personne){
            List<Medicament> medicament = Medicament.getAllMedicaments(c.connectToPostgres(), n.getSymptomeId());
            for(int i =0; i<medicament.size(); i++){
                int h = 0;
                float quantite = n.getEtat()/medicament.get(i).getAgeId();
                double prix = quantite * medicament.get(i).getPrix();
                
                //if(n.getEtat()>4){
                    
                    System.out.println("Le medicament "+medicament.get(i).getNom()+" pour le symptome n°"+n.getSymptomeId()+" de quantite "+quantite+ " et de prix :"+prix);
                    PersonneMedicament pm = new PersonneMedicament(n.getSymptomeId(), quantite, prix);
                    PersonneMedicament.insert(c.connectToPostgres(), n.getSymptomeId(), (int)quantite, prix, olona.getId(), medicament.get(i).getId());
                    personnemedicaments.add(pm);
                    listePrix.add(prix);
                //}
                /**if(n.getEtat()<4){
                    double prixs = medicament.get(i).getPrix();
                    
                    System.out.println("Le medicament "+medicament.get(i).getNom()+" pour le symptome n°"+n.getSymptomeId()+" de quantite "+quantite+ " et de prix :"+prixs);
                    PersonneMedicament pm = new PersonneMedicament(n.getSymptomeId(), quantite, prixs);
                    PersonneMedicament.insert(c.connectToPostgres(), n.getSymptomeId(), (int)quantite, prixs, olona.getId(), medicament.get(i).getId());
                    personnemedicaments.add(pm);
                    listePrix.add(prixs);
                }**/
                
                //maka combinaison efficace sady mora
            }
        }
        System.out.println("\n------------- Proposition -------------- ");
        List<PersonneMedicament> per = PersonneMedicament.getProposition(c.connectToPostgres(), olona.getId());
        double somme = 0;
        for(PersonneMedicament p : per){
            
            List<PersonneMedicament> mp = PersonneMedicament.getTout(c.connectToPostgres(), olona.getId(), p.getSymptome(), p.getPrix());
            for(PersonneMedicament l : mp){
                System.out.println("medoc "+l.getNom()+" symptome "+l.getSymptome()+" quantite "+l.getQuantite()+" prix "+l.getPrix());
                somme += l.getPrix();
            }
            
        }
        System.out.println("\nSomme total : " +somme);
    }
    
}
