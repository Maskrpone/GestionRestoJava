package org.example;


public class Barman extends Preparateur {

    public Barman(String nom, String prenom, int salaire, String poste) {
        super(nom, prenom, salaire, poste);
    }

    //region Methode

    /**
     * Fonction qui prépare les boissons d'une commande
     * @param commande commande à préparer
     */
    public void preparerBoisson(Commande commande){
        Stock stock = new Stock();
        System.out.println( this.getNom() + ", un de nos barman, en train de préparer la commande pour la table " + commande.getTable());
        commande.setEnPreparation(true);
        // Parcourir la liste des plats de la commande
        for (Boisson boisson : commande.getBoissons()) {
            // System.out.println(boisson);
            //Supprimer des stocks
            supprimerDesStocks(boisson, stock);

            // Simuler la cuisson en attente du temps de préparation
            attendre(1);

            System.out.println("Boisson " + boisson.getName() + " prêt pour la table " + commande.getTable());
        }

        System.out.println("Commande des BOISSONS pour la table " + commande.getTable() + " est prête !");
    }

    /**
     * Supprime la boisson des stocks
     * @param boisson Boisson à supprimer
     * @param stock Stock du restaurant
     */
    private void supprimerDesStocks(Boisson boisson, Stock stock){
        stock.useStockBoisson(boisson);
    }
    //endregion
}
