package com.example.enterpriseapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.courseworkalexander.Enterprise;

public class MainController {

    @FXML
    private TableView<Enterprise> enterpriseTable;

    @FXML
    private TableColumn<Enterprise, String> nameColumn;

    @FXML
    private TableColumn<Enterprise, String> addressColumn;

    @FXML
    private TableColumn<Enterprise, Integer> employeesColumn;

    @FXML
    private TableColumn<Enterprise, Double> revenueColumn;

    private ObservableList<Enterprise> enterprises = FXCollections.observableArrayList();

    public void initialize() {
        // Настройка колонок таблицы
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        employeesColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfEmployees"));
        revenueColumn.setCellValueFactory(new PropertyValueFactory<>("annualRevenue"));

        // Добавление тестовых данных
        enterprises.addAll(
                new Enterprise("Предприятие А", "Москва, ул. Ленина, д. 10", 100, 50000000),
                new Enterprise("Предприятие Б", "Санкт-Петербург, пр-т Невский, д. 15", 200, 70000000)
        );

        // Установка данных в таблицу
        enterpriseTable.setItems(enterprises);
    }

    @FXML
    public void handleAddButton(ActionEvent event) {
        // Логика добавления нового предприятия
    }

    @FXML
    public void handleEditButton(ActionEvent event) {
        // Логика редактирования выбранного предприятия
    }

    @FXML
    public void handleDeleteButton(ActionEvent event) {
        // Логика удаления выбранного предприятия
    }
}