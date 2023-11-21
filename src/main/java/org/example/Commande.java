package org.example;

import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Commande {
    private final int table;
    private final ArrayList<Produits> repas;
    private final ArrayList<Produits> boissons;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();


    //region Constructor
    public Commande(int table, ArrayList<Produits> boissons, ArrayList<Produits> repas ) {
        this.table = table;
        this.boissons = boissons;
        this.repas = repas;
    }
    //endregion

    /**
     * Renvoie une chaine de 15 caractères alpha-numériques pour l'utiliser
     * pour nommer les fichier de commande de manière aléatoire
     * @author Hippolyte
     * @return String, une chaine de 15 caractères alpha-numériques
     */
    public String generateRandomString() {
        StringBuilder randomString = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            randomString.append(CHARACTERS.charAt(randomIndex));
        }
        return randomString.toString();
    }

    public void envoi() {
        final String nom_dossier = "commandes/";
        String nom_commande = generateRandomString() + ".commande";

        File dossier = new File(nom_dossier);

        //TODO: vérifier si aucun fichier ne porte déjà le même prénom
    }
}
