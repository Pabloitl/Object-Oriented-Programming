package retoExam.Screen;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.*;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import retoExam.Signup.SignUp;
import retoExam.entities.Parent;
import retoExam.entities.Student;
import retoExam.entities.User;
import static retoExam.files.FileManager.*;
import retoExam.menu.Menu;
import retoExam.stock.Product;
import retoExam.stock.Stock;
import static reto4.Password.*;

public class Screen {
    
    static private JComboBox textType, textParent;
    static private JLabel labelName, labelType, labelPassword, labelBalance,
            labelParent, labelUser, labelProduct, labelPrice, labelQuantity;
    static private JPanel panel;
    static private JTextField textName, textUser, textProduct, textPrice,
            textQuantity;
    static private JPasswordField textPassword;
    static private JTextArea text;
    static private JScrollPane scroll;
    static private ImageIcon img;
    static private JRadioButton[] menu;
    
    public static void showHistory(Student student){
        configureShowHistory(student);
        showMessageDialog(null, scroll, "History", PLAIN_MESSAGE);
    }
    
    public static ArrayList<Product> showMenuFilter(Menu m){
        ArrayList<Product> ls= new ArrayList();
        
        configureShowMenuFilter(m);
        showConfirmDialog(null, panel, "Menu", OK_CANCEL_OPTION, PLAIN_MESSAGE,
                img);
        
        for(JRadioButton radio: menu)
            if(radio.isSelected()) ls.add(new Product(radio.getText()));
        
        return ls;
    }
    
    public static User login(){
        configureLogin();
        do{
            int confirm = showConfirmDialog(null,
                            panel, "Login", OK_CANCEL_OPTION,
                            PLAIN_MESSAGE, img);
            
            if(confirm != 0) System.exit(0);
        }while(!validateFields(textUser, textPassword));

        return new User(textUser.getText(),
                new String(textPassword.getPassword()));
    }
    
    public static Product stock(Stock stock){
        UIManager.put("OptionPane.minimumSize", new Dimension(0, 0));
        
        ArrayList<String> buffer = new ArrayList();
        for(String s: stock.getMenu()){
            buffer.add(s + ":" + new Product(s).getQuantity()+ "\n");
        }
        
        Object obj = showInputDialog(null, null,
                "Stock", PLAIN_MESSAGE, null, buffer.toArray(), null);
        
        if(obj == null) System.exit(0);
        
        String product = obj.toString().split(SEPARATOR)[0];
        return new Product(product);
    }
    
    public static int order(Product product){
        img = new ImageIcon(PATH_IMGS + "order" + TYPE_IMG);
        
        try{
            String foo = showInputDialog(null,
                    "How many " + product.getName() + " to order?", "order",
                    PLAIN_MESSAGE);
            exitOnNull(foo);
            return Integer.parseInt(foo);
        }catch(Exception e){
            return order(product);
        }
    }
    
    public static void showMessage(String msg){
        showMessageDialog(null, msg, "Message", ERROR_MESSAGE);
    }
    
    public static ArrayList<Product> menu(Menu m){
        configureMenu(m);
        
        showConfirmDialog(null, panel, "Menu: Balance $" +
                new Student(m.getStudent()).getBalance(),
                OK_CANCEL_OPTION,
                PLAIN_MESSAGE,
                img);
        
        ArrayList<Product> ls= new ArrayList();
        
        for(JRadioButton radio: menu)
            if(radio.isSelected()) ls.add(new Product(radio.getText()
                    .split(" ")[0]));
        
        return ls;
    }
    
    public static User signUp(){
        return signUp(true);
    }
    
    public static User signUp(boolean  edit){
        return signUp(edit, null);
    }
    
    public static User signUp(User user){
        return signUp(false, user);
    }
    
    public static User signUp(boolean edit, User user){
        configureSignUp(edit, user);
        do{ 
            if(showConfirmDialog(null,
                    panel, "Signup", OK_CANCEL_OPTION,
                    PLAIN_MESSAGE) != 0) System.exit(0);
        }while(!validateFields());
        
        if(textType.getSelectedItem().toString().equals("Student"))
            return new Student(textName.getText(),
                        new String(textPassword.getPassword()),
                        textType.getSelectedItem().toString(),
                        textParent.getSelectedItem().toString());
        return new User(textName.getText(),
                        new String(textPassword.getPassword()),
                        textType.getSelectedItem().toString());
    }
    
    public static User editUser(User user){
        configureEditUser(user);
        do{ 
            if(showConfirmDialog(null,
                    panel, "Edit user", OK_CANCEL_OPTION,
                    PLAIN_MESSAGE) != 0) System.exit(0);
        }while(!validateFields());
        
        if(textType.getSelectedItem().toString().equals("Student"))
            return new Student(textName.getText(),
                        getHash(new String(textPassword.getPassword())),
                        textType.getSelectedItem().toString(),
                        textParent.getSelectedItem().toString());
        return new User(textName.getText(),
                        getHash(new String(textPassword.getPassword())),
                        textType.getSelectedItem().toString());
    }
    
    public static User chooseUser(){
        return chooseUser("Student", "Parent");
    }
    
    public static Student chooseChild(Parent parent){
        Object response = showInputDialog(null, "Choos user", "Choose",
                PLAIN_MESSAGE, null, getChilds(parent).toArray(),
                getChilds(parent).toArray()[0]);
        
        if(response == null) System.exit(0);
        
        return new Student(response.toString());
    }
    
    private static ArrayList<String> getChilds(Parent parent){
        ArrayList<String> users = new ArrayList();
        
        String[] buffer = getLines(SHADOW);
        for(String line: buffer){
            String[] bar = line.split(SEPARATOR);
            if(bar[SignUp.TYPE_FIELD].equals("Student") &&
                    bar[Student.PARENT_FIELD].equals(parent.getName()))
                users.add(bar[SignUp.NAME_FIELD]);
        }
        
        return users;
    }
    
    public static  User chooseUser(String...include){
        Object response = showInputDialog(null, "Choos user", "Choose",
                PLAIN_MESSAGE, null, getUsers(include).toArray(),
                getUsers(include).toArray()[0]);
        
        if(response == null) System.exit(0);
        
        return new User(response.toString());
    }
    
    public static ArrayList<String> getUsers(String[] include){
        ArrayList<String> users = new ArrayList();
        
        String[] buffer = getLines(SHADOW);
        for(String line: buffer){
            String[] bar = line.split(SEPARATOR);
            for(String i: include)
                if(bar[SignUp.TYPE_FIELD].equals(i))
                    users.add(bar[SignUp.NAME_FIELD]);
        }
        System.out.println(users.toString());
        return users;
    }
    
    public static float credit(){
        img = new ImageIcon(PATH_IMGS + "money" + TYPE_IMG);
        
        try{
            String in =
             showInputDialog(null, img, "Input an amount",
                     PLAIN_MESSAGE);
            
            if(in == null || in.isEmpty()) System.exit(0);
            
            return Float.parseFloat(in);
        }catch(Exception e){
            return credit();
        }
    }
    
    public static void showLowProducts(Stock stock, int min){
        StringBuilder buffer = new StringBuilder();
        for(String s: stock.getMenu()){
            if(new Product(s).getQuantity() <= min)
                buffer.append(s).append(" is low with: ")
                        .append(new Product(s).getQuantity()).append("\n");
        }
        
        if(buffer.toString() != null && !buffer.toString().isEmpty())
            showMessage(buffer.toString());
            //showMessageDialog(null, buffer, "Low Products", WARNING_MESSAGE);
        else
            showMessage("Nothing low");
    }
    
    public static Product addProduct(){
        configureAddProduct();
        int confirm;
        
        //Prompt login
        do{
            confirm = showConfirmDialog(null,
                        panel, "Add product", OK_CANCEL_OPTION,
                        PLAIN_MESSAGE, img);
            
            if(confirm != 0) System.exit(0);
        }while(!validateFields(textProduct, textPrice, textQuantity));

        try{
            return new Product(textProduct.getText(),
                    Integer.parseInt(textQuantity.getText()),
                    Float.parseFloat(textPrice.getText()));
        }catch(Exception e){
            return addProduct();
        }
    }
    
    public static int showOptions(String...options){
        UIManager.put("OptionPane.minimumSize", new Dimension(0, 0));
        
        img = new ImageIcon(PATH_IMGS + "options" + TYPE_IMG);
        
        int opt = showOptionDialog(null, null, "Options",
                PLAIN_MESSAGE, PLAIN_MESSAGE,
                img, options, options[0]);
        
        if(opt == -1) System.exit(0);
        return opt;
    }
    
    private static boolean validateFields(JTextField...args){
        for(JTextField test: args)
            if(!validateText(test.getText())) return false;
        
        return true;
    }
    
    private static boolean validateText(String eval){
        return eval != null && !eval.isEmpty();
    }
    
    private static void configureShowHistory(Student student){
        UIManager.put("OptionPane.minimumSize", new Dimension(270, 200));
        text = new JTextArea();
        scroll = new JScrollPane(text);
        
        StringBuilder sb = new StringBuilder();
        for(String s : student.getHistory()){
            sb.append(s).append("\n");
        }
        
        text.setText(sb.toString());
        text.setRows(10);
        text.setEditable(false);
    }
    
    private static void configureShowMenuFilter(Menu m){
        img = new ImageIcon(PATH_IMGS + "menu" + TYPE_IMG);
        
        panel = new JPanel();
        menu = new JRadioButton[m.getMenu().length];
        
        for(int i = 0; i < menu.length; i++)
            menu[i] = new JRadioButton();
        
        //Radio Buttons
        int paddingX = 10, paddingY = 10, x = 0;
        for(int i = 0; i < menu.length; i++){
            menu[i].setText(m.getCompleteMenu(i));
            //Transform i to use in coordinates
            x = i * 100;
            System.out.println(x/400);
            menu[i].setBounds(x%400 + paddingX, x/400*50 + paddingY, 100, 20);
        }
        
        for(String s: m.getRestrictions()){
            for(JRadioButton rb : menu){
                if(rb.getText().equals(s)) rb.setSelected(true);
            }
        }
        
        UIManager.put("OptionPane.minimumSize", new Dimension(470, x/400*50 + 100));
        
        //Panel
        panel.setLayout(null);
        for(JRadioButton b: menu)
            panel.add(b);
        panel.setVisible(true);
    }
    
    private static void configureLogin(){
        img = new ImageIcon(PATH_IMGS + "login" +  TYPE_IMG);
        
        panel = new JPanel();
        labelUser = new JLabel();
        labelPassword = new JLabel();
        textUser = new JTextField();
        textPassword = new JPasswordField();
        
        UIManager.put("OptionPane.minimumSize", new Dimension(300, 150));
        
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
    
    private static void  configureMenu(Menu m){
        img = new ImageIcon(PATH_IMGS + "menu" + TYPE_IMG);
        
        panel = new JPanel();
        menu = new JRadioButton[m.getMenuLength()];
        
        for(int i = 0; i < menu.length; i++)
            menu[i] = new JRadioButton();
        
        //Radio Buttons
        int paddingX = 10, paddingY = 10, x = 0;
        for(int i = 0; i < menu.length; i++){
            menu[i].setText(m.getMenu(i) + " $" +
                    new Product(m.getMenu(i)).getPrice());
            //Transform i to use in coordinates
            x = i * 150;
            menu[i].setBounds(x%600 + paddingX, x/600*50 + paddingY, 150, 20);
        }
        
        UIManager.put("OptionPane.minimumSize",
                new Dimension((x<600)?x + 250:670, x/600*50 + 100));
        
        //Panel
        panel.setLayout(null);
        for(JRadioButton b: menu)
            panel.add(b);
        panel.setVisible(true);
    }
    
    private static void configureSignUp(boolean edit, User user){
        panel = new JPanel();
        labelName = new JLabel();
        labelType = new JLabel();
        labelBalance = new JLabel();
        labelParent = new JLabel();
        labelPassword = new JLabel();
        textName = new JTextField();
        textType = new JComboBox();
        textParent = new JComboBox();
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
        
        //List Type
        String[] users = {"Parent", "Student", "Admin"};
        
        textType.setBounds(100, 50, 100, 20);
        textType.setEnabled(edit);
        textType.addActionListener(new Controller());
        for(String s: users)
            textType.addItem(s);
        
        //Label Password
        labelPassword.setText("Password: ");
        labelPassword.setBounds(0, 100, 100, 20);
        labelPassword.setVisible(edit);
        
        //Text Password
        textPassword.setBounds(100, 100, 100, 20);
        textPassword.setVisible(edit);
        
        //Label Balance
        labelBalance.setBounds(100, 100, 200, 20);
        labelBalance.setVisible(!edit);
        
        //Label Parent
        labelParent.setBounds(0, 150, 100, 20);
        labelParent.setText("Parent: ");
        labelParent.setVisible(!edit);
        
        //List Parent
        String[] include = {"Parent"};
        textParent.setBounds(100, 150, 100, 20);
        for(String s: getUsers(include))
            textParent.addItem(s);
        textParent.setEnabled(edit);
        textParent.setVisible(!edit);
        
        if(!edit){
            textName.setText(user.getName());
            textType.setSelectedItem(user.getType());
            if(user.getType().equals("Student")){
                labelBalance.setText("Balance: " +
                        new Student(user.getName()).getBalance());
                System.out.println(new Student(user.getName()).getParent());
                textParent.setSelectedItem(new Student(user.getName()).getParent());
            }
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
        panel.add(labelParent);
        panel.add(textParent);
        
        panel.setVisible(true);
        UIManager.put("OptionPane.minimumSize", new Dimension(250, 250));
    }
    
    private static void configureEditUser(User user){
        panel = new JPanel();
        labelName = new JLabel();
        labelType = new JLabel();
        labelParent = new JLabel();
        labelPassword = new JLabel();
        textName = new JTextField();
        labelBalance = new JLabel();
        textType = new JComboBox();
        textParent = new JComboBox();
        textPassword = new JPasswordField();
        
        //Label Name
        labelName.setText("Name: ");
        labelName.setBounds(0, 0, 100, 20);
        
        //Text Name
        textName.setBounds(100, 0, 100, 20);
        textName.setEditable(false);
        
        //Label Type
        labelType.setText("Type: ");
        labelType.setBounds(0, 50, 100, 20);
        
        //List Type
        String[] users = {"Parent", "Student", "Admin"};
        
        textType.setBounds(100, 50, 100, 20);
        textType.addActionListener(new Controller());
        for(String s: users)
            textType.addItem(s);
        
        //Label Password
        labelPassword.setText("Password: ");
        labelPassword.setBounds(0, 100, 100, 20);
        
        //Text Password
        textPassword.setBounds(100, 100, 100, 20);
        
        //Label Parent
        labelParent.setBounds(0, 150, 100, 20);
        labelParent.setText("Parent: ");
        
        //List Parent
        String[] include = {"Parent"};
        textParent.setBounds(100, 150, 100, 20);
        for(String s: getUsers(include))
            textParent.addItem(s);
        
        textName.setText(user.getName());
        textType.setSelectedItem(user.getType());
        if(user.getType().equals("Student")){
            labelBalance.setText("Balance: " +
                    new Student(user.getName()).getBalance());
            System.out.println(new Student(user.getName()).getParent());
            textParent.setSelectedItem(new Student(user.getName()).getParent());
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
        panel.add(labelParent);
        panel.add(textParent);
        
        panel.setVisible(true);
        UIManager.put("OptionPane.minimumSize", new Dimension(250, 250));
    }
    
    private static void configureAddProduct(){
        img = new ImageIcon(PATH_IMGS + "product" +  TYPE_IMG);
        
        panel = new JPanel();
        labelProduct = new JLabel();
        labelPrice = new JLabel();
        labelQuantity = new JLabel();
        textProduct = new JTextField();
        textPrice = new JTextField();
        textQuantity = new JTextField();
        
        UIManager.put("OptionPane.minimumSize", new Dimension(300,200));
        
        //Label User
        labelProduct.setText("Product: ");
        labelProduct.setBounds(0, 0, 100, 20);
        
        //Text User
        textProduct.setBounds(100, 0, 100, 20);
        
        //Label Quantiry
        labelQuantity.setText("Quantity: ");
        labelQuantity.setBounds(0, 50, 100, 20);
        
        //Text Quantity
        textQuantity.setBounds(100, 50, 100, 20);
        
        //Label Price
        labelPrice.setText("Price: ");
        labelPrice.setBounds(0, 100, 100, 20);
        
        //Text Price
        textPrice.setBounds(100, 100, 100, 20);
        
        //Panel
        panel.setLayout(null);
        panel.add(labelProduct);
        panel.add(textProduct);
        panel.add(labelPrice);
        panel.add(textPrice);
        panel.add(labelQuantity);
        panel.add(textQuantity);
        panel.setVisible(true);
    }
    
    private static class Controller implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean f = textType.getSelectedItem().toString().equals("Student");
            
            textParent.setVisible(f);
            labelParent.setVisible(f);
        }
        
    }
}
