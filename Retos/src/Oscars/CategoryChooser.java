package Oscars;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CategoryChooser {
    
    ArrayList<String> categories = new ArrayList();
    
    public CategoryChooser(){
        loadCategories("src/Oscars/files/categories.txt");
    }
    
    public String prompt(){
        String[] options = new String[categories.size()];
        categories.toArray(options);
        Object o = JOptionPane.showInputDialog(null, "Elige una categoria:", "Oscars",
                    JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon("src/Oscars/imgs/Nominees.png"), options,
                    options[0]);
        
        if(o == null) return null;
        return o.toString();
    }
    
    private void loadCategories(String src){
        try{
            Scanner file = new Scanner(new FileInputStream(src));
            while(file.hasNext()){
                categories.add(file.nextLine());
            }
        }catch(FileNotFoundException ex){
            ex.getMessage();
        }
    }
}
