package com.example.demo.entity;

import javax.persistence.*;

@Entity
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String type;
    @Column
    private double price;

    public Shipping() {
    }

    public Shipping(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
