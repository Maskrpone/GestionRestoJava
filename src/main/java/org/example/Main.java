package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Employe e = new Employe("Doe", "John", 1000, "Serveur");
        Stock s = new Stock();
        Map<Ingredients, Integer> sto = new HashMap<>();
        Ingredients i1 = new Ingredients("Tomate", 1);
        Ingredients i2 = new Ingredients("Patate", 2);
        Ingredients i3 = new Ingredients("Steak", 4);
        sto.put(i1, 10);
        sto.put(i2, 8);
        sto.put(i3, 3);
        s.setStock((HashMap<Ingredients, Integer>) sto);
        Manager m = new Manager("Doe", "Jane", 2000, "Manager", s );
        m.consulterStock();
        //JSON j = new JSON();
    }
}

