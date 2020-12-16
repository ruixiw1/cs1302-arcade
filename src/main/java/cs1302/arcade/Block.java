package cs1302.arcade;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Block class.
 */
class Block {
    private String name;
    private Color color;

    /**
     * to get the name.
     * @return name
     */
    public String getName() {
        return name;
    } // getName.

    /**
     * to get the color.
     * @return color
     */
    public Color getColor() {
        return color;
    } // getColor.

    private Pane pane;
    private int form;
    private int xMax = Controller.XMAX;
    private int yMax = Controller.YMAX;
    private int size = Controller.SIZE;
    Rectangle a = new Rectangle(size - 1, size - 1);
    Rectangle b = new Rectangle(size - 1, size - 1);
    Rectangle c = new Rectangle(size - 1, size - 1);
    Rectangle d = new Rectangle(size - 1, size - 1);

    /**
     * constructor.
     * @param pane
     * @param name
     */
    Block(Pane pane, String name) {
        make(pane, name);
        a.setX(a.getX() + 350);
        b.setX(b.getX() + 350);
        c.setX(c.getX() + 350);
        d.setX(d.getX() + 350);
        a.setY(a.getY() + 400);
        b.setY(b.getY() + 400);
        c.setY(c.getY() + 400);
        d.setY(d.getY() + 400);
        setVisual(true);
    } // Block.

    /**
     * constructor.
     * @param pane
     */
    Block(Pane pane) {
        form = 1;
        double rand = Math.random() * 700;
        if (rand < 100) {
            make(pane, "s");
        } else if (rand < 200) {
            make(pane, "z");
        } else if (rand < 300) {
            make(pane, "l");
        } else if (rand < 400) {
            make(pane, "j");
        } else if (rand < 500) {
            make(pane, "t");
        } else if (rand < 600) {
            make(pane, "i");
        } else {
            make(pane, "o");
        }
    } // Block.

    /**
     * make class.
     * @param pane
     * @param name
     */
    private void make(Pane pane, String name) {
        this.name = name;
        setColor();
        this.pane = pane;
        initFrom();
    } // make.

    /**
     * set the color.
     */
    private void setColor() {
        switch (name) {
        case "s":
            color = Color.GHOSTWHITE;
            break;
        case "z":
            color = Color.HOTPINK;
            break;
        case "l":
            color = Color.SPRINGGREEN;
            break;
        case "j":
            color = Color.ORANGERED;
            break;
        case "t":
            color = Color.ALICEBLUE;
            break;
        case "i":
            color = Color.DODGERBLUE;
            break;
        case "o":
            color = Color.ORANGE;
            break;
        }
        a.setFill(color);
        b.setFill(color);
        c.setFill(color);
        d.setFill(color);
    } // setColor.

    /**
     * initiate the graph.
     */
    private void initFrom() {
        form = 0;
        initFromS(name);
        initFromZ(name);
        initFromL(name);
        initFromJ(name);
        initFromT(name);
        initFromI(name);
        initFromO(name);
    } // initFrom.

    /**
     * initiate the graph (initFrom plus).
     * @param name
     */
    void initFromS(String name) {
        if ("s".equals(name)) {
            b.setX(xMax / 2 - size);
            b.setY(0);
            a.setX(xMax / 2);
            a.setY(0);
            c.setX(b.getX());
            c.setY(b.getY() + size);
            d.setY(c.getY());
            d.setX(c.getX() - size);
        }
    } // initFromS.

    /**
     * initiate the graph (initFrom plus).
     * @param name
     */
    void initFromZ(String name) {
        if ("z".equals(name)) {
            c.setX(xMax / 2 - size);
            c.setY(0);
            d.setX(c.getX() - size);
            d.setY(c.getY());
            b.setY(c.getY() + size);
            b.setX(c.getX());
            a.setX(c.getX() + size);
            a.setY(b.getY());
        }
    } // initFromZ.

    /**
     * initiate the graph (initFrom plus).
     * @param name
     */
    void initFromL(String name) {
        if ("l".equals(name)) {
            b.setY(size);
            b.setX(xMax / 2 - size);
            a.setY(b.getY());
            a.setX(b.getX() - size);
            c.setY(b.getY());
            c.setX(b.getX() + size);
            d.setY(b.getY() - size);
            d.setX(c.getX());
        }
    } // initFromL.

    /**
     * initiate the graph (initFrom plus).
     * @param name
     */
    void initFromJ(String name) {
        if ("j".equals(name)) {
            b.setX(xMax / 2 - size);
            b.setY(0);
            a.setY(b.getY());
            a.setX(b.getX() - size);
            c.setY(b.getY());
            c.setX(b.getX() + size);
            d.setX(c.getX());
            d.setY(c.getY() + size);
        }
    } // initFromJ.

    /**
     * initiate the graph (initFrom plus).
     * @param name
     */
    void initFromT(String name) {
        if ("t".equals(name)) {
            b.setX(xMax / 2 - size);
            b.setY(size);
            a.setY(b.getY());
            a.setX(b.getX() + size);
            c.setX(b.getX() - size);
            c.setY(b.getY());
            d.setX(b.getX());
            d.setY(0);
        }
    } // initFromT

    /**
     * initiate the graph (initFrom plus).
     * @param name
     */
    void initFromI(String name) {
        if ("i".equals(name)) {
            a.setX(xMax / 2 - 2 * size);
            a.setY(0);
            b.setX(xMax / 2 - size);
            b.setY(0);
            c.setX(xMax / 2);
            c.setY(0);
            d.setX(xMax / 2 + size);
            d.setY(0);
        }
    } // initFromI.

    /**
     * initiate the graph (initFrom plus).
     * @param name
     */
    void initFromO(String name) {
        if ("o".equals(name)) {
            a.setX(xMax / 2 - size);
            a.setY(0);
            b.setX(a.getX());
            b.setY(a.getY() + size);
            c.setX(b.getX() - size);
            c.setY(b.getY());
            d.setX(c.getX());
            d.setY(a.getY());
        }
    } // initFromO.

    /**
     * to set the visual.
     * @param bool
     */
    void setVisual(boolean bool) {
        if (bool) {
            pane.getChildren().addAll(a, b, c, d);
        }
    } // setVisual.

    /**
     * move to right.
     */
    void moveRight() {
        a.setX(a.getX() + size);
        b.setX(b.getX() + size);
        c.setX(c.getX() + size);
        d.setX(d.getX() + size);
    } // moveRight.

    /**
     * move to left.
     */
    void moveLeft() {
        a.setX(a.getX() - size);
        b.setX(b.getX() - size);
        c.setX(c.getX() - size);
        d.setX(d.getX() - size);
    } // moveLeft.

    /**
     * move down.
     */
    void moveDown() {
        a.setY(a.getY() + size);
        b.setY(b.getY() + size);
        c.setY(c.getY() + size);
        d.setY(d.getY() + size);
    } // moveDown.

    /**
     * check whether can move.
     * @param name
     * @param x move to right
     * @param y move down
     * @return true if it can move
     */
    boolean canMove(String name, int x, int y) {
        Rectangle rect;
        switch (name) {
        case "a":
            rect = a;
            break;
        case "b":
            rect = b;
            break;
        case "c":
            rect = c;
            break;
        default:
            rect = d;
            break;
        }
        boolean xb = false;
        boolean yb = false;
        if (x >= 0) {
            xb = rect.getX() + x * size <= xMax - size;
        }
        if (x < 0) {
            xb = rect.getX() + x * size >= 0;
        }
        if (y >= 0) {
            yb = rect.getY() + y * size <= yMax - size;
        }
        if (y < 0) {
            yb = rect.getY() + y * size >= 0;
        }
        // check the next
        return xb && yb && Controller.MESH[(int) rect.getX()
            / size + x][(int) rect.getY() / size + y] == 0;
    } // canMove.

    /**
     * turn the graph.
     */
    void turn() {
        form = (form + 1) % 4;
        s();
        z();
        l();
        j();
        t();
        i();
        if (name.equals("o")) {
            System.out.println("Can not turn the graph!");
        }
    } // turn.

    /**
     * remove all.
     */
    void remove() {
        pane.getChildren().removeAll(a, b, c, d);
    } // remove.

    /**
     * check whether on the top.
     * @return true if is on the top
     */
    boolean onTheTop() {
        return a.getY() <= 0 || b.getY() <= 0 || c.getY() <= 0 || d.getY() <= 0;
    } // onTheTop.

    /**
     * name eaual to s.
     */
    void s() {
        if (name.equals("s")) {
            if (form == 1 || form == 3) {
                if (canMove("b", 0, -1) && canMove("b", 1, 0) && canMove("b", 1, 1)) {
                    a.setY(b.getY() - size);
                    a.setX(b.getX());
                    c.setX(b.getX() + size);
                    c.setY(b.getY());
                    d.setX(b.getX() + size);
                    d.setY(b.getY() + size);
                } else {
                    form = (form - 1) % 4;
                }
            }
            if (form == 0 || form == 2) {
                if (canMove("b", 1, 0) && canMove("b", 0, 1) && canMove("b", -1, 1)) {
                    a.setY(b.getY());
                    a.setX(b.getX() + size);
                    c.setX(b.getX());
                    c.setY(b.getY() + size);
                    d.setX(b.getX() - size);
                    d.setY(b.getY() + size);
                } else {
                    form = (form - 1) % 4;
                }
            }
        }
    } // s.

    /**
     * name equal to z.
     */
    void z() {
        if (name.equals("z")) {
            if (form == 1 || form == 3) {
                if (canMove("b", 0, -1) && canMove("b", -1, 0) && canMove("b", -1, 1)) {
                    a.setY(b.getY() - size);
                    a.setX(b.getX());
                    c.setX(b.getX() - size);
                    c.setY(b.getY());
                    d.setX(b.getX() - size);
                    d.setY(b.getY() + size);
                } else {
                    form = (form - 1) % 4;
                }
            }
            if (form == 0 || form == 2) {
                if (canMove("b", 1, 0) && canMove("b", 0, -1) && canMove("b", -1, -1)) {
                    a.setX(b.getX() + size);
                    a.setY(b.getY());
                    c.setX(b.getX());
                    c.setY(b.getY() - size);
                    d.setX(b.getX() - size);
                    d.setY(b.getY() - size);
                } else {
                    form = (form - 1) % 4;
                }
            }
        }
    } // z.

    /**
     * form equal to one.
     */
    void formeqOne() {
        if (form == 1) {
            if (canMove("b", 0, -1) && canMove("b", -1, -2) && canMove("b", -1, 0)
                && canMove("b", 0, 0) && canMove("b", -1, -1)) {
                b.setY(b.getY() - size);
                a.setX(b.getX() - size);
                a.setY(b.getY() - size);
                c.setX(b.getX() - size);
                c.setY(b.getY() + size);
                d.setX(b.getX());
                d.setY(b.getY() + size);
                b.setX(b.getX() - size);
            } else {
                form = (form - 1) % 4;
            }
        }
    } // formeqOne.

    /**
     * name equal to l.
     */
    void l() {
        if (name.equals("l")) {
            formeqOne();
            if (form == 2) {
                if (canMove("b", 1, 0) && canMove("b", 2, -1) && canMove("b", 0, -1)
                    && canMove("b", 0, 0) && canMove("b", 1, -1)) {
                    b.setX(b.getX() + size);
                    a.setX(b.getX() + size);
                    a.setY(b.getY() - size);
                    c.setX(b.getX() - size);
                    c.setY(b.getY() - size);
                    d.setY(b.getY());
                    d.setX(b.getX() - size);
                    b.setY(b.getY() - size);
                } else {
                    form = (form - 1) % 4;
                }
            }
            if (form == 3) {
                if (canMove("b", 0, 1) && canMove("b", 1, 2) && canMove("b", 1, 1)
                    && canMove("b", 0, 0) && canMove("b", 1, 1)) {
                    b.setY(b.getY() + size);
                    a.setX(b.getX() + size);
                    a.setY(b.getY() + size);
                    c.setX(b.getX() + size);
                    c.setY(b.getY() - size);
                    d.setX(b.getX());
                    d.setY(b.getY() - size);
                    b.setX(b.getX() + size);
                } else {
                    form = (form - 1) % 4;
                }
            }
            if (form == 0) {
                if (canMove("b", -1, 0) && canMove("b", -2, 1) && canMove("b", 0, 1)
                    && canMove("b", 0, 0) && canMove("b", -1, 1)) {
                    b.setX(b.getX() - size);
                    a.setX(b.getX() - size);
                    a.setY(b.getY() + size);
                    c.setX(b.getX() + size);
                    c.setY(b.getY() + size);
                    d.setY(b.getY());
                    d.setX(b.getX() + size);
                    b.setY(b.getY() + size);
                } else {
                    form = (form - 1) % 4;
                }
            }
        }
    } // l.

    /**
     * form equal to one.
     */
    void  jFormEqOne() {
        if (form == 1) {
            if (canMove("b", 0, 1) && canMove("b", 1, 0) && canMove("b", 1, 2) && canMove("b", 0, 2)
                    && canMove("b", 1, 1)) {
                b.setY(b.getY() + size);
                a.setX(b.getX() + size);
                a.setY(b.getY() - size);
                c.setY(b.getY() + size);
                c.setX(b.getX() + size);
                d.setY(b.getY() + size);
                d.setX(b.getX());
                b.setX(b.getX() + size);
            } else {
                form = (form - 1) % 4;
            }
        }
    } // jFormEqOne.

    /**
     * name equal to j.
     */
    void j() {
        if (name.equals("j")) {
            jFormEqOne();
            if (form == 2) {
                if (canMove("b", -1, 0) && canMove("b", 0, 1) && canMove("b", -2, 1)
                    && canMove("b", -2, 0) && canMove("b", -1, 1)) {
                    b.setX(b.getX() - size);
                    a.setX(b.getX() + size);
                    a.setY(b.getY() + size);
                    c.setX(b.getX() - size);
                    c.setY(b.getY() + size);
                    d.setX(b.getX() - size);
                    d.setY(b.getY());
                    b.setY(b.getY() + size);
                } else {
                    form = (form - 1) % 4;
                }
            }
            if (form == 3) {
                if (canMove("b", 0, -1) && canMove("b", -1, 0) && canMove("b", -1, -2)
                    && canMove("b", 0, -2) && canMove("b", -1, -1)) {
                    b.setY(b.getY() - size);
                    a.setX(b.getX() - size);
                    a.setY(b.getY() + size);
                    c.setX(b.getX() - size);
                    c.setY(b.getY() - size);
                    d.setX(b.getX());
                    d.setY(b.getY() - size);
                    b.setX(b.getX() - size);
                } else {
                    form = (form - 1) % 4;
                }
            }
            if (form == 0) {
                if (canMove("b", 1, 0) && canMove("b", 0, -1) && canMove("b", 2, -1)
                    && canMove("b", 2, 0) && canMove("b", 1, -1)) {
                    b.setX(b.getX() + size);
                    a.setX(b.getX() - size);
                    a.setY(b.getY() - size);
                    c.setX(b.getX() + size);
                    c.setY(b.getY() - size);
                    d.setX(b.getX() + size);
                    d.setY(b.getY());
                    b.setY(b.getY() - size);
                } else {
                    form = (form - 1) % 4;
                }
            }
        }
    } // j.

    /**
     * name equal to i.
     */
    void i() {
        if (name.equals("i")) {
            if (form == 1 || form == 3) {
                if (canMove("c", 0, -2) && canMove("c", 0, -1) && canMove("c", 0, 1)) {
                    a.setX(c.getX());
                    a.setY(c.getY() - size * 2);
                    b.setY(c.getY() - size);
                    b.setX(c.getX());
                    d.setX(c.getX());
                    d.setY(c.getY() + size);
                } else {
                    form = (form - 1) % 4;
                }
            }
            if (form == 0 || form == 2) {
                if (canMove("c", 2, 0) && canMove("c", 1, 0) && canMove("c", -1, 0)) {
                    a.setX(c.getX() + size * 2);
                    a.setY(c.getY());
                    b.setY(c.getY());
                    b.setX(c.getX() + size);
                    d.setX(c.getX() - size);
                    d.setY(c.getY());
                } else {
                    form = (form - 1) % 4;
                }
            }
        }
    } // i.

    /**
     * name equal to t.
     */
    void t() {
        if (name.equals("t")) {
            if (form == 1) {
                if (canMove("b", 0, 1) && canMove("b", 0, -1) && canMove("b", 1, 0)) {
                    a.setX(b.getX());
                    a.setY(b.getY() + size);
                    c.setY(b.getY() - size);
                    c.setX(b.getX());
                    d.setX(b.getX() + size);
                    d.setY(b.getY());
                } else {
                    form = (form - 1) % 4;
                }
            }
            if (form == 2) {
                if (canMove("b", -1, 0) && canMove("b", 1, 0) && canMove("b", 0, 1)) {
                    a.setX(b.getX() - size);
                    a.setY(b.getY());
                    c.setY(b.getY());
                    c.setX(b.getX() + size);
                    d.setX(b.getX());
                    d.setY(b.getY() + size);
                } else {
                    form = (form - 1) % 4;
                }
            }
            if (form == 3) {
                if (canMove("b", 0, -1) && canMove("b", 0, 1) && canMove("b", -1, 0)) {
                    a.setX(b.getX());
                    a.setY(b.getY() - size);
                    c.setY(b.getY() + size);
                    c.setX(b.getX());
                    d.setX(b.getX() - size);
                    d.setY(b.getY());
                } else {
                    form = (form - 1) % 4;
                }
            }
            if (form == 0) {
                if (canMove("b", 1, 0) && canMove("b", -1, 0) && canMove("b", 0, -1)) {
                    a.setX(b.getX() + size);
                    a.setY(b.getY());
                    c.setY(b.getY());
                    c.setX(b.getX() - size);
                    d.setX(b.getX());
                    d.setY(b.getY() - size);
                } else {
                    form = (form - 1) % 4;
                }
            }
        }
    } // t.
} // Block.
