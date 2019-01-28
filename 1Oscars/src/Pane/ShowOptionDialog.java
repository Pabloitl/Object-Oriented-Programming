package Pane;

//Category chosen: Performance by an actor in a leading role

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class ShowOptionDialog {
    
    public static void main(String[] args){
        
        String path = "src/imgs/", type = ".png";
        int option;
        ImageIcon[] buttons = new ImageIcon[4];
        
        String[] movies= {"Vice", "Bohemian", "At_Eternitys_Gate",
                         "A_Star_is_Born"};
        
        for(int i = 0; i < movies.length; i++){
            buttons[i] = new ImageIcon(path + movies[i] + type);
        }
        
        do{
            option = JOptionPane.showOptionDialog(null, null,
                     "Performance by an actor in a leading role",
                     JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                     null, buttons, buttons[0]);
            
            if(option != -1)
                JOptionPane.showMessageDialog(null, "Se seleccionó: " +
                     movies[option], null, JOptionPane.PLAIN_MESSAGE,
                     buttons[option]);
        }while(option != -1);
    }
}
