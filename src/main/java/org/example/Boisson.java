package org.example;

public class Boisson {
    private String name;
    private int price;

    //region Constructor

    public Boisson(String name, int price) {
        this.name = name;
        this.price = price;
    }

    //endregion

    //region Getter

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    //endregion

    //region Setter

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    //endregion

    //region Methode

    @Override
    public String toString() {
        return "Boisson{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    //endregion
}
