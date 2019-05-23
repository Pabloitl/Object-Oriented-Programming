package retoExam;

/*
    Periodo: enero-junio (2019)
    Alumno: Pablo Vargas Bermúdez
    Semestre: 2
    Profesor: Carpio Flores Jose Gerardo
*/

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
