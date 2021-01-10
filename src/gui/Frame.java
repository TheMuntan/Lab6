package gui;

import javax.swing.*;

import calculator.Calculator;
import factory.AbstractFactory;
import factory.FactoryProducer;
import person.Person;
import ticket.Ticket;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Frame implements ActionListener{

    private JPanel panel = new JPanel();
    private JFrame mainFrame;
    private int counter = 0;
    private int boundlabelx = 240;
    private int boundfieldx = 320;
    private int boundY = 20;
    private AbstractFactory pFactory = FactoryProducer.getFactory("person");
    private AbstractFactory tFactory = FactoryProducer.getFactory("ticket");    
    private HashMap<Person, Double> pHash = new HashMap<>();
    private Iterator<Person> it;
    private List<Person> pList = new ArrayList<>();
    private List<JLabel> labels = new ArrayList<>();
    private List<JTextField> textFields = new ArrayList<>();
    private List<JLabel> ticketLabels = new ArrayList<>();
    private List<JTextField> ticketFields = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();
    private JLabel counterLabel;
    private JLabel splitLabel;
    private boolean firstFrame = true;
    private boolean evenSplit = false;
    private JLabel totalLabel = new JLabel("Ticket total: ");
    private JTextField totalField = new JTextField();
    private JLabel payedPersonLabel;
    private JTextField payedPersonField;
    private HashMap<HashMap<Person, Person>, Double> tripleHashMap;
    private JLabel incorrectFill = new JLabel("Please make sure everything is filled in correctly!");


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
        mainFrame.setLocation(dim.width/2- mainFrame.getSize().width / 2, dim.height / 2 - mainFrame.getSize().height / 2);

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
                panel.remove(incorrectFill);
                incorrectFill.setForeground(Color.RED);
                incorrectFill.setBounds(420, 630, 300, 20);
                boolean filledCorrectly = true;
                this.refreshFrame();

                if (firstFrame) {
                    for (int i = 0; i < textFields.size(); i++) {
                        if(textFields.get(i).getText().isEmpty()) {
                            filledCorrectly = false;
                        }
                    }
                    if (filledCorrectly) {
                        this.addPersons();
                        firstFrame = false;
                        this.nextFrame();
                    } else {
                        panel.add(incorrectFill);
                    }
                } else {
                    pHash = new HashMap<>();
                    it = pList.iterator();
                    int indexPayed = 0;
                    int currentIndex = 0;

                    if (payedPersonField.getText().isEmpty()) {
                        filledCorrectly = false;
                    } else if (evenSplit && totalField.getText().isEmpty()) {
                        filledCorrectly = false;
                    } else {
                        if (!evenSplit) {
                            for (int i = 0; i < pList.size(); i++) {
                                if (ticketFields.get(i).getText().isEmpty()) {
                                    filledCorrectly = false;
                                }
                            }
                        }
                    }

                    if (filledCorrectly) {

                    while(it.hasNext()) { //iterating through our persons list to find the index of the person who payed
                        if (it.next().getName().equalsIgnoreCase(payedPersonField.getText())) {
                            indexPayed = currentIndex;
                            break;
                        }
                        currentIndex++;
                    }

                    Person payed = pList.get(indexPayed);

                    if (evenSplit) {
                        Double total = Double.parseDouble(totalField.getText());
                        Double perPerson = total/pList.size();
                        for (int i = 0; i < pList.size(); i++) {
                            pHash.put(pList.get(i), perPerson);
                            if (pList.get(i) != payed) 
                                payed.owedBy(pList.get(i), perPerson);
                        }
                    }
                    else {
                        for (int i = 0; i < pList.size(); i++) {
                            pHash.put(pList.get(i), Double.parseDouble(ticketFields.get(i).getText()));
                            if (pList.get(i) != payed) 
                                payed.owedBy(pList.get(i), Double.parseDouble(ticketFields.get(i).getText()));
                        }
                    }
                    tickets.add(tFactory.getTicket(pHash, payed));
                    ticketLabels.clear();
                    ticketFields.clear();
                    this.nextFrame();
                    } else {
                        panel.add(incorrectFill);
                    }
                }
                this.refreshFrame();
                
                break;
            
            case "calculate":
                this.removePriceField();
                panel.removeAll();
                this.refreshFrame();
                Calculator calculator = Calculator.getInstance();
                tripleHashMap = calculator.calculateFinalTotal(pList);
                this.finalFrame();
                break;

            case "close":
                mainFrame.dispose();
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
        for (int i = 0; i < textFields.size(); i++) {
            pList.add(pFactory.getPerson(textFields.get(i).getText()));
        }
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
        boundfieldx = 350;
        
        this.ticketFrame();
    }


    public void ticketFrame() {
        
        JButton button1 = new JButton("Toggle bill split evenly");
        button1.setBounds(10, 20, 200, 20);
        JButton button2 = new JButton("Add ticket");
        button2.setBounds(1120, 620, 120, 40);
        JButton button3 = new JButton("Calculate");
        button3.setBounds(1120, 560, 120, 40);
        evenSplit = false;
        splitLabel = new JLabel("Bill is not split evenly.");
        splitLabel.setBounds(50, 50, 200, 20);
        payedPersonLabel = new JLabel("Person who payed:");
        payedPersonField = new JTextField();
        payedPersonLabel.setBounds(600, 20, 200, 20);
        payedPersonField.setBounds(720, 20, 200, 20);
        this.addPriceField();

        button1.setActionCommand("split");
        button1.addActionListener(this);        
        button2.setActionCommand("next");
        button2.addActionListener(this);
        button3.setActionCommand("calculate");
        button3.addActionListener(this);


        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(splitLabel);
        panel.add(payedPersonLabel);
        panel.add(payedPersonField);


        this.refreshFrame();
    }

    public void addPriceField() {
        for (int i=0; i < textFields.size(); i++) {
        ticketLabels.add(new JLabel(pList.get(i).getName() + " :"));
        ticketFields.add(new JTextField());

        ticketLabels.get(i).setBounds(boundlabelx, boundY, 300, 20);
        ticketFields.get(i).setBounds(boundfieldx, boundY, 200, 20);
        this.updateBoundY();

        panel.add(ticketLabels.get(i));
        panel.add(ticketFields.get(i));

        }

        panel.remove(totalLabel);
        panel.remove(totalField);
        
        boundY = 20;
        this.refreshFrame();

    }

    public void removePriceField() {
        for (int i=textFields.size(); i > 0; i--) {
        panel.remove(ticketLabels.get(i-1));
        panel.remove(ticketFields.get(i-1));

        ticketLabels.remove(i-1);
        ticketFields.remove(i-1);
        }

        totalLabel = new JLabel("Ticket total: ");
        totalField = new JTextField();

        totalLabel.setBounds(boundlabelx, 20, 300, 20);
        totalField.setBounds(boundfieldx, 20, 200, 20);


        panel.add(totalLabel);
        panel.add(totalField);


        this.refreshFrame();
    }

    public void finalFrame() {
        boundY = 20;
        int i = 0;
        Iterator it = tripleHashMap.entrySet().iterator();
        Person owes = null;
        Person owed = null;
        while (it.hasNext()) {
            Map.Entry<HashMap<Person, Person>, Double> pair = (Map.Entry)it.next();
                Iterator it2 = pair.getKey().entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry<Person, Person> pair2 = (Map.Entry)it2.next();
                    owes = pair2.getKey();
                    owed = pair2.getValue();
                }

                if (pair.getValue() > 0) {
                    ticketLabels.add(new JLabel(owes.getName() + " owes €" + pair.getValue() + " to " + owed.getName() + "."));
            
                    ticketLabels.get(i).setBounds(490, boundY, 300, 20);
                    this.updateBoundY();
            
                    panel.add(ticketLabels.get(i));

                    i++;                
                }
        }

        JButton buttonClose = new JButton("Close application");
        buttonClose.setBounds(520, 600, 200, 60);

        panel.add(buttonClose);

        buttonClose.setActionCommand("close");
        buttonClose.addActionListener(this);

    }

}