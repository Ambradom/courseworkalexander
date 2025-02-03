package org.example.courseworkalexander;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManufacturingEnterprise extends Enterprise implements Serializable {
    private static final long serialVersionUID = 1L; // Версия сериализации

    private List<Employee> employees = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    // Конструктор по умолчанию
    public ManufacturingEnterprise() {
        super("", "", 0, 0.0); // Вызов конструктора суперкласса
        this.employees = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public ManufacturingEnterprise(String name, String address, int numberOfEmployees, double annualRevenue) {
        super(name, address, numberOfEmployees, annualRevenue);
        this.employees = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Product> getProducts() {
        return products;
    }
}




