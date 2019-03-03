package retoExam.entities;

import java.util.ArrayList;
import java.util.Arrays;
import retoExam.Signup.SignUp;
import static retoExam.files.FileManager.*;
import retoExam.stock.Product;

public class Student extends User{
    
    public final static int STUDENT_FIELD = 0,
            PRICE_FIELD = 1,
            PARENT_FIELD = 3;
    
    private String parent;
    
    public Student(String name){
        super(name);
    }
    
    public Student(String name, String password, String type, String parent){
        super(name, password, type);
        this.parent = parent;
    }
    
    public float getBalance(){
        return getDeposit() - getSpent();
    }
    
    public String getParent(){
        if(parent != null && !parent.isEmpty())
            return parent;
        
        String[] buffer = getLines(SHADOW);
        for(String line: buffer){
            String[] temp = line.split(SEPARATOR);
            if(temp[SignUp.NAME_FIELD].equals(name))
                return temp[PARENT_FIELD];
        }
        
        return "";
    }
    
    private float getSpent(){
        return getSum(IN);
    }
    
    private float getDeposit(){
        return getSum(OUT);
    }
    
    private float getSum(String file){
        String[] buffer = getLines(file);
        float sum = 0;
        
        for(String line: buffer){
            String[] bar = line.split(SEPARATOR);
            System.out.println(Arrays.toString(bar));
            if(bar[STUDENT_FIELD].equals(name))
                sum += Float.parseFloat(bar[PRICE_FIELD]);
        }
        
        return sum;
    }
    
    public ArrayList<String> getHistory(){
        ArrayList<String> history = new ArrayList();
        String[] bufferIn = getLines(IN);
        String[] bufferOut = getLines(OUT);
        
        for(String s: bufferIn){
            if(s.split(SEPARATOR)[0].equals(name))
                history.add(s);
        }
        
        for(String s: bufferOut){
            if(s.split(SEPARATOR)[0].equals(name))
                history.add(s);
        }
        
        return history;
    }
    
    public void updateRestrictions(ArrayList<Product> restrictions){
        String[] buffer = getLines(MENU);
        boolean found = false;
        StringBuilder builder = new StringBuilder();
        for(String s : buffer){
            if(s.split(SEPARATOR)[STUDENT_FIELD].equals(name)){
                found = true;
                builder.append(User.format(name, Product.format(restrictions)))
                        .append("\n");
                continue;
            }
            builder.append(s).append("\n");
        }
        
        builder.deleteCharAt(builder.length() - 1);
        
        if(found) overWrite(MENU, builder.toString());
        else append(MENU, User.format(name, Product.format(restrictions)));
    }
}
