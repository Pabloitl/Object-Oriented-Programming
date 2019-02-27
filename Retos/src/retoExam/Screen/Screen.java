package retoExam.Screen;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import retoExam.Signup.SignUp;
import retoExam.entities.Student;
import retoExam.entities.User;
import retoExam.files.FileManager;
import retoExam.menu.Menu;
import retoExam.stock.Product;

public class Screen {
    public static User login(){
        JPanel panel = new JPanel();
        JLabel labelUser = new JLabel(), labelPassword = new JLabel();
        JTextField textUser = new JTextField();
        JPasswordField textPassword = new JPasswordField();
        
        UIManager.put("OptionPane.minimumSize", new Dimension(300, 200));
        
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
        
        int confirm;
        
        //Prompt login
        do{
            confirm = JOptionPane.showConfirmDialog(null,
                        panel, "Login", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                System.out.println(confirm);
            
            if(confirm != 0) System.exit(0);
        }while(!validateFields(textUser, textPassword));

        //return ¿¿Eval??
        return new User(textUser.getText(),
                new String(textPassword.getPassword()));
    }
    
    public static void stock(HashMap<String, Integer> stock){
        JTextArea products = new JTextArea();
        JScrollPane scroll = new JScrollPane(products);
        
        StringBuffer buffer = new StringBuffer();
        for(String s: stock.keySet().toArray(new  String[stock.size()])){
            buffer.append(s + ": " + stock.get(s) + "\n");
        }
        
        products.setText(buffer.toString());
        products.setRows(10);
        
       
        JOptionPane.showMessageDialog(null, scroll,
                "Stock", JOptionPane.PLAIN_MESSAGE);
    }
    
    public static ArrayList<Product> menu(Menu m){
        JPanel panel = new JPanel();
        JRadioButton[] menu = new JRadioButton[m.getMenuLength()];
        
        for(int i = 0; i < menu.length; i++)
            menu[i] = new JRadioButton();
        
        //Radio Buttons
        int paddingX = 10, paddingY = 10;
        for(int i = 0; i < menu.length; i++){
            menu[i].setText(m.getMenu(i));
            //Transform i to use in coordinates
            int x = i * 100;
            menu[i].setBounds(x%400 + paddingX, x/400 + paddingY, 100, 20);
        }
        
        //Panel
        panel.setLayout(null);
        for(JRadioButton b: menu)
            panel.add(b);
        panel.setVisible(true);
        
        JOptionPane.showConfirmDialog(null, panel, "Menu",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        
        ArrayList<Product> ls= new ArrayList();
        
        for(JRadioButton radio: menu)
            if(radio.isSelected()) ls.add(new Product(radio.getText()));
        
        return ls;
    }
    
    public static User signUp(boolean  edit){
        return signUp(edit, null);
    }
    
    public static User signUp(User user){
        return signUp(false, user);
    }
    
    public static User signUp(boolean edit, User user){
        JPanel panel;
        JLabel labelName, labelType, labelPassword, labelBalance;
        JTextField textName, textType;
        JPasswordField textPassword;
        
        panel = new JPanel();
        labelName = new JLabel();
        labelType = new JLabel();
        labelBalance = new JLabel();
        labelPassword = new JLabel();
        textName = new JTextField();
        textType = new JTextField();
        textPassword = new JPasswordField();
        
        //Label Name
        labelName.setText("Name: ");
        labelName.setBounds(0, 0, 100, 20);
        
        //Text Name
        textName.setBounds(100, 0, 100, 20);
        textName.setEditable(edit);
        
        //Label Type
        labelType.setText("Type: ");
        labelType.setBounds(0, 50, 100, 20);
        
        //Text Type
        textType.setBounds(100, 50, 100, 20);
        textType.setEditable(edit);
        
        //Label Password
        labelPassword.setText("Password: ");
        labelPassword.setBounds(0, 100, 100, 20);
        labelPassword.setVisible(edit);
        
        //Text Password
        textPassword.setBounds(100, 100, 100, 20);
        textPassword.setVisible(edit);
        
        //Label Balance
        labelBalance.setBounds(100, 100, 100, 20);
        labelBalance.setVisible(!edit);
        
        if(!edit){
            textName.setText(user.getName());
            textType.setText(user.getType());
            if(user.getType().equals("Student"))
                labelBalance.setText("Balance: " +
                        new Student(user.getName()).getBalance());
        }
        
        //Panel setup
        panel.setLayout(null);
        
        panel.add(labelName);
        panel.add(textName);
        panel.add(labelType);
        panel.add(textType);
        panel.add(labelPassword);
        panel.add(textPassword);
        panel.add(labelBalance);
        
        panel.setVisible(true);
        UIManager.put("OptionPane.minimumSize", new Dimension(500, 500));
        
        do{ 
            if(JOptionPane.showConfirmDialog(null,
                    panel, "Signup", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE) != 0) System.exit(0);
        }while(!validateFields());
        
        return new User(textName.getText(),
                        new String(textPassword.getPassword()),
                        textType.getText());
    }
    
    public static User chooseUser(){
        String[] include = {"Student", "Parent"};
        
        return chooseUser(include);
    }
    
    public static  User chooseUser(String[] include){
        Object response = JOptionPane.showInputDialog(null, "Choos user", "Choose",
                JOptionPane.PLAIN_MESSAGE, null, getUsers(include).toArray(),
                getUsers(include).toArray()[0]);
        
        if(response == null) System.exit(0);
        
        return new User(response.toString());
    }
    
    public static ArrayList<String> getUsers(String[] include){
        ArrayList<String> users = new ArrayList();
        
        String[] buffer = FileManager.getLines(FileManager.SHADOW);
        for(String line: buffer){
            String[] bar = line.split(FileManager.SEPARATOR);
            for(String i: include)
                if(bar[SignUp.TYPE_FIELD].equals(i))
                    users.add(bar[SignUp.NAME_FIELD]);
        }
        System.out.println(users.toString());
        return users;
    }
    
    public static float credit(){
        try{
            String in =
             JOptionPane.showInputDialog(null, "Input an amount", null,
                     JOptionPane.PLAIN_MESSAGE);
            
            if(in == null || in.isEmpty()) System.exit(0);
            
            return Float.parseFloat(in);
        }catch(Exception e){
            return credit();
        }
    }
    
    public static int showOptions(String[] options){
        int opt = JOptionPane.showOptionDialog(null, null, "Options",
                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                null, options, null);
        
        if(opt == -1) System.exit(0);
        return opt;
    }
    
    private static boolean validateFields(JTextField...args){
        for(JTextField text: args)
            if(!validateText(text.getText())) return false;
        
        return true;
    }
    
    private static boolean validateText(String eval){
        return eval != null && !eval.isEmpty();
    }
}
