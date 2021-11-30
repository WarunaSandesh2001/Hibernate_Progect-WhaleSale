package Controller;

import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.ItemDAOImpl;
import entity.Customer;
import entity.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import util.FactoryConfiguration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class ItemFormController {
    public AnchorPane itemFormContext;
    public TextField txtItemName;
    public TextField txtQuality;
    public TextField txtPrice;
    public TextField txtDiscount;
    public Label lblItemCode;
    public TableColumn colItemCode;
    public TableColumn colName;
    public TableColumn colQuantity;
    public TableColumn colPrice;
    public TableColumn colDiscount;
    public TableView tblItemDetails;
    public Button btnUpdate;
    public Button btnDelete;
    public TextField txtName1;
    public TextField txtQTY1;
    public TextField txtPrice1;
    public TextField txtDiscount1;
    public TextField txtItemCode1;
    int cartSelectedRow=-1;

    public void backToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        itemFormContext.getChildren().clear();
        itemFormContext.getChildren().add(load);
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        ItemDAO itemDAO = new ItemDAOImpl();
        String id = itemDAO.setItemIDS();
        lblItemCode.setText(id);

        System.out.println(itemDAO.getAll().toString());

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        tblItemDetails.getItems().setAll(itemDAO.getAll());

        tblItemDetails.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRow = (int)newValue;
            try {
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
                List<Item> result = itemDAO.getAll();
                Item item = result.get(cartSelectedRow);
                //System.out.println(item.toString());
                txtItemCode1.setText(item.getiCode());
                txtName1.setText(item.getName());
                txtQTY1.setText(String.valueOf(item.getQty()));
                txtPrice1.setText(String.valueOf(item.getPrice()));
                txtDiscount1.setText(String.valueOf(item.getDiscount()));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    public void addItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!txtItemName.getText().isEmpty() && !txtPrice.getText().isEmpty() && !txtQuality.getText().isEmpty() && !txtDiscount.getText().isEmpty()) {

            Item item = new Item(lblItemCode.getText(), txtItemName.getText(), Double.parseDouble(txtQuality.getText()), Double.parseDouble(txtPrice.getText()), Double.parseDouble(txtDiscount.getText()));

            ItemDAO itemDAO = new ItemDAOImpl();
            itemDAO.add(item);

            txtItemName.clear();
            txtQuality.clear();
            txtPrice.clear();
            txtDiscount.clear();
        }else {
            new Alert(Alert.AlertType.WARNING,"Please fill All fields and try again..!").show();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!txtName1.getText().isEmpty() && !txtPrice1.getText().isEmpty() && !txtQTY1.getText().isEmpty() && !txtDiscount1.getText().isEmpty()) {
            Item item = new Item(txtItemCode1.getText(), txtName1.getText(), Double.parseDouble(txtQTY1.getText()), Double.parseDouble(txtPrice1.getText()), Double.parseDouble(txtDiscount1.getText()));

            ItemDAO itemDAO = new ItemDAOImpl();
            boolean b = itemDAO.update(item);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Error").show();
            }
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(cartSelectedRow==-1){
            new Alert(Alert.AlertType.WARNING,"Please select a customer and try again..!").show();
        }else{
            ItemDAO itemDAO = new ItemDAOImpl();
            List<Item> result = itemDAO.getAll();
            Item item = result.get(cartSelectedRow);
            boolean b = itemDAO.delete(item);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Error").show();
            }
        }

    }
}
