package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame {

    private int counter = 2;
    private JTextField text;

    //https://www.youtube.com/watch?v=5o3fMLPY7qY
    //https://www.youtube.com/watch?v=iE8tZ0hn2Ws
    public Frame() {
        JFrame frame = new JFrame("Money Tracker");
        frame.setSize(1280,720);

        JButton button1 = new JButton("Add person");
        button1.setBounds(10, 20, 140, 20);
        JButton button2 = new JButton("Remove person");
        button2.setBounds(10, 50, 140, 20);

        JLabel label1 = new JLabel("Number of people: " + counter);
        label1.setBounds(10, 80, 300, 20);

        JLabel label2 = new JLabel("Person 1 :");
        label2.setBounds(240, 20, 300, 20);

        JLabel label3 = new JLabel("Person 2 :");
        label3.setBounds(240, 50, 300, 20);

        JTextField textField1 = new JTextField();
        textField1.setBounds(320, 20, 200, 20);

        JTextField textField2 = new JTextField();
        textField2.setBounds(320, 50, 200, 20);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(null); //new GridLayout(0, 1)
        panel.add(button1);
        panel.add(button2);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(textField1);
        panel.add(textField2);

        //centering the frame on any monitor : https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.add(panel, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                label1.setText("Number of people: " + counter);

            }
        }
        );

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (counter > 2) {
                    counter--;
                    label1.setText("Number of people: " + counter);
                }
            }
        }
        );


    }
}

