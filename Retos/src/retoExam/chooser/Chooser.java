package retoExam.chooser;

/*
    Periodo: enero-junio (2019)
    Alumno: Pablo Vargas Bermúdez
    Semestre: 2
    Profesor: Carpio Flores Jose Gerardo
*/

import static retoExam.Screen.Screen.*;
import retoExam.Signup.SignUp;
import retoExam.credit.Credit;
import retoExam.entities.Admin;
import retoExam.entities.Parent;
import retoExam.entities.Student;
import retoExam.entities.User;
import retoExam.history.History;
import retoExam.menu.Menu;
import retoExam.menuFilter.MenuFilter;
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
        process(options[showOptions(options)]);
    }

    private void showStudent(){
        String[] options = {"Menu"};
        process(options[showOptions(options)]);
    }

    private void showParent(){
        String[] options = {"MenuFilter", "History"};
        process(options[showOptions(options)]);
    }

    private void process(String str){
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
                new MenuFilter().prompt((Parent) user);
                break;
            case "History":
                new History().prompt((Parent) user);
        }
    }
}
