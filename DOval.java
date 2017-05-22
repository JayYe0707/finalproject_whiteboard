/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject_whiteboard;

import java.awt.*;
/**
 *
 * @author renjie
 */
public class DOval extends DShape{
    
    public DOval(DShapeModel model, Canvas canvas) {
        super(model, canvas);
    }
    
    public void draw(Graphics g, Boolean isSelected){
        Color color = model.getColor();
        Rectangle bounds = model.getBounds();
        g.setColor(color);
        g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
        super.draw(g, isSelected);
    }
    
}
