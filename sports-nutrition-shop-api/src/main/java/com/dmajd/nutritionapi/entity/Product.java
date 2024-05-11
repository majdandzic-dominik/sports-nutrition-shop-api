package com.dmajd.nutritionapi.entity;

public class Product
{
    private String name;
    private String price;
    private String amount;
    private boolean isAvailable;

    public Product()
    {
    }

    public Product(String name, String price, String amount, boolean isAvailable)
    {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.isAvailable = isAvailable;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public boolean isAvailable()
    {
        return isAvailable;
    }

    public void setAvailable(boolean available)
    {
        isAvailable = available;
    }
}
