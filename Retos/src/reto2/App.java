package reto2;

/*
    Periodo: enero-junio (2019)
    Alumno: Pablo Vargas Bermúdez
    Semestre: 2
    Profesor: Carpio Flores Jose Gerardo
*/

public class App {
    public static void main(String[] args){
        new App().simple();
    }

    public void simple(){
        int vote;

        do{
            Vote v =
            new Vote(new CategoryChooser(CategoryChooser.CATEGORIES_FILE).prompt());

            v.showInfo(vote = v.prompt());
        }while(vote != -1);
    }
}
