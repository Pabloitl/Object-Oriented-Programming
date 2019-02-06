package reto2.Oscars;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CategoryChooser {
    
    final public static String DEFAULT_SOURCE = "src/reto2/Oscars/files/categories.txt",
            DEFAULT_PATH = "src/reto2/Oscars/files/",
            DEFUALT_FILE = "categories.txt";
    
    ArrayList<String> categories = new ArrayList();
    
    public CategoryChooser(String source){
        loadCategories(source);
    }
    
    public String prompt(){
        String nomineesImage = "src/reto2/Oscars/imgs/Nominees.png";
        
        Object o =  JOptionPane.showInputDialog(null, "Elige una categoria:",
                    "Oscars",
                    JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(nomineesImage),
                    ListToArray(categories),
                    categories.get(0));
        
        if(o == null) System.exit(0);
        return o.toString();
    }
    
    private void loadCategories(String source){
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
