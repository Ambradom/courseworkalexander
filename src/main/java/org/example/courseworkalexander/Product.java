package org.example.courseworkalexander;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L; // Версия сериализации
    private String name;
    private double price;

    // Конструктор по умолчанию
    public Product() {
        this.name = "";
        this.price = 0.0;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}




