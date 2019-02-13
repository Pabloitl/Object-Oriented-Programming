package reto4;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Test {

    public static void main(String[] args) {
        Test.run();
    }
    
    private static void run(){
        ImageIcon lockimg = new ImageIcon("src/reto4/imgs/Lock.png");
        
        //Ask for input
        String password = ask("Input a password", lockimg);
        int step = Integer.parseInt(ask("Input a step", lockimg));
        
        //Show hash with static methods
        show(Password.getHash(password, step), lockimg);
        
        //Show hash with dynamic methods
        show(new Password(password, step).getHash(), lockimg);
    }
    
    private static String ask(String msg){
        return ask(msg, null);
    }
    
    private static String ask(String msg, ImageIcon icon){
        return  JOptionPane.showInputDialog(
                null,
                icon,
                msg,
                JOptionPane.PLAIN_MESSAGE);
    }
    
    private static void show(Object msg){
        show(msg, null);
    }
    
    private static void show(Object msg, ImageIcon icon){
        JOptionPane.showMessageDialog(
                null,
                msg,
                "Output",
                JOptionPane.PLAIN_MESSAGE,
                icon);
    }
}
