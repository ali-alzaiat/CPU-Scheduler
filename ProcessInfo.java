/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author medo
 */
public class ProcessInfo {
    private String process_name;
    private int start_time;
    private int end_time;
    private int burst_time;
    private int arrival_time;
    
    public ProcessInfo(String name, int start, int end, int burst, int arrival){
        process_name = name;
        start_time = start;
        end_time = end;
        burst_time = burst;
        arrival_time = arrival;
    }

    public String getProcess_name() {
        return process_name;
    }

    public int getStart_time() {
        return start_time;
    }

    public int getEnd_time() {
        return end_time;
    }
    
    public int getBurst_time() {
        return burst_time;
    }
    
    public int getArrival_time() {
        return arrival_time;
    }

    public void setBurst_time(int burst_time) {
        this.burst_time = burst_time;
    }
    
    
}
