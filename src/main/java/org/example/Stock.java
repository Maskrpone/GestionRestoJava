package org.example;
import java.util.HashMap;

public class Stock {
    private HashMap<String, Integer> stock;
    public Stock() {
        this.stock = new HashMap<String, Integer>();
    }
    public HashMap<String, Integer> getStock() {
        return stock;
    }
    public int getStock(String ingredient) {
        return stock.get(ingredient);
    }
    public void useStock(String ingredient, int quantity) {
        if (stock.containsKey(ingredient)) {
            stock.put(ingredient, stock.get(ingredient) - quantity);
        } else {
            stock.put(ingredient, quantity);
        }
    }
}
