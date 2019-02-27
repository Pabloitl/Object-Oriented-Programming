package retoExam.credit;

import retoExam.Screen.Screen;
import retoExam.entities.Student;
import retoExam.entities.User;
import retoExam.files.FileManager;

public class Credit {
    
    public void prompt(){
        String[] include = {"Student"};
        
        registerPayment(new Student(Screen.chooseUser(include).getName()),
                Screen.credit());
    }
    
    private void registerPayment(Student student, float payment){
        FileManager.append(FileManager.OUT, User.format(student.getName(),
                String.valueOf(payment)));
    }
}
