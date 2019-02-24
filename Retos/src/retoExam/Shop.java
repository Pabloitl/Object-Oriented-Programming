package retoExam;

import retoExam.Signup.SignUp;
import retoExam.entities.User;
import retoExam.login.Login;

public class Shop {
    public static void main(String[] args) {
        signUp();
    }
    
    private static void answer(){
        User session = new Login().prompt();
    }
    
    private static void signUp(){
        new SignUp().prompt();
    }
}
