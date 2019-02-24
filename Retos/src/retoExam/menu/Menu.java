package retoExam.menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import retoExam.entities.Student;

public class Menu {
    JFrame window;
    JPanel panel;
    JRadioButton[] menu;
    
    public Menu(){
        window = new JFrame();
        panel = new JPanel();
        
        for(JRadioButton radio: menu)
            radio = new JRadioButton();
    }
    
    public void prompt(Student user){
        //user.getMenu();
        
        
    }
}
