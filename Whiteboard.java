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
        canvas = new Canvas(this);
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