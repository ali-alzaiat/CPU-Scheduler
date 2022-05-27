/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author medo
 */
public class Algo {
    
    public static ArrayList<ProcessInfo> fcfs(Queue<Process> q){
        ArrayList<ProcessInfo> info = new ArrayList<ProcessInfo>();
        int start_time = 0;
        int current_time = 0;
        int size = q.size();
        for(int i = 0; i < size; i++){
            if(q.peek().getArrival_time() > current_time){
                start_time = q.peek().getArrival_time();
                current_time = start_time + q.peek().getBurst_time();
            }
            else{
                start_time = current_time;
                current_time+= q.peek().getBurst_time();                
            }
            //System.out.println(q.peek().getName() + " " + start_time + " " + current_time );
            info.add(new ProcessInfo(q.peek().getName(),start_time,current_time,q.peek().getBurst_time(),q.peek().getArrival_time()));
            q.remove();
        }
        return info;
    }
    
//    public static void round_robin_test(Queue<Process> q , int quantum){
//        int start_time = 0;
//        int current_time = 0;
//        boolean modified = false;
//        String saved_name = "";
//        boolean saved = false;
//        while(!q.isEmpty()){
//            if(q.peek().getArrival_time() > current_time){
//                String current_name = q.peek().getName();
//                if(saved == false){
//                    saved_name = q.peek().getName();
//                    saved = true;
//                }
//                if(modified == false && saved_name == q.peek().getName()){
//                    current_time = q.peek().getArrival_time();
//                    saved = false;
//                    continue;
//                }
//                else{
//                    q.add(q.poll());
//                }
//                if(current_name == saved_name){
//                    modified = false;
//                }
//            }
//            else{
//                modified = true;
//                int remainder_burst_time = q.peek().getBurst_time() - quantum;
//                if(remainder_burst_time >= 0){
//                    start_time = current_time;
//                    current_time += quantum;
//                    System.out.println(q.peek().getName() + " " + start_time + " " + current_time);
//                    if(remainder_burst_time != 0){
//                        q.peek().setBurst_time(remainder_burst_time);
//                        q.add(q.poll());            
//                    }
//                    else{
//                        q.remove();
//                    }  
//                }
//                else{
//                    start_time = current_time;
//                    current_time += q.peek().getBurst_time();
//                    System.out.println(q.peek().getName() + " " + start_time + " " + current_time);
//                    q.remove();
//                }
//            }
//        }
//    }
    
   public static ArrayList<ProcessInfo> priority_non_pre(Queue<Process> q){
        ArrayList<ProcessInfo> info = new ArrayList<ProcessInfo>();
        int start_time = 0;
        int current_time = q.peek().getArrival_time();
        Queue<Process> q2 = new PriorityQueue<Process>(new ComparePriority());
        while(!q.isEmpty() || !q2.isEmpty()){
            while(!q.isEmpty() && q.peek().getArrival_time() <= current_time ){
               q2.add(q.poll());
            }
            if(!q2.isEmpty()){
                start_time = current_time;
                current_time+= q2.peek().getBurst_time();                
                //System.out.println(q2.peek().getName() + " " + start_time + " " + current_time );
                info.add(new ProcessInfo(q2.peek().getName(),start_time,current_time,q2.peek().getBurst_time(),q2.peek().getArrival_time()));      
                q2.remove(); 
                if(q2.isEmpty() && !q.isEmpty()){
                    if(current_time < q.peek().getArrival_time()){
                        current_time = q.peek().getArrival_time();
                    }
                }
                
            }
        } 
        return info;
    }
   
    public static ArrayList<ProcessInfo> priority_pre(Queue<Process> q){
        ArrayList<ProcessInfo> info = new ArrayList<ProcessInfo>();        
        int size = q.size();        
        int[] time_stamps = new int[size];
        int[] priorities = new int[size];
        int i = 0;
        int j = 0;
        boolean interrupt = false;
        for(Process process : q){
            time_stamps[i] = process.getArrival_time();
            priorities[i] = process.getPriority();            
            i++;
        }
        int start_time = 0;
        int current_time = time_stamps[0];
       
        Queue<Process> q2 = new PriorityQueue<Process>(new ComparePriority());
        while(!q.isEmpty() || !q2.isEmpty()){
            while(!q.isEmpty() && q.peek().getArrival_time() <= current_time ){
                q2.add(q.poll());
                j++;
            }   
            if(!q2.isEmpty()){
                start_time = current_time;
                if(j < size){
                    int k = j;

                    while(k < size && current_time + q2.peek().getBurst_time() >= time_stamps[k]){
                        if(priorities[k] < q2.peek().getPriority()){
                            interrupt = true;
                            break;
                        }
                        k++;
      
                    }
                    if(interrupt == false){
                        current_time+= q2.peek().getBurst_time();
                        //System.out.println(q2.peek().getName() + " " + start_time + " " + current_time );
                        info.add(new ProcessInfo(q2.peek().getName(),start_time,current_time,q2.peek().getBurst_time(),q2.peek().getArrival_time()));                                
                        q2.remove();
                    }

                    else{
                        current_time = time_stamps[k];                    
                        q2.peek().setBurst_time(q2.peek().getBurst_time() - (current_time - start_time));
                        //System.out.println(q2.peek().getName() + " " + start_time + " " + current_time );
                        info.add(new ProcessInfo(q2.peek().getName(),start_time,current_time,q2.peek().getBurst_time(),q2.peek().getArrival_time()));                                
                        if(q2.peek().getBurst_time() == 0){
                            q2.remove();
                        }
                    }
                }
                else{
                    current_time += q2.peek().getBurst_time();
                    //System.out.println(q2.poll().getName() + " " + start_time + " " + current_time );
                    info.add(new ProcessInfo(q2.peek().getName(),start_time,current_time,q2.peek().getBurst_time(),q2.peek().getArrival_time()));   
                    q2.remove();
                }
                if(q2.isEmpty() && !q.isEmpty()){
                    if(current_time < q.peek().getArrival_time()){
                        current_time = q.peek().getArrival_time();
                    }
                }  
                interrupt = false;
            }
        }
        return info;
    }
   
    public static ArrayList<ProcessInfo> sjf_non_pre(Queue<Process> q){
        ArrayList<ProcessInfo> info = new ArrayList<ProcessInfo>();        
        int start_time = 0;
        int current_time = q.peek().getArrival_time();
        Queue<Process> q2 = new PriorityQueue<Process>(new CompareBurTime());
        while(!q.isEmpty() || !q2.isEmpty()){
           while(!q.isEmpty() && q.peek().getArrival_time() <= current_time ){
               q2.add(q.poll());
           }
           if(!q2.isEmpty()){
                start_time = current_time;
                current_time+= q2.peek().getBurst_time();                
                //System.out.println(q2.peek().getName() + " " + start_time + " " + current_time );
                info.add(new ProcessInfo(q2.peek().getName(),start_time,current_time,q2.peek().getBurst_time(),q2.peek().getArrival_time()));                
                q2.remove(); 
                if(q2.isEmpty() && !q.isEmpty() && current_time < q.peek().getArrival_time()){
                    current_time = q.peek().getArrival_time();
                }
           }
        }
        return info;
    }

    public static ArrayList<ProcessInfo> sjf_pre(Queue<Process> q){
        ArrayList<ProcessInfo> info = new ArrayList<ProcessInfo>();                
        int size = q.size();        
        int[] time_stamps = new int[size];
        int i = 0;
        int j = 0;       
        for(Process process : q){
            time_stamps[i] = process.getArrival_time();
            i++;
        }
        int start_time = 0;
        int current_time = time_stamps[0];
       
        Queue<Process> q2 = new PriorityQueue<Process>(new CompareBurTime());
        while(!q.isEmpty() || !q2.isEmpty()){
            while(!q.isEmpty() && q.peek().getArrival_time() <= current_time ){
                q2.add(q.poll());
                j++;
            }   
            if(!q2.isEmpty()){
                start_time = current_time;
                if(j < size){
                    if(time_stamps[j] > current_time + q2.peek().getBurst_time() || q.peek().getBurst_time() > q2.peek().getBurst_time()){
                        current_time+= q2.peek().getBurst_time();
                        //System.out.println(q2.peek().getName() + " " + start_time + " " + current_time );
                        info.add(new ProcessInfo(q2.peek().getName(),start_time,current_time,q2.peek().getBurst_time(),q2.peek().getArrival_time()));                                
                        q2.remove();
                    }

                    else{
                        current_time = time_stamps[j];                    
                        q2.peek().setBurst_time(q2.peek().getBurst_time() - (current_time - start_time));
                        //System.out.println(q2.peek().getName() + " " + start_time + " " + current_time );
                        info.add(new ProcessInfo(q2.peek().getName(),start_time,current_time,q2.peek().getBurst_time(),q2.peek().getArrival_time()));                                
                        if(q2.peek().getBurst_time() == 0){
                            q2.remove();
                        }
                    }
                }
                else{
                    current_time += q2.peek().getBurst_time();
                    //System.out.println(q2.poll().getName() + " " + start_time + " " + current_time );
                    info.add(new ProcessInfo(q2.peek().getName(),start_time,current_time,q2.peek().getBurst_time(),q2.peek().getArrival_time()));        
                    q2.remove();
                }
                if(q2.isEmpty() && !q.isEmpty()){
                    if(current_time < q.peek().getArrival_time()){
                        current_time = q.peek().getArrival_time();
                    }
                }                
            }
        } 
        return info;
    }
    
    public static ArrayList<ProcessInfo> round_robin(Queue<Process> q , int quantum){
        ArrayList<ProcessInfo> info = new ArrayList<ProcessInfo>();        
        int start_time = 0;
        int current_time = q.peek().getArrival_time();
        Queue<Process> q2 = new LinkedList<Process>();

        while(!q.isEmpty() || !q2.isEmpty()){
            while(!q.isEmpty() && q.peek().getArrival_time() <= current_time ){
               q2.add(q.poll());
            }
            if(!q2.isEmpty()){
                start_time = current_time;
                if(q2.peek().getBurst_time() - quantum > 0){
                    current_time+= quantum;                
                    //System.out.println(q2.peek().getName() + " " + start_time + " " + current_time );
                    info.add(new ProcessInfo(q2.peek().getName(),start_time,current_time,q2.peek().getBurst_time(),q2.peek().getArrival_time()));                                    
                    q2.peek().setBurst_time(q2.peek().getBurst_time() - quantum);
                    while(!q.isEmpty() && q.peek().getArrival_time() <= current_time ){
                        q2.add(q.poll());
                    }
                    q2.add(q2.poll());
                }
                else{
                    current_time+= q2.peek().getBurst_time();                                
                    //System.out.println(q2.peek().getName() + " " + start_time + " " + current_time );
                    info.add(new ProcessInfo(q2.peek().getName(),start_time,current_time,q2.peek().getBurst_time(),q2.peek().getArrival_time()));                                
                    q2.remove();
                    
                }
                if(q2.isEmpty() && !q.isEmpty()){
                    if(current_time < q.peek().getArrival_time()){
                        current_time = q.peek().getArrival_time();
                    }
                }
                
            }
        }          
        return info;
    }
}
