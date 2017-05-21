package finalproject_whiteboard;

import java.awt.*;

public class DLine extends DShape {
    public DLine(DShapeModel model, Canvas canvas) {
        super(model, canvas);
    }

    public void draw(Graphics g, Boolean isSelected) {
        Color color = model.getColor();
        Rectangle bounds = model.getBounds();
        g.setColor(color);
        g.drawLine(bounds.x, bounds.y, bounds.width, bounds.height);
        // System.out.println("Draw DRect: " + bounds.x + bounds.y + bounds.width + bounds.height);
        super.draw(g, isSelected);
    }
}