package org.example;

import java.util.HashMap;

public class Commande {
    private Client client;
    private HashMap<String, Integer> produits;

    public Commande(Client client, HashMap<String, Integer> produits) {
        this.client = client;
        this.produits = produits;
    }
}
