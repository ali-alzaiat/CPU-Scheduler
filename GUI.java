/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.event.*;
/**
 *
 * @author omara
 */
public class GUI{

    /**
     * @param args the command line arguments
     */
    static Vector<rect> r = new Vector<rect>();
    public static void main(String[] args) {
        // TODO code application logic here 
        JFrame f = new JFrame("CPU");
        f.setSize(1000,1000);
        JLabel arrival = new JLabel("Arrival time");
        JLabel burst = new JLabel("Burst time");
        JLabel q = new JLabel("quantam");
        JLabel prio = new JLabel("priority");
        JTextArea arrivalArea=new JTextArea();
        JTextArea burstArea=new JTextArea();
        JTextArea qArea=new JTextArea();
        JTextArea prioArea=new JTextArea();
        arrival.setBounds(170,50,100,20);
        burst.setBounds(170,80,100,20);
        arrivalArea.setBounds(250,50,100,20);
        burstArea.setBounds(250,80,100,20);
        prio.setBounds(170,110,100,20);
        q.setBounds(170,110,100,20);       
        qArea.setBounds(250,110,100,20);
        prioArea.setBounds(250,110,100,20);
        String list[]={"Round Robin","pirority(preemtive)","pirority(nonpreemtive)","SJF(preemtive)","SJF(nonpreemtive)","FCFS"};
        JList algorithms = new JList(list);
        algorithms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        algorithms.setBounds(20, 20, 110, 150);
        algorithms.addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                JList list1 = (JList) e.getSource();
                String s = list1.getSelectedValue().toString();
                if(s.equals("Round Robin")){
                    prio.setVisible(false);
                    prioArea.setVisible(false);
                    q.setVisible(true);
                    qArea.setVisible(true);
                    q.repaint();
                    qArea.repaint();
                    prio.repaint();
                    prioArea.repaint();
                    f.repaint();
                }
                else if(s.equals("SJF(preemtive)") || s.equals("SJF(nonpreemtive)")){
                    q.setVisible(false);
                    qArea.setVisible(false);
                    prio.setVisible(false);
                    prioArea.setVisible(false);
                    q.repaint();
                    qArea.repaint();
                    prio.repaint();
                    prioArea.repaint();
                    f.repaint();
                }
                else if(s.equals("pirority(preemtive)") || s.equals("pirority(nonpreemtive)")){
                    prio.setVisible(true);
                    prioArea.setVisible(true);
                    q.setVisible(false);
                    qArea.setVisible(false);
                    q.repaint();
                    qArea.repaint();
                    prio.repaint();
                    prioArea.repaint();
                    f.repaint();
                }
                else if(s.equals("FCFS") ){
                    q.setVisible(false);
                    qArea.setVisible(false);
                    prio.setVisible(false);
                    prioArea.setVisible(false);
                    q.repaint();
                    qArea.repaint();
                    prio.repaint();
                    prioArea.repaint();
                    f.repaint();
                }
            }
        });
        JButton b = new JButton("calculate");
        JButton add = new JButton("add process");
        JButton restart = new JButton("restart");
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.repaint();
            }
        });
        restart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                for(int i =0; i<r.size(); i++){
                    f.remove(r.get(i));
                }
                f.repaint();
            }
        });
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String b = burstArea.getText();
                String a = arrivalArea.getText();
                int x = Integer.parseInt(b);
                int y = Integer.parseInt(a);
                rect m=new rect(10*y,600,10*x,20,1);
                r.add(m);
                f.add(m);
                m.setVisible(false);
                m.setVisible(true);
                f.repaint();
                
            }
        });  
        b.setBounds(50,200,90,20);
        add.setBounds(150,200,130,20);
        restart.setBounds(300,200,90,20);
        q.setVisible(false);
        qArea.setVisible(false);
        prio.setVisible(false);
        prioArea.setVisible(false);
        f.add(prio);
        f.add(q);
        f.add(algorithms);
        f.add(b);
        f.add(add);
        f.add(qArea);
        f.add(prioArea); 
        f.add(restart);       
        f.add(burstArea);
        f.add(arrivalArea); 
        f.add(burst);
        f.add(arrival);  
        f.add(new JLabel());
//        f.setLayout(null);
        f.setVisible(true);       
    }
    
}

