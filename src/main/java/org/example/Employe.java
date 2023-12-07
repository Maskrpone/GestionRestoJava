package org.example;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Employe extends Thread implements Serializable, Runnable {

    // region private
    private final String nom;
    private final String prenom;
    private final int salaire;
    private int jours_travailles;
    private final String poste;

    //endregion

    public void setJours_travailles(int jours_travailles) {
        this.jours_travailles = jours_travailles;
    }

    // region Constructor
    public Employe(String nom, String prenom, int salaire, String poste) {
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
        this.jours_travailles = 0;
        this.poste = poste;
    }

    public Employe(String nom, String prenom, int salaire, int jours_travailles, String poste) {
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
        this.jours_travailles = jours_travailles;
        this.poste = poste;
    }
    //endregion

    //region Getter
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getSalaire() {
        return salaire;
    }

    public String getPoste() {
        return poste;
    }

    public int getJours_travailles() {
        return jours_travailles;
    }

    //endregion

    //region Methodes

    /**
     * Fonction qui sauvegarde le profil employé dans un fichier dans le répertoire "employes/"
     * @author Hippolyte
     */
    public void ecrireEmploye() {
        final String nom_dossier = "employes/";
        final String nom_fichier = this.nom + "_" + this.prenom + ".ser";
        final String chemin_fichier = nom_dossier + nom_fichier;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(chemin_fichier))) {
            oos.writeObject(this);
            System.out.println("Employe écrit: " + this.nom + " " + this.prenom);
        } catch (Exception e) {
            System.err.println("Impossible d'écrire : " + e);
        }
    }

    /**
     * Fonction qui affiche les informations de l'employé dans la console
     * @author Hippolyte
     */
    public void afficher() {
        System.out.println("Nom: " + this.nom);
        System.out.println("Prenom: " + this.prenom);
        System.out.println("Salaire: " + this.salaire);
        System.out.println("Jours travaillés: " + this.jours_travailles);
        System.out.println("Poste: " + this.poste);
    }

    public void afficherConcis() {
        System.out.println(this.nom + " " + this.prenom + " (" + this.poste + ")");
    }

    public void interfaceEmploye() {
        System.out.println("Interface employé, ne devrait jamais être utilisée comme cela");
    }

    //endregion

    // region Runnable
    @Override
    public void run() {
        interfaceEmploye();
    }
    // endregion
}
