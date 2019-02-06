package reto3.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Worker {
    private String name;
    private float income;
    private Date birthday;
    private Date work;
    private char sex;
    
    public Worker(String name, float income, char sex){
        this.name = name;
        this.income = income;
        this.sex = sex;
    }
    
    public Worker(String name, float income, Date birthday, Date work, char sex){
        this.name = name;
        this.income = income;
        this.birthday = birthday;
        this.work = work;
        this.sex = sex;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setIncome(float income){
        if(income < 0) return;
        this.income = income;
    }
    
    public float getIncome(){
        return income;
    }
    
    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }
    
    public Date getBirthday(){
        return birthday;
    }
    
    public void setWork(Date work){
        this.work = work;
    }
    
    public Date getWork(){
        return work;
    }
    
    public void setSex(char sex){
        if(sex != 'F' || sex != 'M') return;
        this.sex = sex;
    }
    
    public char getSex(){
        return sex;
    }
    
    public static ArrayList<Worker> loadWorkers(String src){
        ArrayList<Worker> workers= new ArrayList();
        String separation = ":";
        try{
            Scanner in = new Scanner(new FileInputStream(src));
            while(in.hasNext()){
                String[] data = in.nextLine().split(separation);
                
                Worker tempWorker = new Worker(data[0], Float.valueOf(data[1]),
                        new Date(data[2]), new Date(data[3]),
                        data[4].charAt(0));
                
                workers.add(tempWorker);
            }
        }catch(FileNotFoundException e){
            e.getMessage();
        }
        return workers;
    }
}
