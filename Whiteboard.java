package finalproject_whiteboard;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Whiteboard extends JFrame {
    private final static String THE_TITLE = "Nice Whiteboard";
    private Canvas canvas;
    private JPanel controls;

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
                    System.out.println(color);
                }
            }
        });

        Box box = Box.createHorizontalBox();

        box.add(btnSetColor);

        return box;
    }

    private Box createButtons() {
        JButton btnRect = new JButton("Rect");

        btnRect.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               canvas.addShape(new DRectModel());
           }
        });

        JButton btnOval = new JButton("Oval");
        JButton btnLine = new JButton("Line");
        JButton btnText = new JButton("Text");

        Box box = Box.createHorizontalBox();
        box.add(btnRect);
        box.add(btnOval);
        box.add(btnLine);
        box.add(btnText);

        return box;
    }

    public static void main(String[] args) {
        Whiteboard whiteboard = new Whiteboard();
    }
}