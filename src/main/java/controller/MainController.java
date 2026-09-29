package controller;

import app.AppContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private Label lblNamaBioskop;
    @FXML private Label lblUserRole;
    @FXML private ToggleGroup navGroup;
    @FXML private RadioButton btnNavCustomer;
    @FXML private RadioButton btnNavAdmin;
    @FXML private StackPane mainContentArea;

    private Parent customerView;
    private CustomerController customerController;
    private Parent adminView;
    private AdminController adminController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblNamaBioskop.setText(AppContext.getInstance().getBioskop().getNama().toUpperCase());

        loadViews();

        navGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == btnNavCustomer) {
                switchToCustomerView();
            } else if (newVal == btnNavAdmin) {
                switchToAdminView();
            }
        });

        // Tampilan awal: Customer View
        switchToCustomerView();
    }

    private void loadViews() {
        try {
            FXMLLoader custLoader = new FXMLLoader(getClass().getResource("/fxml/customer/customer-view.fxml"));
            customerView = custLoader.load();
            customerController = custLoader.getController();

            FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("/fxml/admin/admin-view.fxml"));
            adminView = adminLoader.load();
            adminController = adminLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchToCustomerView() {
        if (customerView != null) {
            mainContentArea.getChildren().setAll(customerView);
            if (customerController != null) {
                customerController.refreshData();
            }
            lblUserRole.setText("Mode Pengunjung (Pesan Tiket)");
        }
    }

    private void switchToAdminView() {
        if (adminView != null) {
            mainContentArea.getChildren().setAll(adminView);
            if (adminController != null) {
                adminController.refreshData();
            }
            lblUserRole.setText("Mode Admin (" + AppContext.getInstance().getAdmin().getNama() + ")");
        }
    }
}
