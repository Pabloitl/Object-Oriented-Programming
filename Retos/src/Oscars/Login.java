package Oscars;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Login {
    
    String shadow;
    
    public Login(String shadow){
        this.shadow = shadow;
    }
    
    public String prompt(){
        Hash h = new Hash(Hash.DEFAULT_STEP);
        String usr;
        
        while(!loadShadow(usr = askUser()).equals(h.hash(askPass()))){
            JOptionPane.showMessageDialog(null,
                    "Usuario o contraseña incorrecta", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return usr;
    }
    
    private String askUser(){
        String usr = JOptionPane.showInputDialog(null,
                     new ImageIcon("src/Oscars/imgs/User.png"),
                     "Ingrese usuario",
                     JOptionPane.PLAIN_MESSAGE);
        
        if(usr == null || usr.length() == 0) System.exit(0);
        return usr;
    }
    
    private String askPass(){
        JPasswordField passwordInput = new JPasswordField();
        
        JOptionPane.showConfirmDialog(null, passwordInput,
                    "Ingrese contraseña", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon("src/Oscars/imgs/Lock.png"));
        
        String pass = String.valueOf(passwordInput.getPassword());
        
        if(pass == null || pass.length() == 0) System.exit(0);
        return pass;
    }
    
    private String loadShadow(String usr){
        int usrField = 0, passField = 1;
        
        try {
            Scanner file = new Scanner(new FileInputStream(shadow));
            
            while(file.hasNext()){
                String[] match = file.nextLine().split(":");
                if(match[usrField].equals(usr))
                    return match[passField];
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        
        return null;
    }
}
