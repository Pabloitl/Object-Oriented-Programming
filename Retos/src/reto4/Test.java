package reto4;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Test {
    
        static ImageIcon subsetImg = new ImageIcon("src/reto4/imgs/subsets.png"),
                  lockImg = new ImageIcon("src/reto4/imgs/Lock.png");

    public static void main(String[] args) {
        do{}while(Test.run());
    }
    
    private static boolean run(){
        ImageIcon options[] = {subsetImg, lockImg};
        
        //Choose program to run
        int op = JOptionPane.showOptionDialog(null, "Choose the program",
                "Chooser", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        
        if(op < 0) return false;
        else if(op == 0) runSubsets();
        else runPassword();
        
        return true;
    }
    
    private static void runPassword(){
        //Ask for input
        String password = ask("Input a password", lockImg);
        String stepString = ask("Input a step", lockImg);
        
        if(stepString == null) return;
        
        int step = Integer.parseInt(stepString);
        
        //Show hash with static methods
        show(Password.getHash(password, step), lockImg);
        
        //Show hash with dynamic methods
        show(new Password(password, step).getHash(), lockImg);
    }
    
    private static void runSubsets(){
        //Ask for input
        String maxString = ask("Set the max number", subsetImg);
        String rowsString = ask("Set rows you want to see", subsetImg);
        
        if(maxString == null || rowsString == null)  return;
        
        int max = Integer.parseInt(maxString);
        int rows = Integer.parseInt(rowsString);
        
        //Show subsets with static methods
        show(Subset.toJScrollPane(Subset.getSubsets(max), rows), subsetImg);
        
        //Show subsets with dynamic methods
        show(new Subset(max, rows).toJScrollPane(), subsetImg);
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
