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
public class rect extends JPanel{
    int x;
      int y;
      int w;
      int l;
      int i;
      
      rect(int x, int y, int w, int l, int i){
          this.x = x;
          this.y = y;
          this.w = w;
          this.l = l;
          this.i = i;
      }
      @Override
    public void paint(Graphics g) { 
        setForeground(Color.RED);
        setBackground(Color.WHITE);
        g.setColor(Color.black); 
        g.drawString("P"+i,x+(w/2),y+(l/2));   
        g.drawRect(x, y,w, l);   
          
    } 
}
