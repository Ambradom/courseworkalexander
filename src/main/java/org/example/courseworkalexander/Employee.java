package org.example.courseworkalexander;

import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L; // Версия сериализации
    private String name;
    private String position;

    // Конструктор по умолчанию
    public Employee() {
        this.name = "";
        this.position = "";
    }

    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}



