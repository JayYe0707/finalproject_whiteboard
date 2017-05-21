package finalproject_whiteboard;

import java.awt.*;
import java.util.*;

public abstract class DShape implements ModelListener {
    
    protected DShapeModel model;
    protected Canvas canvas;
    protected int dx = 0;
    protected int dy = 0;
    protected boolean isDragging = false;

    protected final int KNOB_SIZE = 9;
    protected Point resizeAnchor;
    protected Point movingPt;
    protected String editMode;

    public DShape(DShapeModel model, Canvas canvas) {
        this.model = model;
        this.canvas = canvas;
        model.addListener(this);
    }

    public Rectangle getBounds() {
        return model.getBounds();
    }

    public Color getColor() {
        return model.getColor();
    }

    public void setColor(Color c) {
        model.setColor(c);
    }

    public void onMouseReleased() {
        isDragging = false;
        movingPt = null;
        resizeAnchor = null;
        editMode = null;
    }

    public void onMouseDragged(int x, int y) {

        if (editMode == "resize") {
            resize(x, y);
        } else if (editMode == "move") {
            move(x, y);
        }

    }

    private void resize(int x, int y) {
        int ax = (int) resizeAnchor.getX();
        int ay = (int) resizeAnchor.getY();

        if (x > ax && y > ay) {
            model.setBounds(ax, ay, (x - ax), (y - ay));
        } else if (ax > x && ay > y) {
            model.setBounds(x, y, (ax - x), (ay - y));
        } else if (ax > x && y > ay) {
            model.setBounds(x, ay, (ax - x), (y - ay));
        } else {
            model.setBounds(ax, y, (x - ax), (ay - y));
        }
    }

    public void move(int x, int y) {
        Rectangle bounds = model.getBounds();
        if (!isDragging) {
            dx = x - bounds.x;
            dy = y - bounds.y;
            isDragging = true;
        }

        model.setBounds((x - dx), (y - dy), bounds.width, bounds.height);
    }

    public void onMousePressed(Point point) {
        movingPt = knobsContains(point);
        if (movingPt != null) {
            editMode = "resize";
            for (Point p : getKnobs()) {
                if (p.getX() != movingPt.getX() && p.getY() != movingPt.getY()) {
                    resizeAnchor = p;
                    break;
                }
            }
        } else {
            editMode = "move";
        }

        System.out.println("RA: " + resizeAnchor + " MP: " +movingPt);
    }

    private ArrayList<Point> getKnobs() {
        Rectangle bounds = getBounds();
        ArrayList<Point> knobs = new ArrayList<Point>();

        int x = (int) bounds.getX();
        int y = (int) bounds.getY();
        int width = (int) bounds.getWidth();
        int height = (int) bounds.getHeight();
        
        Point leftTop = new Point(x, y);
        Point rightTop = new Point(x + width, y);
        Point leftBottom = new Point(x, y + height);
        Point rightBottom = new Point(x + width, y + height);

        knobs.add(leftTop);
        knobs.add(rightTop);
        knobs.add(leftBottom);
        knobs.add(rightBottom);

        return knobs;
    }

    private Point knobsContains(Point point) {
        ArrayList<Point> knobs = getKnobs();

        for (Point p : knobs) {
            int x = (int) p.getX() - (KNOB_SIZE - 1) / 2;
            int y = (int) p.getY() - (KNOB_SIZE - 1) / 2;
            Rectangle r = new Rectangle(x, y, KNOB_SIZE, KNOB_SIZE);
            if (r.contains(point)) {
                return p;
            }
        }

        return null;
    }

    public boolean containsPoint(Point point) {
        Rectangle bounds = getBounds();
        
        if(bounds.contains(point) || knobsContains(point) != null) {
            return true;
        }
        
        return false;
    }

    public void modelChanged(DShapeModel model) {
        canvas.repaint();
    }

    public void draw(Graphics g, Boolean isSelected) {
        if (isSelected) {
            g.setColor(Color.black);
            ArrayList<Point> knobs = getKnobs();

            for (Point p : knobs) {
                int x = (int) p.getX() - (KNOB_SIZE - 1) / 2;
                int y = (int) p.getY() - (KNOB_SIZE - 1) / 2;

                g.fillRect(x, y, KNOB_SIZE, KNOB_SIZE);
            }
        }
    }
}