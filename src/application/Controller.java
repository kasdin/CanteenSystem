package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;


public class Controller {

    @FXML StackPane loginPane;
    @FXML TextField usernameField;
    @FXML PasswordField passwordField;
    @FXML Button btnLogin;

    @FXML private StackPane menuPane;
    @FXML Button btnServeCustomers;


    @FXML StackPane serveCustomersPane;
    @FXML Button btnServeCustomersAccept;
    @FXML GridPane serveCustomersGrid;
    @FXML GridPane invoiceGrid;
    @FXML Button btnInvoiceCancel;
    @FXML Label labelSubTotal;
    int subtotal = 0;


    @FXML StackPane checkoutPane;
    @FXML Button btnCheckoutAccept;
    @FXML TextField employeeID;

    @FXML StackPane checkoutSuccessPane;
    @FXML Label checkoutSubTotalLabel;
    @FXML Button btnCheckoutSuccessFinish;

    @FXML public void login() {
        DB.selectSQL("SELECT (fldUsername) From tblCanteenEmployees WHERE fldUsername ='" + usernameField.getText() + "'");
        String username = DB.getData();

        DB.selectSQL("SELECT (fldPassword) From tblCanteenEmployees WHERE fldUsername ='" + usernameField.getText() + "'");
        String password = DB.getData();


        if (usernameField.getText().equals(username) && passwordField.getText().equals(password)) {
            loginPane.setVisible(false);
            menuPane.setVisible(true);
        } else {
            System.out.println("Wrong password, try again");
        }

    }

    @FXML public void serveCustomers() {
        ArrayList<String> arrayList = new ArrayList<>();
        menuPane.setVisible(false);
        serveCustomersPane.setVisible(true);

        DB.selectSQL("SELECT concat(fldName,' ', fldPrice, 'KR') FROM tblProducts;");
        do {
            String data = DB.getDisplayData();
            if (data.equals(DB.NOMOREDATA)) {
                break;
            } else {
                arrayList.add(data);
            }

        } while(true);

        for (int i = 0; i < arrayList.size() ; i++) {
            Button product = new Button(arrayList.get(i));
            product.setStyle("-fx-background-color: #ff0037; -fx-text-fill: WHITE; -fx-font-size: 16px");
            product.setId((String.valueOf(i)));
            product.setOnAction(event -> addToInvoice(product.getText(), product.getId()));
            serveCustomersGrid.add(product,0, i);

        }


    }

    public void addToInvoice(String name, String ID) {
        //System.out.println(name.substring(name.length() - 5, name.length() -3));
        subtotal += Integer.valueOf(name.substring(name.length() - 5, name.length() -3));
        Label productLabel = new Label(name);
        invoiceGrid.add(productLabel, 0, Integer.valueOf(ID));
        labelSubTotal.setText("Sub Total: " + subtotal + "KR");

    }

    @FXML public void cancelInvoice() {
        invoiceGrid.getChildren().clear();
        subtotal = 0;
        labelSubTotal.setText("Sub Total: " + subtotal + "KR");

    }

    @FXML public void serveCustomersAccept() {
        menuPane.setVisible(false);
        serveCustomersPane.setVisible(false);
        checkoutPane.setVisible(true);
    }


    @FXML public void checkoutAccept() {

        DB.selectSQL("SELECT fldEmployeeID From tblCustomer WHERE fldEmployeeID ='" + employeeID.getText() + "'");
        String employee = DB.getData();
        int maxCharacters = 4;

        if (employeeID.getText().equals(employee)){
            System.out.println("Succesfully");
            checkoutSuccessPane.setVisible(true);
            menuPane.setVisible(false);
            serveCustomersPane.setVisible(false);
            checkoutPane.setVisible(false);
            checkoutSubTotalLabel.setText(subtotal + "KR has been withdrawn from ID " + employee);

        } else if (employeeID.getText().length() > maxCharacters){
            Label longCode = new Label("Please insert 4 digit code");
            longCode.setTextFill(Color.CORAL);
            longCode.setTranslateY(450);
            longCode.setTranslateX(-40);
            longCode.setFont(new Font(20));
            checkoutPane.getChildren().addAll(longCode);
        }
        else {
            Label wrongCode = new Label("Wrong code, please try again");
            wrongCode.setTextFill(Color.CORAL);
            wrongCode.setTranslateY(450);
            wrongCode.setTranslateX(-40);
            wrongCode.setFont(new Font(20));
            checkoutPane.getChildren().addAll(wrongCode);
        }


    }


    @FXML public void checkoutSuccessFinish() {
        menuPane.setVisible(true);
        serveCustomersPane.setVisible(false);
        checkoutPane.setVisible(false);
        checkoutSuccessPane.setVisible(false);
    }








}
