package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Manager manager = new Manager("Lemichel", "Thibaut", 2000, "Manager");

        Menu menu = new Menu();

        ArrayList<Boisson> boissons = new ArrayList<>();
        boissons.add(menu.getCarteBoisson().get(1));
        boissons.add(menu.getCarteBoisson().get(1));
        boissons.add(menu.getCarteBoisson().get(1));
        boissons.add(menu.getCarteBoisson().get(1));

        ArrayList<Produits> plats = new ArrayList<>();
        plats.add(menu.getCartePlat().get(1));
        plats.add(menu.getCartePlat().get(1));
        plats.add(menu.getCartePlat().get(1));
        plats.add(menu.getCartePlat().get(1));
        plats.add(menu.getCartePlat().get(1));

        Commande commande = new Commande(1, boissons, plats);
        FileText f = new FileText();

        Map<Ingredients, Integer> map = manager.consulterStock();

        f.writeStockToFile(map);
    }
}

