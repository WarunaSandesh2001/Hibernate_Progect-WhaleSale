package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashBoardController {
    public AnchorPane dashBoardContext;

    public void openCustomerForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(load);
    }

    public void openItemForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(load);
    }

    public void openPlaceOrderForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PlaceOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(load);
    }
}
