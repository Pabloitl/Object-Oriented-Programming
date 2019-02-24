package retoExam.Signup;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import reto4.Password;
import retoExam.entities.User;
import retoExam.files.FileManager;

public class SignUp {
    
    JPanel panel;
    JLabel labelName, labelType, labelPassword;
    JTextField textName, textType;
    JPasswordField textPassword;
    
    public SignUp(){
        panel = new JPanel();
        labelName = new JLabel();
        labelType = new JLabel();
        labelPassword = new JLabel();
        textName = new JTextField();
        textType = new JTextField();
        textPassword = new JPasswordField();
    }
    
    private void configureGUI(){
        //Label Name
        labelName.setText("Name: ");
        labelName.setBounds(0, 0, 100, 20);
        
        //Text Name
        textName.setBounds(100, 0, 100, 20);
        
        //Label Type
        labelType.setText("Type: ");
        labelType.setBounds(0, 50, 100, 20);
        
        //Text Type
        textType.setBounds(100, 50, 100, 20);
        
        //Label Password
        labelPassword.setText("Password: ");
        labelPassword.setBounds(0, 100, 100, 20);
        
        //Text Password
        textPassword.setBounds(100, 100, 100, 20);
        
        //Panel setup
        panel.setLayout(null);
        
        panel.add(labelName);
        panel.add(textName);
        panel.add(labelType);
        panel.add(textType);
        panel.add(labelPassword);
        panel.add(textPassword);
        
        panel.setVisible(true);
    }
    
    public void prompt(){
        UIManager.put("OptionPane.minimumSize", new Dimension(500, 500));
        configureGUI();
        
        do{ 
            if(JOptionPane.showConfirmDialog(null,
                    panel, "Signup", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE) != 0) System.exit(0);
        }while(!validateFields());
        
        FileManager.append(FileManager.SHADOW, User.format(textType.getText(),
                textName.getText(),
                Password.getHash(new String(textPassword.getPassword()))));
    }
    
    private boolean validateFields(){
        return  validateText(textName.getText()) &&
                validateText(textType.getText()) &&
                validateText(new String(textPassword.getPassword()));
    }
    
    private boolean validateText(String eval){
        return eval != null && !eval.isEmpty();
    }
}
