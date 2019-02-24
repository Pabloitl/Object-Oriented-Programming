package retoExam.login;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import retoExam.entities.Admin;
import retoExam.entities.Parent;
import retoExam.entities.Student;
import retoExam.entities.User;
import retoExam.files.FileManager;

public class Login {
    
    // Variables from shadow text file =========================================
    final static String SEP = ":";
    
    final static int TYPE_FIELD = 0,
            USER_FIELD = 1,
            PASSWORD_FIELD = 2;
    
    // Objects needed for GUI ==================================================
    JPanel panel;
    JLabel labelUser, labelPassword;
    JTextField textUser;
    JPasswordField textPassword;
    
    // Methods =================================================================
    public Login(){
        //Initialize objects
        panel = new JPanel();
        labelUser = new JLabel();
        labelPassword = new JLabel();
        textUser = new JTextField();
        textPassword = new JPasswordField();
    }
    
    public User prompt(){
        UIManager.put("OptionPane.minimumSize", new Dimension(300, 200));
        configurePrompt();
        
        int confirm;
        
        //Prompt login
        do{
            confirm = JOptionPane.showConfirmDialog(null,
                        panel, "Login", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                System.out.println(confirm);
            
            if(confirm != 0) System.exit(0);
        }while(!validateFields() || !isPassword(textUser.getText(),
                        new String(textPassword.getPassword())));

        //return ¿¿Eval??
        return createUser(getType(textUser.getText()));
    }
    
    private User createUser(String type){
        switch(type){
            case "Student":
                return new Student();
            case "Admin":
                return new Admin();
            case "Parent":
                return new Parent();
            default:
                return null;
        }
    }
    
    private void configurePrompt(){
        //Label User
        labelUser.setText("User: ");
        labelUser.setBounds(0, 0, 100, 20);
        
        //Text User
        textUser.setBounds(100, 0, 100, 20);
        
        //Label Password
        labelPassword.setText("Password: ");
        labelPassword.setBounds(0, 50, 100, 20);
        
        //Text Password
        textPassword.setBounds(100, 50, 100, 20);
        
        //Panel
        panel.setLayout(null);
        panel.add(labelUser);
        panel.add(textUser);
        panel.add(labelPassword);
        panel.add(textPassword);
        panel.setVisible(true);
    }
    
    private boolean validateFields(){
        return  validateText(textUser.getText()) &&
                validateText(new String(textPassword.getPassword()));
    }
    
    private boolean validateText(String eval){
        return eval != null && !eval.isEmpty();
    }
    
    private boolean isPassword(String username, String password){
        
        password = reto4.Password.getHash(password);
        
        if(password.equals(getPassword(username))) return true;
        return false;
    }
    
    private String getPassword(String username){
        String[] buffer = FileManager.getLines(FileManager.SHADOW);
        
        for(String line: buffer){
            String[] temp= line.split(SEP);

            if(temp[USER_FIELD].equals(username)) return temp[PASSWORD_FIELD];
        }
        
        return "";
    }
    
    private String getType(String user){
        String[] buffer = FileManager.getLines(FileManager.SHADOW);
        
        for(String line: buffer){
            String[] temp = line.split(SEP);
            
            if(temp[USER_FIELD].equals(user)) return temp[TYPE_FIELD];
        }
        
        return "";
    }
    
    private boolean userExists(String username){
        String[] buffer = FileManager.getLines(FileManager.SHADOW);

        for(String line:buffer){
            String[] temp= line.split(SEP);

            if(temp[USER_FIELD].equals(username)) return true;
        }
        
        return false;
    }
}
