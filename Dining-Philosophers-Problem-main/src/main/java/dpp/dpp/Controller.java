package dpp.dpp;


//import app.Utilities.Timer;
/*import app.Utilities.Utils;*/
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static boolean RUNNING;


    @FXML
    private TextArea logTextArea;

    //Philosophers
    @FXML
    private ImageView aristotle;
    private Image[] Phil1Img = new Image[3];
    @FXML
    private ImageView descartes;
    private Image[] Phil2Img = new Image[3];
    @FXML
    private ImageView russell;
    private Image[] Phil3Img = new Image[3];
    @FXML
    private ImageView marx;
    private Image[] Phil4Img = new Image[3];
    @FXML
    private ImageView kant;
    private Image[] Phil5Img = new Image[3];

    //Chopsticks
    @FXML
    private ImageView chopstick1;
    @FXML
    private ImageView chopstick2;
    @FXML
    private ImageView chopstick3;
    @FXML
    private ImageView chopstick4;
    @FXML
    private ImageView chopstick5;

    //Generate our philosophers and chopsticks
    private Philosopher[] philosophers = new Philosopher[5];
    private Chopstick[] chopsticks = new Chopstick[5];

    //List with average execution times
    // private ObservableList<Timer> timers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Phil1Img[0] = new Image(getClass().getResourceAsStream("img/Aristotle-Thinking.png"));
        Phil1Img[1] = new Image(getClass().getResourceAsStream("img/Aristotle-Hungry.png"));
        Phil1Img[2] = new Image(getClass().getResourceAsStream("img/Aristotle-Eating.png"));

        Phil2Img[0] = new Image(getClass().getResourceAsStream("img/Descartes-Thinking.png"));
        Phil2Img[1] = new Image(getClass().getResourceAsStream("img/Descartes-Hungry.png"));
        Phil2Img[2] = new Image(getClass().getResourceAsStream("img/Descartes-Eating.png"));

        Phil3Img[0] = new Image(getClass().getResourceAsStream("img/Russell-Thinking.png"));
        Phil3Img[1] = new Image(getClass().getResourceAsStream("img/Russell-Hungry.png"));
        Phil3Img[2] = new Image(getClass().getResourceAsStream("img/Russell-Eating.png"));

        Phil4Img[0] = new Image(getClass().getResourceAsStream("img/Marx-Thinking.png"));
        Phil4Img[1] = new Image(getClass().getResourceAsStream("img/Marx-Hungry.png"));
        Phil4Img[2] = new Image(getClass().getResourceAsStream("img/Marx-Eating.png"));

        Phil5Img[0] = new Image(getClass().getResourceAsStream("img/Kant-Thinking.png"));
        Phil5Img[1] = new Image(getClass().getResourceAsStream("img/Kant-Hungry.png"));
        Phil5Img[2] = new Image(getClass().getResourceAsStream("img/Kant-Eating.png"));

        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new Chopstick(i, logTextArea);
        }
        assignChopstickToItsView();




        //Create philosophers
        philosophers[0] = new Philosopher(chopsticks[0], chopsticks[1], 0, "Phil1", aristotle, Phil1Img[0], Phil1Img[1], Phil1Img[2], logTextArea);
        philosophers[1] = new Philosopher(chopsticks[1], chopsticks[2], 1, "Phil2", descartes, Phil2Img[0], Phil2Img[1], Phil2Img[2], logTextArea);
        philosophers[2] = new Philosopher(chopsticks[2], chopsticks[3], 2, "Phil3", russell, Phil3Img[0], Phil3Img[1], Phil3Img[2], logTextArea);
        philosophers[3] = new Philosopher(chopsticks[3], chopsticks[4], 3, "Phil4", marx, Phil4Img[0], Phil4Img[1], Phil4Img[2], logTextArea);
        philosophers[4] = new Philosopher(chopsticks[4], chopsticks[0], 4, "Phil5", kant, Phil5Img[0], Phil5Img[1], Phil5Img[2], logTextArea);
    }



    public void startAction(ActionEvent actionEvent) {
        logTextArea.appendText("Go! \n");
        RUNNING = true;


        //overrideDefaultValuesWhereNecessary();

        for (Philosopher p : philosophers) {
            new Thread(p).start();
        }
    }

    public void stopAction(ActionEvent actionEvent) {
        logTextArea.appendText("Waiting to stop... \n");
        RUNNING = false;
    }

    private void assignChopstickToItsView() {
        chopsticks[0].setChopstickView(chopstick5);
        chopsticks[1].setChopstickView(chopstick1);
        chopsticks[2].setChopstickView(chopstick2);
        chopsticks[3].setChopstickView(chopstick3);
        chopsticks[4].setChopstickView(chopstick4);
    }



}
