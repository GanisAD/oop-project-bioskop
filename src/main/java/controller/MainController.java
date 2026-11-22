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

/**
 * Controller tampilan utama ({@code main-view.fxml}).
 * Menampilkan nama bioskop dan tombol navigasi untuk berpindah antara
 * tampilan pelanggan ({@link CustomerController}) dan tampilan admin ({@link AdminController}).
 *
 * @author Zinedine Zain Arthurio Y. P.
 * @author Aisyah Mahadma Shaquilla Brahmasari Putri
 * @author Ganis Ahmad Darpita
 * @version 1.0
 */
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

    /**
     * Dipanggil JavaFX setelah FXML dimuat: mengisi nama bioskop, memuat kedua tampilan,
     * memasang listener navigasi, dan menampilkan tampilan pelanggan sebagai awal.
     *
     * @param location  lokasi FXML yang dimuat
     * @param resources resource bundle yang digunakan (boleh {@code null})
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblNamaBioskop.setText(AppContext.getInstance().getBioskop().getNama().toUpperCase());

        loadViews();

        if (navGroup == null) {
            navGroup = new ToggleGroup();
            btnNavCustomer.setToggleGroup(navGroup);
            btnNavAdmin.setToggleGroup(navGroup);
        }

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

    /**
     * Memuat {@code customer-view.fxml} dan {@code admin-view.fxml} beserta controller-nya. Jika gagal, error dicetak ke konsol.
     */
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

    /**
     * Menampilkan tampilan pelanggan, menyegarkan datanya, dan mengubah label peran.
     */
    private void switchToCustomerView() {
        if (customerView != null) {
            mainContentArea.getChildren().setAll(customerView);
            if (customerController != null) {
                customerController.refreshData();
            }
            lblUserRole.setText("Mode Pengunjung (Pesan Tiket)");
        }
    }

    /**
     * Menampilkan tampilan admin, menyegarkan datanya, dan mengubah label peran.
     */
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
