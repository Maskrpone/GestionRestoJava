package org.example;

public class Server extends Employe{
    public Server(String nom, String prenom, int salaire, String poste) {
        super(nom, prenom, salaire);
    }

    public void addCommande(Produits boissons, Produits plats, int table) {
    }

    public boolean verifCommande(Commande commande) {
        return true;
    }

    public void servirCommande(Commande commande) {
    }


}
