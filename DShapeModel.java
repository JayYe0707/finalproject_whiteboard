package finalproject_whiteboard;

import java.util.*;
import java.awt.*;

public abstract class DShapeModel {
    
    protected Rectangle bounds;
    protected Color color;
    protected ArrayList<ModelListener> listeners;

    public DShapeModel() {
        this(0, 0);
    }
    
    public DShapeModel(int x, int y) {
        this(x, y, 0, 0, Color.gray);
    }

    public DShapeModel(int x, int y, int width, int height, Color color) {
        bounds = new Rectangle(x, y, width, height);
        this.color = color;
        listeners = new ArrayList<ModelListener>();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
        notifyChange();
    }
    
//    public void setLineBounds(int x,int y ){
//        
//    }

    public void setColor(Color color) {
        this.color = color;
        notifyChange();
    }

    public Color getColor() {
        return color;
    }

    public void addListener(ModelListener listener) {
        listeners.add(listener);
    }

    public Boolean removeListener(ModelListener listener) {
        return listeners.remove(listener);
    }

    private void notifyChange() {
        for (ModelListener listener : listeners) {
            listener.modelChanged(this);
        }
    }

}