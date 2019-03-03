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
        if(showEditable()){
            writeUser(signUp());
            return;
        }
        
        signUp(chooseUser());
        
    }
    
    private boolean showEditable(){
        String[] options = {"View", "Register"};
        
        return showOptions(options) > 0;
    }
    
    private void writeUser(User user){
        String parent = "";
        if(user instanceof Student)
            parent = ((Student) user).getParent();
        System.out.println(parent);
        append(SHADOW, User.format(user.getType(),
                user.getName(),
                getHash(new String(user.getPassword())), parent));
    }
}
