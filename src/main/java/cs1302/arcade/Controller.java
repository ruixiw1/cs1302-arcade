package cs1302.arcade;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * Controller class.
 */
class Controller {
    static final int SIZE = 35;
    static int XMAX = SIZE * 12;
    static int YMAX = SIZE * 24;
    // set the scene
    static int[][] MESH = new int[XMAX / SIZE][YMAX / SIZE];
    private Scene scene;
    private Block current;
    private Block next;
    private Pane pane;
    private Block paint;
    private boolean game = true;
    private int top = 0;
    int score = 0;
    int lines = 0;
    boolean pause = true;

    /**
     * constructor.
     * @param scene
     * @param pane
     */
    Controller(Scene scene, Pane pane) {
        this.current = new Block(pane);
        current.setVisual(true);
        this.next = new Block(pane);
        this.pane = pane;
        this.scene = scene;
        paint();
        setPress();
    } // Controller.

    /**
     * set the press.
     */
    private void setPress() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (game && !pause) {
                    switch (event.getCode()) {
                    case D:
                        // check move
                        if (current.canMove("a", 1, 0) && current.canMove("b", 1, 0) &&
                            current.canMove("c", 1, 0) && current.canMove("d", 1, 0)) {
                            current.moveRight();
                        }
                        break;
                    case A:
                        if (current.canMove("a", -1, 0) && current.canMove("b", -1, 0) &&
                            current.canMove("c", -1, 0) && current.canMove("d", -1, 0)) {
                            current.moveLeft();
                        }
                        break;
                    case S:
                        down();
                        break;
                    case W:
                        current.turn();
                        break;
                    }
                }
            }
        });
    } // setPress.

    /**
     * move down.
     */
    void down() {
        if (current.canMove("a", 0, 1) && current.canMove("b", 0, 1) &&
                current.canMove("c", 0, 1) && current.canMove("d", 0, 1)) {
            current.moveDown();
            score++;
        }
        if (shouldChang()) { // check whether can move, or change to the next
            setMESH();
            current = next;
            current.setVisual(true);
            exchange(current);
            next = new Block(pane);
            paint.remove();  // show the shape of the next one
            paint();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    removeRows();
                }
            });
        }
        ArcadeApp.game = isGameOver();
    } // down.

    /**
     * exchange to the next object.
     * @param current the current object
     */
    private void exchange(Block current) {
        this.current = current;
    } // exchange.

    /**
     * check whether change to the next one.
     * @return true if it shoule change
     */
    private boolean shouldChang() {
        return judge(current.a) || judge(current.b) || judge(current.c) || judge(current.d);
    } // shouldChang.

    /**
     * judge method.
     * @param r
     * @return true if judge
     */
    private boolean judge(Rectangle r) {
        return (int) r.getY() / SIZE == 23 || MESH[(int) r.getX()
            / SIZE][(int) r.getY() / SIZE + 1] == 1;
    } // judge.

    /**
     * to set MESH.
     */
    private void setMESH() {
        setData(current.a);
        setData(current.b);
        setData(current.c);
        setData(current.d);
    } // setMESH.

    /**
     * set data.
     * @param r
     */
    private void setData(Rectangle r) {
        MESH[(int) r.getX() / SIZE][(int) r.getY() / SIZE] = 1;
    } // setData.

    /**
     * paint method.
     */
    private void paint() {
        paint = new Block(pane, next.getName());
    } // paint.

    /**
     * check whether the game is over.
     * @return true if game is over
     */
    private boolean isGameOver() {
        if (current.onTheTop()) {
            top++;
        } else {
            top = 0;
        }
        return !(top >= 2);
    } // isGameOver.

    /**
     * stop the game.
     */
    void stop() {
        game = false;
        pause = true;
    } // stop.

    /**
     * pause the game.
     */
    void pause() {
        pause = !pause;
    } // pause.

    /**
     * to remove the rows.
     */
    private void removeRows() {
        ArrayList<Node> rects = new ArrayList<>();
        ArrayList<Integer> lineList = new ArrayList<>();
        ArrayList<Integer> lineMoveList = new ArrayList<>();
        ArrayList<Node> newRects = new ArrayList<>();
        int full = 0;
        for (int i = 0; i < MESH[0].length; i++) {
            for (int j = 0; j < MESH.length; j++) {
                if (MESH[j][i] == 1) {
                    full++;
                }
            }
            if (full == MESH.length) {
                lineList.add(i);
            }
            full = 0;
        }
        for (int i = 0; i < lineList.size(); i++) {
            lineMoveList.add(lineList.get(lineList.size() - i - 1));
        }
        if (lineList.size() > 0) {
            for (Node node : pane.getChildren()) {
                if (node instanceof Rectangle) {
                    rects.add(node);
                }
            }
            score += 50 * lineList.size();
            lines += lineList.size();
            removeAll(lineList,rects); // remove all lines
            rects.clear();
            for (Node node : pane.getChildren()) {
                if (node instanceof Rectangle) {
                    newRects.add(node);
                }
            } // get the rest rects
            if (lineMoveList.size() > 0) {
                while (lineMoveList.size() > 0) { // move down
                    for (Node node : newRects) {
                        Rectangle rectangle = (Rectangle) node;
                        if (rectangle.getY() < lineMoveList.get(0) * SIZE
                            && rectangle.getX() < XMAX) {
                            rectangle.setY(rectangle.getY() + SIZE * lineMoveList.size());
                        }
                    }
                    lineMoveList.remove(0);
                }
                setMESHInRR(rects);
                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getX() < XMAX && a != next.a && a != next.b && a != next.c && a != next.d
                        && a != current.a && a != current.b && a != current.c && a != current.d) {
                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 1;
                    }
                }
            }
        }
    } // removeRows.

    /**
     * set MESH in removeRows method.
     * @param rects
     */
    void setMESHInRR(ArrayList<Node> rects) {
        MESH = new int[XMAX / SIZE][YMAX / SIZE];
        for (Node node : pane.getChildren()) {
            if (node instanceof Rectangle) {
                rects.add(node);
            }
        }
    } // setMESHInRR.

    /**
     * remove all lines.
     * @param lineList
     * @param rects
     */
    public void removeAll (ArrayList<Integer> lineList, ArrayList<Node> rects) {
        while (lineList.size() > 0) {
            for (Node node : rects) {
                Rectangle rect = (Rectangle) node;
                if (rect.getY() == lineList.get(0) * SIZE && rect.getX() < XMAX) {
                    MESH[(int) rect.getX() / SIZE][(int) rect.getY() / SIZE] = 0;
                    pane.getChildren().remove(node);
                }
            }
            lineList.remove(0);
        }
    } // removeAll.
} // Controller.
