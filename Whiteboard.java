package finalproject_whiteboard;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Whiteboard extends JFrame {
    private final static String THE_TITLE = "Nice Whiteboard";
    private Canvas canvas;
    private JPanel controls;
    private JTextField inputText;
    private String newText;
    private DShape getData;
    
    
    

    public Whiteboard() {
        this.setTitle(THE_TITLE);
        setLayout(new BorderLayout());
        canvas = new Canvas();
        add(canvas, BorderLayout.CENTER);

        controls = new JPanel();
        controls.setLayout(new GridLayout(6, 1));
        controls.add(createButtons());
        controls.add(createSettings());

        add(controls, BorderLayout.WEST);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private Box createSettings() {
        JButton btnSetColor = new JButton("Pick Color");
        btnSetColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DShape s = canvas.getSelectedShape();
                if (s != null) {
                    Color color = JColorChooser.showDialog(null, "Pick a Color", s.getColor());
                    System.out.println("Color Selected: " + color);
                    s.setColor(color);
                }
            }
        });

        Box box = Box.createHorizontalBox();

        box.add(btnSetColor);

        return box;
    }

    private Box createButtons() {
        JButton btnRect = new JButton("Rect");
         
        //add listenner for rect
        btnRect.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               canvas.addShape(new DRectModel());
           }
        });
         // add button and listenner for oval
        JButton btnOval = new JButton("Oval");
        btnOval.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                canvas.addShape(new DOvalModel()); 
            }
        });
        
        //add line button and listenner
        JButton btnLine = new JButton("Line");
        btnLine.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                canvas.addShape(new DLineModel());
            }
        });
        
        //create text button and listener 
        JButton btnText = new JButton("Text");
        btnText.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                canvas.addShape(new DTextModel());
            }
        });
        
        //create and add textfield for the input text
        inputText = new JTextField(DText.getText(),10);
        //inputText.addKeyListener((KeyListener) this);
        inputText.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent de) {
                newText = inputText.getText();
                DText.setText(newText);
                
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                newText = inputText.getText();
                DText.setText(newText);
          
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                newText = inputText.getText();
                DText.setText(newText);
            }
            
        });
       

        
        


        
        

        Box box = Box.createHorizontalBox();
        box.add(btnRect);
        box.add(btnOval);
        box.add(btnLine);
        box.add(btnText);
        box.add(inputText);
        //controls.add(inputText);
        

        return box;
    }

    public static void main(String[] args) {
        Whiteboard whiteboard = new Whiteboard();
    }

