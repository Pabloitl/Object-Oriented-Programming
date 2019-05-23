package retoExam.history;

/*
    Periodo: enero-junio (2019)
    Alumno: Pablo Vargas Bermúdez
    Semestre: 2
    Profesor: Carpio Flores Jose Gerardo
*/

import static retoExam.Screen.Screen.*;
import retoExam.entities.Parent;

public class History {

    public void prompt(Parent parent){
        showHistory(chooseChild(parent));
    }
}
