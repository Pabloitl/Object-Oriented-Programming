package reto2;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Vote {
    
    //Fields in File
    final static public int NOMINEE = 0,
            IMAGE = 1,
            SINOPSIS = 2,
            VOTES = 3;
    
    final static public String SEP = ":",
            FORMAT_IMG = ".png",
            FORMAT_TEXT = ".txt",
            PATH_IMG = "src/reto2/imgs/",
            PATH_FILES = "src/reto2/files/";
            
    private ArrayList<String> data;
    private String category;
    private ArrayList<ImageIcon> buttons;
    
    public Vote(String category){
        //Initialize variables
        buttons  = new ArrayList();
        data = new ArrayList();
        this.category = category;
        loadCategory(buttons);
    }
    
    public int prompt(){
        //Prepare label
        JLabel msg = new JLabel("Escoge tu preferido");
        msg.setForeground(Color.MAGENTA);
        msg.setFont(new Font("Monospace", Font.ITALIC, 20));
        
        //Convert ArrayList to an array
        ImageIcon[] options = new ImageIcon[buttons.size()];
        buttons.toArray(options);
        
        //Show Option Dialog
        return JOptionPane.showOptionDialog(null, msg, category,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                options, options[0]);
    }
    
    private void loadCategory(ArrayList<ImageIcon> buttons){
        String path = PATH_FILES + category + FORMAT_TEXT;
        
        try{
            Scanner file = new Scanner(new FileInputStream(path));
            
            while(file.hasNext()){
                //Add lines to ArrayList
                this.data.add(file.nextLine());
                
                //Split with sep the las element added in ArrayList
                String[] data = this.data.get(this.data.size() - 1).split(SEP);
                //Create button (ImageIcon)
                buttons.add(new ImageIcon(PATH_IMG +
                        data[IMAGE].replace(" ", "-") + FORMAT_IMG));
            }
        }catch(FileNotFoundException ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public void showInfo(int info){
        if(info < 0) return;
        
        String show = data.get(info).split(SEP)[IMAGE];
        showInfo(show);
    }
    
    private void showInfo(String info){
        //Verify if alternative img exists
        boolean alt = new File(PATH_IMG + info.replace(" ", "-") +
                "-Alt"+ FORMAT_IMG).exists();
        String alternative = (alt)?"-Alt":"";
        
        ImageIcon img = new ImageIcon(PATH_IMG + info.replace(" ", "-")
                + alternative + FORMAT_IMG);
        System.out.println(info.replace(" ", "-") + alternative + FORMAT_IMG);
        JLabel msg = new JLabel();
        String sinopsis = null;
        String vote = null;
        
        //Find sinopsis
        for(String s: data)
            if(s.split(SEP)[IMAGE].equals(info)){
                String[] ss = s.split(SEP);
                
                sinopsis = ss[SINOPSIS];
                vote = ss[NOMINEE];
            }
                
        //Show sinopsis
        msg.setText(vote + ": " + sinopsis);
        msg.setFont(new Font("Monospace", Font.BOLD, 12));
        JOptionPane.showMessageDialog(null, msg, "Informacion",
                JOptionPane.PLAIN_MESSAGE, img);
    }
}
