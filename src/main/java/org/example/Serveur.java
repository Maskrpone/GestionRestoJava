package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serveur extends Employe{

    //region Constructor
    public Serveur(String nom, String prenom, int salaire, String poste) {
        super(nom, prenom, salaire, poste);
    }

    //endregion

    /**
     * Fonction qui prend en charge les commandes
     * @author  Hippolyte
     * @param table le numéro de la table
     * @param boissons tableau des boissons
     * @param repas tableau des repas
     */
    public void addCommande(int table, ArrayList<Boisson> boissons, ArrayList<Produits> repas) {
        int produit_impossible = 0;
        for (Boisson it : boissons) {
            if(inStock(it)) {
                System.out.println("Nous pouvons produire " + it.getName());
            } else {
                System.err.println("Nous ne pouvons pas produire " + it.getName());
                produit_impossible++;
            }
        }
        for (Produits it : repas) {
            if (inStock(it)) {
                System.out.println("Nous pouvons produire " + it.getNom());
            } else {
                System.err.println("Nous ne pouvons pas produire " + it.getNom());
                produit_impossible++;
            }
        }
        if (produit_impossible == 0) {
            Commande commande = new Commande(table, boissons, repas);
            // envoi de la commande en cuisine et au bar
            this.envoi(commande);
            System.out.println("Commande envoyée à la cuisine et au bar.");
        }
    }

    /**
     * Fonction qui renvoie True s'il y a suffisemment d'ingrédients dans le stock et False sinon.
     * @author Hippolyte
     * @param prod qui est le produit dont on veut controler la disponibilité des ingrédients
     * @return Boolean
     */
    public boolean inStock(Produits prod) {
        Stock stock = new Stock();
        for (String key : prod.getIngredients().keySet()) {
            if (stock.getIngredient(key) < prod.getIngredients().get(key)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Fonction qui renvoie True si la boisson est disponible et False sinon.
     * @author Hippolyte
     * @param boisson   la boisson dont on veut vérifier la disponibilité
     * @return  boolean
     */

    public boolean inStock(Boisson boisson) {
        Stock stock = new Stock();
        return stock.getIngredient(boisson.getName()) != 0;
    }

    /**
     * Fonction qui renvoie le numéro de la commande suivante
     * @param dossier chemin vers le dossier des commandes
     * @param prefixe prefixe du nom du fichier
     * @return  le numéro de la commande actuelle
     */
    public int obtenirCommandeSuivante(String dossier, String prefixe) {
        File dossierCommandes = new File(dossier);
        File[] fichiers = dossierCommandes.listFiles(((dir, name) -> name.startsWith(prefixe)));

        int dernierNumero = 0;

        if (fichiers != null) {
            for (File fichier : fichiers) {
                String nom = fichier.getName();
                int numero = Integer.parseInt(nom.substring(prefixe.length(), nom.lastIndexOf('.')));
                dernierNumero = Math.max(dernierNumero, numero);
            }
        }
        return dernierNumero + 1;
    }

    /**
     * Fonction pour sérialiser la commande dans le dossier /commandes/
     * @author   Hippolyte
     * @param commande la commande à sérialiser
     */
    public void envoi(Commande commande) {
        final String nom_dossier = "/commandes/";
        String prefixe = "COMM_";
        int numeroCommande = obtenirCommandeSuivante(nom_dossier,prefixe);
        String nomFichier = prefixe + numeroCommande + ".ser";
        String cheminFichier = nom_dossier + nomFichier;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cheminFichier))) {
            oos.writeObject(commande);
            System.out.println("La commande de la table" + commande.getTable() + " est envoyée en cuisine !");
        } catch (IOException e) {
            System.err.println("Impossible d'écrire : " + e);
        }
    }

    public boolean verifCommande(Commande commande) {
        return true;
    }

    public void servirCommande(Commande commande) {
    }


}
