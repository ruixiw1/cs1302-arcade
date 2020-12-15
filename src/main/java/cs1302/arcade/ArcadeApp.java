package cs1302.arcade;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;


/**
 * Application subclass for {@code ArcadeApp}.
 *
 * @version 2019.fa
 */
public class ArcadeApp extends Application {
    private int xMax = Controller.XMAX;
    private int yMax = Controller.YMAX;
    private Text scoreText = new Text("Score: ");
    private Button quit = new Button("Quit");
    private Button start = new Button("Pause\\Start");
    private Text level = new Text("Lines: ");
    private Button levelSpeed = new Button("Level  1~3");
    private Button highestScore = new Button("Highest score");
    private Integer size = 1;
    static boolean game;

    private Integer minSize = 0;

    private String url = "src/main/resources/score.txt";
    Pane pane = new Pane(); // the pane

    Pane paneByHighestScore = new Pane();

    /**
     * the start method.
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        game = true;

        Scene gameScene = new Scene(pane, xMax + 300, yMax);// 300 the size of the score scene
        drawing(pane);
        Controller controller = new Controller(gameScene, pane);// create a new controller
        stage.setScene(gameScene);
        stage.setTitle("Tetris");
        BackgroundFill bkg = new BackgroundFill(Paint.valueOf("#73C6B6"),
            new CornerRadii(0), new Insets(0));
        Background bg = new Background(bkg);
        pane.setBackground(bg);
        stage.show();
        listend(controller);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (game && !controller.pause) {
                            leaveBySize(controller, size);
                            // level
                            scoreText.setText("Score: " + controller.score);
                            level.setText("Lines: " + controller.lines);
                        } else if (!game) {
                            controller.stop();
                            // game over
                            Text over = new Text("GAME OVER");
                            over.setFill(Color.RED);
                            over.setStyle("-fx-font: 100 arial;");
                            over.setY(yMax / 2);
                            over.setX(50);
                            pane.getChildren().add(over);
                            gameOverByFile(scoreText.getText());
                        }
                    }
                });
            }
        };
        timer.schedule(task, 0, 400);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() { // set close window = quit
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
    } // start.

    /**
     * to write the text.
     * @param url
     * @param text
     */
    public static void writeText(String url, String text) {
        File file = new File(url); //url create if doen't exist
        try {
            file.createNewFile(); // create a new file

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(text);
            bw.flush(); // flush into the file
            bw.close(); // close the file
        } catch (IOException e) {
            e.printStackTrace();
        }

    } // writeText.

    /**
     * game over by file.
     * @param text
     */
    public void gameOverByFile(String text) {
        if (minSize == 0) {
            try {
                text = text.replace("Score: ", "");
                String s = readText(url);
                String[] split = s.split("\n");
                Integer[] newArr = new Integer[split.length + 1];
                int a = 0;
                for (String s1 : split) {
                    newArr[a] = Integer.parseInt(s1);
                    a++;
                }
                newArr[split.length] = Integer.parseInt(text);
                for (int i = 0; i < newArr.length - 1; i++) {
                    for (int j = 0; j < newArr.length - 1 - i; j++) {
                        if (newArr[j + 1] > newArr[j]) {
                            int temp = newArr[j];
                            newArr[j] = newArr[j + 1];
                            newArr[j + 1] = temp;
                        }
                    }
                }
                StringBuffer buffer = new StringBuffer();
                if (newArr.length > 10) {
                    for (int i = 0; i < newArr.length - 1; i++) {
                        buffer.append(newArr[i] + "\n");
                    }
                } else {
                    for (int i = 0; i < newArr.length; i++) {
                        buffer.append(newArr[i] + "\n");
                    }
                }
                writeText(url,buffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                minSize++;
            }
        }
    } // gameOverByFile

    /**
     * leave by size.
     * @param controller
     * @param size
     */
    public void leaveBySize(Controller controller, int size) {
        for (int i = 0; i < size; i++) {
            controller.down();
        }
    } // leaveBySize.

    /**
     * draw the scene.
     * @param pane
     */
    public void drawing(Pane pane) {
        // separate to the score region
        Line line = new Line(xMax, 0, xMax, yMax);
        line.setStroke(Color.ANTIQUEWHITE);
        // show the score
        scoreText.setStyle("-fx-font: 25 arial;");
        scoreText.setY(100);
        scoreText.setX(xMax + 10);
        scoreText.setFill(Color.RED);
        // show the line
        level.setStyle("-fx-font: 25 arial;");
        level.setY(200);
        level.setX(xMax + 10);
        level.setFill(Color.GREEN);
        // show the next
        Text next = new Text("NEXT: ");
        next.setStyle("-fx-font: 30 arial;");
        next.setY(300);
        next.setX(xMax + 10);
        next.setFill(Color.YELLOW);
        pane.getChildren().addAll(line, scoreText, level, next);
        // set the buttons
        levelSpeed.setPrefSize(150, 50);
        levelSpeed.setLayoutX(xMax + 10);
        levelSpeed.setLayoutY(yMax - 170);

        start.setPrefSize(150, 50);
        start.setLayoutX(xMax + 10);
        start.setLayoutY(yMax - 100);

        highestScore.setPrefSize(100, 50);
        highestScore.setLayoutX(xMax + 180);
        highestScore.setLayoutY(yMax - 170);

        quit.setPrefSize(100, 50);
        quit.setLayoutX(xMax + 180);
        quit.setLayoutY(yMax - 100);
        pane.getChildren().addAll(start, quit, levelSpeed, highestScore);
    } // drawing.

    /**
     * to add listend.
     * @param controller
     */
    public void listend(Controller controller) {
        // set the listend event
        quit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });
        start.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.pause();
            }
        });
        levelSpeed.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (size == 1) {
                    size = 2;
                } else if (size == 2) {
                    size = 5;
                } else if (size == 5) {
                    size = 1;
                }
            }
        });
        highestScore.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    alert();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    } // listend.

    /**
     * to alert.
     */
    public void alert() throws Exception {
        Stage newStage = new Stage();
        newStage.setTitle("Highest score");
        Label l = new Label(readText(url));
        Scene s = new Scene(l, 300, 175);
        newStage.setResizable(false);
        newStage.setScene(s);
        newStage.show();
    } // alert.

    /**
     * to read the text.
     * @param url text
     * @return the result string
     */
    public String readText(String url) throws Exception {
        File file = new File(url);
        byte[] bytes = new byte[1024];
        StringBuffer sb = new StringBuffer();
        FileInputStream in = new FileInputStream(file);
        int len;
        while ((len = in.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, len));
        }
        return sb.toString();
    } // readText.
} // ArcadeApp
