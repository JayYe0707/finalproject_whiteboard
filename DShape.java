package finalproject_whiteboard;

import java.awt.*;

public abstract class DShape implements ModelListener {
    
    protected DShapeModel model;
    protected Canvas canvas;
    protected int dx = 0;
    protected int dy = 0;
    protected boolean isDragging = false;

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
            g.setColor(Color.red);
            g.drawString("X", bounds.x, bounds.y);
        }
    }
}