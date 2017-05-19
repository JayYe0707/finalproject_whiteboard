import java.awt.*;

public class DShape {
    
    protected DShapeModel model;
    protected Canvas canvas;

    public DShape(DShapeModel model, Canvas canvas) {
        this.model = model;
        this.canvas = canvas;
    }

    public Rectangle getBounds() {
        return model.getBounds();
    }

    public boolean containsPoint(Point point) {
        Rectangle bounds = getBounds();
        
        if(bounds.contains(point)) {
            return true;
        }
        
        return false;
    }

    public void draw(Graphics g, Boolean isSelected) {
        if (isSelected) {
            Rectangle bounds = model.getBounds();
            g.setColor(Color.red);
            g.drawString("X", bounds.x, bounds.y);
        }
    }
}