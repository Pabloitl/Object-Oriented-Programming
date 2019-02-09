package reto2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CategoryChooser {
    
    final public static String CATEGORIES_FILE = "categories.txt",
            CATEGORIES_IMG = "Chooser.png";
    
    ArrayList<String> categories = new ArrayList();
    
    public CategoryChooser(String source){
        loadCategories(source);
    }
    
    public String prompt(){
        String img = reto2.Vote.PATH_IMG + CATEGORIES_IMG;
        
        Object o =  JOptionPane.showInputDialog(null, "Elige una categoria:",
                    "Selección de categoría",
                    JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(img),
                    ListToArray(categories),
                    categories.get(0));
        
        if(o == null) System.exit(0);
        return o.toString();
    }
    
    private void loadCategories(String source){
        source = Vote.PATH_FILES + source;
        try{
            Scanner file = new Scanner(new FileInputStream(source));
            while(file.hasNext()){
                categories.add(file.nextLine());
            }
        }catch(FileNotFoundException ex){
            ex.getMessage();
        }
    }
    
    private String[] ListToArray(ArrayList<String> list){
        String[] array = new String[list.size()];
        list.toArray(array);
        
        return array;
    }
}