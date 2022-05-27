/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.util.Comparator;

/**
 *
 * @author medo
 */
public class CompareBurTime implements Comparator<Process> {
    @Override
        public int compare(Process p1, Process p2){
        if(p1.getBurst_time() < p2.getBurst_time()) return -1;
        if(p1.getBurst_time() > p2.getBurst_time()) return 1;
        else return 0;
    }
}
