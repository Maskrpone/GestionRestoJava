package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Employe e = new Employe("Doe", "John", 1000, "Serveur");
        Manager m = new Manager("Doe", "Jane", 2000, "Manager", new HashMap<String, Integer>());
        Stock s = new Stock();
        Ingredients i = new Ingredients("Tomate", 1);
        JSON j = new JSON();
    }
}

