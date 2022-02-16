package view;


import controller.Controller;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class GUI extends Application
{

    public static void main(String[] args)
    {
        assert args != null;
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        /* Variables*/
        final int WINDOW_WIDTH = 475, WINDOW_HEIGHT = 550;
        final AnchorPane root = createPane(stage);
        final Scene scene = new Scene(root);

        /*style sheet*/
        scene.getStylesheets().add(new File("src/main/resources/css/style.css").toURI().toString());

        /*Window setup*/
        stage.getIcons().add(new Image(new File("src/main/resources/images/icon.png").toURI().toString()));
        stage.setResizable(false);
        stage.setTitle("Fishing Friend");
        stage.setWidth(WINDOW_WIDTH);
        stage.setHeight(WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /*Returns AnchorPane for GUI*/
    public static AnchorPane createPane(Stage stage)
    {
        /*GUI elements*/
        final AnchorPane pane = new AnchorPane();
        final Button start = new Button("Start");
        final Button stop = new Button("Stop");
        final Button close = new Button("Close");

        /*ImageView settings/styling. */
        Image image = new Image(new File("src/main/resources/images/spinningWheel.gif").toURI().toString());
        ImageView imageView = new ImageView();
        imageView.setId("wheelImageView");
        imageView.setImage(image);
        imageView.setFitHeight(21.0);
        imageView.setFitWidth(21.0);
        imageView.setLayoutX(15);
        imageView.setLayoutY(475);
        imageView.setVisible(false);

        /*Button(Start) settings/styling. */
        start.setCursor(Cursor.HAND);
        start.setMinHeight(40);
        start.setMinWidth(110);
        start.setMaxHeight(40);
        start.setMaxWidth(110);
        start.setLayoutX(70);
        start.setLayoutY(250);

        /*Button(Stop) settings/styling. */
        stop.setCursor(Cursor.HAND);
        stop.setMinHeight(40);
        stop.setMinWidth(110);
        stop.setMaxHeight(40);
        stop.setMaxWidth(110);
        stop.setLayoutX(70);
        stop.setLayoutY(250);
        stop.setVisible(false);

        /*Button(Close) settings/styling. */
        close.setCursor(Cursor.HAND);
        close.setMinHeight(40);
        close.setMinWidth(110);
        close.setMaxHeight(40);
        close.setMaxWidth(110);
        close.setLayoutX(280);
        close.setLayoutY(250);

        /*Lambda actions on button clicks*/
        start.setOnAction(e ->
        {
            Controller.INSTANCE.start();
            imageView.setVisible(true);
            stop.setVisible(true);
            start.setVisible(false);
        });
        stop.setOnAction(e ->
        {
            Controller.INSTANCE.stop();
            imageView.setVisible(false);
            start.setVisible(true);
            stop.setVisible(false);
        });
        close.setOnAction(e -> stage.close());

        /*Adding all elements to AnchorPane/GUI */
        pane.styleProperty().set("-fx-background-color: #181818");
        pane.getChildren().add(start);
        pane.getChildren().add(stop);
        pane.getChildren().add(close);
        pane.getChildren().add(imageView);

        return pane;
    }
}
