package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Article;
import com.mmn.circuitscourts.services.ImageDAO;
import com.mmn.circuitscourts.services.MarketplaceDAO;
import com.mmn.circuitscourts.services.ProducteurDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.w3c.dom.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddArticlesController {
    @FXML
    Label articleName;
    @FXML
    Label articlePrice;
    @FXML
    Label articleDescription;
    @FXML
    Label tagName;
    @FXML
    TextField tfName;
    @FXML
    TextField tfPrice;
    @FXML
    TextField tfDescription;
    @FXML
    TextField tfWeight;
    @FXML
    ComboBox cbTag;
    @FXML
    Button fileChooser;
    @FXML
    VBox imageContainer;

    File file;


    public void onBackButton() {
        ViewFactory.getInstance().showProdArticlesInterface();
    }

    public void initialize() throws SQLException {
        initTags();
        tfDescription.textProperty().addListener((observableValue, s, t1) -> {
            articleDescription.setText(t1);
        });
        tfName.textProperty().addListener(((observableValue, s, t1) -> {
            articleName.setText(t1);
        }));
        tfPrice.textProperty().addListener((observableValue, s, t1) -> {
            articlePrice.setText(t1 + " €");
        });
        cbTag.setOnAction(actionEvent -> {
            tagName.setText((String) cbTag.getValue());
        });

    }

    public void initTags() throws SQLException {
        MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
        ArrayList<String> tags = marketplaceDAO.getAllTags();
        cbTag.getItems().addAll(tags);
    }

    public void onBrowse() throws MalformedURLException {
        FileChooser fC = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Images files (*.png, *.jpg)", "*.png", "*.jpg");
        fC.getExtensionFilters().add(extensionFilter);
        file = fC.showOpenDialog(ViewFactory.getInstance().getMainStage());

        if (file != null) {
            Image image1 = new Image(file.toURI().toURL().toString());
            ImageView imageView = new ImageView(image1);
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            imageContainer.getChildren().clear();
            imageContainer.getChildren().add(imageView);
        }
    }

    public void onCreate() throws SQLException, FileNotFoundException {
        String name = tfName.getText();
        String description = tfDescription.getText();
        double price = Double.parseDouble(tfPrice.getText());
        String categorie = (String) cbTag.getValue();
        double weight = Double.parseDouble(tfWeight.getText());
        ImageDAO imageDAO = new ImageDAO();
        int imageId = imageDAO.add(file);

        ProducteurDAO producteurDAO = new ProducteurDAO();
        Article a = new Article(name, categorie, description, price, weight, imageId, producteurDAO.getByAccountId(App.userConnected.getId()).getNumSiret());



        ViewFactory.getInstance().showProdArticlesInterface();
        ArticlesController.showSuccessPopUp(a.getId());

    }
}
