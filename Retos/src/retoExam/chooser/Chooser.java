package retoExam.chooser;

import retoExam.Screen.Screen;
import retoExam.Signup.SignUp;
import retoExam.credit.Credit;
import retoExam.entities.Admin;
import retoExam.entities.Student;
import retoExam.entities.User;
import retoExam.menu.Menu;
import retoExam.stock.Stock;

public class Chooser {
    User user;
    
    public Chooser(User user){
        this.user = user;
    }
    
    public void prompt(){
        if(user instanceof Admin)
            showAdmin();
        else if(user instanceof Student)
            showStudent();
        else
            showParent();
    }
    
    private void showAdmin(){
        String[] options = {"Products", "Users", "Credit"};
        process(options[Screen.showOptions(options)]);
    }
    
    private void showStudent(){
        String[] options = {"Menu"};
        process(options[Screen.showOptions(options)]);
    }
    
    private void showParent(){
        String[] options = {"MenuFilter"};
        process(options[Screen.showOptions(options)]);
    }
    
    private void process(String str){
        System.out.println("asdf");
        switch(str){
            case "Products":
                new Stock().prompt();
                break;
            case "Users":
                new SignUp().prompt();
                break;
            case "Credit":
                new Credit().prompt();
                break;
            case "Menu":
                new Menu((Student) user).prompt();
                break;
            case "MenuFilter":
                
        }
    }
}
