package Controller;

import com.jfoenix.controls.JFXTextField;
import dao.custom.ItemDAO;
import dao.custom.impl.ItemDAOImpl;
import entity.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderFormController {
    public AnchorPane placeOrderContext;
    public TextField txtContact;
    public JFXTextField txtSearchCustomer;
    public TextField txtNIC;
    public TextField txtName;
    public ComboBox cmbItemList;

    public void backToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        placeOrderContext.getChildren().clear();
        placeOrderContext.getChildren().add(load);
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        ItemDAO itemDAO = new ItemDAOImpl();
        //List<String> itemList = itemDAO.getAllItemNames();
        //System.out.println(itemList.toString());
        //cmbItemList.getItems().setAll(itemList);
    }

    public void searchCustomerOnAction(ActionEvent actionEvent) {

    }
}
