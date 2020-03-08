/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projektas;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author uslegaityte
 */
public class Window {

    private JButton btn1, btn2, btn3;
    private JTextArea output;
    private JTextField input;
    private JLabel textAreaName;
    private int sizeOfInitialSubSet, sizeOfGenSet, sizeOfLeftSubSet;
    private double coef;
    private static MySparseArray<Phone> sparse;
    Phone[] sarasasSuT;
    public Window() {
        JFrame frame = new JFrame("Telefonu parduotuve");

        JPanel mainPanel = new JPanel();
        
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        
        textAreaName = new JLabel("Jei reikia, įveskite informaciją:");
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy=0;
        mainPanel.add(textAreaName, c);
        
        input = new JTextField();
        input.setSize(250, 20);
        c.weightx = 0.25;
        c.gridx = 1;
        c.gridy=0;
        mainPanel.add(input, c);
        //======MYGTUKAI
        JPanel my = new JPanel(new GridBagLayout());
        btn1 = new JButton("btn1");
        c.gridx = 0;
        c.gridy = 0;
        my.add(btn1,c);        
        btn2 = new JButton("btn2");
        c.gridx = 0;
        c.gridy = 1;
        my.add(btn2,c);         
        btn3= new JButton("btn3");
        c.gridx = 0;
        c.gridy = 2;
        my.add(btn3,c); 
        c.gridx=1;
        c.gridy=1;
        mainPanel.add(my,c);
        
        
        output = new JTextArea(50, 1);
        output.setSize(30,1);
        c.weighty=0.1;
        c.gridx = 0;
        c.gridy = 1;
        //output.setText("dkjv\n\n\n\n\n\n\n\nsjkdskg\najdgajsfd");
        JScrollPane out = new JScrollPane(output);
        out.setMinimumSize(new Dimension(250, 400));
        output.setVisible(true);
        output.setEditable(false);
        mainPanel.add(out,c); 
        
        
        frame.add(mainPanel);
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void generateData(){
               
    }

    public static void main(String[] args) {
        new Window();
    }
}

