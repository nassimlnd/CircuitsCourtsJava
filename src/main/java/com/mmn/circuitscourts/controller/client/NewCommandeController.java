package com.mmn.circuitscourts.controller.client;

import com.mmn.circuitscourts.models.Article;
import com.mmn.circuitscourts.services.ImageDAO;
import com.mmn.circuitscourts.services.MarketplaceDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public class NewCommandeController {

    public static int articleId = 0;
    @FXML
    VBox articleContainer;
    @FXML
    TextField tfQuantity;
    @FXML
    Label totalPrice;


    public void initialize() throws SQLException, IOException {
        articleContainer.getChildren().add(createArticle(articleId));
        tfQuantity.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.equals("")) {
                tfQuantity.getStyleClass().clear();
                tfQuantity.getStyleClass().add("text-field");
                try {
                    Double.parseDouble(t1);
                    totalPrice.setText("Prix total : " + calculTotalPrice(articleId, Integer.parseInt(t1)) + " €");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (NumberFormatException e) {
                    tfQuantity.getStyleClass().clear();
                    tfQuantity.getStyleClass().add("text-field");
                    tfQuantity.getStyleClass().add("text-field-error");
                }
            }
        });
    }

    public double calculTotalPrice(int articleId, int quantity) throws SQLException {
        MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
        Article article = marketplaceDAO.getById(articleId);
        return article.getPrice() * quantity;
    }

    public VBox createArticle(int id) throws SQLException, IOException {
        MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
        Article a = null;
        try {
            a = marketplaceDAO.getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        VBox article = new VBox();
        article.setMaxHeight(414);
        article.setPrefHeight(414);
        article.setPrefWidth(240);
        HBox.setMargin(article, new Insets(0, 0, 0, 40));
        article.getStyleClass().add("marketplace-article");

        /* Label représentant l'image dans l'article */
        VBox imageContainer = new VBox();
        imageContainer.setPrefHeight(200);
        imageContainer.setPrefWidth(240);
        imageContainer.setMaxHeight(200);
        imageContainer.setMaxWidth(240);
        imageContainer.setAlignment(Pos.CENTER);
        imageContainer.getStyleClass().add("marketplace-article-img");

        ImageDAO imageDAO = new ImageDAO();
        ImageView imageView = new ImageView(imageDAO.getImage(a.getImageId(), ".png"));
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        imageContainer.getChildren().add(imageView);
        article.getChildren().add(imageContainer);

        /* HBox contenant les tags */
        HBox tags = new HBox();
        createArticleTags(a, tags);
        tags.setPadding(new Insets(10, 5, 5, 10));
        article.getChildren().add(tags);

        /* VBox contenant les détails de l'article */
        VBox articleContent = new VBox();
        articleContent.setPrefWidth(238);
        articleContent.setPrefHeight(167);
        VBox.setMargin(articleContent, new Insets(10, 0, 0, 0));
        articleContent.setPadding(new Insets(0, 15, 0, 15));
        article.getChildren().add(articleContent);

        /* Label titre de l'article */
        Label title = new Label(a.getName());
        title.getStyleClass().add("marketplace-article-title");
        articleContent.getChildren().add(title);

        /* Label description de l'article */
        Label description = new Label(a.getDescription());
        description.getStyleClass().add("marketplace-article-description");
        VBox.setMargin(description, new Insets(20, 0, 0, 0));
        articleContent.getChildren().add(description);

        /* HBox price et commander */
        HBox articleBottom = new HBox();
        articleBottom.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(articleBottom, new Insets(50, 0, 0, 0));
        articleContent.getChildren().add(articleBottom);

        /* Label price */
        Label price = new Label(a.getPrice() + " €");
        price.setPrefHeight(27);
        price.setPrefWidth(123);
        price.getStyleClass().add("marketplace-article-price");
        articleBottom.getChildren().add(price);

        /* Button commander */
        Button commanderButton = new Button("Commander");
        commanderButton.setDisable(true);
        commanderButton.setMnemonicParsing(false);
        commanderButton.getStyleClass().add("marketplace-article-button");
        articleBottom.getChildren().add(commanderButton);

        /* Label id article */

        return article;
    }

    public void createArticleTags(Article article, HBox tags) {
        Label tag = new Label(article.getCategorie());
        tag.setAlignment(Pos.CENTER);
        tag.setMaxHeight(24);
        tag.getStyleClass().add("marketplace-tag");
        tag.setPadding(new Insets(2, 10, 2, 10));

        tags.getChildren().add(tag);
    }

    public void onBackButton() {
        ViewFactory.getInstance().showClientMarketplaceInterface();
    }
}
