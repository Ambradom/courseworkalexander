```java
package org.example.courseworkalexander;

public class Enterprise {
    private String name;
    private String address;
    private int numberOfEmployees;
    private double annualRevenue;

    public Enterprise(String name, String address, int numberOfEmployees, double annualRevenue) {
        this.name = name;
        this.address = address;
        this.numberOfEmployees = numberOfEmployees;
        this.annualRevenue = annualRevenue;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public double getAnnualRevenue() {
        return annualRevenue;
    }

    // Сеттеры
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public void setAnnualRevenue(double annualRevenue) {
        this.annualRevenue = annualRevenue;
    }
}
