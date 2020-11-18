package fr.romgrm.MyFirstApp.models;

public class FoodItem {

    // attributs

    private String name;
    private String mnemonic;
    private double price;


    // constructor

    public FoodItem (String name, String mnemonic, double price){
        this.name = name;
        this.mnemonic = mnemonic;
        this.price = price;
    }

   // getter

    public String getName() {
        return name;
    }

    public String getMnemonic(){
        return mnemonic;
    }

    public double getPrice() {
        return price;
    }

    // methods
}
