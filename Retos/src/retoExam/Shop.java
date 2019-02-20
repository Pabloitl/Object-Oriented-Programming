package retoExam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Shop {
    
    final static String SHADOW_FILE = "shadow",
            TYPE_TEXT = ".txt",
            PATH_FILES = "src/retoExam/files/",
            SEP = ":";
    
    final static int TYPE_FIELD = 0,
            USER_FIELD = 1,
            PASSWORD_FIELD = 2;
    
    JFrame window;
    JPanel panel;
    
    
    public Shop(){
        window = new JFrame();
        panel = new JPanel();
    }
    
    public String[] answer(){
        //Returns user
        String[] options = {"Student","Parent"};
        int type; String username, password;
        
        //Ask for user and  type
        do{
        type = JOptionPane.showOptionDialog(null, "Choose type", "Login",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null,
                options, options[0]);
        
        username = JOptionPane.showInputDialog(null,
                "Input your username", "Login", JOptionPane.PLAIN_MESSAGE);
        
        }while(!userExists(options[type], username));
        
        do{
            password = JOptionPane.showInputDialog(null, "Input your password",
                    "Login", JOptionPane.PLAIN_MESSAGE);
        }while(!isPassword(options[type], username, password));
        
        
        String[] pair = {options[type], username};
        return pair;
    }
    
    private boolean isPassword(String type, String username, String password){
        
        password = reto4.Password.getHash(password);
        
        if(password.equals(getPassword(type, username))) return true;
        return false;
    }
    
    private String getPassword(String type, String username){
        try {
            Scanner io = new Scanner(new FileInputStream(PATH_FILES + SHADOW_FILE + TYPE_TEXT));
            
            while(io.hasNext()){
                String[] temp= io.next().split(SEP);
                
                if(temp[TYPE_FIELD].equals(type) && temp[USER_FIELD].equals(username)) return temp[PASSWORD_FIELD];
            }
        } catch (FileNotFoundException ex) {
            ex.getStackTrace();
        }
        
        return "";
    }
    
    private boolean userExists(String type, String username){
        try {
            Scanner io = new Scanner(new FileInputStream(PATH_FILES + SHADOW_FILE + TYPE_TEXT));
            
            while(io.hasNext()){
                String[] temp= io.next().split(SEP);
                
                if(temp[TYPE_FIELD].equals(type) && temp[USER_FIELD].equals(username)) return true;
            }
        } catch (FileNotFoundException ex) {
            ex.getStackTrace();
        }
        
        return false;
    }
}
