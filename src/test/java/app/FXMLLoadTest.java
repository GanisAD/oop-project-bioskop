package app;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.InputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FXMLLoadTest {
    public static void main(String[] args) throws Exception {
        System.out.println("--- Testing FXML Loading (Scene Builder Compatibility) ---");
        CountDownLatch latch = new CountDownLatch(1);
        final Throwable[] error = new Throwable[1];

        Platform.startup(() -> {
            try {
                // Test 1: customer-view.fxml
                System.out.println("Testing /fxml/customer/customer-view.fxml...");
                FXMLLoader l1 = new FXMLLoader(FXMLLoadTest.class.getResource("/fxml/customer/customer-view.fxml"));
                Parent p1 = l1.load();
                if (p1 == null) throw new RuntimeException("customer-view root null");
                System.out.println("✓ customer-view.fxml OK!");

                // Test 2: main-view.fxml
                System.out.println("Testing /fxml/main-view.fxml...");
                FXMLLoader l2 = new FXMLLoader(FXMLLoadTest.class.getResource("/fxml/main-view.fxml"));
                Parent p2 = l2.load();
                if (p2 == null) throw new RuntimeException("main-view root null");
                System.out.println("✓ main-view.fxml OK!");

                // Test 3: admin-view.fxml
                System.out.println("Testing /fxml/admin/admin-view.fxml...");
                FXMLLoader l3 = new FXMLLoader(FXMLLoadTest.class.getResource("/fxml/admin/admin-view.fxml"));
                Parent p3 = l3.load();
                if (p3 == null) throw new RuntimeException("admin-view root null");
                System.out.println("✓ admin-view.fxml OK!");

                System.out.println("ALL FXMLs verified successfully!");
            } catch (Throwable t) {
                error[0] = t;
            } finally {
                latch.countDown();
            }
        });

        latch.await(10, TimeUnit.SECONDS);
        if (error[0] != null) {
            error[0].printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }
}
