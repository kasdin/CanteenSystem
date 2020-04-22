package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;



public class Controller {

    @FXML StackPane loginPane;
    @FXML TextField usernameField;
    @FXML PasswordField passwordField;
    @FXML Button btnLogin;

    @FXML private StackPane menuPane;
    @FXML Button btnServeCustomers;


    @FXML StackPane serveCustomersPane;
    @FXML Button btnServeCustomersAccept;

    @FXML StackPane checkoutPane;
    @FXML Button btnCheckoutAccept;

    @FXML StackPane checkoutSuccessPane;
    @FXML Button btnCheckoutSuccessFinish;

    @FXML public void login() {
        if (usernameField.getText().equals("Kasper") && passwordField.getText().equals("Admin")) {
            loginPane.setVisible(false);
            menuPane.setVisible(true);
        } else {
            System.out.println("Wrong password, try again");
        }

    }

    @FXML public void serveCustomers() {
        menuPane.setVisible(false);
        serveCustomersPane.setVisible(true);
    }

    @FXML public void serveCustomersAccept() {
        menuPane.setVisible(false);
        serveCustomersPane.setVisible(false);
        checkoutPane.setVisible(true);
    }

    @FXML public void checkoutAccept() {
        menuPane.setVisible(false);
        serveCustomersPane.setVisible(false);
        checkoutPane.setVisible(false);
        checkoutSuccessPane.setVisible(true);
    }

    @FXML public void checkoutSuccessFinish() {
        menuPane.setVisible(true);
        serveCustomersPane.setVisible(false);
        checkoutPane.setVisible(false);
        checkoutSuccessPane.setVisible(false);
    }








}
