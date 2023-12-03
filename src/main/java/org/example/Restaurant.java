package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {
    private Manager manager;
    private ArrayList<Employe> employes;

    public Restaurant() {
        Manager manager = new Manager("Lemichel", "Thibaut", 2000, "Manager");

    }

    public void ecranAccueil() {
        System.out.println("Quel écran souhaitez-vous afficher ?");
        System.out.println("1. Ecran Manager");
        System.out.println("2. Ecran Serveur");
        System.out.println("3. Ecran Barman");
        System.out.println("4. Ecran Cuisinier");
        System.out.println("5. Quitter");

        do {
            Scanner sc = new Scanner(System.in);
            int choix = sc.nextInt();
            switch (choix) {
                case 1:
                    this.ecranManager();
                    break;
                case 2:
                    this.ecranServeur();
                    break;
                case 3:
                    this.ecranBarman();
                    break;
                case 4:
                    this.ecranCuisinier();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Veuillez saisir un nombre entre 1 et 5");
                    break;
            }
        } while (true);

    }

    public void ecranManager() {
        System.out.println("Écran Manager, que voulez-vous faire ?");

    }

    public void ecranServeur() {
    }

    public void ecranBarman() {
    }

    public void ecranCuisinier() {
    }

}
