package retoExam.menu;

import java.util.ArrayList;
import retoExam.Screen.Screen;
import retoExam.entities.Student;
import retoExam.entities.User;
import retoExam.files.FileManager;
import retoExam.stock.Product;
import retoExam.stock.Stock;

public class Menu {
    
    public static String SEPARATOR = "-";
    
    public static int STUDENT_FIELD = 0,
            RESTRICTIONS_FIELD = 1;
    
    Student student;
    String[] filteredMenu;
    
    public Menu(Student student){
        this.student = student;
        filteredMenu = filterMenu();
    }
    
    public void prompt(){
        ArrayList<Product> bought = Screen.menu(this);
        
        for(Product p: bought)
            registerBuy(p);
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
    
    private String[] getMenu(){
        return new Stock().getMenu();
    }
    
    private String[] getRestrictions(){
        String[] buffer = FileManager.getLines(FileManager.MENU);
        
        for(String line: buffer){
            
            String[] foo = line.split(FileManager.SEPARATOR);
            if(foo[STUDENT_FIELD].equals(student.getName()))
                return foo[RESTRICTIONS_FIELD].split(SEPARATOR);
        }
        
        return null;
    }
    
    private void registerBuy(Product p){
        String toAppend = User.format(student.getName(), String.valueOf(p.getPrice()));
        
        FileManager.append(FileManager.IN, toAppend);
    }
    
    public String getMenu(int i){
        return filteredMenu[i];
    }
    
    public int getMenuLength(){
        return filteredMenu.length;
    }
}
