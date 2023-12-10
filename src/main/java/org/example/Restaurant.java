package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {
    private Manager manager;
    private ArrayList<Serveur> serveurs;
    private ArrayList<Cuisinier> cuisiniers;
    private ArrayList<Barman> barmans;


    private final int[] tab = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

    //region Constructor
    public Restaurant() {
        Manager manager = new Manager("Lemichel", "Thibaut", 2000, "Manager");
        //this.employes = manager.formerEquipe();
        this.serveurs = new ArrayList<>();
        this.cuisiniers = new ArrayList<>();
        this.barmans = new ArrayList<>();
    }
    //endregion

    /**
     * Fonction qui initialise le restaurant en créant les employés et les tables
    //  */
    // public void debutJournee() throws ErrorHandler {
    //     ArrayList<Employe> serveurs = this.manager.formerEquipe("serveur");
    //     ArrayList<Employe> cuisiniers = this.manager.formerEquipe("cuisinier");
    //     ArrayList<Employe> barmans = this.manager.formerEquipe("barman");

    //     if (serveurs.size() < 4 || cuisiniers.size() < 2 || barmans.isEmpty()) {
    //         throw new ErrorHandler("Il vous faut au moins 4 serveurs, 2 cuisiniers et 1 barman pour ouvrir le restaurant");
    //     }

    //     for (Employe employe : serveurs) {
    //         this.serveurs.add((Serveur) employe);
    //     }
    //     for (Employe employe : cuisiniers) {
    //         this.cuisiniers.add((Cuisinier) employe);
    //     }
    //     for (Employe employe : barmans) {
    //         this.barmans.add((Barman) employe);
    //     }

    //     System.out.println("Le restaurant est ouvert !");

    // }

    // region interface
    public void interfaceAccueil() {
        System.out.println("Menu d'accueil : ");
        String operation;
        try (Scanner sc = new Scanner(System.in)) {
            do {
                System.out.println("Que voulez-vous faire ?");
                System.out.println("1. Se connecter en tant que manager");
                System.out.println("2. Se connecter en tant qu'employé");
                System.out.println("0. Mettre fin au programme");
                System.out.print("> ");
                operation = sc.nextLine();

            } while (!operation.equals("0"));
        }
    }
    // endregion interface

}
