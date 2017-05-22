/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject_whiteboard;

import java.awt.*;


/**
 *
 * @author renjoe
 */
public class DText extends DShape{
    private static String text = "hihi";
    public DText(DShapeModel model, Canvas canvas, String text) {
        super(model, canvas);
        //this.text = text;
    }

        public void draw(Graphics g, Boolean isSelected) {
        Color color = model.getColor();
        Rectangle bounds = model.getBounds();
        g.setColor(color);
        g.drawString(getText(), bounds.x, bounds.y);

        // System.out.println("Draw DRect: " + bounds.x + bounds.y + bounds.width + bounds.height);
        super.draw(g, isSelected);
    }
        public static String getText(){
         return DText.text;
    }
        
    public DTextModel getModel()
    {
    	return (DTextModel) model;
    }
}
