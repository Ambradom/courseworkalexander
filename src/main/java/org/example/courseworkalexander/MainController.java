package org.example.courseworkalexander;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private TableView<ManufacturingEnterprise> enterpriseTable;

    @FXML
    private TableColumn<ManufacturingEnterprise, String> nameColumn;

    @FXML
    private TableColumn<ManufacturingEnterprise, String> addressColumn;

    @FXML
    private TableColumn<ManufacturingEnterprise, Integer> employeesColumn;

    @FXML
    private TableColumn<ManufacturingEnterprise, Double> revenueColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField employeesField;

    @FXML
    private TextField revenueField;

    @FXML
    private TextField employeeNameField; // Поле ввода имени сотрудника
    @FXML
    private TextField employeePositionField; // Поле ввода должности сотрудника

    @FXML
    private TextField productNameField; // Поле ввода названия продукта
    @FXML
    private TextField productPriceField; // Поле ввода цены продукта

    private ObservableList<ManufacturingEnterprise> enterprises = FXCollections.observableArrayList();

    public void initialize() {
        // Настройка колонок таблицы
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        employeesColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfEmployees"));
        revenueColumn.setCellValueFactory(new PropertyValueFactory<>("annualRevenue"));

        // Инициализация таблицы
        loadEnterprises();

        // Обработчик выбора строки в таблице
        enterpriseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Заполнение полей текстом при выборе предприятия
                nameField.setText(newValue.getName());
                addressField.setText(newValue.getAddress());
                employeesField.setText(String.valueOf(newValue.getNumberOfEmployees()));
                revenueField.setText(String.valueOf(newValue.getAnnualRevenue()));
            }
        });
    }

    private void loadEnterprises() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("enterprises.dat"))) {
            List<ManufacturingEnterprise> loadedEnterprises = (List<ManufacturingEnterprise>) ois.readObject();
            enterprises.addAll(loadedEnterprises);
            enterpriseTable.setItems(enterprises);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddButton(ActionEvent event) {
        String name = nameField.getText();
        String address = addressField.getText();
        String employeesText = employeesField.getText();
        String revenueText = revenueField.getText();

        if (name.isEmpty() || address.isEmpty() || employeesText.isEmpty() || revenueText.isEmpty()) {
            showAlert("Ошибка", "Все поля должны быть заполнены.");
            return;
        }

        try {
            int employees = Integer.parseInt(employeesText);
            double revenue = Double.parseDouble(revenueText);

            ManufacturingEnterprise newEnterprise = new ManufacturingEnterprise(name, address, employees, revenue);
            enterprises.add(newEnterprise);
            enterpriseTable.setItems(enterprises); // Установите данные в таблицу после добавления

            saveEnterprises();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Количество сотрудников и годовой доход должны быть числами.");
        }
    }

    @FXML
    public void handleEditButton(ActionEvent event) {
        ManufacturingEnterprise selectedEnterprise = enterpriseTable.getSelectionModel().getSelectedItem();
        if (selectedEnterprise != null) {
            String name = nameField.getText();
            String address = addressField.getText();
            int employees = Integer.parseInt(employeesField.getText());
            double revenue = Double.parseDouble(revenueField.getText());

            selectedEnterprise.setName(name);
            selectedEnterprise.setAddress(address);
            selectedEnterprise.setNumberOfEmployees(employees);
            selectedEnterprise.setAnnualRevenue(revenue);

            enterpriseTable.refresh();
            saveEnterprises();
            clearFields();
        } else {
            showAlert("Ошибка", "Пожалуйста, выберите предприятие для редактирования.");
        }
    }

    @FXML
    public void handleDeleteButton(ActionEvent event) {
        ManufacturingEnterprise selectedEnterprise = enterpriseTable.getSelectionModel().getSelectedItem();
        if (selectedEnterprise != null) {
            enterprises.remove(selectedEnterprise);
            enterpriseTable.setItems(enterprises); // Обновить таблицу
            saveEnterprises(); // Сохранить изменения
            clearFields(); // Очистить поля ввода
        } else {
            showAlert("Ошибка", "Пожалуйста, выберите предприятие для удаления.");
        }
    }

    @FXML
    public void handleAddEmployeeButton(ActionEvent event) {
        ManufacturingEnterprise selectedEnterprise = enterpriseTable.getSelectionModel().getSelectedItem();
        if (selectedEnterprise != null) {
            String employeeName = employeeNameField.getText();
            String employeePosition = employeePositionField.getText();

            if (employeeName.isEmpty() || employeePosition.isEmpty()) {
                showAlert("Ошибка", "Все поля должны быть заполнены.");
                return;
            }

            Employee newEmployee = new Employee(employeeName, employeePosition);
            selectedEnterprise.addEmployee(newEmployee); // Добавляем сотрудника
            saveEnterprises(); // Сохраняем изменения
            employeeNameField.clear();
            employeePositionField.clear();
        } else {
            showAlert("Ошибка", "Пожалуйста, выберите предприятие для добавления сотрудника.");
        }
    }

    @FXML
    public void handleAddProductButton(ActionEvent event) {
        ManufacturingEnterprise selectedEnterprise = enterpriseTable.getSelectionModel().getSelectedItem();
        if (selectedEnterprise != null) {
            String productName = productNameField.getText();
            String productPriceText = productPriceField.getText();

            if (productName.isEmpty() || productPriceText.isEmpty()) {
                showAlert("Ошибка", "Все поля должны быть заполнены.");
                return;
            }

            try {
                double productPrice = Double.parseDouble(productPriceText);
                Product newProduct = new Product(productName, productPrice);
                selectedEnterprise.addProduct(newProduct); // Добавляем продукт
                saveEnterprises(); // Сохраняем изменения
                productNameField.clear();
                productPriceField.clear();
            } catch (NumberFormatException e) {
                showAlert("Ошибка", "Цена должна быть числом.");
            }
        } else {
            showAlert("Ошибка", "Пожалуйста, выберите предприятие для добавления продукта.");
        }
    }


    @FXML
    public void handleViewEmployeesButton(ActionEvent event) {
        ManufacturingEnterprise selectedEnterprise = enterpriseTable.getSelectionModel().getSelectedItem();
        if (selectedEnterprise != null) {
            List<Employee> employees = selectedEnterprise.getEmployees();
            StringBuilder employeeList = new StringBuilder("Сотрудники:\n");
            if (employees.isEmpty()) {
                employeeList.append("Нет сотрудников.");
            } else {
                for (Employee emp : employees) {
                    if (emp != null) {
                        employeeList.append(emp.getName()).append(" - ").append(emp.getPosition()).append("\n");
                    } else {
                        employeeList.append("Неизвестный сотрудник\n"); // Обработка случая с null
                    }
                }
            }
            showAlert("Список сотрудников", employeeList.toString());
        } else {
            showAlert("Ошибка", "Пожалуйста, выберите предприятие для просмотра сотрудников.");
        }
    }

    @FXML
    public void handleViewProductsButton(ActionEvent event) {
        ManufacturingEnterprise selectedEnterprise = enterpriseTable.getSelectionModel().getSelectedItem();
        if (selectedEnterprise != null) {
            List<Product> products = selectedEnterprise.getProducts();
            StringBuilder productList = new StringBuilder("Продукты:\n");
            if (products.isEmpty()) {
                productList.append("Нет продуктов.");
            } else {
                for (Product prod : products) {
                    productList.append(prod.getName()).append(" - ").append(prod.getPrice()).append("\n");
                }
            }
            showAlert("Список продуктов", productList.toString());
        } else {
            showAlert("Ошибка", "Пожалуйста, выберите предприятие для просмотра продуктов.");
        }
    }

    private void clearFields() {
        nameField.clear();
        addressField.clear();
        employeesField.clear();
        revenueField.clear();
        employeeNameField.clear();
        employeePositionField.clear();
        productNameField.clear();
        productPriceField.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void saveEnterprises() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("enterprises.dat"))) {
            oos.writeObject(new ArrayList<>(enterprises));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



