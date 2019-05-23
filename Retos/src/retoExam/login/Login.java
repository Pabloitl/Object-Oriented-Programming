package retoExam.login;

/*
    Periodo: enero-junio (2019)
    Alumno: Pablo Vargas Bermúdez
    Semestre: 2
    Profesor: Carpio Flores Jose Gerardo
*/

import static retoExam.Screen.Screen.*;
import retoExam.entities.Admin;
import retoExam.entities.Parent;
import retoExam.entities.Student;
import retoExam.entities.User;
import static retoExam.files.FileManager.*;
import static reto4.Password.*;

public class Login {

    // Variables from shadow text file =========================================

    final static int TYPE_FIELD = 0,
            USER_FIELD = 1,
            PASSWORD_FIELD = 2;

    public User prompt(){
        User test;

        do{
            test = login();
        }while(!userExists(test.getName()) || !isPassword(test.getName(), test.getPassword()));

        return createUser(getType(test.getName()), test.getName());
    }

    private User createUser(String type, String name){
        switch(type){
            case "Student":
                return new Student(name);
            case "Admin":
                return new Admin(name);
            case "Parent":
                return new Parent(name);
            default:
                return null;
        }
    }

    private boolean isPassword(String username, String password){
        password = getHash(password);

        return password.equals(getPassword(username));
    }

    private String getPassword(String username){
        String[] buffer = getLines(SHADOW);

        for(String line: buffer){
            String[] temp= line.split(SEPARATOR);

            if(temp[USER_FIELD].equals(username)) return temp[PASSWORD_FIELD];
        }

        return "";
    }

    private String getType(String user){
        String[] buffer = getLines(SHADOW);
        for(String line: buffer){
            String[] temp = line.split(SEPARATOR);

            if(temp[USER_FIELD].equals(user)) return temp[TYPE_FIELD];
        }

        return "";
    }

    private boolean userExists(String username){
        String[] buffer = getLines(SHADOW);

        for(String line:buffer){
            String[] temp= line.split(SEPARATOR);

            if(temp[USER_FIELD].equals(username)) return true;
        }

        return false;
    }
}
