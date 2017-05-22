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
public class DText extends DShape{
    private String textFromDText=null;

    public DText(DShapeModel model, Canvas canvas, String textFromModel) {
        super(model, canvas);
        this.textFromDText = textFromModel;
    }

    public void draw(Graphics g, Boolean isSelected) {
        Color color = model.getColor();
        Rectangle bounds = model.getBounds();
        g.setColor(color);
        g.drawString(this.textFromDText, bounds.x, bounds.y+bounds.height);

        // System.out.println("Draw DRect: " + bounds.x + bounds.y + bounds.width + bounds.height);
        super.draw(g, isSelected);
    }
    
    public void setText(String text){
        DTextModel m = (DTextModel) model;
        m.setText(text);
    }

//        public static String getText(){
//         return DText.text;
//    }
//        public static void setText(String newText){
//             DText.text = newText;
//             
//        }
}
