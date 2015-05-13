package com.salkulator.dwiajik.salkulator;

/**
 * Created by dwiajik on 5/13/2015.
 */
public class Item {

    private String name;
    private double price;
    private double discount;
    private double total;

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public double getPrice()
    {
        return this.price;
    }

    public void setDiscount(double discount)
    {
        this.discount = discount;
    }

    public double getDiscount()
    {
        return this.discount;
    }

    public double getTotal()
    {
        return this.price * (1 - this.discount);
    }

}
