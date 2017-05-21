package finalproject_whiteboard;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javafx.stage.FileChooser;


public class Whiteboard extends JFrame {
    private final static String THE_TITLE = "Nice Whiteboard";
    private Canvas canvas;
    private JPanel controls;
    private JTextField inputText;
    private String textFromCanvas;
    private JFileChooser fileChooser;
    

    public Whiteboard() {
        this.setTitle(THE_TITLE);
        
        fileChooser = new JFileChooser();
        
        setLayout(new BorderLayout());
        canvas = new Canvas();
        add(canvas, BorderLayout.CENTER);

        controls = new JPanel();
        controls.setLayout(new GridLayout(6, 1));
        controls.add(createButtons());

        add(controls, BorderLayout.WEST);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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
        Action action = new AbstractAction(){
           @Override
           public void actionPerformed(ActionEvent e){
            System.out.println("some action");
            }
        };
        inputText.addActionListener( action );
        
        //create button that implements save funciton
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		save();
        	}
        });
        
        //create button that implements open function
        JButton openButton = new JButton("Open");
        openButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		open();
        	}
        });
        
        //create button that implements save image function
        JButton saveImageButton = new JButton("Save Image");
        saveImageButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		saveImage();
        	}
        });


        Box box = Box.createHorizontalBox();
        box.add(btnRect);
        box.add(btnOval);
        box.add(btnLine);
        box.add(btnText);
        
        Box box2 = Box.createHorizontalBox();
        box2.add(inputText);
        //controls.add(inputText);
        
        Box box3 = Box.createHorizontalBox();
        box3.add(saveButton);
        box3.add(openButton);
        box3.add(saveImageButton);
        
        Box totalBox = Box.createVerticalBox();
        totalBox.add(box3);
        totalBox.add(box);
        totalBox.add(box2);
        

        return totalBox;
    }
    
    private void save(){
    	fileChooser.setSelectedFile(new File(".xml"));
        int value = fileChooser.showSaveDialog(this);
        
        if(value == JFileChooser.APPROVE_OPTION) 
        {
            File file = fileChooser.getSelectedFile();
            
            if(file.exists())
            {
            	JOptionPane.showMessageDialog(null, "File Already Exists");
            	return;
            }
            try
            {
            	XMLEncoder xmlOut = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
            	ArrayList<DShapeModel> models = this.canvas.getShapeModels();
            	xmlOut.writeObject(models);
            	xmlOut.close();
            }
            catch (IOException e)
            {
            	e.printStackTrace();
            }
        }
        
        fileChooser.setSelectedFile(new File(""));
        
    }  
    
    private void open() 
    {
        int value = fileChooser.showOpenDialog(this);
        
        if(value == JFileChooser.APPROVE_OPTION) 
        {
        	File file = fileChooser.getSelectedFile();
        	
        	if(!file.getName().endsWith(".xml"))
        	{
        		JOptionPane.showMessageDialog(null, "Invalid File!");
        		return;
        	}
        	
        	ArrayList<DShapeModel> models = null;
        	
        	try
        	{
        		XMLDecoder xmlIn = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
        		models = (ArrayList<DShapeModel>) xmlIn.readObject();
        		xmlIn.close();
        		this.canvas.clear();
        		
        		for(DShapeModel model : models)
        		{
        			this.canvas.addShape(model);
        		}
        	}
        	catch(IOException e)
        	{
        		e.printStackTrace();
        	}
        }
    }  
    
    private void saveImage(){
    	int value = fileChooser.showSaveDialog(this);
    	
    	if(value == JFileChooser.APPROVE_OPTION)
    	{
    		BufferedImage image = (BufferedImage) this.canvas.createImage(this.canvas.getWidth(), this.canvas.getHeight());
    		
    		Graphics graphic = image.getGraphics();
    		this.canvas.paintAll(graphic);
    		graphic.dispose();
    		
    		File file = fileChooser.getSelectedFile();
    		File png = new File(file + ".png");
    		
    		try
    		{
    			ImageIO.write(image, "png", png);
    		}
    		catch (IOException e)
    		{
    			e.printStackTrace();
    		}
    		
    	}
    }

    public static void main(String[] args) {
        Whiteboard whiteboard = new Whiteboard();
    }
}

