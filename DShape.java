package finalproject_whiteboard;

import java.awt.*;

public abstract class DShape implements ModelListener {
    
    protected DShapeModel model;
    protected Canvas canvas;

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

    public boolean containsPoint(Point point) {
        Rectangle bounds = getBounds();
        
        if(bounds.contains(point)) {
            return true;
        }
        
        return false;
    }

    public void modelChanged(DShapeModel model) {
        canvas.repaint();
        System.out.println("DShape LALAALL");
    }

    public void draw(Graphics g, Boolean isSelected) {
        if (isSelected) {
            Rectangle bounds = model.getBounds();
            g.setColor(Color.red);
            g.drawString("X", bounds.x, bounds.y);
        }
    }
}