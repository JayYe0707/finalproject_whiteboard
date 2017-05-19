import java.awt.*;

public class DRect extends DShape {
    public DRect(DShapeModel model, Canvas canvas) {
        super(model, canvas);
    }

    @Override
    public void draw(Graphics g, Boolean isSelected) {
        Color color = model.getColor();
        Rectangle bounds = model.getBounds();
        g.setColor(color);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

        // System.out.println("Draw DRect: " + bounds.x + bounds.y + bounds.width + bounds.height);
        super.draw(g, isSelected);
    }
}