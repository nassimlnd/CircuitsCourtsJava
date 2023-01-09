package com.mmn.circuitscourts.controller.client;

import com.mmn.circuitscourts.models.Article;
import com.mmn.circuitscourts.services.ImageDAO;
import com.mmn.circuitscourts.services.MarketplaceDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarketplaceController {

    @FXML
    HBox tagsContainer;
    @FXML
    VBox loadingContainer, successPopup, lineContainer;
    @FXML
    Button previousPage, nextPage;
    @FXML
    Label actualPage;
    static VBox popup;

    int pageNumber;


    public void initialize() throws SQLException {
        popup = successPopup;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    showAllTags();
                    showArticlesPerPage(0);
                    initButtons();
                    actualPage.setText(String.valueOf(pageNumber + 1));
                    loadingContainer.setVisible(false);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public void initButtons() {
        previousPage.setOnMouseClicked(mouseEvent -> {
            if (pageNumber > 0) {
                pageNumber--;
                try {
                    showArticlesPerPage(pageNumber);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                actualPage.setText(String.valueOf(pageNumber + 1));
            }
        });

        nextPage.setOnMouseClicked(mouseEvent -> {
            int totalPages = 0;
            try {
                totalPages = Article.article.getAll().size() / 25;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (pageNumber < totalPages) {
                pageNumber++;
                try {
                    showArticlesPerPage(pageNumber);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                actualPage.setText(String.valueOf(pageNumber + 1));
            }
        });
    }

    private void showArticlesPerPage(int pageNumber) throws SQLException {
        lineContainer.getChildren().clear();
        ArrayList<Article> articles = Article.article.getAllByPage(pageNumber);;
        ArrayList<HBox> lines = new ArrayList<>();

        int numberLines = 0;
        if (articles.size() < 3) {
            numberLines = 1;
        } else if (articles.size() % 3 == 0) {
            numberLines = articles.size() / 3;
        } else {
            numberLines = (articles.size() / 3) + 1;
        }

        for (int i = 0; i < numberLines; i++) {
            lines.add(createLine());
        }

        articles.forEach(article -> {
            int index = articles.indexOf(article);
            int indexLine = (index / 3);
            try {
                lines.get(indexLine).getChildren().add(createArticle(article));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        lines.forEach(hBox -> {
            lineContainer.getChildren().add(hBox);
        });
    }

    /**
     * Méthode créant un article dans la vue du Marketplace
     *
     * @return
     */
    public VBox createArticle(Article a) throws IOException, SQLException {
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
        ImageView imageView = new ImageView(imageDAO.getImage(a.getImageId(), imageDAO.getExtById(a.getImageId())));
        imageView.setFitWidth(230);
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
        commanderButton.setOnMouseClicked(mouseEvent -> {
            ViewFactory.getInstance().showClientNewCommandeInterface(a.getId());
        });
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

    public HBox createLine() {
        HBox articleLine = new HBox();
        articleLine.setAlignment(Pos.CENTER_LEFT);
        articleLine.setMinHeight(420);
        articleLine.setPrefHeight(420);
        articleLine.setPrefWidth(934);
        articleLine.setPadding(new Insets(15, 40, 0, 10));
        VBox.setMargin(articleLine, new Insets(20, 0, 0, 0));
        return articleLine;
    }

    public Button createTag(String tagName) {
        Button tag = new Button(tagName);
        tag.setMinHeight(24);
        tag.setPadding(new Insets(0, 5, 0, 5));
        tag.getStyleClass().add("marketplace-tag");
        HBox.setMargin(tag, new Insets(0, 5, 0, 0));
        tag.setOnMouseClicked(mouseEvent -> {
            loadingContainer.setVisible(true);
            Platform.runLater(() -> {
                try {
                    tagsContainer.getChildren().forEach(node -> {
                        if (node instanceof Button) {
                            node.getStyleClass().clear();
                            node.getStyleClass().add("marketplace-tag");
                        }
                    });
                    tag.getStyleClass().add("marketplace-tag-active");
                    showArticleByTag(tagName);
                    loadingContainer.setVisible(false);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        });
        return tag;
    }

    public void showAllArticles() throws SQLException {
        lineContainer.getChildren().clear();
        MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
        ArrayList<Article> articles = marketplaceDAO.getAll();
        ArrayList<HBox> lines = new ArrayList<>();

        int numberLines = 0;
        if (articles.size() < 3) {
            numberLines = 1;
        } else if (articles.size() % 3 == 0) {
            numberLines = articles.size() / 3;
        } else {
            numberLines = (articles.size() / 3) + 1;
        }

        for (int i = 0; i < numberLines; i++) {
            lines.add(createLine());
        }

        articles.forEach(article -> {
            int index = articles.indexOf(article);
            int indexLine = (index / 3);
            try {
                lines.get(indexLine).getChildren().add(createArticle(article));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        lines.forEach(hBox -> {
            lineContainer.getChildren().add(hBox);
        });

    }

    public void showAllTags() throws SQLException {
        MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
        ArrayList<String> tagsName = marketplaceDAO.getAllTags();
        ArrayList<Button> tags = new ArrayList<>();

        Button tagAll = new Button("Tout");
        tagAll.setMinHeight(24);
        tagAll.setPadding(new Insets(0, 5, 0, 5));
        tagAll.getStyleClass().add("marketplace-tag");
        tagAll.getStyleClass().add("marketplace-tag-active");
        HBox.setMargin(tagAll, new Insets(0, 5, 0, 0));
        tagAll.setOnMouseClicked(mouseEvent -> {
            loadingContainer.setVisible(true);
            Platform.runLater(() -> {
                try {
                    tagsContainer.getChildren().forEach(node -> {
                        if (node instanceof Button) {
                            node.getStyleClass().clear();
                            node.getStyleClass().add("marketplace-tag");
                        }
                    });
                    tagAll.getStyleClass().add("marketplace-tag-active");
                    lineContainer.getChildren().clear();
                    showArticlesPerPage(0);
                    loadingContainer.setVisible(false);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        });

        tags.add(tagAll);

        tagsName.forEach(tag -> {
            tags.add(createTag(tag));
        });

        tags.forEach(tag -> {
            tagsContainer.getChildren().add(tag);
        });
    }

    public void showArticleByTag(String tagName) throws SQLException {
        loadingContainer.setVisible(true);
        MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
        ArrayList<Article> articles = marketplaceDAO.getByTag(tagName);
        ArrayList<HBox> lines = new ArrayList<>();

        lineContainer.getChildren().clear();

        int numberLines = 0;
        if (articles.size() < 3) {
            numberLines = 1;
        } else if (articles.size() % 3 == 0) {
            numberLines = articles.size() / 3;
        } else {
            numberLines = (articles.size() / 3) + 1;
        }

        for (int i = 0; i < numberLines; i++) {
            lines.add(createLine());
        }

        articles.forEach(article -> {
            int index = articles.indexOf(article);
            int indexLine = (index / 3);
            try {
                lines.get(indexLine).getChildren().add(createArticle(article));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        lines.forEach(hBox -> {
            lineContainer.getChildren().add(hBox);
        });
        loadingContainer.setVisible(false);
    }

    public static void showFailPopup(String message) {
    }

    public static void showSuccessPopup() {
        popup.setVisible(true);
    }

    public void onClosePopup() {
        popup.setVisible(false);
    }
}
