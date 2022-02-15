package view;

import javafx.application.Application;

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
