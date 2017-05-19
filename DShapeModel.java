import java.util.*;
import java.awt.*;

public abstract class DShapeModel {
    
    protected Rectangle bounds;
    protected Color color;

    public DShapeModel() {
        this(0, 0);
    }
    
    public DShapeModel(int x, int y) {
        this(x, y, 0, 0, Color.gray);
    }

    public DShapeModel(int x, int y, int width, int height, Color color) {
        bounds = new Rectangle(x, y, width, height);
        this.color = color;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}