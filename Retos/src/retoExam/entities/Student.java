package retoExam.entities;

import java.util.Arrays;
import retoExam.files.FileManager;

public class Student extends User{
    
    public final static int STUDENT_FIELD = 0,
            PRICE_FIELD = 1;
    
    public Student(String name){
        super(name);
    }
    
    public float getBalance(){
        return getDeposit() - getSpent();
    }
    
    private float getSpent(){
        return getSum(FileManager.IN);
    }
    
    private float getDeposit(){
        return getSum(FileManager.OUT);
    }
    
    private float getSum(String file){
        String[] buffer = FileManager.getLines(file);
        float sum = 0;
        
        for(String line: buffer){
            String[] bar = line.split(FileManager.SEPARATOR);
            System.out.println(Arrays.toString(bar));
            if(bar[STUDENT_FIELD].equals(name))
                sum += Float.parseFloat(bar[PRICE_FIELD]);
        }
        
        return sum;
    }
}
