/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author dell
 */


public class JavaApplication5 {

    /**
     * @param args the command line arguments
     */
    static Scanner sc = new Scanner(System.in);
    
        public static void main(String[] args) {
        
    System.out.print("Enter number of processes: ");
    int numberOfProcesses  = sc.nextInt();
    ArrayList<Process> arr = new ArrayList<Process>();
        for (int i = 0; i < numberOfProcesses; i++) {
            Process x = new Process();
            System.out.println("for the "+ i+1 +"st process enter the process name,arrival time,burst time and priority accordingly");
            x.name=sc.next();
            x.arrivalTime=sc.nextDouble();
            x.burstTime=sc.nextDouble();
            x.priority=sc.nextInt();
            arr.add(x);
        }
        priorityNonPreemptive(arr);
        
    
    }
    
    static public void priorityNonPreemptive(ArrayList<Process> arr){
        
        int size = arr.size();
        //calculate total time
        int total = 0;
        for (int i = 0; i < size; i++) {
            total+=arr.get(i).burstTime;
        }
        
        
        
        Collections.sort(arr);
        int time = 0;
        if (size == 1){
            time = 0;
        while(arr.get(0).burstTime>0){
            System.out.print(arr.get(0).name+" ");
                  time++;
                  
                  arr.get(0).burstTime--;
        }
        
        }
        else if (size >= 2){
            time = 0;
            for (int i = 0; i < total; i++) {
                int k = 0 ; //number of process arrived
                for (int m = 0; m < arr.size(); m++) {
                    if (arr.get(m).arrivalTime<=time)
                        k++;
                }
                 int maxPriority = -1;
                for (int j = 0; j < k; j++) {
                    
                    if (arr.get(j).priority>maxPriority &&arr.get(j).burstTime>0){
                    maxPriority=j; //the choosen
                    }
                }
                if (maxPriority!=-1);{
                  System.out.print(arr.get(maxPriority).name+" ");
                  time++;
                  
                  arr.get(maxPriority).burstTime--;
                  if (arr.get(maxPriority).burstTime == 0){
                  arr.get(maxPriority).departureTime = time;
                  }
            }
                
            }
            //printing departure time versus name
            for (int i = 0; i < arr.size(); i++) {
                System.out.println("");
                System.out.println(arr.get(i).name+" "+arr.get(i).departureTime+"  ");
            }
        }
    
    }
    

    
}