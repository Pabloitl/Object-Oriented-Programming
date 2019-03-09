package retoExam.Signup;

import static reto4.Password.*;
import static retoExam.Screen.Screen.*;
import retoExam.entities.Student;
import retoExam.entities.User;
import static retoExam.files.FileManager.*;

public class SignUp {
    
    public static final int TYPE_FIELD = 0,
            NAME_FIELD = 1,
            PASSWORD_FIELD = 2;
    
    public void prompt(){
        switch(showEditable()){
            case 0:
                signUp(chooseUser());
                break;
            case 1:
                writeUser(signUp());
                break;
            case 2:
                User user = chooseUser();
                replace(SHADOW, user, editUser(user));
        }
    }
    
    private int showEditable(){
        return showOptions("View", "Register", "Edit");
    }
    
    private void writeUser(User user){
        String parent = "";
        if(user instanceof Student)
            parent = ((Student) user).getParent();
        System.out.println(parent);
        append(SHADOW, User.format(user.getType(),
                user.getName(),
                getHash(user.getPassword()), parent));
    }
}
