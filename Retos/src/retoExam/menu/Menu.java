package retoExam.menu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import static retoExam.Screen.Screen.*;
import retoExam.entities.Student;
import retoExam.entities.User;
import static retoExam.files.FileManager.*;
import retoExam.stock.Product;
import retoExam.stock.Stock;

public class Menu {
    
    public static String SEPARATOR_MENU = "-";
    
    public static int STUDENT_FIELD = 0,
            RESTRICTIONS_FIELD = 1;
    
    Student student;
    String[] filteredMenu;
    
    public Menu(Student student){
        this.student = student;
        filteredMenu = filterMenu();
    }
    
    public String getStudent(){
        return student.getName();
    }
    
    public void prompt(){
        ArrayList<Product> bought = menu(this);
        if(!verifySell(bought, student)){
            showMessage("An error ocurred during the transaction");
            return;
        }
        
        bought.stream().map((p) -> {
            registerBuy(p);
            return p;
        }).forEach((p) -> {
            new Stock().updateStock(p, p.getQuantity() - 1);
        });
    }
    
    private String[] filterMenu(){
        ArrayList<String> buffer = new ArrayList();
        
        primary:
        for(String s: getMenu()){
            for(String ss: getRestrictions()){
                if(s.equals(ss)) continue primary;
            }
            buffer.add(s);
        }
        
        String[] toReturn = new String[buffer.size()];
        buffer.toArray(toReturn);
        
        return toReturn;
    }
    
    public String[] getMenu(){
        return new Stock().getMenu();
    }
    
    public String getCompleteMenu(int i){
        return new Stock().getMenu()[i];
    }
    
    public String[] getRestrictions(){
        String[] buffer = getLines(MENU);
        
        for(String line: buffer){
            
            String[] foo = line.split(SEPARATOR);
            
            if(foo[STUDENT_FIELD].equals(student.getName()) && foo.length >= RESTRICTIONS_FIELD + 1)
                return foo[RESTRICTIONS_FIELD].split(SEPARATOR_MENU);
        }
        
        return new String[0];
    }
    
    private void registerBuy(Product p){
        String toAppend = User.format(student.getName(), String.valueOf(p.getPrice()),
                new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()),
                p.getName());
        
        append(IN, toAppend);
    }
    
    public String getMenu(int i){
        return filteredMenu[i];
    }
    
    public int getMenuLength(){
        return filteredMenu.length;
    }

    private boolean verifySell(ArrayList<Product> products, Student student) {
        float sum = 0;
        
        for(Product p : products){
            if(!(p.getQuantity() > 0)) return false;
            sum += p.getPrice();
        }
        return student.getBalance() > sum;
    }
}
