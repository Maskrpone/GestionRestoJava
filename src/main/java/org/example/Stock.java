package org.example;
import java.util.HashMap;

public class Stock {
    private HashMap<Ingredients, Integer> stock;

    //region Constructor
    public Stock() {
        this.stock = new HashMap<Ingredients, Integer>();
    }
    //endregion

    //region Getter
    public HashMap<Ingredients, Integer> getStock() {
        return stock;
    }
    public int getStock(String ingredient) {
        return stock.get(ingredient);
    }

    //endregion
    /*
    public void useStock(String ingredient, int quantity) {
        if (stock.containsKey(ingredient)) {
            stock.put(ingredient, stock.get(ingredient) - quantity);
        } else {
            stock.put(ingredient, quantity);
        }
    }
*/
    //region Setter
    public void setStock(HashMap<Ingredients, Integer> _stock) {
        this.stock = _stock;
    }

    //endregion
}
