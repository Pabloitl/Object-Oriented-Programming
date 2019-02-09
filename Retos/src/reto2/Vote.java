package reto2;

import java.awt.Color;
import java.awt.Font;
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
            
    ArrayList<String> data;
    String category;
    ArrayList<ImageIcon> buttons;
    
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
        ImageIcon img = new ImageIcon(PATH_IMG + info.replace(" ", "-")
                + FORMAT_IMG);
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
    
//    public boolean process(int option, String user){
//        //Validate a valid entry
//        if(option < buttons.size() && option >= 0){
//            //Count vote
//            return countVote(data.get(option).split(SEP)[IMAGE], user);
//        }
//        return false;
//    }
//    
//    private boolean countVote(String info, String user){
//        boolean voted = false;
//        //Prepare Label
//        JLabel msg = new JLabel("Ya tienes registrado un voto");
//        msg.setFont(new Font("Monospace", Font.ITALIC, 20));
//        
//        //Verify if the user has already voted
//        for(String s:data) if(s.split(SEP)[VOTES].contains(user)) voted = true;
//        
//        if(voted){
//            //Prompt vote already done
//            JOptionPane.showMessageDialog(null, msg, "Voto",
//                        JOptionPane.INFORMATION_MESSAGE);
//        }else{
//            //Register Vote
//            registerVote(info, user);
//        }
//        
//        //Show sinopsis
//        showInfo(info);
//        return false;
//    }
//    
//    private void registerVote(String info, String user){
//        JLabel msg = new JLabel("Ya tienes registrado un voto");
//        msg.setFont(new Font("Monospace", Font.ITALIC, 20));
//        
//        //Search in every line of the file for a match of the vote
//        for(int i = 0; i < data.size(); i++){
//            //Prepare variables
//            String s = data.get(i);
//            String[] ss = s.split(SEP);
//            
//            //Find line with movie and without vote
//            if(ss[IMAGE].contains(info) && !ss[VOTES].contains(user)){
//                //Replace line with line with vote
//                data.add(i, s + user);
//                data.remove(i + 1);
//                //Prompt vote to user
//                msg.setText("Voto registrado por: " + info);
//                JOptionPane.showMessageDialog(null, msg, "Voto",
//                        JOptionPane.INFORMATION_MESSAGE);
//                //Write vote to file
//                updateCategory();
//                return;
//            }
//        }
//    }
//    
//    private void updateCategory(){
//        try {
//            //Open file to write
//            FileWriter fw = new FileWriter(PATH_FILES + category + FORMAT_TEXT);
//            //Write all lines in data ArrayList
//            for(int i = 0; i < data.size(); i++)fw.write(data.get(i) + "\n");
//            
//            //Close file
//            fw.flush();
//            fw.close();
//        }catch(Exception ex){
//            ex.getMessage();
//        }
//    }
}
