/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author medo
 */
public class Process {
    private String name;
    private static int name_counter;
    private int arrival_time;
    private int burst_time;
    private int priority;

    
    public Process(int arrTime, int burTime){
        name_counter++;
        name = "P" + name_counter;
        arrival_time = arrTime;
        burst_time = burTime;
        priority = 0;
        
    }
    public Process(int arrTime, int burTime, int prio){
        name_counter++;
        name = "P" + name_counter;
        arrival_time = arrTime;
        burst_time = burTime;
        priority = prio;
    }

    public  String getName() {
        return name;
    }
    
    public int getArrival_time() {
        return arrival_time;
    }
    
    public int getBurst_time() {
        return burst_time;
    }
    
    public int getPriority() {
        return priority;
    }

    public void setBurst_time(int burst_time) {
        this.burst_time = burst_time;
    }

    public static void setName_counter(int name_counter) {
        Process.name_counter = name_counter;
    }    
}
