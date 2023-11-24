package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Employe e = new Employe("Doe", "John", 1000, "Serveur");
        Stock s = new Stock();
        ArrayList<Ingredients> stock = new ArrayList<>();

        Ingredients i1 = new Ingredients("Tomate", 1, 10);
        Ingredients i2 = new Ingredients("Patate", 2, 10);
        Ingredients i3 = new Ingredients("Steak", 4, 10);
        stock.add(i1);
        stock.add(i2);
        stock.add(i3);

        s.setStock(stock);
        Manager m = new Manager("Doe", "Jane", 2000, "Manager", s );
        System.out.println(m.ingredientsACommanderRapide(10, 20));
        System.out.println(m.consulterStockPriceToPay());
        System.out.println(i1.getNb());
        System.out.println(i2.getNb());
        System.out.println(i3.getNb());
    }
}

