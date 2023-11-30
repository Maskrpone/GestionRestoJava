package org.example;

public class Preparateur extends Employe{
    public Preparateur(String nom, String prenom, int salaire, String poste) {
        super(nom, prenom, salaire, poste);
    }
    public void prepare(Commande commande) {

    }

    /**
     * Permet au thread d'attendre le temps de la preparation
     * @param tempsAttente temps d'attente pour le plat
     */
    protected void attendre(int tempsAttente) {
        try {
            // Simuler l'attente en millisecondes (vous pouvez ajuster cette logique en fonction de vos besoins)
            Thread.sleep(tempsAttente * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
