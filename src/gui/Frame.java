package gui;

import javax.swing.*;

import person.Person;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Frame implements ActionListener{

    private JPanel panel = new JPanel();
    private JFrame frame;
    private int counter = 2;
    private int boundlabelx = 240;
    private int boundfieldx = 320;
    private int boundY = 20;
    private Person[] pList;
    private List<JLabel> labels = new ArrayList<JLabel>();
    private List<JTextField> textFields = new ArrayList<JTextField>();
    private JLabel counterLabel;


    //https://www.youtube.com/watch?v=5o3fMLPY7qY
    //https://www.youtube.com/watch?v=iE8tZ0hn2Ws
    public Frame() {
        frame = new JFrame("Money Tracker");
        frame.setSize(1280,720);

        JButton button1 = new JButton("Add person");
        button1.setBounds(10, 20, 130, 20);
        JButton button2 = new JButton("Remove person");
        button2.setBounds(10, 50, 130, 20);
        JButton button3 = new JButton("Next");
        button3.setBounds(1120, 620, 120, 40);

        
        counterLabel = new JLabel("Number of people: " + counter);
        counterLabel.setBounds(10, 80, 300, 20);

        JLabel label1 = new JLabel("Person 1 :");
        label1.setBounds(boundlabelx, boundY, 300, 20);

        JTextField textField1 = new JTextField();
        textField1.setBounds(boundfieldx, boundY, 200, 20);

        this.updateBoundY();

        JLabel label2 = new JLabel("Person 2 :");
        label2.setBounds(boundlabelx, boundY, 300, 20);

        JTextField textField2 = new JTextField();
        textField2.setBounds(boundfieldx, boundY, 200, 20);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(null); //new GridLayout(0, 1)
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(counterLabel);
        panel.add(label1);
        panel.add(label2);
        panel.add(textField1);
        panel.add(textField2);

        //centering the frame on any monitor : https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.add(panel, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        button1.setActionCommand("add");
        button1.addActionListener(this);

        button2.setActionCommand("remove");
        button2.addActionListener(this);

        button3.setActionCommand("next");
        button3.addActionListener(this);

        this.refreshFrame();

    }

    public void addPersonField() {
        labels.add(new JLabel("Person " + counter + " : "));
        textFields.add(new JTextField());

        labels.get(counter-3).setBounds(boundlabelx, boundY, 300, 20);
        textFields.get(counter-3).setBounds(boundfieldx, boundY, 200, 20);

        panel.add(labels.get(counter-3));
        panel.add(textFields.get(counter-3));

    }

    public void removePersonField() {
        panel.remove(labels.get(counter-3));
        panel.remove(textFields.get(counter-3));

        labels.remove(counter-3);
        textFields.remove(counter-3);

    }

    public void updateBoundY() {
        boundY += 30;
    }

    public void refreshFrame() { //refreshes the entire frame and its components
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public void nextFrame() {
        panel.removeAll();



        
        this.refreshFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()) {
            case "add":
                if (counter < 10) {
                    counter++;
                    counterLabel.setText("Number of people: " + counter);
                    this.updateBoundY();
                    this.addPersonField();
                    this.refreshFrame();
                }
                break;
        
            case "remove":
                if (counter > 2) {
                    this.removePersonField();
                    counter--;
                    boundY -= 30;
                    counterLabel.setText("Number of people: " + counter);
                    this.refreshFrame();
                }
                break;

            case "next":
                this.nextFrame();
                break;

            default:
                break;
        }
    }

}

