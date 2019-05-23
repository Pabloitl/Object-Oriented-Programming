package retoExam.menuFilter;

/*
    Periodo: enero-junio (2019)
    Alumno: Pablo Vargas Bermúdez
    Semestre: 2
    Profesor: Carpio Flores Jose Gerardo
*/

import java.util.ArrayList;
import static retoExam.Screen.Screen.*;
import retoExam.entities.Parent;
import retoExam.entities.Student;
import retoExam.menu.Menu;
import retoExam.stock.Product;

public class MenuFilter {
    public void prompt(Parent parent){
        Student student;
        ArrayList<Product> showMenuFilter =
                showMenuFilter(new  Menu(student = chooseChild(parent)));

        student.updateRestrictions(showMenuFilter);
    }
}
