/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

/**
 *
 * @author dell
 */
public class Process implements Comparable{

    public Process(String name, double arrivalTime, double burstTime, int priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }
    public Process(String name, double arrivalTime, double burstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        
    }
    public Process() {
        
        
    }
     @Override
    public int compareTo(Object comparestu) {
        double compareage=((Process)comparestu).arrivalTime;
        /* For Ascending order*/
        return (int)(this.arrivalTime-compareage);

        /* For Descending order do like this */
       // return (int)(compareage-this.arrivalTime);
    }
    
    String  name;
    double arrivalTime;
    double burstTime;
    int priority;

   
}
