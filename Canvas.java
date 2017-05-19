import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Canvas extends JPanel {
    private static final int HEIGHT = 400;
    private static final int WIDTH = 400;

    private static final int SHAPE_X = 10;
    private static final int SHAPE_Y = 10;

    private static final int SHAPE_W = 50;
    private static final int SHAPE_H = 50;

    private ArrayList<DShape> shapes;
    private DShape selectedShape;

    public Canvas(Whiteboard board) {
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(getMinimumSize());
        setBackground(Color.white);
        shapes = new ArrayList<DShape>();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectShape(e.getPoint());
            }
        });
    }

    private void selectShape(Point point) {
        Boolean clickOnShape = false;
        for (DShape shape : shapes) {
            if (shape.containsPoint(point)) {
                selectedShape = shape;
                clickOnShape = true;
            }
        }
        if (!clickOnShape) {
            selectedShape = null;
        }
        System.out.println(selectedShape);
        repaint();
    }

    public void addShape(DShapeModel model) {
        // System.out.println(model);
        DShape shape = null;

        // set bounds
        Random rand = new Random();
        int w = rand.nextInt(100) + SHAPE_W;
        rand = new Random();
        int h = rand.nextInt(100) + SHAPE_H;
        rand = new Random();

        int x = rand.nextInt(WIDTH - w) + SHAPE_X;
        rand = new Random();
        int y = rand.nextInt(HEIGHT - h) + SHAPE_Y;

        model.setBounds(x, y, w, h);

        if(model instanceof DRectModel) {
            shape = new DRect(model, this);
        }

        shapes.add(shape);
        System.out.println(shapes.toString());
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);

        for (DShape shape : shapes) {
            shape.draw(g2, (shape == selectedShape));
        }
    }
}