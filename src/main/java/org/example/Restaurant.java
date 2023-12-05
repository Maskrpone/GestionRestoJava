package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {
    private Manager manager;
    private ArrayList<Employe> employes;

    //region Constructor
    public Restaurant() {
        Manager manager = new Manager("Lemichel", "Thibaut", 2000, "Manager");
        //this.employes = manager.formerEquipe();
        this.employes = new ArrayList<>();
    }
    //endregion


}
