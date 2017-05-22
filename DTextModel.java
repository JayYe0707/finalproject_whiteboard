/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject_whiteboard;

/**
 *
 * @author renjie
 */
public class DTextModel extends DShapeModel{
    String textFromModel = null;
    
    public DTextModel(String textFromWhiteBoard){
        super();
        textFromModel = textFromWhiteBoard;
        
    }
    public  void setText(String updatedText){
        this.textFromModel = updatedText;
        super.notifyChange();
    }
    public  String getText(){
        return this.textFromModel;
    }
}
