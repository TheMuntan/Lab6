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
    private JFrame mainFrame;
    private int counter = 0;
    private int boundlabelx = 240;
    private int boundfieldx = 320;
    private int boundY = 20;
    private List<Person> pList = new ArrayList<Person>();
    private List<JLabel> labels = new ArrayList<JLabel>();
    private List<JTextField> textFields = new ArrayList<JTextField>();
    private List<JLabel> ticketLabels = new ArrayList<JLabel>();
    private List<JTextField> ticketFields = new ArrayList<JTextField>();
    private JLabel counterLabel;
    private JLabel splitLabel;
    private boolean firstFrame = true;
    private boolean evenSplit = false;


    //https://www.youtube.com/watch?v=5o3fMLPY7qY
    //https://www.youtube.com/watch?v=iE8tZ0hn2Ws
    public Frame() {
        mainFrame = new JFrame("Money Tracker");
        mainFrame.setSize(1280,720);

        JButton button1 = new JButton("Add person");
        button1.setBounds(10, 20, 130, 20);
        JButton button2 = new JButton("Remove person");
        button2.setBounds(10, 50, 130, 20);
        JButton button3 = new JButton("Next");
        button3.setBounds(1120, 620, 120, 40);

        counterLabel = new JLabel("Number of people: " + (counter + 2));
        counterLabel.setBounds(10, 80, 300, 20);


        labels.add(new JLabel("Person " + (counter + 1) + " : "));
        textFields.add(new JTextField());

        labels.get(counter).setBounds(boundlabelx, boundY, 300, 20);

        textFields.get(counter).setBounds(boundfieldx, boundY, 200, 20);

        counter++;
        this.updateBoundY();

        labels.add(new JLabel("Person " + (counter + 1) + " : "));
        textFields.add(new JTextField());

        labels.get(counter).setBounds(boundlabelx, boundY, 300, 20);

        textFields.get(counter).setBounds(boundfieldx, boundY, 200, 20);



        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(null); //new GridLayout(0, 1)
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(counterLabel);
        panel.add(labels.get(counter-1));
        panel.add(labels.get(counter));
        panel.add(textFields.get(counter-1));
        panel.add(textFields.get(counter));

        //centering the frame on any monitor : https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setLocation(dim.width/2- mainFrame.getSize().width / 2,
                dim.height / 2 - mainFrame.getSize().height / 2);

        mainFrame.add(panel, BorderLayout.CENTER);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        button1.setActionCommand("add");
        button1.addActionListener(this);

        button2.setActionCommand("remove");
        button2.addActionListener(this);

        button3.setActionCommand("next");
        button3.addActionListener(this);

        this.refreshFrame();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "add":
                if (counter < 11) {
                    counter++;
                    counterLabel.setText("Number of people: " + (counter + 1));
                    this.updateBoundY();
                    this.addPersonField();
                    this.refreshFrame();
                }
                break;

            case "remove":
                if (counter > 1) {
                    this.removePersonField();
                    counter--;
                    boundY -= 30;
                    counterLabel.setText("Number of people: " + (counter + 1));
                    this.refreshFrame();
                }
                break;

            case "split":
                evenSplit = !evenSplit;
                if (evenSplit) {
                    splitLabel.setText("Bill is split evenly.");
                    this.removePriceField();
                }
                else {
                    splitLabel.setText("Bill is not split evenly.");
                    this.addPriceField();
                }
                this.refreshFrame();
                break;

            case "next":
                if (firstFrame)
                    this.addPersons();
                this.nextFrame();
                break;

            default:
                break;
        }
    }

    public void addPersonField() {
        labels.add(new JLabel("Person " + (counter + 1) + " : "));
        textFields.add(new JTextField());

        labels.get(counter).setBounds(boundlabelx, boundY, 300, 20);
        textFields.get(counter).setBounds(boundfieldx, boundY, 200, 20);

        panel.add(labels.get(counter));
        panel.add(textFields.get(counter));

    }

    public void removePersonField() {
        panel.remove(labels.get(counter));
        panel.remove(textFields.get(counter));

        labels.remove(counter);
        textFields.remove(counter);

    }

    public void addPersons() {

    }

    public void updateBoundY() {
        boundY += 30;
    }

    public void refreshFrame() { // refreshes the entire frame and its components
        SwingUtilities.updateComponentTreeUI(mainFrame);
        
    }

    public void nextFrame() {
        panel.removeAll();
        boundY = 20;
        
        this.refreshFrame();
        this.ticketFrame();
    }


    public void ticketFrame() {
        
        JButton button1 = new JButton("Toggle bill split evenly");
        button1.setBounds(10, 20, 200, 20);
        JButton button2 = new JButton("Next");
        button2.setBounds(1120, 620, 120, 40);
        splitLabel = new JLabel("Bill is not split evenly.");
        splitLabel.setBounds(50, 50, 200, 20);
        this.addPriceField();

        button1.setActionCommand("split");
        button1.addActionListener(this);


        panel.add(button1);
        panel.add(button2);
        panel.add(splitLabel);


        this.refreshFrame();
    }

    public void addPriceField() {
        for (int i=0; i < 10; i++) {
        ticketLabels.add(new JLabel("Person " + (i + 1) + " : "));
        ticketFields.add(new JTextField());

        ticketLabels.get(i).setBounds(boundlabelx, boundY, 300, 20);
        ticketFields.get(i).setBounds(boundfieldx, boundY, 200, 20);
        this.updateBoundY();

        panel.add(ticketLabels.get(i));
        panel.add(ticketFields.get(i));

        }
        boundY = 20;
        this.refreshFrame();

    }

    public void removePriceField() {
        for (int i=9; i >= 0; i--) {
        panel.remove(ticketLabels.get(i));
        panel.remove(ticketFields.get(i));

        ticketLabels.remove(i);
        ticketFields.remove(i);
        }

        this.refreshFrame();
    }

}

