package Controller;

import dao.custom.CustomerDAO;
import dao.custom.impl.CustomerDAOImpl;
import entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {
    public AnchorPane customerFormContext;
    public TextField txtCContact;
    public TextField txtCAddress;
    public TextField txtCNIC;
    public TextField txtCName;
    public TextField txtCEmail;
    public Label lblCustomerID;
    public TableView tblCustomerDetails;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colNIC;
    public TableColumn colContact;
    public TableColumn colEmail;
    public TableColumn colAddress;
    public TextField txtCEmail1;
    public TextField txtcID1;
    public TextField txtCContact1;
    public TextField txtCAddress1;
    public TextField txtCName1;
    public TextField txtCNIC1;
    public Button btnUpdate;
    public Button btnDelete;
    int cartSelectedRow = -1;

    public void backToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        customerFormContext.getChildren().clear();
        customerFormContext.getChildren().add(load);

    }

    public void initialize() throws SQLException, ClassNotFoundException {
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        CustomerDAO customerDAO = new CustomerDAOImpl();
        String id = customerDAO.setCustomerIDS();
        lblCustomerID.setText(id);

        colID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tblCustomerDetails.getItems().setAll(customerDAO.getAll());

        tblCustomerDetails.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRow = (int)newValue;
            try {
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
                List<Customer> result = customerDAO.getAll();
                Customer customer = result.get(cartSelectedRow);
                txtcID1.setText(customer.getCustomerID());
                txtCName1.setText(customer.getName());
                txtCNIC1.setText(customer.getNic());
                txtCAddress1.setText(customer.getAddress());
                txtCContact1.setText(customer.getContact());
                txtCEmail1.setText(customer.getEmail());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }


    public void addCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!txtCName.getText().isEmpty() && !txtCAddress.getText().isEmpty() && !txtCContact.getText().isEmpty() && !txtCEmail.getText().isEmpty() && !txtCNIC.getText().isEmpty()) {

            Customer customer = new Customer(lblCustomerID.getText(), txtCName.getText(), txtCAddress.getText(), txtCNIC.getText(), txtCContact.getText(), txtCEmail.getText());

            CustomerDAO customerDAO = new CustomerDAOImpl();
            customerDAO.add(customer);

            txtCNIC.clear();
            txtCEmail.clear();
            txtCContact.clear();
            txtCAddress.clear();
            txtCName.clear();
        }else {
            new Alert(Alert.AlertType.WARNING,"Please fill All fields and try again..!").show();
        }
    }

    public void updateCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!txtCName1.getText().isEmpty() && !txtCAddress1.getText().isEmpty() && !txtCContact1.getText().isEmpty() && !txtCEmail1.getText().isEmpty() && !txtCNIC1.getText().isEmpty()) {
            Customer customer = new Customer(txtcID1.getText(), txtCName1.getText(), txtCAddress1.getText(), txtCNIC1.getText(), txtCContact1.getText(), txtCEmail1.getText());

            CustomerDAO customerDAO = new CustomerDAOImpl();
            boolean b = customerDAO.update(customer);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Error").show();
            }
        }
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(cartSelectedRow==-1){
            new Alert(Alert.AlertType.WARNING,"Please select a customer and try again..!").show();
        }else{
            CustomerDAO customerDAO = new CustomerDAOImpl();
            List<Customer> result = customerDAO.getAll();
            Customer customer = result.get(cartSelectedRow);
            boolean b = customerDAO.delete(customer);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Error").show();
            }
        }
    }
}
