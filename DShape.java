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

    public void endDragging() {
        isDragging = false;
    }

    public void move(int x, int y) {
        Rectangle bounds = model.getBounds();
        if (!isDragging) {
            dx = x - bounds.x;
            dy = y - bounds.y;
            isDragging = true;
        }
        System.out.println("dx: " + dx + " dy: " +dy);
        model.setBounds((x - dx), (y - dy), bounds.width, bounds.height);
    }

    public ArrayList<Point> getKnobs() {
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

    public boolean containsPoint(Point point) {
        Rectangle bounds = getBounds();
        
        if(bounds.contains(point)) {
            return true;
        }
        
        return false;
    }

    public void modelChanged(DShapeModel model) {
        canvas.repaint();
    }

    public void draw(Graphics g, Boolean isSelected) {
        if (isSelected) {
            Rectangle bounds = model.getBounds();
            g.setColor(Color.black);
            // g.drawString("X", bounds.x, bounds.y);
            ArrayList<Point> knobs = getKnobs();

            for (Point p : knobs) {
                int x = (int) p.getX() - (KNOB_SIZE - 1) / 2;
                int y = (int) p.getY() - (KNOB_SIZE - 1) / 2;
                g.fillRect(x, y, KNOB_SIZE, KNOB_SIZE);

                // System.out.println(p);
            }
        }
    }
}