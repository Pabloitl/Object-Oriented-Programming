package retoExam.credit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import static retoExam.Screen.Screen.*;
import retoExam.entities.Student;
import retoExam.entities.User;
import retoExam.files.FileManager;

public class Credit {
    
    public void prompt(){
        registerPayment(new Student(chooseUser("Student").getName()),
                credit());
    }
    
    private void registerPayment(Student student, float payment){
        FileManager.append(FileManager.OUT, User.format(student.getName(),
                String.valueOf(payment),
                new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())));
    }
}
