package retoExam;

import retoExam.chooser.Chooser;
import retoExam.login.Login;

public class Shop {
    public static void main(String[] args) {
        run();
    }
    
    private static void run(){
        new Chooser(new Login().prompt()).prompt();
    }
}