import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JIMachine extends Application {
    private ImageView imageView;
    @Override

    public void start(Stage primaryStage) throws IOException {
    //set title and initialize buttons
        primaryStage.setTitle("Java Image Machine");
        Button buttonOpen = new Button("Open");
        //zoom out button & action
        Button zoutbtn = new Button("Zoom Out");
        zoutbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                double x = imageView.getFitHeight();
                double y = imageView.getFitWidth();
                //Calculate percentage difference and subtract
                double f = x * .25;
                double w = y * .25;
                double q = x - f;
                double r = y - w;
                //Update image
                imageView.setFitHeight(q);
                imageView.setFitWidth(r);
            }
        });
        //100% button and action handler
        Button fullbtn = new Button("100%");
        fullbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                imageView.setPreserveRatio(true);
            }
        });
        //Zoom In button and action handler
        Button zinbtn = new Button("Zoom In");
        zinbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double x = imageView.getFitHeight();
                double y = imageView.getFitWidth();
                //Calculate percentage difference and add
                double f = x * .25;
                double w = y * .25;
                double q = x + f;
                double r = y + w;
                //Update image
                imageView.setFitHeight(q);
                imageView.setFitWidth(r);
            }
        });
        //Exit button and action handler
        Button exitbtn = new Button("Exit");
        exitbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        // attach listener to Open button
        buttonOpen.setOnAction(buttonOpenEventListener);
        // create an object of imageVIew
        imageView = new ImageView();
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        //creat HBOX for BannerPane menu
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(25, 12, 15, 25));
        hbox.getChildren().addAll(buttonOpen, zoutbtn, fullbtn, zinbtn, exitbtn);
        //Create BorderPane to house image and menu
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(imageView);
        borderPane.setBottom(hbox);

        //Create window scene
        Scene scene = new Scene(borderPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // event handler for OPEN button
    private EventHandler<ActionEvent> buttonOpenEventListener
            = new EventHandler<>() {
        @Override
        public void handle(ActionEvent t) {
            // create fileChooser
            FileChooser fileChooser = new FileChooser();
            //jpg and png filter
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG"), new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG"));
            //Opens required dialog box for selecting file
            File file = fileChooser.showOpenDialog(null);
            // update image into imageView
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
        }
    };
    //final launch
    public static void main(String[] args) {
        launch();
    }
}