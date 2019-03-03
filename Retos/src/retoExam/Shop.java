package retoExam;

import retoExam.chooser.Chooser;
import retoExam.entities.User;
import retoExam.login.Login;

public class Shop {
    public static void main(String[] args) {
        run();
    }
    
    private static void run(){
        User user= new Login().prompt();
        while(true) new Chooser(user).prompt();
    }
}