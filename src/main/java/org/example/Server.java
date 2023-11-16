package org.example;

import java.lang.reflect.Array;
import java.util.HashMap;

public class Server extends Employe{
    public Server(String nom, String prenom, int salaire, String poste) {
        super(nom, prenom, salaire);
    }

    public void addCommande(int table, HashMap<String, int> boissons, HashMap<String, int> repas) {

    }

    public boolean verifCommande(Commande commande) {
        return true;
    }

    public void servirCommande(Commande commande) {
    }


}
