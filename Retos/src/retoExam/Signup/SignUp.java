package retoExam.Signup;

import reto4.Password;
import retoExam.Screen.Screen;
import retoExam.entities.User;
import retoExam.files.FileManager;

public class SignUp {
    
    public static final int TYPE_FIELD = 0,
            NAME_FIELD = 1,
            PASSWORD_FIELD = 2;
    
    public void prompt(){
        if(showEditable()){
            writeUser(Screen.signUp(true));
            return;
        }
        
        Screen.signUp(Screen.chooseUser());
        
    }
    
    private boolean showEditable(){
        String[] options = {"View", "Register"};
        
        return Screen.showOptions(options) > 0;
    }
    
    private void writeUser(User user){
        FileManager.append(FileManager.SHADOW, User.format(user.getType(),
                user.getName(),
                Password.getHash(new String(user.getPassword()))));
    }
}
